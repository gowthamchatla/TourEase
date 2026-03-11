package com.tourease.app

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MyTripsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_trips)

        // Back button
        val ivBack: ImageView = findViewById(R.id.ivBack)
        ivBack.setOnClickListener {
            finish()
        }

        // Add trip button
        val ivAddTrip: ImageView = findViewById(R.id.ivAddTrip)
        ivAddTrip.setOnClickListener {
            Toast.makeText(this, "Add trip feature coming soon!", Toast.LENGTH_SHORT).show()
        }

        // Tab buttons
        val btnUpcoming: Button = findViewById(R.id.btnUpcoming)
        val btnOngoing: Button = findViewById(R.id.btnOngoing)
        val btnPast: Button = findViewById(R.id.btnPast)

        btnUpcoming.setOnClickListener {
            switchTab(btnUpcoming, btnOngoing, btnPast)
            Toast.makeText(this, "Showing upcoming trips", Toast.LENGTH_SHORT).show()
        }

        btnOngoing.setOnClickListener {
            switchTab(btnOngoing, btnUpcoming, btnPast)
            Toast.makeText(this, "Showing ongoing trips", Toast.LENGTH_SHORT).show()
        }

        btnPast.setOnClickListener {
            switchTab(btnPast, btnUpcoming, btnOngoing)
            Toast.makeText(this, "Showing past trips", Toast.LENGTH_SHORT).show()
        }

        // Plan trip button (empty state)
        val btnPlanTrip: Button = findViewById(R.id.btnPlanTrip)
        btnPlanTrip.setOnClickListener {
            Toast.makeText(this, "Plan a Trip feature coming soon!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun switchTab(selected: Button, other1: Button, other2: Button) {
        // Set selected tab orange
        selected.backgroundTintList = android.content.res.ColorStateList.valueOf(
            android.graphics.Color.parseColor("#FF6B35")
        )
        selected.setTextColor(android.graphics.Color.WHITE)

        // Set other tabs gray
        val grayColor = android.graphics.Color.parseColor("#E0E0E0")
        val darkGrayColor = android.graphics.Color.parseColor("#666666")

        other1.backgroundTintList = android.content.res.ColorStateList.valueOf(grayColor)
        other1.setTextColor(darkGrayColor)

        other2.backgroundTintList = android.content.res.ColorStateList.valueOf(grayColor)
        other2.setTextColor(darkGrayColor)
    }
}