package com.tourease.app

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
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
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.tourease.app.models.Post
import com.tourease.app.repository.SocialRepository

class UserProfileActivity : AppCompatActivity() {

    private val repository = SocialRepository()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        supportActionBar?.hide()

        val targetUserId = intent.getStringExtra("USER_ID") ?: return

        val ivBack: ImageView = findViewById(R.id.ivBack)
        val tvTitle: TextView = findViewById(R.id.tvTitle)
        val ivProfilePic: ImageView = findViewById(R.id.ivProfilePic)
        val tvUserName: TextView = findViewById(R.id.tvUserName)
        val tvBio: TextView = findViewById(R.id.tvBio)
        val tvPostCount: TextView = findViewById(R.id.tvPostCount)
        val tvFollowerCount: TextView = findViewById(R.id.tvFollowerCount)
        val tvFireTotal: TextView = findViewById(R.id.tvFireTotal)
        val btnFollow: Button = findViewById(R.id.btnFollow)
        val rvUserPosts: RecyclerView = findViewById(R.id.rvUserPosts)

        ivBack.setOnClickListener { finish() }

        // Hide follow button if viewing own profile
        if (targetUserId == repository.currentUserId) {
            btnFollow.visibility = View.GONE
        }

        // Load user data
        db.collection("users").document(targetUserId).get()
            .addOnSuccessListener { doc ->
                val userName = doc.getString("username") ?: "User"
                tvUserName.text = userName
                tvTitle.text = userName
                tvBio.text = doc.getString("bio") ?: "No bio yet"

                val followers = doc.getLong("followers") ?: 0
                tvFollowerCount.text = "$followers"

                doc.getString("photoBase64")?.let { base64 ->
                    try {
                        val bytes = Base64.decode(base64, Base64.DEFAULT)
                        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                        ivProfilePic.setImageBitmap(bitmap)
                    } catch (_: Exception) { }
                }
            }

        // Load user posts
        rvUserPosts.layoutManager = LinearLayoutManager(this)
        db.collection("posts")
            .whereEqualTo("userId", targetUserId)
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { snapshot ->
                val posts = snapshot.documents.mapNotNull { it.toObject(Post::class.java) }
                tvPostCount.text = "${posts.size}"

                // Calculate total fires
                val totalFires = posts.sumOf { it.fireCount }
                tvFireTotal.text = "${totalFires}🔥"

                rvUserPosts.adapter = SimplePostAdapter(posts)
            }

        // Follow button
        repository.isFollowing(targetUserId) { isFollowing ->
            runOnUiThread {
                updateFollowButton(btnFollow, isFollowing)
            }
        }

        btnFollow.setOnClickListener {
            repository.toggleFollow(targetUserId) { nowFollowing ->
                runOnUiThread {
                    updateFollowButton(btnFollow, nowFollowing)
                }
            }
        }
    }

    private fun updateFollowButton(btn: Button, isFollowing: Boolean) {
        if (isFollowing) {
            btn.text = "Following"
            btn.backgroundTintList = android.content.res.ColorStateList.valueOf(
                android.graphics.Color.parseColor("#2A2A40")
            )
            btn.setTextColor(android.graphics.Color.parseColor("#7C7C99"))
        } else {
            btn.text = "Follow"
            btn.backgroundTintList = android.content.res.ColorStateList.valueOf(
                android.graphics.Color.parseColor("#6C63FF")
            )
            btn.setTextColor(android.graphics.Color.WHITE)
        }
    }

    // Simple adapter showing just text posts (no actions)
    inner class SimplePostAdapter(private val posts: List<Post>) :
        RecyclerView.Adapter<SimplePostAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvText: TextView = view.findViewById(R.id.tvPostText)
            val tvTime: TextView = view.findViewById(R.id.tvTimestamp)
            val tvLocation: TextView = view.findViewById(R.id.tvLocation)
            val llVibeTags: LinearLayout = view.findViewById(R.id.llVibeTags)
            val tvFireCount: TextView = view.findViewById(R.id.tvFireCount)
            val tvCommentCount: TextView = view.findViewById(R.id.tvCommentCount)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_post, parent, false)
            // Hide user info row since we're on their profile
            view.findViewById<View>(R.id.tvFollow).visibility = View.GONE
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val post = posts[position]
            holder.tvText.text = post.text
            holder.tvFireCount.text = "${post.fireCount}"
            holder.tvCommentCount.text = "${post.commentCount}"

            if (post.location.isNotEmpty()) {
                holder.tvLocation.visibility = View.VISIBLE
                holder.tvLocation.text = "📍 ${post.location}"
            } else {
                holder.tvLocation.visibility = View.GONE
            }

            // Vibe tags
            if (post.vibeTags.isNotEmpty()) {
                holder.llVibeTags.visibility = View.VISIBLE
                holder.llVibeTags.removeAllViews()
                for (tag in post.vibeTags) {
                    val tv = TextView(holder.itemView.context).apply {
                        text = tag
                        textSize = 12f
                        setTextColor(android.graphics.Color.parseColor("#B388FF"))
                        background = resources.getDrawable(R.drawable.bg_vibe_tag, null)
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        ).apply { marginEnd = 8 }
                    }
                    holder.llVibeTags.addView(tv)
                }
            } else {
                holder.llVibeTags.visibility = View.GONE
            }

            val timeDiff = System.currentTimeMillis() - post.timestamp.toDate().time
            val hours = timeDiff / 3600000
            val days = hours / 24
            holder.tvTime.text = when {
                hours < 1 -> "just now"
                hours < 24 -> "${hours}h ago"
                else -> "${days}d ago"
            }
        }

        override fun getItemCount() = posts.size
    }
}