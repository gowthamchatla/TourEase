package com.tourease.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DestinationDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_detail)

        val destinationId = intent.getIntExtra("DESTINATION_ID", -1)
        val destination = DestinationsData.getAllDestinations().find { it.id == destinationId }

        if (destination == null) {
            finish()
            return
        }

        val ivBack: ImageView = findViewById(R.id.ivBack)
        val tvDestinationName: TextView = findViewById(R.id.tvDestinationName)
        val tvEmoji: TextView = findViewById(R.id.tvEmoji)
        val tvDescription: TextView = findViewById(R.id.tvDescription)
        val tvRating: TextView = findViewById(R.id.tvRating)
        val tvPrice: TextView = findViewById(R.id.tvPrice)
        val tvDays: TextView = findViewById(R.id.tvDays)
        val tvBestTime: TextView = findViewById(R.id.tvBestTime)
        val tvHighlights: TextView = findViewById(R.id.tvHighlights)
        val btnPlanTripHere: Button = findViewById(R.id.btnPlanTripHere)

        tvDestinationName.text = destination.name
        tvEmoji.text = destination.emoji
        tvDescription.text = destination.description
        tvRating.text = "⭐ ${destination.rating}"
        tvPrice.text = "💰 ${destination.priceRange}"
        tvDays.text = "📅 ${destination.daysNeeded}"
        tvBestTime.text = destination.bestTime
        tvHighlights.text = destination.highlights.joinToString("\n") { "• $it" }

        ivBack.setOnClickListener {
            finish()
        }

        btnPlanTripHere.setOnClickListener {
            val intent = Intent(this, PlanTripActivity::class.java)
            intent.putExtra("DESTINATION_NAME", destination.name)
            startActivity(intent)
        }
    }
}