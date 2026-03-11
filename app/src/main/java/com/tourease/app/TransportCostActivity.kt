package com.tourease.app

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.roundToInt

class TransportCostActivity : AppCompatActivity() {

    // Hardcoded distances (km) between major Indian cities
    private val distanceMap = mapOf(
        "delhi" to mapOf("goa" to 1867, "manali" to 530, "jaipur" to 281, "udaipur" to 660, "rishikesh" to 230, "varanasi" to 821, "mumbai" to 1400, "chennai" to 2180, "kolkata" to 1530, "bangalore" to 2150, "hyderabad" to 1550, "pune" to 1450, "kochi" to 2640, "pondicherry" to 2200, "coorg" to 2230, "meghalaya" to 1880, "munnar" to 2700, "hampi" to 1900, "alleppey" to 2670, "darjeeling" to 1550, "leh ladakh" to 990, "ooty" to 2550, "kasol" to 510, "mcleodganj" to 480, "gangtok" to 1650, "kodaikanal" to 2500, "andaman" to 3500),
        "mumbai" to mapOf("goa" to 590, "pune" to 150, "jaipur" to 1150, "udaipur" to 770, "delhi" to 1400, "chennai" to 1340, "bangalore" to 980, "hyderabad" to 710, "kolkata" to 2050, "kochi" to 1340, "manali" to 1950, "pondicherry" to 1370, "coorg" to 1050, "varanasi" to 1500, "hampi" to 760, "alleppey" to 1370, "ooty" to 1200, "munnar" to 1400, "kodaikanal" to 1300, "rishikesh" to 1600, "andaman" to 3000),
        "chennai" to mapOf("goa" to 890, "pondicherry" to 150, "bangalore" to 350, "hyderabad" to 630, "mumbai" to 1340, "delhi" to 2180, "kolkata" to 1670, "kochi" to 700, "coorg" to 530, "ooty" to 560, "munnar" to 590, "kodaikanal" to 470, "hampi" to 600, "alleppey" to 730, "madurai" to 460, "rameshwaram" to 570, "andaman" to 1400, "pune" to 1200, "manali" to 2700, "jaipur" to 1900, "udaipur" to 1800, "varanasi" to 1700),
        "bangalore" to mapOf("goa" to 560, "chennai" to 350, "hyderabad" to 570, "mumbai" to 980, "delhi" to 2150, "kochi" to 530, "coorg" to 250, "ooty" to 270, "munnar" to 480, "kodaikanal" to 470, "hampi" to 340, "alleppey" to 570, "pondicherry" to 310, "pune" to 840, "mysore" to 150, "andaman" to 2800),
        "kolkata" to mapOf("delhi" to 1530, "mumbai" to 2050, "chennai" to 1670, "darjeeling" to 600, "gangtok" to 560, "meghalaya" to 680, "varanasi" to 680, "goa" to 1860, "bangalore" to 1870, "hyderabad" to 1490, "andaman" to 1650),
        "hyderabad" to mapOf("goa" to 660, "chennai" to 630, "bangalore" to 570, "mumbai" to 710, "delhi" to 1550, "kolkata" to 1490, "kochi" to 1100, "hampi" to 370, "pune" to 560, "varanasi" to 1250)
    )

    // Per km rates in INR
    private val rates = mapOf(
        "train_sleeper" to 0.65,
        "train_ac3" to 1.35,
        "train_ac2" to 1.95,
        "bus_govt" to 1.20,
        "bus_private" to 1.80,
        "flight" to 4.50,
        "auto_cab" to 14.0
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transport_cost)

        val origin = intent.getStringExtra("origin") ?: ""
        val destination = intent.getStringExtra("destination") ?: ""
        val travelers = intent.getStringExtra("travelers") ?: "Solo"

        val ivBack: ImageView = findViewById(R.id.ivBack)
        ivBack.setOnClickListener { finish() }

        val tvRoute: TextView = findViewById(R.id.tvRoute)
        if (origin.isNotEmpty()) {
            tvRoute.text = "${origin.capitalize()} → ${destination.capitalize()}"
        } else {
            tvRoute.text = "📍 ${destination.capitalize()}"
        }

        val distance = getDistance(origin.lowercase().trim(), destination.lowercase().trim())

        val tvDistance: TextView = findViewById(R.id.tvDistance)
        tvDistance.text = if (distance > 0) "$distance km" else "Distance unavailable"

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

        // Best value recommendation
        val tvBestValue: TextView = findViewById(R.id.tvBestValue)
        if (distance > 0) {
            val recommendation = when {
                distance < 100 -> "🛺 Short trip — Auto/Cab is your best bet!"
                distance < 400 -> "🚌 Bus is the sweet spot for this distance"
                distance < 1200 -> "🚂 Train (AC 3-tier) — best value for money"
                else -> "✈️ Long haul — consider a flight to save time"
            }
            tvBestValue.text = recommendation
        } else {
            tvBestValue.text = "Add more cities coming soon! Try major Indian cities."
        }
    }

    private fun getDistance(origin: String, destination: String): Int {
        // Direct lookup
        distanceMap[origin]?.get(destination)?.let { return it }
        // Reverse lookup
        distanceMap[destination]?.get(origin)?.let { return it }
        return 0
    }

    private fun calcCost(distance: Int, mode: String): String {
        val rate = rates[mode] ?: return "N/A"
        val baseCost = (distance * rate).roundToInt()
        // Add base charges
        val finalCost = when {
            mode.startsWith("train") -> baseCost + 250
            mode.startsWith("bus") -> baseCost + 100
            mode == "flight" -> baseCost + 1500
            mode == "auto_cab" -> baseCost + 50
            else -> baseCost
        }
        return formatCurrency(finalCost)
    }

    private fun formatCurrency(amount: Int): String {
        if (amount >= 1000) {
            val thousands = amount / 1000
            val hundreds = (amount % 1000) / 100
            return if (hundreds > 0) "${thousands},${String.format("%03d", amount % 1000)}"
            else "${thousands},000"
        }
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
}