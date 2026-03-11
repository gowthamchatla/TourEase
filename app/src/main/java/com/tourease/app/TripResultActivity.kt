package com.tourease.app

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.math.roundToInt

class TripResultActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()

    private val distanceMap = mapOf(
        "delhi" to mapOf("goa" to 1867, "manali" to 530, "jaipur" to 281, "udaipur" to 660, "rishikesh" to 230, "varanasi" to 821, "mumbai" to 1400, "chennai" to 2180, "kolkata" to 1530, "bangalore" to 2150, "hyderabad" to 1550, "pune" to 1450, "kochi" to 2640, "pondicherry" to 2200, "coorg" to 2230, "meghalaya" to 1880, "munnar" to 2700, "hampi" to 1900, "alleppey" to 2670, "darjeeling" to 1550, "leh ladakh" to 990, "ooty" to 2550, "kasol" to 510, "mcleodganj" to 480, "gangtok" to 1650, "kodaikanal" to 2500, "andaman" to 3500),
        "mumbai" to mapOf("goa" to 590, "pune" to 150, "jaipur" to 1150, "udaipur" to 770, "delhi" to 1400, "chennai" to 1340, "bangalore" to 980, "hyderabad" to 710, "kolkata" to 2050, "kochi" to 1340, "manali" to 1950, "pondicherry" to 1370, "coorg" to 1050, "varanasi" to 1500, "hampi" to 760, "alleppey" to 1370, "ooty" to 1200, "munnar" to 1400, "kodaikanal" to 1300, "rishikesh" to 1600, "andaman" to 3000),
        "chennai" to mapOf("goa" to 890, "pondicherry" to 150, "bangalore" to 350, "hyderabad" to 630, "mumbai" to 1340, "delhi" to 2180, "kolkata" to 1670, "kochi" to 700, "coorg" to 530, "ooty" to 560, "munnar" to 590, "kodaikanal" to 470, "hampi" to 600, "alleppey" to 730, "madurai" to 460, "rameshwaram" to 570, "andaman" to 1400, "pune" to 1200, "manali" to 2700, "jaipur" to 1900, "udaipur" to 1800, "varanasi" to 1700),
        "bangalore" to mapOf("goa" to 560, "chennai" to 350, "hyderabad" to 570, "mumbai" to 980, "delhi" to 2150, "kochi" to 530, "coorg" to 250, "ooty" to 270, "munnar" to 480, "kodaikanal" to 470, "hampi" to 340, "alleppey" to 570, "pondicherry" to 310, "pune" to 840, "mysore" to 150, "andaman" to 2800),
        "kolkata" to mapOf("delhi" to 1530, "mumbai" to 2050, "chennai" to 1670, "darjeeling" to 600, "gangtok" to 560, "meghalaya" to 680, "varanasi" to 680, "goa" to 1860, "bangalore" to 1870, "hyderabad" to 1490, "andaman" to 1650),
        "hyderabad" to mapOf("goa" to 660, "chennai" to 630, "bangalore" to 570, "mumbai" to 710, "delhi" to 1550, "kolkata" to 1490, "kochi" to 1100, "hampi" to 370, "pune" to 560, "varanasi" to 1250)
    )

    private val rates = mapOf(
        "train_sleeper" to 0.65, "train_ac3" to 1.35, "train_ac2" to 1.95,
        "bus_govt" to 1.20, "bus_private" to 1.80, "flight" to 4.50, "auto_cab" to 14.0
    )

    private val itineraryData = mapOf(
        "goa" to listOf(
            DayPlan("Day 1", "Arrival & North Goa Beaches", listOf(
                "Morning - Arrive & check into hostel/hotel",
                "Afternoon - Baga Beach & Calangute Beach",
                "Evening - Titos Lane nightlife & street food"
            )),
            DayPlan("Day 2", "Adventure & Culture", listOf(
                "Morning - Dudhsagar Waterfalls trek",
                "Afternoon - Old Goa churches (Basilica of Bom Jesus)",
                "Evening - Anjuna Flea Market & beach shacks"
            )),
            DayPlan("Day 3", "South Goa & Departure", listOf(
                "Morning - Palolem Beach (kayaking & dolphins)",
                "Afternoon - Cabo de Rama Fort",
                "Evening - Depart with memories"
            ))
        ),
        "manali" to listOf(
            DayPlan("Day 1", "Arrival & Mall Road", listOf(
                "Morning - Arrive in Manali, settle in",
                "Afternoon - Hidimba Devi Temple & Van Vihar",
                "Evening - Mall Road shopping & cafe hopping"
            )),
            DayPlan("Day 2", "Solang Valley Adventure", listOf(
                "Morning - Solang Valley (paragliding/zorbing)",
                "Afternoon - Atal Tunnel sightseeing",
                "Evening - Old Manali cafes & live music"
            )),
            DayPlan("Day 3", "Rohtang & Departure", listOf(
                "Morning - Rohtang Pass (snow activities)",
                "Afternoon - Vashisht Hot Springs",
                "Evening - Depart"
            ))
        ),
        "jaipur" to listOf(
            DayPlan("Day 1", "Royal Forts", listOf(
                "Morning - Amber Fort (elephant ride optional)",
                "Afternoon - Jal Mahal & Nahargarh Fort",
                "Evening - Chokhi Dhani village dinner"
            )),
            DayPlan("Day 2", "Pink City Tour", listOf(
                "Morning - Hawa Mahal & City Palace",
                "Afternoon - Jantar Mantar & Albert Hall Museum",
                "Evening - Johari Bazaar shopping"
            )),
            DayPlan("Day 3", "Day Trip & Departure", listOf(
                "Morning - Chand Baori stepwell (Abhaneri)",
                "Afternoon - Local food trail (dal baati churma)",
                "Evening - Depart"
            ))
        ),
        "pondicherry" to listOf(
            DayPlan("Day 1", "French Quarter Vibes", listOf(
                "Morning - Arrive & explore White Town",
                "Afternoon - Promenade Beach & War Memorial",
                "Evening - Cafe des Arts & French cuisine"
            )),
            DayPlan("Day 2", "Auroville & Beaches", listOf(
                "Morning - Auroville Matrimandir visit",
                "Afternoon - Paradise Beach (boat ride)",
                "Evening - Night bazaar & street shopping"
            )),
            DayPlan("Day 3", "Temple & Departure", listOf(
                "Morning - Sri Aurobindo Ashram",
                "Afternoon - Serenity Beach & surfing",
                "Evening - Depart"
            ))
        ),
        "varanasi" to listOf(
            DayPlan("Day 1", "Ghats & Spirituality", listOf(
                "Morning - Sunrise boat ride on Ganges",
                "Afternoon - Kashi Vishwanath Temple",
                "Evening - Ganga Aarti at Dashashwamedh Ghat"
            )),
            DayPlan("Day 2", "Culture & Food", listOf(
                "Morning - Sarnath (Buddhist pilgrimage)",
                "Afternoon - Banarasi silk shopping",
                "Evening - Street food trail (kachori, lassi, chaat)"
            )),
            DayPlan("Day 3", "Hidden Gems & Departure", listOf(
                "Morning - Ramnagar Fort across the river",
                "Afternoon - Tulsi Manas Temple & BHU campus",
                "Evening - Depart"
            ))
        ),
        "lonavala" to listOf(
            DayPlan("Day 1", "Viewpoints & Caves", listOf(
                "Morning - Tiger's Leap viewpoint",
                "Afternoon - Karla & Bhaja Caves",
                "Evening - Chikki shopping at main market"
            ))
        ),
        "mahabaleshwar" to listOf(
            DayPlan("Day 1", "Strawberry Country", listOf(
                "Morning - Mapro Garden & strawberry picking",
                "Afternoon - Arthur's Seat & Elephant Head Point",
                "Evening - Venna Lake boating"
            ))
        ),
        "mysore" to listOf(
            DayPlan("Day 1", "Royal Mysore", listOf(
                "Morning - Mysore Palace tour",
                "Afternoon - Chamundi Hills & Brindavan Gardens",
                "Evening - Devaraja Market & Mysore Pak tasting"
            ))
        ),
        "ooty" to listOf(
            DayPlan("Day 1", "Queen of Hill Stations", listOf(
                "Morning - Ooty Lake & Botanical Gardens",
                "Afternoon - Nilgiri Mountain Railway ride",
                "Evening - Tea factory visit & local chocolate"
            )),
            DayPlan("Day 2", "Nature & Views", listOf(
                "Morning - Doddabetta Peak sunrise",
                "Afternoon - Pykara Falls & Pine Forest",
                "Evening - Depart"
            ))
        ),
        "coorg" to listOf(
            DayPlan("Day 1", "Coffee Country", listOf(
                "Morning - Abbey Falls trek",
                "Afternoon - Coffee plantation tour",
                "Evening - Raja's Seat sunset point"
            )),
            DayPlan("Day 2", "Adventure Day", listOf(
                "Morning - Dubare Elephant Camp",
                "Afternoon - Namdroling Monastery (Golden Temple)",
                "Evening - Depart"
            ))
        ),
        "hampi" to listOf(
            DayPlan("Day 1", "Ruins & Boulders", listOf(
                "Morning - Virupaksha Temple at sunrise",
                "Afternoon - Vittala Temple & Stone Chariot",
                "Evening - Hippie Island sunset & coracle ride"
            )),
            DayPlan("Day 2", "Explore & Depart", listOf(
                "Morning - Matanga Hill sunrise trek",
                "Afternoon - Royal Enclosure & Underground Temple",
                "Evening - Depart"
            ))
        ),
        "kodaikanal" to listOf(
            DayPlan("Day 1", "Princess of Hill Stations", listOf(
                "Morning - Kodai Lake cycling & boating",
                "Afternoon - Coaker's Walk & Pillar Rocks",
                "Evening - Homemade chocolate shopping"
            )),
            DayPlan("Day 2", "Waterfalls & Views", listOf(
                "Morning - Bear Shola Falls & Green Valley View",
                "Afternoon - Dolphin's Nose viewpoint",
                "Evening - Depart"
            ))
        ),
        "alleppey" to listOf(
            DayPlan("Day 1", "Backwaters Paradise", listOf(
                "Morning - Houseboat check-in & backwater cruise",
                "Afternoon - Village walk & toddy shop visit",
                "Evening - Sunset on the houseboat with Kerala dinner"
            )),
            DayPlan("Day 2", "Beach & Depart", listOf(
                "Morning - Alappuzha Beach & lighthouse",
                "Afternoon - Pathiramanal island visit",
                "Evening - Depart"
            ))
        )
    )

    private val placesData = mapOf(
        "goa" to listOf(
            PlaceInfo("Baga Beach", "Most popular beach, great nightlife nearby"),
            PlaceInfo("Dudhsagar Falls", "Stunning waterfall, best during monsoon"),
            PlaceInfo("Fort Aguada", "Portuguese fort with lighthouse & sea views"),
            PlaceInfo("Anjuna Flea Market", "Wednesday market - clothes, jewelry, vibes")
        ),
        "manali" to listOf(
            PlaceInfo("Solang Valley", "Paragliding, skiing & adventure sports hub"),
            PlaceInfo("Rohtang Pass", "Snow point at 3,978m - permit required"),
            PlaceInfo("Hidimba Temple", "Ancient cave temple in cedar forest"),
            PlaceInfo("Old Manali", "Cafes, live music & chill vibes")
        ),
        "jaipur" to listOf(
            PlaceInfo("Amber Fort", "Stunning hilltop fort with mirror palace"),
            PlaceInfo("Hawa Mahal", "Iconic pink sandstone wind palace"),
            PlaceInfo("Nahargarh Fort", "Best sunset views over the city"),
            PlaceInfo("Johari Bazaar", "Famous for jewelry & Rajasthani crafts")
        ),
        "pondicherry" to listOf(
            PlaceInfo("White Town", "French colonial streets, colorful & aesthetic"),
            PlaceInfo("Auroville", "Universal township - peaceful & unique"),
            PlaceInfo("Paradise Beach", "Secluded beach, accessible by boat only"),
            PlaceInfo("Cafe des Arts", "Popular cafe in French Quarter")
        ),
        "varanasi" to listOf(
            PlaceInfo("Dashashwamedh Ghat", "Famous Ganga Aarti every evening"),
            PlaceInfo("Sarnath", "Where Buddha gave his first sermon"),
            PlaceInfo("Kashi Vishwanath", "One of the 12 Jyotirlingas"),
            PlaceInfo("Blue Lassi Shop", "Legendary lassi shop since 1925")
        )
    )

    private val budgetPerDay = mapOf(
        "500" to BudgetEstimate(300, 400, 100),
        "2000-5000" to BudgetEstimate(500, 600, 200),
        "5000-15000" to BudgetEstimate(1500, 1000, 500),
        "15000-50000" to BudgetEstimate(4000, 2000, 1500)
    )

    private val cityCoords = mapOf(
        "delhi" to Pair(28.6139, 77.2090), "mumbai" to Pair(19.0760, 72.8777),
        "chennai" to Pair(13.0827, 80.2707), "bangalore" to Pair(12.9716, 77.5946),
        "kolkata" to Pair(22.5726, 88.3639), "hyderabad" to Pair(17.3850, 78.4867),
        "pune" to Pair(18.5204, 73.8567), "goa" to Pair(15.2993, 74.1240),
        "jaipur" to Pair(26.9124, 75.7873), "udaipur" to Pair(24.5854, 73.7125),
        "manali" to Pair(32.2396, 77.1887), "rishikesh" to Pair(30.0869, 78.2676),
        "varanasi" to Pair(25.3176, 82.9739), "kochi" to Pair(9.9312, 76.2673),
        "munnar" to Pair(10.0889, 77.0595), "pondicherry" to Pair(11.9416, 79.8083),
        "coorg" to Pair(12.3375, 75.8069), "ooty" to Pair(11.4102, 76.6950),
        "kodaikanal" to Pair(10.2381, 77.4892), "darjeeling" to Pair(27.0360, 88.2627),
        "gangtok" to Pair(27.3389, 88.6065), "meghalaya" to Pair(25.4670, 91.3662),
        "leh ladakh" to Pair(34.1526, 77.5771), "kasol" to Pair(32.0100, 77.3150),
        "mcleodganj" to Pair(32.2426, 76.3213), "hampi" to Pair(15.3350, 76.4600),
        "alleppey" to Pair(9.4981, 76.3388), "mysore" to Pair(12.2958, 76.6394),
        "madurai" to Pair(9.9252, 78.1198), "rameshwaram" to Pair(9.2876, 79.3129),
        "andaman" to Pair(11.7401, 92.6586), "lonavala" to Pair(18.7546, 73.4062),
        "mahabaleshwar" to Pair(17.9307, 73.6477)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trip_result)

        val origin = intent.getStringExtra("origin") ?: ""
        val destination = intent.getStringExtra("destination") ?: ""
        val travelers = intent.getStringExtra("travelers") ?: "Solo"
        val budget = intent.getStringExtra("budget") ?: "5000-15000"
        val tripType = intent.getStringExtra("tripType") ?: "Sightseeing"
        val hideTransport = intent.getBooleanExtra("hideTransport", false)
        val studentSaverMode = intent.getBooleanExtra("studentSaverMode", false)

        findViewById<ImageView>(R.id.ivBack).setOnClickListener { finish() }

        val tvRoute: TextView = findViewById(R.id.tvRoute)
        tvRoute.text = if (origin.isNotEmpty()) {
            "${prettyName(origin)} -> ${prettyName(destination)}"
        } else {
            "Trip to ${prettyName(destination)}"
        }

        val tvTripMeta: TextView = findViewById(R.id.tvTripMeta)
        val budgetLabel = when (budget) {
            "500" -> "₹500"
            "2000-5000" -> "₹2K-5K"
            "5000-15000" -> "₹5K-15K"
            "15000-50000" -> "₹15K+"
            else -> budget
        }
        tvTripMeta.text = "$travelers · $tripType · $budgetLabel"

        val distance = if (origin.isNotEmpty()) {
            getDistance(origin.lowercase().trim(), destination.lowercase().trim())
        } else {
            0
        }

        val tvDistance: TextView = findViewById(R.id.tvDistance)
        if (hideTransport || origin.isEmpty()) {
            tvDistance.text = "Budget trip plan"
            findViewById<TextView>(R.id.tvTransportTitle).visibility = View.GONE
            findViewById<LinearLayout>(R.id.cardTrain).visibility = View.GONE
            findViewById<LinearLayout>(R.id.cardBus).visibility = View.GONE
            findViewById<LinearLayout>(R.id.cardFlightCab).visibility = View.GONE
            findViewById<LinearLayout>(R.id.rowTransportBudget).visibility = View.GONE
        } else {
            tvDistance.text = if (distance > 0) "$distance km" else "Distance unavailable"
            populateTransportCosts(distance)
        }

        populateStudentDeals(destination.lowercase().trim(), studentSaverMode)
        populateItinerary(destination.lowercase().trim())
        populatePlaces(destination.lowercase().trim())
        populateBudget(distance, budget, hideTransport || origin.isEmpty())

        val tvBestValue: TextView = findViewById(R.id.tvBestValue)
        tvBestValue.text = when {
            studentSaverMode -> "Student Saver Mode is on - compare external deals before booking."
            hideTransport || origin.isEmpty() -> "Student hack: Travel in groups of 4 to split costs."
            distance < 100 -> "Short trip - skip hotels and make it a day trip."
            distance < 400 -> "Book buses early for the best rates."
            distance < 1200 -> "Book trains 6 to 8 weeks early for cheaper fares."
            else -> "Compare flights on at least 2 providers before paying."
        }
    }

    private fun populateStudentDeals(destination: String, studentSaverMode: Boolean) {
        val titleView: TextView = findViewById(R.id.tvStudentDealsTitle)
        val subtitleView: TextView = findViewById(R.id.tvStudentDealsSubtitle)
        val container: LinearLayout = findViewById(R.id.studentDealsContainer)
        container.removeAllViews()

        titleView.text = if (studentSaverMode) {
            "Student Saver Deals"
        } else {
            "External Deals You Can Compare"
        }
        subtitleView.text = if (studentSaverMode) {
            "Live student deals from Firebase. Tap to open the provider."
        } else {
            "Live provider deals for this destination. Tap to compare prices."
        }

        db.collection("student_deals")
            .whereEqualTo("destination", destination)
            .whereEqualTo("isActive", true)
            .get()
            .addOnSuccessListener { result ->
                val deals = result.toObjects(StudentDeal::class.java)
                    .filter { !studentSaverMode || it.studentOnly }

                if (deals.isEmpty()) {
                    getFallbackDeals(destination).forEach { deal ->
                        container.addView(createDealCard(deal, studentSaverMode))
                    }
                } else {
                    deals.forEach { deal ->
                        container.addView(createDealCard(deal, studentSaverMode))
                    }
                }
            }
            .addOnFailureListener {
                subtitleView.text = "Could not load live deals. Showing fallback deals."
                getFallbackDeals(destination).forEach { deal ->
                    container.addView(createDealCard(deal, studentSaverMode))
                }
            }
    }

    private fun createDealCard(deal: StudentDeal, studentSaverMode: Boolean): LinearLayout {
        val card = createCard()
        card.setPadding(dp(16), dp(16), dp(16), dp(16))

        val provider = TextView(this).apply {
            text = deal.provider
            textSize = 12f
            setTypeface(null, Typeface.BOLD)
            setTextColor(Color.parseColor("#0E7490"))
        }
        card.addView(provider)

        val title = TextView(this).apply {
            text = deal.title
            textSize = 16f
            setTypeface(null, Typeface.BOLD)
            setTextColor(Color.parseColor("#1A1A2E"))
            setPadding(0, dp(6), 0, 0)
        }
        card.addView(title)

        val meta = TextView(this).apply {
            text = "${deal.note} • ${deal.validity}"
            textSize = 12f
            setTextColor(Color.parseColor("#6B7280"))
            setPadding(0, dp(4), 0, 0)
        }
        card.addView(meta)

        val priceRow = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(0, dp(10), 0, 0)
        }

        val original = TextView(this).apply {
            text = "₹${formatCurrency(deal.originalPrice)}"
            textSize = 13f
            setTextColor(Color.parseColor("#9CA3AF"))
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
        priceRow.addView(original)

        val current = TextView(this).apply {
            text = "  ₹${formatCurrency(deal.dealPrice)}"
            textSize = 19f
            setTypeface(null, Typeface.BOLD)
            setTextColor(if (studentSaverMode) Color.parseColor("#059669") else Color.parseColor("#FF6B35"))
        }
        priceRow.addView(current)

        val seats = TextView(this).apply {
            text = "  ${deal.seatsLeft} left"
            textSize = 12f
            setTextColor(Color.parseColor("#B45309"))
        }
        priceRow.addView(seats)
        card.addView(priceRow)

        val cta = TextView(this).apply {
            text = if (studentSaverMode) "Open Student Deal" else "Open Provider Deal"
            textSize = 13f
            setTypeface(null, Typeface.BOLD)
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
            setPadding(dp(14), dp(10), dp(14), dp(10))
            background = GradientDrawable().apply {
                cornerRadius = dp(12).toFloat()
                setColor(if (studentSaverMode) Color.parseColor("#0F766E") else Color.parseColor("#FF6B35"))
            }
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.topMargin = dp(12)
            layoutParams = params
            setOnClickListener { openDeal(deal.bookingUrl) }
        }
        card.addView(cta)

        return card
    }

    private fun openDeal(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    private fun populateTransportCosts(distance: Int) {
        val tvTrainSleeper: TextView = findViewById(R.id.tvTrainSleeper)
        val tvTrainAC3: TextView = findViewById(R.id.tvTrainAC3)
        val tvTrainAC2: TextView = findViewById(R.id.tvTrainAC2)
        val tvBusGovt: TextView = findViewById(R.id.tvBusGovt)
        val tvBusPrivate: TextView = findViewById(R.id.tvBusPrivate)
        val tvFlight: TextView = findViewById(R.id.tvFlight)
        val tvAutoCab: TextView = findViewById(R.id.tvAutoCab)

        val tvTrainSleeperTime: TextView = findViewById(R.id.tvTrainSleeperTime)
        val tvTrainAC3Time: TextView = findViewById(R.id.tvTrainAC3Time)
        val tvTrainAC2Time: TextView = findViewById(R.id.tvTrainAC2Time)
        val tvBusGovtTime: TextView = findViewById(R.id.tvBusGovtTime)
        val tvBusPrivateTime: TextView = findViewById(R.id.tvBusPrivateTime)
        val tvFlightTime: TextView = findViewById(R.id.tvFlightTime)
        val tvAutoCabTime: TextView = findViewById(R.id.tvAutoCabTime)

        if (distance > 0) {
            tvTrainSleeper.text = "₹${calcCost(distance, "train_sleeper")}"
            tvTrainAC3.text = "₹${calcCost(distance, "train_ac3")}"
            tvTrainAC2.text = "₹${calcCost(distance, "train_ac2")}"
            tvBusGovt.text = "₹${calcCost(distance, "bus_govt")}"
            tvBusPrivate.text = "₹${calcCost(distance, "bus_private")}"
            tvFlight.text = "₹${calcCost(distance, "flight")}"
            tvAutoCab.text = "₹${calcCost(distance, "auto_cab")}"

            tvTrainSleeperTime.text = estimateTime(distance, 50)
            tvTrainAC3Time.text = estimateTime(distance, 55)
            tvTrainAC2Time.text = estimateTime(distance, 55)
            tvBusGovtTime.text = estimateTime(distance, 45)
            tvBusPrivateTime.text = estimateTime(distance, 55)
            tvFlightTime.text = estimateTime(distance, 700)
            tvAutoCabTime.text = estimateTime(distance, 60)
        } else {
            val na = "N/A"
            listOf(tvTrainSleeper, tvTrainAC3, tvTrainAC2, tvBusGovt, tvBusPrivate, tvFlight, tvAutoCab).forEach { it.text = na }
            listOf(tvTrainSleeperTime, tvTrainAC3Time, tvTrainAC2Time, tvBusGovtTime, tvBusPrivateTime, tvFlightTime, tvAutoCabTime).forEach { it.text = "" }
        }
    }

    private fun populateItinerary(destination: String) {
        val container: LinearLayout = findViewById(R.id.itineraryContainer)
        container.removeAllViews()

        val days = itineraryData[destination] ?: getDefaultItinerary(destination)

        for (day in days) {
            val card = createCard()
            val cardLayout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(dp(16), dp(16), dp(16), dp(16))
            }

            val dayTitle = TextView(this).apply {
                text = "${day.day} - ${day.title}"
                textSize = 15f
                setTypeface(null, Typeface.BOLD)
                setTextColor(Color.parseColor("#1A1A2E"))
            }
            cardLayout.addView(dayTitle)

            for (activity in day.activities) {
                val activityView = TextView(this).apply {
                    text = activity
                    textSize = 13f
                    setTextColor(Color.parseColor("#555555"))
                    setPadding(0, dp(6), 0, 0)
                }
                cardLayout.addView(activityView)
            }

            card.addView(cardLayout)
            container.addView(card)
        }
    }

    private fun populatePlaces(destination: String) {
        val container: LinearLayout = findViewById(R.id.placesContainer)
        container.removeAllViews()

        val places = placesData[destination] ?: getDefaultPlaces(destination)

        for (place in places) {
            val card = createCard()
            val info = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(dp(14), dp(12), dp(14), dp(12))
            }

            val name = TextView(this).apply {
                text = place.name
                textSize = 14f
                setTypeface(null, Typeface.BOLD)
                setTextColor(Color.parseColor("#1A1A2E"))
            }
            info.addView(name)

            val desc = TextView(this).apply {
                text = place.description
                textSize = 12f
                setTextColor(Color.parseColor("#888888"))
                setPadding(0, dp(4), 0, 0)
            }
            info.addView(desc)

            card.addView(info)
            container.addView(card)
        }
    }

    private fun populateBudget(distance: Int, budget: String, noTransport: Boolean) {
        val estimate = budgetPerDay[budget] ?: BudgetEstimate(1500, 1000, 500)
        val days = when (budget) {
            "500" -> 1
            "2000-5000" -> 2
            else -> 3
        }

        val transportCost = if (!noTransport && distance > 0) calcCostRaw(distance, "train_ac3") else 0
        val staysCost = estimate.stays * days
        val foodCost = estimate.food * days
        val activitiesCost = estimate.activities * days
        val total = transportCost + staysCost + foodCost + activitiesCost

        if (!noTransport) {
            findViewById<TextView>(R.id.tvBudgetTransport).text = "₹${formatCurrency(transportCost)}"
        }
        findViewById<TextView>(R.id.tvBudgetStays).text = "₹${formatCurrency(staysCost)}"
        findViewById<TextView>(R.id.tvBudgetFood).text = "₹${formatCurrency(foodCost)}"
        findViewById<TextView>(R.id.tvBudgetActivities).text = "₹${formatCurrency(activitiesCost)}"
        findViewById<TextView>(R.id.tvBudgetTotal).text = "₹${formatCurrency(total)}"
    }

    private fun getDefaultItinerary(destination: String): List<DayPlan> {
        val cap = prettyName(destination)
        return listOf(
            DayPlan("Day 1", "Arrive & Explore $cap", listOf(
                "Morning - Arrive & check in",
                "Afternoon - Explore local attractions",
                "Evening - Local food & night walk"
            )),
            DayPlan("Day 2", "Sightseeing & Adventure", listOf(
                "Morning - Visit top-rated tourist spot",
                "Afternoon - Local market & shopping",
                "Evening - Cultural experience"
            )),
            DayPlan("Day 3", "Hidden Gems & Departure", listOf(
                "Morning - Offbeat location nearby",
                "Afternoon - Souvenir shopping & lunch",
                "Evening - Depart with memories"
            ))
        )
    }

    private fun getDefaultPlaces(destination: String): List<PlaceInfo> {
        val cap = prettyName(destination)
        return listOf(
            PlaceInfo("Top attraction in $cap", "Must-visit landmark"),
            PlaceInfo("Local food street", "Try the local specialties"),
            PlaceInfo("Scenic viewpoint", "Best for photos & sunsets"),
            PlaceInfo("Local market", "Shopping & souvenirs")
        )
    }

    private fun getFallbackDeals(destination: String): List<StudentDeal> {
        val cap = prettyName(destination)
        return listOf(
            StudentDeal("MakeMyTrip", "$cap budget stay", 3999, 2999, "Good student value", "Check live price", 8, "https://www.makemytrip.com/"),
            StudentDeal("Goibibo", "$cap weekend trip", 4599, 3399, "Budget-friendly pick", "Limited offer", 6, "https://www.goibibo.com/"),
            StudentDeal("Booking.com", "$cap hostel or guesthouse", 2499, 1799, "Low-cost stay", "Popular now", 5, "https://www.booking.com/")
        )
    }

    private fun createCard(): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            background = GradientDrawable().apply {
                cornerRadius = 16f * resources.displayMetrics.density
                setColor(Color.WHITE)
            }
            elevation = 2f * resources.displayMetrics.density
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, dp(6), 0, 0)
            }
        }
    }

    private fun prettyName(value: String): String =
        value.trim().split(" ").joinToString(" ") { part ->
            part.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }

    private fun dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()

    private fun getDistance(origin: String, destination: String): Int {
        distanceMap[origin]?.get(destination)?.let { return it }
        distanceMap[destination]?.get(origin)?.let { return it }
        return estimateDistanceFromCoords(origin, destination)
    }

    private fun estimateDistanceFromCoords(origin: String, destination: String): Int {
        val from = cityCoords[origin] ?: return 0
        val to = cityCoords[destination] ?: return 0
        val latDiff = Math.toRadians(to.first - from.first)
        val lonDiff = Math.toRadians(to.second - from.second)
        val a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2) +
                Math.cos(Math.toRadians(from.first)) * Math.cos(Math.toRadians(to.first)) *
                Math.sin(lonDiff / 2) * Math.sin(lonDiff / 2)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        val straightLine = 6371 * c
        return (straightLine * 1.3).roundToInt()
    }

    private fun calcCost(distance: Int, mode: String): String = formatCurrency(calcCostRaw(distance, mode))

    private fun calcCostRaw(distance: Int, mode: String): Int {
        val rate = rates[mode] ?: return 0
        val baseCost = (distance * rate).roundToInt()
        return when {
            mode.startsWith("train") -> baseCost + 250
            mode.startsWith("bus") -> baseCost + 100
            mode == "flight" -> baseCost + 1500
            mode == "auto_cab" -> baseCost + 50
            else -> baseCost
        }
    }

    private fun formatCurrency(amount: Int): String {
        if (amount >= 1000) return String.format("%,d", amount)
        return amount.toString()
    }

    private fun estimateTime(distance: Int, avgSpeedKmh: Int): String {
        val hours = distance.toDouble() / avgSpeedKmh
        return if (hours < 1) {
            "${(hours * 60).roundToInt()} min"
        } else {
            val h = hours.toInt()
            val m = ((hours - h) * 60).roundToInt()
            if (m > 0) "${h}h ${m}m" else "${h}h"
        }
    }

    data class DayPlan(val day: String, val title: String, val activities: List<String>)
    data class PlaceInfo(val name: String, val description: String)
    data class BudgetEstimate(val stays: Int, val food: Int, val activities: Int)
    data class StudentDeal(
        val provider: String = "",
        val title: String = "",
        val originalPrice: Int = 0,
        val dealPrice: Int = 0,
        val note: String = "",
        val validity: String = "",
        val seatsLeft: Int = 0,
        val bookingUrl: String = "",
        val destination: String = "",
        val studentOnly: Boolean = true,
        val isActive: Boolean = true
    )
}
