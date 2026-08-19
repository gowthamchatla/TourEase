package com.tourease.app

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
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

    data class WeatherInfo(
        val emoji: String,
        val tempC: Int,
        val desc: String,
        val seasonLabel: String,
        val seasonColor: String
    )

    private val weatherMap = mapOf(
        "goa"           to WeatherInfo("☀️", 32, "Sunny & humid · Beach season peak!", "Best Nov–Feb", "#00C896"),
        "manali"        to WeatherInfo("❄️", 4,  "Cold & snowy · Pack heavy layers", "Best Apr–Jun", "#6C63FF"),
        "pondicherry"   to WeatherInfo("🌤️", 30, "Warm & breezy · Great for beaches", "Best Oct–Feb", "#00C896"),
        "coorg"         to WeatherInfo("🌧️", 22, "Misty hills · Monsoon vibes", "Best Mar–Jun", "#FF6B35"),
        "jaipur"        to WeatherInfo("🌞", 35, "Hot & dry · Carry sunscreen!", "Best Oct–Mar", "#FFB800"),
        "udaipur"       to WeatherInfo("🌤️", 28, "Pleasant · Lakes are gorgeous now", "Best Sep–Mar", "#00C896"),
        "meghalaya"     to WeatherInfo("🌧️", 18, "Rainy & lush · Waterfalls at peak", "Best Mar–Jun", "#6C63FF"),
        "varanasi"      to WeatherInfo("🌫️", 26, "Hazy & spiritual · River ghats lit", "Best Oct–Mar", "#FFB800"),
        "munnar"        to WeatherInfo("🌿", 20, "Cool & green · Tea gardens blooming", "Best Sep–May", "#00C896"),
        "rishikesh"     to WeatherInfo("☀️", 24, "Sunny · Perfect for rafting season", "Best Feb–May", "#00C896"),
        "hampi"         to WeatherInfo("🌞", 34, "Hot & historic · Go early mornings", "Best Oct–Feb", "#FF6B35"),
        "alleppey"      to WeatherInfo("🌴", 28, "Warm & backwaters are calm", "Best Aug–Mar", "#00C896"),
        "darjeeling"    to WeatherInfo("🌥️", 12, "Cool & foggy · Tea & sunrises", "Best Mar–May", "#6C63FF"),
        "leh ladakh"    to WeatherInfo("🏔️", 8,  "Cold & high altitude · Roads open", "Best Jun–Sep", "#FFB800"),
        "ooty"          to WeatherInfo("🌿", 17, "Cool & misty · Nilgiri charm", "Best Apr–Jun", "#00C896"),
        "kasol"         to WeatherInfo("❄️", 10, "Cold mountain vibes · Backpacker heaven", "Best Apr–Jun", "#6C63FF"),
        "mcleodganj"    to WeatherInfo("🌥️", 15, "Cool & peaceful · Tibetan culture", "Best Mar–Jun", "#6C63FF"),
        "gangtok"       to WeatherInfo("🌤️", 16, "Pleasant · Himalayan views clear", "Best Mar–May", "#00C896"),
        "kodaikanal"    to WeatherInfo("🌿", 18, "Cool & misty · Perfect hill escape", "Best Apr–Jun", "#00C896"),
        "andaman"       to WeatherInfo("🏖️", 29, "Warm & clear · Snorkeling season!", "Best Oct–May", "#00C896"),
        "shimla"        to WeatherInfo("❄️", 8,  "Cold & snowy · White winter magic", "Best Apr–Jun", "#6C63FF"),
        "mussoorie"     to WeatherInfo("🌤️", 14, "Cool & scenic · Queen of hills", "Best Mar–Jun", "#00C896"),
        "nainital"      to WeatherInfo("🌿", 16, "Fresh & cool · Lake reflects hills", "Best Mar–Jun", "#00C896"),
        "agra"          to WeatherInfo("🌞", 33, "Hot · Visit Taj at sunrise", "Best Oct–Mar", "#FFB800"),
        "amritsar"      to WeatherInfo("☀️", 28, "Warm · Golden Temple glows at dusk", "Best Oct–Mar", "#FFB800"),
        "jodhpur"       to WeatherInfo("🌵", 36, "Hot & desert dry · Blue city vibes", "Best Oct–Feb", "#FF6B35"),
        "pushkar"       to WeatherInfo("☀️", 30, "Warm · Camel fair season", "Best Oct–Mar", "#FFB800"),
        "lonavala"      to WeatherInfo("🌧️", 23, "Misty & green · Waterfalls flowing", "Best Jun–Sep", "#6C63FF"),
        "mahabaleshwar" to WeatherInfo("🌿", 20, "Cool & strawberry season!", "Best Mar–Jun", "#00C896"),
        "wayanad"       to WeatherInfo("🌿", 22, "Lush & green · Wildlife active", "Best Sep–May", "#00C896"),
        "varkala"       to WeatherInfo("🌊", 29, "Warm · Clifftop beach paradise", "Best Oct–Mar", "#00C896"),
        "delhi"         to WeatherInfo("🌫️", 28, "Polluted but buzzing · City energy", "Best Oct–Feb", "#FFB800"),
        "mumbai"        to WeatherInfo("💧", 30, "Humid · Monsoon spirit alive", "Best Nov–Feb", "#6C63FF"),
        "chennai"       to WeatherInfo("🌡️", 34, "Hot & coastal · Marina Beach vibes", "Best Nov–Feb", "#FF6B35"),
        "bangalore"     to WeatherInfo("⛅", 26, "Pleasant all year · Garden city", "Best Oct–Feb", "#00C896"),
        "kolkata"       to WeatherInfo("🌫️", 30, "Humid & cultural · City of joy", "Best Oct–Feb", "#FFB800"),
        "hyderabad"     to WeatherInfo("☀️", 32, "Warm · Biryani weather is always good", "Best Oct–Feb", "#FFB800"),
        "pune"          to WeatherInfo("⛅", 27, "Pleasant · College city energy", "Best Oct–Feb", "#00C896"),
        "kochi"         to WeatherInfo("🌴", 28, "Warm & coastal · Fort Kochi charm", "Best Sep–Mar", "#00C896"),
        "mysore"        to WeatherInfo("🌤️", 25, "Pleasant · Palace is stunning", "Best Oct–Feb", "#00C896"),
        "madurai"       to WeatherInfo("🌡️", 35, "Hot · Meenakshi temple at golden hour", "Best Oct–Feb", "#FF6B35"),
        "rameshwaram"   to WeatherInfo("☀️", 32, "Hot & sacred · Pilgrimage season", "Best Nov–Mar", "#FFB800"),
        "alibaug"       to WeatherInfo("🌊", 28, "Warm · Mumbai's weekend beach escape", "Best Oct–Mar", "#00C896"),
        "thekkady"      to WeatherInfo("🌿", 20, "Cool · Wildlife & spice gardens", "Best Sep–Apr", "#00C896"),
        "mount abu"     to WeatherInfo("🌤️", 22, "Cool & pleasant · Rajasthan's hill escape", "Best Sep–Mar", "#00C896")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_trip)

        // Push content below status bar
        androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.statusBarSpacer)
        ) { view, insets ->
            val statusBarHeight = insets.getInsets(androidx.core.view.WindowInsetsCompat.Type.statusBars()).top
            view.layoutParams.height = statusBarHeight
            view.requestLayout()
            insets
        }

        // Transparent status bar so dark bg extends behind it
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        val ivBack: ImageView = findViewById(R.id.ivBack)
        ivBack.setOnClickListener { finish() }

        val adapter = android.widget.ArrayAdapter(
            this, android.R.layout.simple_dropdown_item_1line, indianCities
        )
        val etOrigin: AutoCompleteTextView = findViewById(R.id.etOrigin)
        val etDestination: AutoCompleteTextView = findViewById(R.id.etDestination)
        etOrigin.setAdapter(adapter)
        etDestination.setAdapter(adapter)

        // Show weather while typing
        etDestination.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val city = s?.toString()?.trim() ?: ""
                if (city.length >= 3) showWeather(city) else hideWeather()
            }
        })

        // Also fire when user selects from dropdown (afterTextChanged may fire before text is committed)
        etDestination.setOnItemClickListener { _, _, _, _ ->
            val city = etDestination.text.toString().trim()
            if (city.isNotEmpty()) showWeather(city)
        }

        val etDates: EditText = findViewById(R.id.etDates)
        etDates.setOnClickListener {
            val calendar = java.util.Calendar.getInstance()
            android.app.DatePickerDialog(this, { _, sYear, sMonth, sDay ->
                val startDate = "${sDay}/${sMonth + 1}/${sYear}"
                android.app.DatePickerDialog(this, { _, eYear, eMonth, eDay ->
                    val endDate = "${eDay}/${eMonth + 1}/${eYear}"
                    etDates.setText("$startDate — $endDate")
                }, sYear, sMonth, sDay).show()
            }, calendar.get(java.util.Calendar.YEAR),
                calendar.get(java.util.Calendar.MONTH),
                calendar.get(java.util.Calendar.DAY_OF_MONTH)).show()
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

        findViewById<Button>(R.id.btnSurpriseMe).setOnClickListener { surpriseMe() }

        findViewById<Button>(R.id.btnGenerateTrip).setOnClickListener {
            val origin = etOrigin.text.toString().trim()
            val destination = etDestination.text.toString().trim()
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

    private fun showWeather(city: String) {
        val key = city.lowercase().trim()
        val weather = weatherMap[key]
            ?: weatherMap.entries.firstOrNull { key.contains(it.key) || it.key.contains(key) }?.value
            ?: return

        val card = findViewById<LinearLayout>(R.id.cardWeather)
        card.visibility = View.VISIBLE

        findViewById<TextView>(R.id.tvWeatherEmoji).text = weather.emoji
        findViewById<TextView>(R.id.tvWeatherCity).text =
            "Weather in ${city.split(" ").joinToString(" ") { it.replaceFirstChar { c -> c.uppercase() } }}"
        findViewById<TextView>(R.id.tvWeatherDesc).text = weather.desc
        findViewById<TextView>(R.id.tvWeatherTemp).text = "${weather.tempC}°C"
        val seasonView = findViewById<TextView>(R.id.tvWeatherSeason)
        seasonView.text = weather.seasonLabel
        seasonView.setTextColor(Color.parseColor(weather.seasonColor))
    }

    private fun hideWeather() {
        findViewById<LinearLayout>(R.id.cardWeather).visibility = View.GONE
    }

    private fun selectChip(selected: Button, allButtons: List<Button>, onSelect: () -> Unit) {
        allButtons.forEach { btn ->
            val bg = GradientDrawable()
            bg.cornerRadius = 10f * resources.displayMetrics.density
            bg.setColor(Color.parseColor("#1E1E38"))
            btn.background = bg
            btn.setTextColor(Color.parseColor("#9B9BB4"))
        }
        val selectedBg = GradientDrawable()
        selectedBg.cornerRadius = 10f * resources.displayMetrics.density
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

        val etDestination = findViewById<AutoCompleteTextView>(R.id.etDestination)
        etDestination.setText(randomDest)
        showWeather(randomDest)

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