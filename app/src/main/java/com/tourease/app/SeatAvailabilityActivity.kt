package com.tourease.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tourease.app.models.MockTrainData
import com.tourease.app.models.SeatAvailability

class SeatAvailabilityActivity : AppCompatActivity() {

    private lateinit var rvAvailability: RecyclerView
    private var selectedClassType = "SL"
    private var trainNo = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_availability)
        supportActionBar?.hide()

        trainNo = intent.getStringExtra("TRAIN_NO") ?: "12952"
        val trainName = intent.getStringExtra("TRAIN_NAME") ?: "Rajdhani Express"

        val ivBack: ImageView = findViewById(R.id.ivBack)
        val tvTrainName: TextView = findViewById(R.id.tvTrainName)
        val tvTrainInfo: TextView = findViewById(R.id.tvTrainInfo)
        rvAvailability = findViewById(R.id.rvAvailability)

        ivBack.setOnClickListener { finish() }
        tvTrainName.text = trainName
        tvTrainInfo.text = "#$trainNo • Seat Availability"

        rvAvailability.layoutManager = LinearLayoutManager(this)

        // Class buttons
        val btnSL: Button = findViewById(R.id.btnClassSL)
        val btn3A: Button = findViewById(R.id.btnClass3A)
        val btn2A: Button = findViewById(R.id.btnClass2A)
        val btn1A: Button = findViewById(R.id.btnClass1A)
        val btn2S: Button = findViewById(R.id.btnClass2S)

        val allButtons = listOf(btnSL, btn3A, btn2A, btn1A, btn2S)
        val classTypes = listOf("SL", "3A", "2A", "1A", "2S")

        for (i in allButtons.indices) {
            allButtons[i].setOnClickListener {
                selectedClassType = classTypes[i]
                updateButtonStyles(allButtons, i)
                loadAvailability()
            }
        }

        // Load default
        loadAvailability()
    }

    private fun updateButtonStyles(buttons: List<Button>, selectedIndex: Int) {
        for (i in buttons.indices) {
            if (i == selectedIndex) {
                buttons[i].backgroundTintList = android.content.res.ColorStateList.valueOf(
                    android.graphics.Color.parseColor("#6C63FF")
                )
                buttons[i].setTextColor(android.graphics.Color.WHITE)
            } else {
                buttons[i].backgroundTintList = android.content.res.ColorStateList.valueOf(
                    android.graphics.Color.parseColor("#2A2A40")
                )
                buttons[i].setTextColor(android.graphics.Color.parseColor("#9999AA"))
            }
        }
    }

    private fun loadAvailability() {
        val response = MockTrainData.getMockSeatAvailability(trainNo, selectedClassType)
        val data = response.data ?: emptyList()
        rvAvailability.adapter = AvailabilityAdapter(data)
    }

    inner class AvailabilityAdapter(private val items: List<SeatAvailability>) :
        RecyclerView.Adapter<AvailabilityAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvDate: TextView = view.findViewById(R.id.tvAvailDate)
            val tvDay: TextView = view.findViewById(R.id.tvAvailDay)
            val tvStatus: TextView = view.findViewById(R.id.tvAvailStatus)
            val tvProbability: TextView = view.findViewById(R.id.tvProbability)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_availability, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]

            // Parse date for display
            try {
                val parts = item.date.split("-")
                val months = arrayOf("", "Jan", "Feb", "Mar", "Apr", "May", "Jun",
                    "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
                val day = parts[0]
                val month = months[parts[1].toInt()]
                holder.tvDate.text = "$day-$month"

                val days = arrayOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
                holder.tvDay.text = days[position % 7]
            } catch (e: Exception) {
                holder.tvDate.text = item.date
                holder.tvDay.text = ""
            }

            holder.tvStatus.text = item.status

            // Color based on status
            val statusColor = when {
                item.status.startsWith("AVL") -> "#00E676"
                item.status.startsWith("RAC") -> "#FF9800"
                item.status.startsWith("WL") -> "#FF5252"
                else -> "#FF5252"
            }
            holder.tvStatus.setTextColor(android.graphics.Color.parseColor(statusColor))

            holder.tvProbability.text = item.confirmProbabilityPercent ?: "-"
        }

        override fun getItemCount() = items.size
    }
}


