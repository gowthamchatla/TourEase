package com.tourease.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tourease.app.models.FareClass
import com.tourease.app.models.MockTrainData

class FareDetailActivity : AppCompatActivity() {

    private lateinit var rvFares: RecyclerView
    private lateinit var btnGeneral: Button
    private lateinit var btnTatkal: Button

    private var generalFares: List<FareClass> = emptyList()
    private var tatkalFares: List<FareClass> = emptyList()
    private var showingGeneral = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fare_detail)
        supportActionBar?.hide()

        val trainNo = intent.getStringExtra("TRAIN_NO") ?: "12952"
        val trainName = intent.getStringExtra("TRAIN_NAME") ?: "Rajdhani Express"
        val fromCode = intent.getStringExtra("FROM_CODE") ?: "NDLS"
        val toCode = intent.getStringExtra("TO_CODE") ?: "MMCT"

        val ivBack: ImageView = findViewById(R.id.ivBack)
        val tvTrainName: TextView = findViewById(R.id.tvTrainName)
        val tvTrainNumber: TextView = findViewById(R.id.tvTrainNumber)
        rvFares = findViewById(R.id.rvFares)
        btnGeneral = findViewById(R.id.btnGeneral)
        btnTatkal = findViewById(R.id.btnTatkal)

        ivBack.setOnClickListener { finish() }
        tvTrainName.text = trainName
        tvTrainNumber.text = "#$trainNo • $fromCode → $toCode"

        // Get mock fare data
        val fareResponse = MockTrainData.getMockFare(trainNo, fromCode, toCode)
        generalFares = fareResponse.data?.general ?: emptyList()
        tatkalFares = fareResponse.data?.tatkal ?: emptyList()

        rvFares.layoutManager = LinearLayoutManager(this)
        showFares(generalFares)

        btnGeneral.setOnClickListener {
            showingGeneral = true
            btnGeneral.backgroundTintList = android.content.res.ColorStateList.valueOf(
                android.graphics.Color.parseColor("#6C63FF")
            )
            btnGeneral.setTextColor(android.graphics.Color.WHITE)
            btnTatkal.backgroundTintList = android.content.res.ColorStateList.valueOf(
                android.graphics.Color.parseColor("#2A2A40")
            )
            btnTatkal.setTextColor(android.graphics.Color.parseColor("#9999AA"))
            showFares(generalFares)
        }

        btnTatkal.setOnClickListener {
            showingGeneral = false
            btnTatkal.backgroundTintList = android.content.res.ColorStateList.valueOf(
                android.graphics.Color.parseColor("#FF5722")
            )
            btnTatkal.setTextColor(android.graphics.Color.WHITE)
            btnGeneral.backgroundTintList = android.content.res.ColorStateList.valueOf(
                android.graphics.Color.parseColor("#2A2A40")
            )
            btnGeneral.setTextColor(android.graphics.Color.parseColor("#9999AA"))
            showFares(tatkalFares)
        }
    }

    private fun showFares(fares: List<FareClass>) {
        rvFares.adapter = FareAdapter(fares)
    }

    inner class FareAdapter(private val fares: List<FareClass>) :
        RecyclerView.Adapter<FareAdapter.FareViewHolder>() {

        inner class FareViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvClassType: TextView = view.findViewById(R.id.tvClassType)
            val tvTotalFare: TextView = view.findViewById(R.id.tvTotalFare)
            val llBreakdown: LinearLayout = view.findViewById(R.id.llBreakdown)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FareViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_fare, parent, false)
            return FareViewHolder(view)
        }

        override fun onBindViewHolder(holder: FareViewHolder, position: Int) {
            val fare = fares[position]

            val classNames = mapOf(
                "SL" to "Sleeper", "3A" to "3rd AC", "2A" to "2nd AC",
                "1A" to "1st AC", "2S" to "Second Sitting", "CC" to "Chair Car",
                "3E" to "3rd Economy"
            )

            holder.tvClassType.text = classNames[fare.classType] ?: fare.classType
            holder.tvTotalFare.text = "₹${fare.fare}"

            // Add breakdown rows
            holder.llBreakdown.removeAllViews()
            for (item in fare.breakup) {
                if (item.key == "total") continue // Skip total, already shown

                val row = LinearLayout(holder.itemView.context).apply {
                    orientation = LinearLayout.HORIZONTAL
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply { bottomMargin = 8 }
                }

                val titleTv = TextView(holder.itemView.context).apply {
                    text = item.title
                    textSize = 13f
                    setTextColor(android.graphics.Color.parseColor("#7C7C99"))
                    layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
                }

                val costTv = TextView(holder.itemView.context).apply {
                    text = "₹${item.cost}"
                    textSize = 13f
                    setTextColor(android.graphics.Color.parseColor("#CCCCDD"))
                }

                row.addView(titleTv)
                row.addView(costTv)
                holder.llBreakdown.addView(row)
            }
        }

        override fun getItemCount() = fares.size
    }
}

