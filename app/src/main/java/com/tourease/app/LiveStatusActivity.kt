package com.tourease.app

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.tourease.app.models.LiveStation
import com.tourease.app.models.MockTrainData

class LiveStatusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_status)
        supportActionBar?.hide()

        val ivBack: ImageView = findViewById(R.id.ivBack)
        val etTrainNumber: EditText = findViewById(R.id.etTrainNumber)
        val btnTrackTrain: Button = findViewById(R.id.btnTrackTrain)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val cardStatus: CardView = findViewById(R.id.cardStatus)

        // Pre-fill if train number passed from another screen
        val passedTrainNo = intent.getStringExtra("TRAIN_NO")
        if (passedTrainNo != null) {
            etTrainNumber.setText(passedTrainNo)
        }

        ivBack.setOnClickListener { finish() }

        btnTrackTrain.setOnClickListener {
            val trainNo = etTrainNumber.text.toString().trim()

            if (trainNo.isEmpty() || trainNo.length < 4) {
                Toast.makeText(this, "Enter valid train number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            progressBar.visibility = View.VISIBLE
            cardStatus.visibility = View.GONE
            btnTrackTrain.isEnabled = false

            // Simulate API delay
            android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                progressBar.visibility = View.GONE
                btnTrackTrain.isEnabled = true

                val response = MockTrainData.getMockLiveStatus(trainNo)

                if (response.status && response.data != null) {
                    val data = response.data
                    cardStatus.visibility = View.VISIBLE

                    findViewById<TextView>(R.id.tvLiveTrainName).text = data.trainName
                    findViewById<TextView>(R.id.tvLiveTrainNumber).text = "#${data.trainNumber}"
                    findViewById<TextView>(R.id.tvCurrentStation).text = data.currentStationName
                    findViewById<TextView>(R.id.tvUpdatedTime).text = "Updated: ${data.updatedTime}"

                    // Delay status
                    val delayTv = findViewById<TextView>(R.id.tvDelayStatus)
                    val delayMin = data.delay.toIntOrNull() ?: 0
                    if (delayMin > 0) {
                        delayTv.text = "Running late by $delayMin min"
                        delayTv.setTextColor(android.graphics.Color.parseColor("#FF9800"))
                    } else {
                        delayTv.text = "Running on time"
                        delayTv.setTextColor(android.graphics.Color.parseColor("#00E676"))
                    }

                    // Previous stations
                    val llPrevious: LinearLayout = findViewById(R.id.llPreviousStations)
                    llPrevious.removeAllViews()
                    data.previousStations?.forEach { station ->
                        llPrevious.addView(createStationRow(station, true))
                    }

                    // Upcoming stations
                    val llUpcoming: LinearLayout = findViewById(R.id.llUpcomingStations)
                    llUpcoming.removeAllViews()
                    data.upcomingStations?.forEach { station ->
                        llUpcoming.addView(createStationRow(station, false))
                    }
                } else {
                    Toast.makeText(this, "Train status not available", Toast.LENGTH_SHORT).show()
                }
            }, 1500)
        }

        // Auto-track if train number was passed
        if (passedTrainNo != null) {
            btnTrackTrain.performClick()
        }
    }

    private fun createStationRow(station: LiveStation, isPast: Boolean): LinearLayout {
        val row = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = android.view.Gravity.CENTER_VERTICAL
            setPadding(0, 20, 0, 20)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        // Timeline dot
        val dot = View(this).apply {
            val size = if (isPast) 12 else 10
            layoutParams = LinearLayout.LayoutParams(size.dp(), size.dp()).apply {
                marginEnd = 16.dp()
            }
            val color = if (isPast) "#00E676" else "#7C7C99"
            setBackgroundColor(android.graphics.Color.parseColor(color))
        }

        // Station name
        val nameTv = TextView(this).apply {
            text = station.stationName
            textSize = 13f
            val color = if (isPast) "#CCCCDD" else "#7C7C99"
            setTextColor(android.graphics.Color.parseColor(color))
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
        }

        // Arrival time
        val timeTv = TextView(this).apply {
            text = station.arrives
            textSize = 13f
            val color = if (isPast) "#FFFFFF" else "#7C7C99"
            setTextColor(android.graphics.Color.parseColor(color))
        }

        // Delay
        val delayTv = TextView(this).apply {
            val delayMin = station.delay.toIntOrNull() ?: 0
            if (isPast && delayMin > 0) {
                text = " +${delayMin}m"
                textSize = 11f
                setTextColor(android.graphics.Color.parseColor("#FF5252"))
            } else {
                text = ""
            }
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { marginStart = 8.dp() }
        }

        row.addView(dot)
        row.addView(nameTv)
        row.addView(timeTv)
        row.addView(delayTv)
        return row
    }

    private fun Int.dp(): Int = (this * resources.displayMetrics.density).toInt()
}
