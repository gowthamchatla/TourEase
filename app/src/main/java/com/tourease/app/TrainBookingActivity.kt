package com.tourease.app

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.tourease.app.data.StationCodes
import com.tourease.app.models.TrainData
import com.tourease.app.repository.RailwayRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class TrainBookingActivity : AppCompatActivity() {

    private var selectedClass = "SL"
    private lateinit var etFromLocation: AutoCompleteTextView
    private lateinit var etToLocation: AutoCompleteTextView
    private lateinit var etJourneyDate: EditText
    private lateinit var btnSearchTrains: Button
    private lateinit var progressBar: ProgressBar

    private var apiDateFormat = ""

    private val repository = RailwayRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_train_booking)

        supportActionBar?.hide()

        val ivBack: ImageView = findViewById(R.id.ivBack)
        etFromLocation = findViewById(R.id.etFromLocation)
        etToLocation = findViewById(R.id.etToLocation)
        etJourneyDate = findViewById(R.id.etJourneyDate)
        val ivSwapLocations: TextView = findViewById(R.id.ivSwapLocations)
        btnSearchTrains = findViewById(R.id.btnSearchTrains)
        progressBar = findViewById(R.id.progressBar)

        val btnSleeper: Button = findViewById(R.id.btnSleeper)
        val btn3AC: Button = findViewById(R.id.btn3AC)
        val btn2AC: Button = findViewById(R.id.btn2AC)
        val btn1AC: Button = findViewById(R.id.btn1AC)
        val btnCC: Button = findViewById(R.id.btnCC)

        ivBack.setOnClickListener { finish() }

        setupStationAutocomplete()

        etJourneyDate.setOnClickListener { showDatePicker() }

        ivSwapLocations.setOnClickListener {
            val temp = etFromLocation.text.toString()
            etFromLocation.setText(etToLocation.text.toString())
            etToLocation.setText(temp)
        }

        btnSleeper.setOnClickListener { selectClass("SL", btnSleeper) }
        btn3AC.setOnClickListener { selectClass("3AC", btn3AC) }
        btn2AC.setOnClickListener { selectClass("2AC", btn2AC) }
        btn1AC.setOnClickListener { selectClass("1AC", btn1AC) }
        btnCC.setOnClickListener { selectClass("CC", btnCC) }

        btnSearchTrains.setOnClickListener { searchTrains() }

        setupPopularRoutes()

        // Quick action cards
        setupQuickActions()
    }

    private fun setupStationAutocomplete() {
        val stationNames = StationCodes.getStationNames()

        val fromAdapter = ArrayAdapter(this, R.layout.item_station_dropdown, stationNames)
        etFromLocation.setAdapter(fromAdapter)

        val toAdapter = ArrayAdapter(this, R.layout.item_station_dropdown, stationNames)
        etToLocation.setAdapter(toAdapter)
    }

    private fun setupPopularRoutes() {
        findViewById<TextView>(R.id.chipRoute1).setOnClickListener {
            etFromLocation.setText("New Delhi")
            etToLocation.setText("Mumbai Central")
        }
        findViewById<TextView>(R.id.chipRoute2).setOnClickListener {
            etFromLocation.setText("Chennai Central")
            etToLocation.setText("Bangalore")
        }
        findViewById<TextView>(R.id.chipRoute3).setOnClickListener {
            etFromLocation.setText("Mumbai Central")
            etToLocation.setText("Goa")
        }
        findViewById<TextView>(R.id.chipRoute4).setOnClickListener {
            etFromLocation.setText("New Delhi")
            etToLocation.setText("Jaipur")
        }
    }

    private fun setupQuickActions() {
        val cardPnr: CardView = findViewById(R.id.cardPnrStatus)
        val cardLiveTrack: CardView = findViewById(R.id.cardLiveTrack)

        cardPnr.setOnClickListener {
            startActivity(Intent(this, PnrStatusActivity::class.java))
        }

        cardLiveTrack.setOnClickListener {
            startActivity(Intent(this, LiveStatusActivity::class.java))
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val displayDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                etJourneyDate.setText(displayDate)

                // API format: YYYY-MM-DD
                apiDateFormat = String.format(
                    "%04d-%02d-%02d",
                    selectedYear,
                    selectedMonth + 1,
                    selectedDay
                )
            },
            year, month, day
        )

        datePickerDialog.datePicker.minDate = calendar.timeInMillis
        datePickerDialog.show()
    }

    private fun selectClass(className: String, selectedButton: Button) {
        selectedClass = className

        val allButtons = listOf(
            findViewById<Button>(R.id.btnSleeper),
            findViewById<Button>(R.id.btn3AC),
            findViewById<Button>(R.id.btn2AC),
            findViewById<Button>(R.id.btn1AC),
            findViewById<Button>(R.id.btnCC)
        )

        for (btn in allButtons) {
            btn.backgroundTintList = android.content.res.ColorStateList.valueOf(
                android.graphics.Color.parseColor("#2A2A40")
            )
            btn.setTextColor(android.graphics.Color.parseColor("#9999AA"))
        }

        selectedButton.backgroundTintList = android.content.res.ColorStateList.valueOf(
            android.graphics.Color.parseColor("#6C63FF")
        )
        selectedButton.setTextColor(android.graphics.Color.WHITE)
    }

    private fun searchTrains() {
        val from = etFromLocation.text.toString().trim()
        val to = etToLocation.text.toString().trim()
        val date = etJourneyDate.text.toString().trim()

        if (from.isEmpty()) {
            Toast.makeText(this, "Enter departure station", Toast.LENGTH_SHORT).show()
            return
        }
        if (to.isEmpty()) {
            Toast.makeText(this, "Enter destination station", Toast.LENGTH_SHORT).show()
            return
        }
        if (date.isEmpty()) {
            Toast.makeText(this, "Select journey date", Toast.LENGTH_SHORT).show()
            return
        }

        val fromCode = StationCodes.getCode(from)
        val toCode = StationCodes.getCode(to)

        if (fromCode != null && toCode != null && apiDateFormat.isNotEmpty()) {
            searchWithAPI(from, to, date, fromCode, toCode)
        } else {
            if (fromCode == null || toCode == null) {
                Toast.makeText(this, "Please select stations from suggestions", Toast.LENGTH_SHORT).show()
            } else {
                searchWithHardcodedData(from, to, date)
            }
        }
    }

    private fun searchWithAPI(from: String, to: String, date: String, fromCode: String, toCode: String) {
        progressBar.visibility = View.VISIBLE
        btnSearchTrains.isEnabled = false
        btnSearchTrains.text = "Searching..."

        CoroutineScope(Dispatchers.IO).launch {
            val result = repository.searchTrains(fromCode, toCode, apiDateFormat)

            withContext(Dispatchers.Main) {
                progressBar.visibility = View.GONE
                btnSearchTrains.isEnabled = true
                btnSearchTrains.text = "Search Trains"

                result.onSuccess { trains ->
                    if (trains.isNotEmpty()) {
                        val trainList = trains.map { info ->
                            val durationFormatted = try {
                                val parts = info.duration.split(":")
                                "${parts[0]}h ${parts[1]}m"
                            } catch (e: Exception) {
                                info.duration
                            }

                            Train(
                                trainNumber = info.trainNumber,
                                trainName = info.trainName,
                                fromStation = info.fromStationName,
                                toStation = info.toStationName,
                                departureTime = info.departureTime,
                                arrivalTime = info.arrivalTime,
                                duration = durationFormatted,
                                runsDays = info.runDays.joinToString(", "),
                                sleeperPrice = 0,
                                ac3Price = 0,
                                ac2Price = 0,
                                ac1Price = 0,
                                sleeperAvailable = 0,
                                ac3Available = 0,
                                ac2Available = 0,
                                ac1Available = 0
                            )
                        }

                        val intent = Intent(this@TrainBookingActivity, TrainResultsActivity::class.java)
                        intent.putExtra("FROM", from)
                        intent.putExtra("TO", to)
                        intent.putExtra("DATE", date)
                        intent.putExtra("CLASS", selectedClass)
                        intent.putExtra("trains", ArrayList(trainList))
                        intent.putExtra("IS_LIVE", true)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@TrainBookingActivity, "No live trains found. Showing cached results.", Toast.LENGTH_SHORT).show()
                        searchWithHardcodedData(from, to, date)
                    }
                }

                result.onFailure {
                    Toast.makeText(this@TrainBookingActivity, "Couldn't reach server. Showing cached results.", Toast.LENGTH_SHORT).show()
                    searchWithHardcodedData(from, to, date)
                }
            }
        }
    }

    private fun searchWithHardcodedData(from: String, to: String, date: String) {
        val intent = Intent(this, TrainResultsActivity::class.java)
        intent.putExtra("FROM", from)
        intent.putExtra("TO", to)
        intent.putExtra("DATE", date)
        intent.putExtra("CLASS", selectedClass)
        intent.putExtra("IS_LIVE", false)
        startActivity(intent)
    }
}
