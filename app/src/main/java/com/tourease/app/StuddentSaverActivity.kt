package com.tourease.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class StudentSaverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_saver)

        findViewById<ImageView>(R.id.ivBack).setOnClickListener { finish() }

        findViewById<LinearLayout>(R.id.dealMakeMyTrip).setOnClickListener {
            openUrl(
                "https://www.makemytrip.com/offers/student-offers.html",
                "com.makemytrip"
            )
        }

        findViewById<LinearLayout>(R.id.dealRedBus).setOnClickListener {
            openUrl(
                "https://www.redbus.in/offers",
                "in.redbus.android"
            )
        }

        findViewById<LinearLayout>(R.id.dealIRCTC).setOnClickListener {
            openUrl(
                "https://www.irctc.co.in",
                "cris.org.in.prs.ima"
            )
        }

        findViewById<LinearLayout>(R.id.dealOla).setOnClickListener {
            openUrl(
                "https://www.olacabs.com",
                "com.olacabs.customer"
            )
        }

        findViewById<LinearLayout>(R.id.dealHostel).setOnClickListener {
            openUrl(
                "https://www.zostel.com",
                null
            )
        }

        findViewById<CardView>(R.id.template500).setOnClickListener {
            openPlanTrip("500")
        }

        findViewById<CardView>(R.id.template1k).setOnClickListener {
            openPlanTrip("2000-5000")
        }

        findViewById<CardView>(R.id.template2k).setOnClickListener {
            openPlanTrip("5000-15000")
        }

        findViewById<CardView>(R.id.template5k).setOnClickListener {
            openPlanTrip("15000-50000")
        }

        findViewById<Button>(R.id.btnShareApp).setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(
                    Intent.EXTRA_TEXT,
                    "Check out TourEase for student deals and budget trip ideas.\n\nDownload: https://tourease.app"
                )
            }
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
    }

    private fun openUrl(webUrl: String, packageName: String?) {
        if (packageName != null) {
            val appIntent = packageManager.getLaunchIntentForPackage(packageName)
            if (appIntent != null) {
                startActivity(appIntent)
                return
            }
        }

        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(webUrl)))
        } catch (_: Exception) {
            Toast.makeText(this, "Couldn't open link", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openPlanTrip(budget: String) {
        val budgetDestinations = mapOf(
            "500" to listOf("Pondicherry", "Mahabaleshwar", "Lonavala", "Mysore", "Ooty"),
            "2000-5000" to listOf("Goa", "Hampi", "Coorg", "Kodaikanal", "Alleppey"),
            "5000-15000" to listOf("Manali", "Jaipur", "Udaipur", "Rishikesh", "Varanasi"),
            "15000-50000" to listOf("Leh Ladakh", "Andaman", "Meghalaya", "Darjeeling", "Gangtok")
        )

        val destinations = budgetDestinations[budget] ?: listOf("Goa")
        val randomDestination = destinations.random()
        val randomType = listOf("Adventure", "Relaxation", "Sightseeing", "Weekend", "Beach").random()

        val intent = Intent(this, TripResultActivity::class.java).apply {
            putExtra("origin", "")
            putExtra("destination", randomDestination)
            putExtra("travelers", "Solo")
            putExtra("budget", budget)
            putExtra("tripType", randomType)
            putExtra("hideTransport", true)
            putExtra("studentSaverMode", true)
        }
        startActivity(intent)

        Toast.makeText(this, "Check out $randomDestination", Toast.LENGTH_SHORT).show()
    }
}
