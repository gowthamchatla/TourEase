package com.tourease.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AccountActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        findViewById<ImageView>(R.id.ivBack).setOnClickListener { finish() }

        loadUserInfo()

        // Profile card → open profile
        findViewById<LinearLayout>(R.id.layoutProfileCard).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        // My Bookings
        findViewById<LinearLayout>(R.id.optionBookings).setOnClickListener {
            Toast.makeText(this, "My Bookings coming soon!", Toast.LENGTH_SHORT).show()
        }

        // Notifications
        findViewById<LinearLayout>(R.id.optionNotifications).setOnClickListener {
            startActivity(Intent(this, NotificationsActivity::class.java))
        }

        // Travel Stats
        findViewById<LinearLayout>(R.id.optionTravelStats).setOnClickListener {
            showTravelStatsDialog()
        }

        // Invite Friends
        findViewById<LinearLayout>(R.id.optionInvite).setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "TourEase - Travel Smart!")
            shareIntent.putExtra(Intent.EXTRA_TEXT,
                "Hey! Check out TourEase - plan trips, find cheap transport, and connect with fellow travelers!\n\nDownload now: https://tourease.app")
            startActivity(Intent.createChooser(shareIntent, "Invite via"))
        }

        // Rate the App
        findViewById<LinearLayout>(R.id.optionRate).setOnClickListener {
            Toast.makeText(this, "Thanks for your support! Rating will be available on Play Store launch.", Toast.LENGTH_LONG).show()
        }

        // Help & Support
        findViewById<LinearLayout>(R.id.optionHelp).setOnClickListener {
            showHelpDialog()
        }

        // About TourEase
        findViewById<LinearLayout>(R.id.optionAbout).setOnClickListener {
            showAboutDialog()
        }

        // Logout
        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            showLogoutDialog()
        }
    }

    override fun onResume() {
        super.onResume()
        loadUserInfo()
    }

    private fun loadUserInfo() {
        val userId = auth.currentUser?.uid ?: return
        val email = auth.currentUser?.email ?: ""

        findViewById<TextView>(R.id.tvEmail).text = email

        firestore.collection("users").document(userId)
            .get()
            .addOnSuccessListener { doc ->
                if (doc.exists() && !isDestroyed && !isFinishing) {
                    findViewById<TextView>(R.id.tvUsername).text =
                        doc.getString("username") ?: "User"

                    val photoUrl = doc.getString("photoUrl")
                    if (!photoUrl.isNullOrEmpty()) {
                        Glide.with(this@AccountActivity)
                            .load(photoUrl)
                            .placeholder(android.R.drawable.sym_def_app_icon)
                            .circleCrop()
                            .into(findViewById(R.id.ivProfilePic))
                    }
                }
            }
    }

    private fun showTravelStatsDialog() {
        val userId = auth.currentUser?.uid ?: return

        firestore.collection("posts")
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { posts ->
                val totalPosts = posts.size()
                var totalFires = 0
                val cities = mutableSetOf<String>()

                for (post in posts) {
                    totalFires += (post.getLong("fireCount") ?: 0).toInt()
                    post.getString("location")?.let { cities.add(it) }
                }

                firestore.collection("users").document(userId)
                    .collection("following")
                    .get()
                    .addOnSuccessListener { following ->
                        val stats = "✈️ Your Travel Stats\n\n" +
                                "📝 Posts shared: $totalPosts\n" +
                                "🔥 Total fires earned: $totalFires\n" +
                                "🏙️ Cities mentioned: ${if (cities.isEmpty()) "Start posting!" else cities.size.toString()}\n" +
                                "👥 Travelers you follow: ${following.size()}\n\n" +
                                "Keep exploring & sharing! 🌍"

                        if (!isDestroyed && !isFinishing) {
                            AlertDialog.Builder(this)
                                .setTitle("📊 Travel Stats")
                                .setMessage(stats)
                                .setPositiveButton("Nice!", null)
                                .show()
                        }
                    }
            }
    }

    private fun showHelpDialog() {
        AlertDialog.Builder(this)
            .setTitle("Help & Support")
            .setMessage("Need help with TourEase?\n\n" +
                    "📧 Email: support@tourease.app\n\n" +
                    "💬 Or reach out on TourTalk — our community is always happy to help!\n\n" +
                    "Common issues:\n" +
                    "• Can't upload photo? Check internet connection\n" +
                    "• Booking not showing? Pull to refresh\n" +
                    "• App crashing? Update to latest version")
            .setPositiveButton("Got it", null)
            .show()
    }

    private fun showAboutDialog() {
        AlertDialog.Builder(this)
            .setTitle("About TourEase")
            .setMessage("TourEase v1.0\n\n" +
                    "Your all-in-one travel companion\n\n" +
                    "✈️ Plan trips with AI\n" +
                    "🚂 Book trains, buses & flights\n" +
                    "💬 Connect on TourTalk\n" +
                    "🎓 Student-friendly budgets\n\n" +
                    "Built with ❤️ for travelers who explore smart.")
            .setPositiveButton("Cool!", null)
            .show()
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Yes") { _, _ ->
                auth.signOut()
                val intent = Intent(this, SignupActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}