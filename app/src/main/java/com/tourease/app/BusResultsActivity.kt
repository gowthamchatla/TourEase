package com.tourease.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tourease.app.models.BusData

class BusResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_results)
        supportActionBar?.hide()

        val from = intent.getStringExtra("FROM") ?: ""
        val to = intent.getStringExtra("TO") ?: ""
        val date = intent.getStringExtra("DATE") ?: ""
        val seatType = intent.getStringExtra("SEAT_TYPE") ?: "Sleeper"

        val ivBack: ImageView = findViewById(R.id.ivBack)
        val tvRoute: TextView = findViewById(R.id.tvRoute)
        val tvDate: TextView = findViewById(R.id.tvDate)
        val tvResultsCount: TextView = findViewById(R.id.tvResultsCount)
        val rvBuses: RecyclerView = findViewById(R.id.rvBuses)

        ivBack.setOnClickListener { finish() }

        tvRoute.text = "$from → $to"
        tvDate.text = date

        val buses = BusData.getBuses(from, to)

        if (buses.isEmpty()) {
            tvResultsCount.text = "No buses found for this route"
            Toast.makeText(
                this,
                "Try: Delhi→Mumbai, Chennai→Bangalore, Mumbai→Goa",
                Toast.LENGTH_LONG
            ).show()
        } else {
            tvResultsCount.text = "${buses.size} buses found"
        }

        rvBuses.layoutManager = LinearLayoutManager(this)
        rvBuses.adapter = BusAdapter(buses, seatType)
    }

    inner class BusAdapter(
        private val buses: List<Bus>,
        private val selectedSeatType: String
    ) : RecyclerView.Adapter<BusAdapter.BusViewHolder>() {

        inner class BusViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvBusName: TextView = view.findViewById(R.id.tvBusName)
            val tvOperatorName: TextView = view.findViewById(R.id.tvOperatorName)
            val tvBusType: TextView = view.findViewById(R.id.tvBusType)
            val tvDepartureTime: TextView = view.findViewById(R.id.tvDepartureTime)
            val tvArrivalTime: TextView = view.findViewById(R.id.tvArrivalTime)
            val tvFromCity: TextView = view.findViewById(R.id.tvFromCity)
            val tvToCity: TextView = view.findViewById(R.id.tvToCity)
            val tvDuration: TextView = view.findViewById(R.id.tvDuration)
            val tvRating: TextView = view.findViewById(R.id.tvRating)
            val tvAmenities: TextView = view.findViewById(R.id.tvAmenities)
            val tvSleeperPrice: TextView = view.findViewById(R.id.tvSleeperPrice)
            val tvSleeperAvail: TextView = view.findViewById(R.id.tvSleeperAvail)
            val tvSemiSleeperPrice: TextView = view.findViewById(R.id.tvSemiSleeperPrice)
            val tvSemiSleeperAvail: TextView = view.findViewById(R.id.tvSemiSleeperAvail)
            val tvSittingPrice: TextView = view.findViewById(R.id.tvSittingPrice)
            val tvSittingAvail: TextView = view.findViewById(R.id.tvSittingAvail)
            val tvBookBus: TextView = view.findViewById(R.id.tvBookBus)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_bus, parent, false)
            return BusViewHolder(view)
        }

        override fun onBindViewHolder(holder: BusViewHolder, position: Int) {
            val bus = buses[position]

            holder.tvBusName.text = bus.busName
            holder.tvOperatorName.text = bus.operatorName
            holder.tvBusType.text = bus.busType
            holder.tvDepartureTime.text = bus.departureTime
            holder.tvArrivalTime.text = bus.arrivalTime
            holder.tvFromCity.text = bus.fromCity
            holder.tvToCity.text = bus.toCity
            holder.tvDuration.text = bus.duration
            holder.tvRating.text = "⭐ ${bus.rating}"
            holder.tvAmenities.text = bus.amenities

            // Sleeper
            if (bus.sleeperPrice > 0) {
                holder.tvSleeperPrice.text = "₹${bus.sleeperPrice}"
                holder.tvSleeperAvail.text = "Avl-${bus.sleeperAvailable}"
            } else {
                holder.tvSleeperPrice.text = "N/A"
                holder.tvSleeperAvail.text = "—"
            }

            // Semi-Sleeper
            if (bus.semiSleeperPrice > 0) {
                holder.tvSemiSleeperPrice.text = "₹${bus.semiSleeperPrice}"
                holder.tvSemiSleeperAvail.text = "Avl-${bus.semiSleeperAvailable}"
            } else {
                holder.tvSemiSleeperPrice.text = "N/A"
                holder.tvSemiSleeperAvail.text = "—"
            }

            // Sitting
            if (bus.sittingPrice > 0) {
                holder.tvSittingPrice.text = "₹${bus.sittingPrice}"
                holder.tvSittingAvail.text = "Avl-${bus.sittingAvailable}"
            } else {
                holder.tvSittingPrice.text = "N/A"
                holder.tvSittingAvail.text = "—"
            }

            // Highlight selected seat type
            val highlightColor = android.graphics.Color.parseColor("#6C63FF")
            val normalColor = android.graphics.Color.parseColor("#2A2A40")

            holder.itemView.findViewById<View>(R.id.cardSleeper)
                .setBackgroundColor(if (selectedSeatType == "Sleeper") highlightColor else normalColor)
            holder.itemView.findViewById<View>(R.id.cardSemiSleeper)
                .setBackgroundColor(if (selectedSeatType == "Semi-Sleeper") highlightColor else normalColor)
            holder.itemView.findViewById<View>(R.id.cardSitting)
                .setBackgroundColor(if (selectedSeatType == "Sitting") highlightColor else normalColor)

            holder.tvBookBus.setOnClickListener {
                Toast.makeText(
                    holder.itemView.context,
                    "Redirecting to ${bus.operatorName} booking...",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        override fun getItemCount() = buses.size
    }
}