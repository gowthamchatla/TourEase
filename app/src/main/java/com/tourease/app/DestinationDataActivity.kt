package com.tourease.app

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class DestinationDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_detail)

        val statusBarSpacer: android.view.View = findViewById(R.id.statusBarSpacer)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            statusBarSpacer.layoutParams.height = systemBars.top
            statusBarSpacer.requestLayout()
            insets
        }

        val destinationId = intent.getIntExtra("DESTINATION_ID", -1)
        val destination = DestinationsData.getAllDestinations().find { it.id == destinationId }

        if (destination == null) { finish(); return }

        val ivHero: ImageView = findViewById(R.id.ivHero)
        val ivBack: ImageView = findViewById(R.id.ivBack)
        val tvName: TextView = findViewById(R.id.tvDestinationName)
        val tvEmoji: TextView = findViewById(R.id.tvEmoji)
        val tvDescription: TextView = findViewById(R.id.tvDescription)
        val tvRating: TextView = findViewById(R.id.tvRating)
        val tvPrice: TextView = findViewById(R.id.tvPrice)
        val tvDays: TextView = findViewById(R.id.tvDays)
        val tvBestTime: TextView = findViewById(R.id.tvBestTime)
        val tvEntryFee: TextView = findViewById(R.id.tvEntryFee)
        val tvHighlights: TextView = findViewById(R.id.tvHighlights)
        val tvHowToReach: TextView = findViewById(R.id.tvHowToReach)
        val tvTips: TextView = findViewById(R.id.tvTips)
        val spotsContainer: LinearLayout = findViewById(R.id.spotsContainer)
        val btnPlanTrip: Button = findViewById(R.id.btnPlanTripHere)

        if (destination.drawableRes != 0) {
            ivHero.setImageResource(destination.drawableRes)
        } else {
            Glide.with(this).load(destination.imageUrl)
                .placeholder(R.drawable.profile_cover_default_bg)
                .centerCrop().into(ivHero)
        }

        tvName.text = destination.name
        tvEmoji.text = destination.emoji
        tvDescription.text = destination.description
        tvRating.text = "⭐ ${destination.rating}"
        tvPrice.text = "💰 ${destination.priceRange}"
        tvDays.text = "📅 ${destination.daysNeeded}"
        tvBestTime.text = destination.bestTime
        tvEntryFee.text = destination.entryFee
        tvHighlights.text = destination.highlights.joinToString("\n") { "• $it" }
        tvHowToReach.text = destination.howToReach.joinToString("\n\n")
        tvTips.text = destination.tips.joinToString("\n\n") { "💡 $it" }

        // Build clickable trending spot cards
        spotsContainer.removeAllViews()
        destination.trendingSpots.forEachIndexed { index, spot ->
            val card = androidx.cardview.widget.CardView(this)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 0, 0, 16)
            card.layoutParams = params
            card.radius = 24f
            card.cardElevation = 8f
            card.setCardBackgroundColor(android.graphics.Color.parseColor("#1A1A2E"))

            val row = LinearLayout(this)
            row.orientation = LinearLayout.HORIZONTAL
            row.setPadding(32, 28, 32, 28)
            row.gravity = android.view.Gravity.CENTER_VERTICAL

            val ivSpot = ImageView(this)
            val ivParams = LinearLayout.LayoutParams(100, 100)
            ivParams.setMargins(0, 0, 24, 0)
            ivSpot.layoutParams = ivParams
            ivSpot.scaleType = ImageView.ScaleType.CENTER_CROP
            ivSpot.clipToOutline = true
            val shape = android.graphics.drawable.GradientDrawable()
            shape.cornerRadius = 16f
            ivSpot.background = shape

            if (spot.imageUrls.isNotEmpty()) {
                Glide.with(this).load(spot.imageUrls[0])
                    .placeholder(R.drawable.profile_cover_default_bg)
                    .centerCrop().into(ivSpot)
            }

            val textCol = LinearLayout(this)
            textCol.orientation = LinearLayout.VERTICAL
            textCol.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)

            val tvNum = TextView(this)
            tvNum.text = "${index + 1}. ${spot.name}"
            tvNum.textSize = 15f
            tvNum.setTypeface(null, android.graphics.Typeface.BOLD)
            tvNum.setTextColor(android.graphics.Color.WHITE)

            val tvDesc = TextView(this)
            tvDesc.text = spot.description.take(70) + "..."
            tvDesc.textSize = 12f
            tvDesc.setTextColor(android.graphics.Color.parseColor("#9B9BB4"))
            tvDesc.setPadding(0, 6, 0, 0)

            val tvFee = TextView(this)
            tvFee.text = "🎟 ${spot.entryFee}"
            tvFee.textSize = 11f
            tvFee.setTextColor(android.graphics.Color.parseColor("#FF6B35"))
            tvFee.setPadding(0, 4, 0, 0)

            textCol.addView(tvNum)
            textCol.addView(tvDesc)
            textCol.addView(tvFee)

            val tvArrow = TextView(this)
            tvArrow.text = " ›"
            tvArrow.textSize = 22f
            tvArrow.setTextColor(android.graphics.Color.parseColor("#6C63FF"))

            row.addView(ivSpot)
            row.addView(textCol)
            row.addView(tvArrow)
            card.addView(row)

            card.setOnClickListener {
                val intent = Intent(this, SpotDetailActivity::class.java)
                intent.putExtra("SPOT_NAME", spot.name)
                intent.putExtra("SPOT_DESCRIPTION", spot.description)
                intent.putExtra("SPOT_BEST_TIME", spot.bestTime)
                intent.putExtra("SPOT_ENTRY_FEE", spot.entryFee)
                intent.putStringArrayListExtra("SPOT_TIPS", ArrayList(spot.tips))
                intent.putStringArrayListExtra("SPOT_IMAGES", ArrayList(spot.imageUrls))
                startActivity(intent)
            }

            spotsContainer.addView(card)
        }

        ivBack.setOnClickListener { finish() }

        btnPlanTrip.setOnClickListener {
            val intent = Intent(this, PlanTripActivity::class.java)
            intent.putExtra("DESTINATION_NAME", destination.name)
            startActivity(intent)
        }
    }
}