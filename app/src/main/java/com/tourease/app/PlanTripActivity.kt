package com.tourease.app

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.AutoCompleteTextView

class PlanTripActivity : AppCompatActivity() {

    private var selectedTravelers = ""
    private var selectedTripType = ""
    private var selectedBudget = ""

    private val travelerButtons = mutableListOf<Button>()
    private val tripTypeButtons = mutableListOf<Button>()
    private val budgetButtons = mutableListOf<Button>()

    // Surprise Me data
    private val surpriseDestinations = listOf(
        "Goa", "Manali", "Pondicherry", "Coorg", "Jaipur",
        "Udaipur", "Meghalaya", "Varanasi", "Munnar", "Rishikesh",
        "Hampi", "Alleppey", "Darjeeling", "Leh Ladakh", "Ooty",
        "Kasol", "Mcleodganj", "Gangtok", "Kodaikanal", "Andaman"
    )

    private val surpriseTripTypes = listOf(
        "Adventure", "Relaxation", "Sightseeing", "Road Trip", "Weekend", "Beach"
    )
    private val indianCities = listOf(
        "Delhi", "Mumbai", "Chennai", "Bangalore", "Kolkata",
        "Hyderabad", "Pune", "Goa", "Jaipur", "Udaipur",
        "Manali", "Rishikesh", "Varanasi", "Kochi", "Munnar",
        "Pondicherry", "Coorg", "Ooty", "Kodaikanal", "Darjeeling",
        "Gangtok", "Meghalaya", "Leh Ladakh", "Kasol", "Mcleodganj",
        "Hampi", "Alleppey", "Mysore", "Madurai", "Rameshwaram",
        "Andaman", "Shimla", "Mussoorie", "Nainital", "Agra",
        "Amritsar", "Jodhpur", "Pushkar", "Mount Abu", "Lonavala",
        "Mahabaleshwar", "Alibaug", "Wayanad", "Thekkady", "Varkala"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_trip)

        val ivBack: ImageView = findViewById(R.id.ivBack)
        ivBack.setOnClickListener { finish() }

        val adapter = android.widget.ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, indianCities)
        val etOrigin: AutoCompleteTextView = findViewById(R.id.etOrigin)
        val etDestination: AutoCompleteTextView = findViewById(R.id.etDestination)
        etOrigin.setAdapter(adapter)
        etDestination.setAdapter(adapter)

        val etDates: EditText = findViewById(R.id.etDates)
        etDates.setOnClickListener {
            val calendar = java.util.Calendar.getInstance()
            val year = calendar.get(java.util.Calendar.YEAR)
            val month = calendar.get(java.util.Calendar.MONTH)
            val day = calendar.get(java.util.Calendar.DAY_OF_MONTH)



            // Start date picker
            android.app.DatePickerDialog(this, { _, sYear, sMonth, sDay ->
                val startDate = "${sDay}/${sMonth + 1}/${sYear}"

                // End date picker
                android.app.DatePickerDialog(this, { _, eYear, eMonth, eDay ->
                    val endDate = "${eDay}/${eMonth + 1}/${eYear}"
                    etDates.setText("$startDate — $endDate")
                }, sYear, sMonth, sDay).show()

            }, year, month, day).show()
        }

        // Traveler buttons
        val btnSolo: Button = findViewById(R.id.btnSolo)
        val btnCouple: Button = findViewById(R.id.btnCouple)
        val btnFamily: Button = findViewById(R.id.btnFamily)
        val btnFriends: Button = findViewById(R.id.btnFriends)
        travelerButtons.addAll(listOf(btnSolo, btnCouple, btnFamily, btnFriends))

        val travelerLabels = listOf("Solo", "Couple", "Family", "Friends")
        travelerButtons.forEachIndexed { index, btn ->
            btn.setOnClickListener { selectChip(btn, travelerButtons) { selectedTravelers = travelerLabels[index] } }
        }

        // Budget buttons
        val btnBudgetLow: Button = findViewById(R.id.btnBudgetLow)
        val btnBudgetMid: Button = findViewById(R.id.btnBudgetMid)
        val btnBudgetHigh: Button = findViewById(R.id.btnBudgetHigh)
        budgetButtons.addAll(listOf(btnBudgetLow, btnBudgetMid, btnBudgetHigh))

        val budgetLabels = listOf("2000-5000", "5000-15000", "15000-50000")
        budgetButtons.forEachIndexed { index, btn ->
            btn.setOnClickListener { selectChip(btn, budgetButtons) { selectedBudget = budgetLabels[index] } }
        }

        // Trip type buttons
        val btnAdventure: Button = findViewById(R.id.btnAdventure)
        val btnRelaxation: Button = findViewById(R.id.btnRelaxation)
        val btnSightseeing: Button = findViewById(R.id.btnSightseeing)
        val btnRoadTrip: Button = findViewById(R.id.btnRoadTrip)
        val btnWeekend: Button = findViewById(R.id.btnWeekend)
        val btnBeach: Button = findViewById(R.id.btnBeach)
        tripTypeButtons.addAll(listOf(btnAdventure, btnRelaxation, btnSightseeing, btnRoadTrip, btnWeekend, btnBeach))

        val tripLabels = listOf("Adventure", "Relaxation", "Sightseeing", "Road Trip", "Weekend", "Beach")
        tripTypeButtons.forEachIndexed { index, btn ->
            btn.setOnClickListener { selectChip(btn, tripTypeButtons) { selectedTripType = tripLabels[index] } }
        }

        // Surprise Me
        val btnSurpriseMe: Button = findViewById(R.id.btnSurpriseMe)
        btnSurpriseMe.setOnClickListener { surpriseMe() }

        // Generate Trip
        val btnGenerateTrip: Button = findViewById(R.id.btnGenerateTrip)
        btnGenerateTrip.setOnClickListener {
            val origin = findViewById<AutoCompleteTextView>(R.id.etOrigin).text.toString().trim()
            val destination = findViewById<AutoCompleteTextView>(R.id.etDestination).text.toString().trim()

            if (origin.isEmpty()) {
                Toast.makeText(this, "Where are you travelling from?", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (destination.isEmpty()) {
                Toast.makeText(this, "Where do you want to go?", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, TripResultActivity::class.java)
            intent.putExtra("origin", origin)
            intent.putExtra("destination", destination)
            intent.putExtra("travelers", selectedTravelers)
            intent.putExtra("budget", selectedBudget)
            intent.putExtra("tripType", selectedTripType)
            startActivity(intent)
        }
    }

    private fun selectChip(selected: Button, allButtons: List<Button>, onSelect: () -> Unit) {
        allButtons.forEach { btn ->
            val bg = GradientDrawable()
            bg.cornerRadius = 12f * resources.displayMetrics.density
            bg.setColor(Color.parseColor("#F0F0F5"))
            bg.setStroke((1 * resources.displayMetrics.density).toInt(), Color.parseColor("#E0E0E0"))
            btn.background = bg
            btn.setTextColor(Color.parseColor("#666666"))
        }

        val selectedBg = GradientDrawable()
        selectedBg.cornerRadius = 12f * resources.displayMetrics.density
        selectedBg.setColor(Color.parseColor("#FF6B35"))
        selected.background = selectedBg
        selected.setTextColor(Color.WHITE)

        onSelect()
    }

    private fun surpriseMe() {
        val randomDest = surpriseDestinations.random()
        val randomTripIndex = (0 until tripTypeButtons.size).random()
        val randomBudgetIndex = (0 until budgetButtons.size).random()
        val randomTravelerIndex = (0 until travelerButtons.size).random()

        findViewById<AutoCompleteTextView>(R.id.etDestination).setText(randomDest)

        // Select random chips
        selectChip(tripTypeButtons[randomTripIndex], tripTypeButtons) {
            selectedTripType = surpriseTripTypes[randomTripIndex]
        }
        selectChip(budgetButtons[randomBudgetIndex], budgetButtons) {
            selectedBudget = listOf("2000-5000", "5000-15000", "15000-50000")[randomBudgetIndex]
        }
        selectChip(travelerButtons[randomTravelerIndex], travelerButtons) {
            selectedTravelers = listOf("Solo", "Couple", "Family", "Friends")[randomTravelerIndex]
        }

        Toast.makeText(this, "🎲 How about $randomDest?", Toast.LENGTH_SHORT).show()
    }
}






