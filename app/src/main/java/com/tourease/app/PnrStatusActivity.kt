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
import com.tourease.app.models.MockTrainData

class PnrStatusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pnr_status)
        supportActionBar?.hide()

        val ivBack: ImageView = findViewById(R.id.ivBack)
        val etPnrNumber: EditText = findViewById(R.id.etPnrNumber)
        val btnCheckPnr: Button = findViewById(R.id.btnCheckPnr)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val cardResult: CardView = findViewById(R.id.cardResult)

        ivBack.setOnClickListener { finish() }

        btnCheckPnr.setOnClickListener {
            val pnr = etPnrNumber.text.toString().trim()

            if (pnr.length != 10) {
                Toast.makeText(this, "Enter valid 10-digit PNR number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Show loading
            progressBar.visibility = View.VISIBLE
            cardResult.visibility = View.GONE
            btnCheckPnr.isEnabled = false

            // Simulate API delay
            android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                progressBar.visibility = View.GONE
                btnCheckPnr.isEnabled = true

                // Get mock data
                val response = MockTrainData.getMockPnrStatus(pnr)

                if (response.status && response.data != null) {
                    val data = response.data
                    cardResult.visibility = View.VISIBLE

                    findViewById<TextView>(R.id.tvPnrTrainName).text = data.trainName
                    findViewById<TextView>(R.id.tvPnrTrainNumber).text = "#${data.trainNumber}"
                    findViewById<TextView>(R.id.tvPnrFrom).text = data.from
                    findViewById<TextView>(R.id.tvPnrTo).text = data.to
                    findViewById<TextView>(R.id.tvPnrDate).text = data.dateOfJourney
                    findViewById<TextView>(R.id.tvPnrClass).text = data.journeyClass
                    findViewById<TextView>(R.id.tvPnrChart).text = data.chartStatus

                    // Add passenger rows
                    val llPassengers: LinearLayout = findViewById(R.id.llPassengers)
                    llPassengers.removeAllViews()

                    for ((index, passenger) in data.passengerList.withIndex()) {
                        val passengerCard = LinearLayout(this).apply {
                            orientation = LinearLayout.HORIZONTAL
                            setPadding(40, 30, 40, 30)
                            layoutParams = LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                            ).apply { bottomMargin = 12 }
                        }

                        // Set background based on status
                        val bgColor = if (passenger.currentStatus.startsWith("CNF")) "#0D2818" else "#2E1A08"

                        passengerCard.setBackgroundColor(android.graphics.Color.parseColor(bgColor))

                        val nameTv = TextView(this).apply {
                            text = "Passenger ${index + 1}"
                            textSize = 14f
                            setTextColor(android.graphics.Color.parseColor("#CCCCDD"))
                            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
                        }

                        val statusTv = TextView(this).apply {
                            text = passenger.currentStatus
                            textSize = 14f
                            setTypeface(null, android.graphics.Typeface.BOLD)
                            val statusColor = when {
                                passenger.currentStatus.startsWith("CNF") -> "#00E676"
                                passenger.currentStatus.startsWith("RAC") -> "#FF9800"
                                else -> "#FF5252"
                            }
                            setTextColor(android.graphics.Color.parseColor(statusColor))
                        }

                        passengerCard.addView(nameTv)
                        passengerCard.addView(statusTv)
                        llPassengers.addView(passengerCard)
                    }
                } else {
                    Toast.makeText(this, "PNR not found", Toast.LENGTH_SHORT).show()
                }
            }, 1500)
        }
    }
}
