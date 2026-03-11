package com.tourease.app

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class BusBookingActivity : AppCompatActivity() {

    private var selectedSeatType = "Sleeper"
    private lateinit var etFromCity: AutoCompleteTextView
    private lateinit var etToCity: AutoCompleteTextView
    private lateinit var etJourneyDate: EditText
    private lateinit var btnSearchBuses: Button

    private val popularCities = listOf(
        "Delhi", "Mumbai", "Bangalore", "Chennai", "Hyderabad",
        "Pune", "Kolkata", "Jaipur", "Ahmedabad", "Goa",
        "Bhubaneswar", "Lucknow", "Chandigarh", "Indore", "Nagpur",
        "Kochi", "Coimbatore", "Visakhapatnam", "Surat", "Vadodara"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_booking)
        supportActionBar?.hide()

        val ivBack: ImageView = findViewById(R.id.ivBack)
        etFromCity = findViewById(R.id.etFromCity)
        etToCity = findViewById(R.id.etToCity)
        etJourneyDate = findViewById(R.id.etJourneyDate)
        val ivSwapLocations: TextView = findViewById(R.id.ivSwapLocations)
        btnSearchBuses = findViewById(R.id.btnSearchBuses)

        val btnSleeper: Button = findViewById(R.id.btnSleeper)
        val btnSemiSleeper: Button = findViewById(R.id.btnSemiSleeper)
        val btnSitting: Button = findViewById(R.id.btnSitting)

        ivBack.setOnClickListener { finish() }

        setupCityAutocomplete()

        etJourneyDate.setOnClickListener { showDatePicker() }

        ivSwapLocations.setOnClickListener {
            val temp = etFromCity.text.toString()
            etFromCity.setText(etToCity.text.toString())
            etToCity.setText(temp)
        }

        // Default: Sleeper selected
        selectSeatType("Sleeper", btnSleeper)

        btnSleeper.setOnClickListener { selectSeatType("Sleeper", btnSleeper) }
        btnSemiSleeper.setOnClickListener { selectSeatType("Semi-Sleeper", btnSemiSleeper) }
        btnSitting.setOnClickListener { selectSeatType("Sitting", btnSitting) }

        btnSearchBuses.setOnClickListener { searchBuses() }

        setupPopularRoutes()
    }

    private fun setupCityAutocomplete() {
        val fromAdapter = ArrayAdapter(this, R.layout.item_station_dropdown, popularCities)
        etFromCity.setAdapter(fromAdapter)

        val toAdapter = ArrayAdapter(this, R.layout.item_station_dropdown, popularCities)
        etToCity.setAdapter(toAdapter)
    }

    private fun setupPopularRoutes() {
        findViewById<TextView>(R.id.chipRoute1).setOnClickListener {
            etFromCity.setText("Delhi")
            etToCity.setText("Mumbai")
        }
        findViewById<TextView>(R.id.chipRoute2).setOnClickListener {
            etFromCity.setText("Chennai")
            etToCity.setText("Bangalore")
        }
        findViewById<TextView>(R.id.chipRoute3).setOnClickListener {
            etFromCity.setText("Mumbai")
            etToCity.setText("Goa")
        }
        findViewById<TextView>(R.id.chipRoute4).setOnClickListener {
            etFromCity.setText("Hyderabad")
            etToCity.setText("Pune")
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            this,
            { _, year, month, day ->
                val displayDate = "$day/${month + 1}/$year"
                etJourneyDate.setText(displayDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            datePicker.minDate = calendar.timeInMillis
            show()
        }
    }

    private fun selectSeatType(seatType: String, selectedButton: Button) {
        selectedSeatType = seatType

        val allButtons = listOf(
            findViewById<Button>(R.id.btnSleeper),
            findViewById<Button>(R.id.btnSemiSleeper),
            findViewById<Button>(R.id.btnSitting)
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

    private fun searchBuses() {
        val from = etFromCity.text.toString().trim()
        val to = etToCity.text.toString().trim()
        val date = etJourneyDate.text.toString().trim()

        if (from.isEmpty()) {
            Toast.makeText(this, "Enter departure city", Toast.LENGTH_SHORT).show()
            return
        }
        if (to.isEmpty()) {
            Toast.makeText(this, "Enter destination city", Toast.LENGTH_SHORT).show()
            return
        }
        if (date.isEmpty()) {
            Toast.makeText(this, "Select journey date", Toast.LENGTH_SHORT).show()
            return
        }
        if (from.equals(to, ignoreCase = true)) {
            Toast.makeText(this, "Departure and destination can't be same", Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(this, BusResultsActivity::class.java)
        intent.putExtra("FROM", from)
        intent.putExtra("TO", to)
        intent.putExtra("DATE", date)
        intent.putExtra("SEAT_TYPE", selectedSeatType)
        startActivity(intent)
    }
}
