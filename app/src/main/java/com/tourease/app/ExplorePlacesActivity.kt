package com.tourease.app

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class ExplorePlacesActivity : AppCompatActivity() {

    private var currentCategory = "All"
    private lateinit var destinationsContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore_places)

        val ivBack: ImageView = findViewById(R.id.ivBack)
        ivBack.setOnClickListener { finish() }

        val cardNearbyPlaces: androidx.cardview.widget.CardView = findViewById(R.id.cardNearbyPlaces)
        cardNearbyPlaces.setOnClickListener {
            startActivity(Intent(this, NearbyPlacesActivity::class.java))
        }

        val btnMountains: LinearLayout = findViewById(R.id.catMountains)
        val btnBeaches: LinearLayout = findViewById(R.id.catBeaches)
        val btnHeritage: LinearLayout = findViewById(R.id.catHeritage)
        val btnNature: LinearLayout = findViewById(R.id.catNature)
        val btnSpiritual: LinearLayout = findViewById(R.id.catSpiritual)
        val btnAdventure: LinearLayout = findViewById(R.id.catAdventure)
        val btnFood: LinearLayout = findViewById(R.id.catFood)
        val btnShopping: LinearLayout = findViewById(R.id.catShopping)

        btnMountains.setOnClickListener { filterByCategory("Mountains") }
        btnBeaches.setOnClickListener { filterByCategory("Beaches") }
        btnHeritage.setOnClickListener { filterByCategory("Heritage") }
        btnNature.setOnClickListener { filterByCategory("Nature") }
        btnSpiritual.setOnClickListener { filterByCategory("Spiritual") }
        btnAdventure.setOnClickListener { filterByCategory("Adventure") }
        btnFood.setOnClickListener { filterByCategory("Food") }
        btnShopping.setOnClickListener { filterByCategory("Shopping") }

        destinationsContainer = findViewById(R.id.destinationsContainer)

        showAllDestinations()

        val etSearch: android.widget.EditText = findViewById(R.id.etSearch)
        etSearch.addTextChangedListener(object : android.text.TextWatcher {
            override fun afterTextChanged(s: android.text.Editable?) {
                val query = s.toString().trim()
                if (query.isEmpty()) {
                    showAllDestinations()
                } else {
                    val localResults = DestinationsData.searchDestinations(query)
                    if (localResults.isNotEmpty()) {
                        updateDestinationCards(localResults)
                    } else {
                        searchWikipedia(query)
                    }
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun filterByCategory(category: String) {
        currentCategory = category
        val destinations = DestinationsData.getByCategory(category)
        updateDestinationCards(destinations)
    }

    private fun showAllDestinations() {
        val destinations = DestinationsData.getAllDestinations()
        updateDestinationCards(destinations)
    }

    private fun updateDestinationCards(destinations: List<Destination>) {
        destinationsContainer.removeAllViews()
        destinations.forEach { destination ->
            val card = createDestinationCard(destination)
            destinationsContainer.addView(card)
        }
        destinationsContainer.visibility = android.view.View.VISIBLE
    }

    private fun createDestinationCard(destination: Destination): CardView {
        val card = CardView(this)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 0, 32)
        card.layoutParams = params
        card.radius = 40f
        card.cardElevation = 12f

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(40, 40, 40, 40)

        // ✅ Real photo ImageView instead of emoji TextView
        val imageView = ImageView(this)
        val imageParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 500
        )
        imageParams.setMargins(0, 0, 0, 24)
        imageView.layoutParams = imageParams
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.clipToOutline = true
        val shape = android.graphics.drawable.GradientDrawable()
        shape.cornerRadius = 24f
        imageView.background = shape

        com.bumptech.glide.Glide.with(this)
            .load(destination.imageUrl)
            .placeholder(R.drawable.profile_cover_default_bg)
            .centerCrop()
            .into(imageView)

        val name = android.widget.TextView(this)
        name.text = destination.name
        name.textSize = 22f
        name.setTypeface(null, android.graphics.Typeface.BOLD)
        name.setTextColor(android.graphics.Color.parseColor("#333333"))
        name.setPadding(0, 0, 0, 12)

        val info = android.widget.TextView(this)
        info.text = "⭐ ${destination.rating} · ${destination.emoji} ${destination.category} · 💰 ${destination.priceRange}"
        info.textSize = 14f
        info.setTextColor(android.graphics.Color.parseColor("#666666"))

        layout.addView(imageView)
        layout.addView(name)
        layout.addView(info)
        card.addView(layout)

        card.setOnClickListener {
            val intent = Intent(this, DestinationDetailActivity::class.java)
            intent.putExtra("DESTINATION_ID", destination.id)
            startActivity(intent)
        }

        return card
    }

    private fun searchWikipedia(query: String) {
        destinationsContainer.removeAllViews()
        val card = createExternalSearchCard(query)
        destinationsContainer.addView(card)
        destinationsContainer.visibility = android.view.View.VISIBLE
    }

    private fun createExternalSearchCard(placeName: String): CardView {
        val card = CardView(this)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 0, 32)
        card.layoutParams = params
        card.radius = 40f
        card.cardElevation = 12f
        card.setCardBackgroundColor(android.graphics.Color.parseColor("#FFF3E0"))

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(40, 40, 40, 40)

        val emoji = android.widget.TextView(this)
        emoji.text = "🌍"
        emoji.textSize = 64f
        emoji.gravity = android.view.Gravity.CENTER

        val name = android.widget.TextView(this)
        name.text = placeName
        name.textSize = 22f
        name.setTypeface(null, android.graphics.Typeface.BOLD)
        name.setTextColor(android.graphics.Color.parseColor("#333333"))
        name.setPadding(0, 24, 0, 12)

        val info = android.widget.TextView(this)
        info.text = "ℹ️ Limited info available\n\nThis destination is not in our featured list yet.\n\nWe're working on adding more places!"
        info.textSize = 14f
        info.setTextColor(android.graphics.Color.parseColor("#666666"))

        val button = android.widget.Button(this)
        button.text = "Search on Google"
        button.setBackgroundColor(android.graphics.Color.parseColor("#FF6B35"))
        button.setTextColor(android.graphics.Color.WHITE)
        button.setPadding(40, 30, 40, 30)
        val btnParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        btnParams.setMargins(0, 24, 0, 0)
        button.layoutParams = btnParams

        button.setOnClickListener {
            val intent = android.content.Intent(android.content.Intent.ACTION_VIEW)
            intent.data = android.net.Uri.parse("https://www.google.com/search?q=$placeName+travel+guide")
            startActivity(intent)
        }

        layout.addView(emoji)
        layout.addView(name)
        layout.addView(info)
        layout.addView(button)
        card.addView(layout)

        return card
    }
}