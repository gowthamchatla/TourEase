package com.tourease.app

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tourease.app.models.TrainData

class TrainResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_train_results)
        supportActionBar?.hide()

        val from = intent.getStringExtra("FROM") ?: ""
        val to = intent.getStringExtra("TO") ?: ""
        val date = intent.getStringExtra("DATE") ?: ""
        val trainClass = intent.getStringExtra("CLASS") ?: "SL"
        val isLive = intent.getBooleanExtra("IS_LIVE", false)

        val ivBack: ImageView = findViewById(R.id.ivBack)
        val tvRoute: TextView = findViewById(R.id.tvRoute)
        val tvDate: TextView = findViewById(R.id.tvDate)
        val tvResultsCount: TextView = findViewById(R.id.tvResultsCount)
        val tvLiveBadge: TextView = findViewById(R.id.tvLiveBadge)
        val rvTrains: RecyclerView = findViewById(R.id.rvTrains)

        ivBack.setOnClickListener { finish() }

        tvRoute.text = "$from → $to"
        tvDate.text = date

        val trains: List<Train>

        if (isLive) {
            @Suppress("DEPRECATION")
            val passedTrains = intent.getSerializableExtra("trains") as? ArrayList<Train>
            trains = passedTrains ?: emptyList()
            tvLiveBadge.visibility = View.VISIBLE
        } else {
            trains = TrainData.getTrains(from, to)
            tvLiveBadge.visibility = View.GONE
        }

        if (trains.isEmpty()) {
            tvResultsCount.text = "No trains found for this route"
            Toast.makeText(
                this,
                "Try popular routes like:\nDelhi→Mumbai, Chennai→Bangalore",
                Toast.LENGTH_LONG
            ).show()
        } else {
            val source = if (isLive) "live" else "cached"
            tvResultsCount.text = "${trains.size} trains found ($source)"
        }

        rvTrains.layoutManager = LinearLayoutManager(this)
        rvTrains.adapter = TrainAdapter(trains, isLive)
    }

    inner class TrainAdapter(
        private val trains: List<Train>,
        private val isLive: Boolean
    ) : RecyclerView.Adapter<TrainAdapter.TrainViewHolder>() {

        inner class TrainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvTrainNumber: TextView = view.findViewById(R.id.tvTrainNumber)
            val tvTrainName: TextView = view.findViewById(R.id.tvTrainName)
            val tvDepartureTime: TextView = view.findViewById(R.id.tvDepartureTime)
            val tvArrivalTime: TextView = view.findViewById(R.id.tvArrivalTime)
            val tvFromStation: TextView = view.findViewById(R.id.tvFromStation)
            val tvToStation: TextView = view.findViewById(R.id.tvToStation)
            val tvDuration: TextView = view.findViewById(R.id.tvDuration)
            val tvSleeperPrice: TextView = view.findViewById(R.id.tvSleeperPrice)
            val tvSleeperAvail: TextView = view.findViewById(R.id.tvSleeperAvail)
            val tv3ACPrice: TextView = view.findViewById(R.id.tv3ACPrice)
            val tv3ACAvail: TextView = view.findViewById(R.id.tv3ACAvail)
            val tv2ACPrice: TextView = view.findViewById(R.id.tv2ACPrice)
            val tv2ACAvail: TextView = view.findViewById(R.id.tv2ACAvail)
            val btnBookTrain: Button = view.findViewById(R.id.btnBookTrain)
            val btnCheckSeats: Button = view.findViewById(R.id.btnCheckSeats)
            val btnLiveTrack: Button = view.findViewById(R.id.btnLiveTrack)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_train, parent, false)
            return TrainViewHolder(view)
        }

        override fun onBindViewHolder(holder: TrainViewHolder, position: Int) {
            val train = trains[position]

            holder.tvTrainNumber.text = train.trainNumber
            holder.tvTrainName.text = train.trainName
            holder.tvDepartureTime.text = train.departureTime
            holder.tvArrivalTime.text = train.arrivalTime
            holder.tvFromStation.text = train.fromStation
            holder.tvToStation.text = train.toStation
            holder.tvDuration.text = train.duration

            if (isLive && train.sleeperPrice == 0) {
                holder.tvSleeperPrice.text = "—"
                holder.tvSleeperAvail.text = "Tap Fare"
                holder.tv3ACPrice.text = "—"
                holder.tv3ACAvail.text = "Tap Fare"
                holder.tv2ACPrice.text = "—"
                holder.tv2ACAvail.text = "Tap Fare"
            } else {
                holder.tvSleeperPrice.text = "₹${train.sleeperPrice}"
                holder.tvSleeperAvail.text = "Avl-${train.sleeperAvailable}"
                holder.tv3ACPrice.text = "₹${train.ac3Price}"
                holder.tv3ACAvail.text = "Avl-${train.ac3Available}"
                holder.tv2ACPrice.text = "₹${train.ac2Price}"
                holder.tv2ACAvail.text = "Avl-${train.ac2Available}"
            }

            // View Fare button
            holder.btnBookTrain.setOnClickListener {
                val intent = Intent(this@TrainResultsActivity, FareDetailActivity::class.java)
                intent.putExtra("TRAIN_NO", train.trainNumber)
                intent.putExtra("TRAIN_NAME", train.trainName)
                intent.putExtra("FROM_CODE", train.fromStation)
                intent.putExtra("TO_CODE", train.toStation)
                startActivity(intent)
            }

            // Check Seats button
            holder.btnCheckSeats.setOnClickListener {
                val intent = Intent(this@TrainResultsActivity, SeatAvailabilityActivity::class.java)
                intent.putExtra("TRAIN_NO", train.trainNumber)
                intent.putExtra("TRAIN_NAME", train.trainName)
                startActivity(intent)
            }

            // Live Track button
            holder.btnLiveTrack.setOnClickListener {
                val intent = Intent(this@TrainResultsActivity, LiveStatusActivity::class.java)
                intent.putExtra("TRAIN_NO", train.trainNumber)
                startActivity(intent)
            }
        }

        override fun getItemCount() = trains.size
    }
}
