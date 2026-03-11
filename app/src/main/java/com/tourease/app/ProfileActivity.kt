package com.tourease.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private var currentUserId = ""
    private var viewingUserId = ""
    private var isOwnProfile = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPrefs = getSharedPreferences("AppSettings", MODE_PRIVATE)
        val isDarkMode = sharedPrefs.getBoolean("DarkMode", false)
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        setContentView(R.layout.activity_profile)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        currentUserId = auth.currentUser?.uid ?: ""

        viewingUserId = intent.getStringExtra("userId") ?: currentUserId
        isOwnProfile = viewingUserId == currentUserId

        val ivBack: ImageView = findViewById(R.id.ivBack)
        val ivSettings: ImageView = findViewById(R.id.ivSettings)
        val btnEditProfile: Button = findViewById(R.id.btnEditProfile)
        val btnSearchUsers: Button = findViewById(R.id.btnSearchUsers)
        val switchDarkMode: SwitchCompat = findViewById(R.id.switchDarkMode)
        val layoutDarkMode: CardView = findViewById(R.id.layoutDarkMode)

        ivBack.setOnClickListener { finish() }

        if (isOwnProfile) {
            ivSettings.visibility = View.VISIBLE
            btnEditProfile.text = "Edit Profile"
            switchDarkMode.isChecked = isDarkMode
            layoutDarkMode.visibility = View.VISIBLE
        } else {
            ivSettings.visibility = View.GONE
            btnEditProfile.text = "Follow"
            btnEditProfile.backgroundTintList = android.content.res.ColorStateList.valueOf(
                android.graphics.Color.parseColor("#6C63FF")
            )
            btnEditProfile.setTextColor(android.graphics.Color.WHITE)
            layoutDarkMode.visibility = View.GONE
            checkFollowStatus(btnEditProfile)
        }

        ivSettings.setOnClickListener { showLogoutDialog() }

        btnEditProfile.setOnClickListener {
            if (isOwnProfile) {
                startActivity(Intent(this, EditProfileActivity::class.java))
            } else {
                toggleFollow(btnEditProfile)
            }
        }

        btnSearchUsers.setOnClickListener {
            startActivity(Intent(this, SearchUsersActivity::class.java))
        }

        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            sharedPrefs.edit().putBoolean("DarkMode", isChecked).apply()
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            recreate()
        }

        loadProfileData()
        loadStats()
        loadUserPosts()
    }

    override fun onResume() {
        super.onResume()
        loadProfileData()
        loadStats()
        loadUserPosts()
    }

    private fun loadProfileData() {
        firestore.collection("users").document(viewingUserId)
            .get()
            .addOnSuccessListener { doc ->
                if (doc.exists() && !isDestroyed && !isFinishing) {
                    findViewById<TextView>(R.id.tvUsername).text =
                        doc.getString("username") ?: "User"
                    findViewById<TextView>(R.id.tvBio).text =
                        doc.getString("bio") ?: "No bio yet"

                    // Profile picture
                    val photoUrl = doc.getString("photoUrl")
                    if (!photoUrl.isNullOrEmpty()) {
                        Glide.with(this@ProfileActivity)
                            .load(photoUrl)
                            .placeholder(android.R.drawable.sym_def_app_icon)
                            .circleCrop()
                            .into(findViewById(R.id.ivProfilePic))
                    }

                    // Cover photo
                    val coverUrl = doc.getString("coverUrl")
                    if (!coverUrl.isNullOrEmpty()) {
                        Glide.with(this@ProfileActivity)
                            .load(coverUrl)
                            .centerCrop()
                            .into(findViewById(R.id.ivCoverPhoto))
                    }
                }
            }
    }

    private fun loadStats() {
        // Post count + total fires
        firestore.collection("posts")
            .whereEqualTo("userId", viewingUserId)
            .get()
            .addOnSuccessListener { posts ->
                if (!isDestroyed && !isFinishing) {
                    findViewById<TextView>(R.id.tvPostCount).text = posts.size().toString()

                    var totalFires = 0
                    for (post in posts) {
                        totalFires += (post.getLong("fireCount") ?: 0).toInt()
                    }
                    findViewById<TextView>(R.id.tvFireTotal).text = "$totalFires Fires"
                }
            }

        // Follower count
        firestore.collection("users").document(viewingUserId)
            .collection("followers")
            .get()
            .addOnSuccessListener { followers ->
                if (!isDestroyed && !isFinishing) {
                    findViewById<TextView>(R.id.tvFollowerCount).text = followers.size().toString()
                }
            }

        // Following count
        firestore.collection("users").document(viewingUserId)
            .collection("following")
            .get()
            .addOnSuccessListener { following ->
                if (!isDestroyed && !isFinishing) {
                    findViewById<TextView>(R.id.tvFollowingCount).text = following.size().toString()
                }
            }
    }

    private fun loadUserPosts() {
        val rv: RecyclerView = findViewById(R.id.rvUserPosts)
        val layoutNoPosts: LinearLayout = findViewById(R.id.layoutNoPosts)

        rv.layoutManager = LinearLayoutManager(this)

        firestore.collection("posts")
            .whereEqualTo("userId", viewingUserId)
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { snapshot ->
                if (!isDestroyed && !isFinishing) {
                    if (snapshot.isEmpty) {
                        rv.visibility = View.GONE
                        layoutNoPosts.visibility = View.VISIBLE
                    } else {
                        rv.visibility = View.VISIBLE
                        layoutNoPosts.visibility = View.GONE

                        val posts = snapshot.documents.map { doc ->
                            val data = doc.data?.toMutableMap() ?: return@map null
                            data["postId"] = doc.id
                            data
                        }.filterNotNull()

                        rv.adapter = ProfilePostsAdapter(this, posts)
                    }
                }
            }
    }

    private fun checkFollowStatus(btnFollow: Button) {
        firestore.collection("users").document(currentUserId)
            .collection("following").document(viewingUserId)
            .get()
            .addOnSuccessListener { doc ->
                if (doc.exists()) {
                    btnFollow.text = "Following"
                    btnFollow.backgroundTintList = android.content.res.ColorStateList.valueOf(
                        android.graphics.Color.parseColor("#1A1A2E")
                    )
                } else {
                    btnFollow.text = "Follow"
                    btnFollow.backgroundTintList = android.content.res.ColorStateList.valueOf(
                        android.graphics.Color.parseColor("#FF6B35")
                    )
                }
            }
    }

    private fun toggleFollow(btnFollow: Button) {
        val followingRef = firestore.collection("users").document(currentUserId)
            .collection("following").document(viewingUserId)
        val followerRef = firestore.collection("users").document(viewingUserId)
            .collection("followers").document(currentUserId)

        followingRef.get().addOnSuccessListener { doc ->
            if (doc.exists()) {
                followingRef.delete()
                followerRef.delete()
                btnFollow.text = "Follow"
                btnFollow.backgroundTintList = android.content.res.ColorStateList.valueOf(
                    android.graphics.Color.parseColor("#FF6B35")
                )
                loadStats()
            } else {
                followingRef.set(mapOf("timestamp" to System.currentTimeMillis()))
                followerRef.set(mapOf("timestamp" to System.currentTimeMillis()))
                btnFollow.text = "Following"
                btnFollow.backgroundTintList = android.content.res.ColorStateList.valueOf(
                    android.graphics.Color.parseColor("#1A1A2E")
                )
                loadStats()
            }
        }
    }

    private fun showLogoutDialog() {
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Logout")
        builder.setMessage("Are you sure you want to logout?")
        builder.setPositiveButton("Yes") { _, _ ->
            auth.signOut()
            val intent = Intent(this, SignupActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("Cancel", null)
        builder.show()
    }
}