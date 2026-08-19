package com.tourease.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Profile pic click
        val ivProfile = findViewById<ImageView>(R.id.ivProfile)
        val cardProfile = findViewById<CardView>(R.id.cardProfile)
        cardProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        ivProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        // Load username
        loadUserData()

        // Bottom Navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_activity -> {
                    startActivity(Intent(this, SocialFeedActivity::class.java))
                    true
                }
                R.id.nav_ai -> {
                    startActivity(Intent(this, AiOgActivity::class.java))
                    true
                }
                R.id.nav_saved -> {
                    startActivity(Intent(this, SavedPlacesActivity::class.java))
                    true
                }
                R.id.nav_account -> {
                    startActivity(Intent(this, AccountActivity::class.java))
                    true
                }


                else -> false
            }
        }

        // Quick Access
        findViewById<LinearLayout>(R.id.btnTrain).setOnClickListener {
            startActivity(Intent(this, TrainBookingActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.btnFlight).setOnClickListener {
            startActivity(Intent(this, FlightBookingActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.btnBus).setOnClickListener {
            startActivity(Intent(this, BusBookingActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.btnStays).setOnClickListener {
            startActivity(Intent(this, StaysActivity::class.java))
        }

        // Feature Cards
        findViewById<CardView>(R.id.cardExplore).setOnClickListener {
            startActivity(Intent(this, ExplorePlacesActivity::class.java))
        }
        findViewById<CardView>(R.id.cardPlanTrip).setOnClickListener {
            startActivity(Intent(this, PlanTripActivity::class.java))
        }
        findViewById<CardView>(R.id.cardMyTrips).setOnClickListener {
            startActivity(Intent(this, MyTripsActivity::class.java))
        }
        findViewById<CardView>(R.id.cardStudent).setOnClickListener {
            startActivity(Intent(this, StudentSaverActivity::class.java))
        }

        // Trending banner
        findViewById<View>(R.id.btnTrendingPlan).setOnClickListener {
            val intent = Intent(this, PlanTripActivity::class.java)
            startActivity(intent)
        }

        // TourTalk banner
        findViewById<LinearLayout>(R.id.bannerTourTalk).setOnClickListener {
            startActivity(Intent(this, SocialFeedActivity::class.java))
        }

        // Search
        val ivSearch: CardView = findViewById(R.id.ivSearch)
        val searchBarCard: CardView = findViewById(R.id.searchBarCard)
        val etSearchBar: EditText = findViewById(R.id.etSearchBar)
        val ivCloseSearch: ImageView = findViewById(R.id.ivCloseSearch)

        ivSearch.setOnClickListener {
            searchBarCard.visibility = View.VISIBLE
            etSearchBar.requestFocus()
        }

        ivCloseSearch.setOnClickListener {
            searchBarCard.visibility = View.GONE
            etSearchBar.text.clear()
        }

        etSearchBar.addTextChangedListener(object : android.text.TextWatcher {
            override fun afterTextChanged(s: android.text.Editable?) {
                val query = s.toString()
                if (query.isNotEmpty()) {
                    val intent = Intent(this@HomeActivity, ExplorePlacesActivity::class.java)
                    intent.putExtra("SEARCH_QUERY", query)
                    startActivity(intent)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Notification
        val ivNotification: CardView = findViewById(R.id.ivNotification)
        ivNotification.setOnClickListener {
            startActivity(Intent(this, NotificationsActivity::class.java))
        }
    }

    private fun loadUserData() {
        val userId = auth.currentUser?.uid ?: return

        firestore.collection("users").document(userId)
            .get()
            .addOnSuccessListener { doc ->
                if (doc.exists()) {
                    val username = doc.getString("username") ?: "Explorer"
                    findViewById<TextView>(R.id.tvUsername).text = username

                    // Set greeting based on time
                    val hour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
                    val greeting = when {
                        hour < 12 -> "Good morning 🌅"
                        hour < 17 -> "Good afternoon ☀️"
                        hour < 21 -> "Good evening 🌇"
                        else -> "Night owl? 🦉"
                    }
                    findViewById<TextView>(R.id.tvGreeting).text = greeting

                    // Profile picture with Glide
                    val photoUrl = doc.getString("photoUrl")
                    if (!photoUrl.isNullOrEmpty() && !isDestroyed && !isFinishing) {
                        Glide.with(this@HomeActivity)
                            .load(photoUrl)
                            .placeholder(android.R.drawable.sym_def_app_icon)
                            .circleCrop()
                            .into(findViewById(R.id.ivProfile))
                    }
                }
            }
    }

    override fun onResume() {
        super.onResume()
        loadUserData()
    }
}