package com.tourease.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SavedPlacesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_places)

        val btnExplore: Button = findViewById(R.id.btnExplore)
        btnExplore.setOnClickListener {
            val intent = Intent(this, ExplorePlacesActivity::class.java)
            startActivity(intent)
        }
    }
}