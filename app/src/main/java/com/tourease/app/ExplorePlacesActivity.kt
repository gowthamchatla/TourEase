package com.tourease.app

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ExplorePlacesActivity : AppCompatActivity() {

    private var currentCategory = "All"
    private lateinit var destinationsContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore_places)

        val ivBack: ImageView = findViewById(R.id.ivBack)
        ivBack.setOnClickListener { finish() }

        val btnMountains: LinearLayout = findViewById(R.id.btnMountains)
        val btnBeaches: LinearLayout = findViewById(R.id.btnBeaches)
        val btnHeritage: LinearLayout = findViewById(R.id.btnHeritage)
        val btnNature: LinearLayout = findViewById(R.id.btnNature)
        val btnSpiritual: LinearLayout = findViewById(R.id.btnSpiritual)
        val btnAdventure: LinearLayout = findViewById(R.id.btnAdventure)
        val btnFood: LinearLayout = findViewById(R.id.btnFood)
        val btnShopping: LinearLayout = findViewById(R.id.btnShopping)

        btnMountains.setOnClickListener { filterByCategory("Mountains") }
        btnBeaches.setOnClickListener { filterByCategory("Beaches") }
        btnHeritage.setOnClickListener { filterByCategory("Heritage") }
        btnNature.setOnClickListener { filterByCategory("Nature") }
        btnSpiritual.setOnClickListener { filterByCategory("Spiritual") }
        btnAdventure.setOnClickListener { filterByCategory("Adventure") }
        btnFood.setOnClickListener { filterByCategory("Food") }
        btnShopping.setOnClickListener { filterByCategory("Shopping") }

        showAllDestinations()

        // Enhanced Search with Wikipedia fallback
        val etSearch: android.widget.EditText = findViewById(R.id.etSearch)
        etSearch.addTextChangedListener(object : android.text.TextWatcher {
            override fun afterTextChanged(s: android.text.Editable?) {
                val query = s.toString().trim()
                if (query.isEmpty()) {
                    showAllDestinations()
                } else {
                    // First check our 30 destinations
                    val localResults = DestinationsData.searchDestinations(query)
                    if (localResults.isNotEmpty()) {
                        updateDestinationCards(localResults)

                    } else {
                        // Not in our database - search Wikipedia
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
        val container = findViewById<LinearLayout>(R.id.tripsContainer)
        container.removeAllViews()

        destinations.forEach { destination ->
            val card = createDestinationCard(destination)
            container.addView(card)
        }

        container.visibility = android.view.View.VISIBLE
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

        val emoji = android.widget.TextView(this)
        emoji.text = destination.emoji
        emoji.textSize = 64f
        emoji.gravity = android.view.Gravity.CENTER

        val name = android.widget.TextView(this)
        name.text = destination.name
        name.textSize = 22f
        name.setTypeface(null, android.graphics.Typeface.BOLD)
        name.setTextColor(android.graphics.Color.parseColor("#333333"))
        name.setPadding(0, 24, 0, 12)

        val info = android.widget.TextView(this)
        info.text = "⭐ ${destination.rating} · ${destination.emoji} ${destination.category} · 💰 ${destination.priceRange}"
        info.textSize = 14f
        info.setTextColor(android.graphics.Color.parseColor("#666666"))

        layout.addView(emoji)
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

        // For now, show a placeholder
        val container = findViewById<LinearLayout>(R.id.tripsContainer)
        container.removeAllViews()

        val card = createExternalSearchCard(query)
        container.addView(card)

        container.visibility = android.view.View.VISIBLE
    }

    private fun createExternalSearchCard(placeName: String): androidx.cardview.widget.CardView {
        val card = androidx.cardview.widget.CardView(this)
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