package com.tourease.app

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tourease.app.models.Post
import com.tourease.app.repository.SocialRepository

class SocialFeedActivity : AppCompatActivity() {

    private lateinit var rvFeed: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var llEmptyState: LinearLayout
    private lateinit var tabDiscover: TextView
    private lateinit var tabFollowing: TextView

    private val repository = SocialRepository()
    private var isDiscoverTab = true
    private var posts = mutableListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social_feed)
        supportActionBar?.hide()

        val ivBack: ImageView = findViewById(R.id.ivBack)
        rvFeed = findViewById(R.id.rvFeed)
        progressBar = findViewById(R.id.progressBar)
        llEmptyState = findViewById(R.id.llEmptyState)
        tabDiscover = findViewById(R.id.tabDiscover)
        tabFollowing = findViewById(R.id.tabFollowing)
        val fabCreatePost: FloatingActionButton = findViewById(R.id.fabCreatePost)

        ivBack.setOnClickListener { finish() }

        rvFeed.layoutManager = LinearLayoutManager(this)

        tabDiscover.setOnClickListener {
            isDiscoverTab = true
            updateTabUI()
            loadFeed()
        }

        tabFollowing.setOnClickListener {
            isDiscoverTab = false
            updateTabUI()
            loadFeed()
        }

        fabCreatePost.setOnClickListener {
            startActivity(Intent(this, CreatePostActivity::class.java))
        }

        loadFeed()
    }

    override fun onResume() {
        super.onResume()
        loadFeed()
    }

    private fun updateTabUI() {
        if (isDiscoverTab) {
            tabDiscover.setBackgroundResource(R.drawable.bg_tab_selected)
            tabDiscover.setTextColor(android.graphics.Color.WHITE)
            tabFollowing.setBackgroundResource(R.drawable.bg_tab_unselected)
            tabFollowing.setTextColor(android.graphics.Color.parseColor("#7C7C99"))
        } else {
            tabFollowing.setBackgroundResource(R.drawable.bg_tab_selected)
            tabFollowing.setTextColor(android.graphics.Color.WHITE)
            tabDiscover.setBackgroundResource(R.drawable.bg_tab_unselected)
            tabDiscover.setTextColor(android.graphics.Color.parseColor("#7C7C99"))
        }
    }

    private fun loadFeed() {
        progressBar.visibility = View.VISIBLE
        llEmptyState.visibility = View.GONE

        val callback: (List<Post>) -> Unit = { fetchedPosts ->
            runOnUiThread {
                progressBar.visibility = View.GONE
                posts.clear()
                posts.addAll(fetchedPosts)

                if (posts.isEmpty()) {
                    llEmptyState.visibility = View.VISIBLE
                    rvFeed.visibility = View.GONE
                    val emptyTitle = findViewById<TextView>(R.id.tvEmptyTitle)
                    val emptySub = findViewById<TextView>(R.id.tvEmptySubtitle)
                    if (isDiscoverTab) {
                        emptyTitle.text = "No posts yet"
                        emptySub.text = "Be the first to share your travel story!"
                    } else {
                        emptyTitle.text = "Your feed is empty"
                        emptySub.text = "Follow travellers to see their posts here"
                    }
                } else {
                    llEmptyState.visibility = View.GONE
                    rvFeed.visibility = View.VISIBLE
                    rvFeed.adapter = PostAdapter(posts)
                }
            }
        }

        if (isDiscoverTab) {
            repository.getDiscoverFeed(callback)
        } else {
            repository.getFollowingFeed(callback)
        }
    }

    inner class PostAdapter(private val posts: List<Post>) :
        RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

        inner class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val ivProfilePic: ImageView = view.findViewById(R.id.ivProfilePic)
            val tvUserName: TextView = view.findViewById(R.id.tvUserName)
            val tvAnonBadge: TextView = view.findViewById(R.id.tvAnonBadge)
            val tvTimestamp: TextView = view.findViewById(R.id.tvTimestamp)
            val tvLocation: TextView = view.findViewById(R.id.tvLocation)
            val tvFollow: TextView = view.findViewById(R.id.tvFollow)
            val tvPostText: TextView = view.findViewById(R.id.tvPostText)
            val cardImage: androidx.cardview.widget.CardView = view.findViewById(R.id.cardImage)
            val ivPostImage: ImageView = view.findViewById(R.id.ivPostImage)
            val llVibeTags: LinearLayout = view.findViewById(R.id.llVibeTags)
            val llFire: LinearLayout = view.findViewById(R.id.llFire)
            val tvFireIcon: TextView = view.findViewById(R.id.tvFireIcon)
            val tvFireCount: TextView = view.findViewById(R.id.tvFireCount)
            val llComment: LinearLayout = view.findViewById(R.id.llComment)
            val tvCommentCount: TextView = view.findViewById(R.id.tvCommentCount)
            val llBookmark: LinearLayout = view.findViewById(R.id.llBookmark)
            val tvBookmarkIcon: TextView = view.findViewById(R.id.tvBookmarkIcon)
            val ivPostMenu: ImageView = view.findViewById(R.id.ivPostMenu)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_post, parent, false)
            return PostViewHolder(view)
        }

        override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
            val post = posts[position]
            val currentUserId = repository.currentUserId

            // User info
            holder.tvUserName.text = post.userName
            holder.tvAnonBadge.visibility = if (post.isAnonymous) View.VISIBLE else View.GONE

            // Profile pic
            if (post.userPhotoBase64.isNotEmpty()) {
                try {
                    val bytes = Base64.decode(post.userPhotoBase64, Base64.DEFAULT)
                    val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                    holder.ivProfilePic.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    holder.ivProfilePic.setImageResource(android.R.drawable.sym_def_app_icon)
                }
            } else {
                holder.ivProfilePic.setImageResource(android.R.drawable.sym_def_app_icon)
            }

            // Timestamp
            val timeDiff = System.currentTimeMillis() - post.timestamp.toDate().time
            val minutes = timeDiff / 60000
            val hours = minutes / 60
            val days = hours / 24
            holder.tvTimestamp.text = when {
                minutes < 1 -> "just now"
                minutes < 60 -> "${minutes}m ago"
                hours < 24 -> "${hours}h ago"
                days < 7 -> "${days}d ago"
                else -> "${days / 7}w ago"
            }

            // Location
            if (post.location.isNotEmpty()) {
                holder.tvLocation.visibility = View.VISIBLE
                holder.tvLocation.text = "📍 ${post.location}"
            } else {
                holder.tvLocation.visibility = View.GONE
            }

            // Post text
            holder.tvPostText.text = post.text

            // Post image
            if (post.imageUrl.isNotEmpty()) {
                holder.cardImage.visibility = View.VISIBLE
                try {
                    Glide.with(holder.itemView.context)
                        .load(post.imageUrl)
                        .centerCrop()
                        .into(holder.ivPostImage)
                } catch (e: Exception) {
                    holder.cardImage.visibility = View.GONE
                }
            } else {
                holder.cardImage.visibility = View.GONE
            }

            // Vibe tags
            if (post.vibeTags.isNotEmpty()) {
                holder.llVibeTags.visibility = View.VISIBLE
                holder.llVibeTags.removeAllViews()
                for (tag in post.vibeTags) {
                    val tagView = TextView(holder.itemView.context).apply {
                        text = tag
                        textSize = 12f
                        setTextColor(android.graphics.Color.parseColor("#B388FF"))
                        background = resources.getDrawable(R.drawable.bg_vibe_tag, null)
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        ).apply { marginEnd = 8 }
                    }
                    holder.llVibeTags.addView(tagView)
                }
            } else {
                holder.llVibeTags.visibility = View.GONE
            }

            // Fire state
            val isFired = currentUserId in post.firedBy
            holder.tvFireCount.text = "${post.fireCount}"
            holder.tvFireCount.setTextColor(
                if (isFired) android.graphics.Color.parseColor("#FF6B35")
                else android.graphics.Color.parseColor("#7C7C99")
            )

            // Fire action
            holder.llFire.setOnClickListener {
                repository.toggleFire(post.id) { nowFired, newCount ->
                    holder.tvFireCount.text = "$newCount"
                    holder.tvFireCount.setTextColor(
                        if (nowFired) android.graphics.Color.parseColor("#FF6B35")
                        else android.graphics.Color.parseColor("#7C7C99")
                    )
                }
            }

            // Comment count
            holder.tvCommentCount.text = "${post.commentCount}"

            // Comment action
            holder.llComment.setOnClickListener {
                val intent = Intent(this@SocialFeedActivity, CommentsActivity::class.java)
                intent.putExtra("POST_ID", post.id)
                startActivity(intent)
            }

            // Bookmark state
            val isBookmarked = currentUserId in post.bookmarkedBy
            holder.tvBookmarkIcon.text = if (isBookmarked) "🔖" else "📑"

            // Bookmark action
            holder.llBookmark.setOnClickListener {
                repository.toggleBookmark(post.id) { nowBookmarked ->
                    holder.tvBookmarkIcon.text = if (nowBookmarked) "🔖" else "📑"
                }
            }

            // Follow button
            if (post.userId == currentUserId || post.isAnonymous) {
                holder.tvFollow.visibility = View.GONE
            } else {
                holder.tvFollow.visibility = View.VISIBLE
                repository.isFollowing(post.userId) { following ->
                    runOnUiThread {
                        holder.tvFollow.text = if (following) "Following" else "Follow"
                        holder.tvFollow.setTextColor(
                            if (following) android.graphics.Color.parseColor("#7C7C99")
                            else android.graphics.Color.parseColor("#6C63FF")
                        )
                    }
                }

                holder.tvFollow.setOnClickListener {
                    repository.toggleFollow(post.userId) { nowFollowing ->
                        runOnUiThread {
                            holder.tvFollow.text = if (nowFollowing) "Following" else "Follow"
                            holder.tvFollow.setTextColor(
                                if (nowFollowing) android.graphics.Color.parseColor("#7C7C99")
                                else android.graphics.Color.parseColor("#6C63FF")
                            )
                        }
                    }
                }
            }

            // Tap username to view profile
            holder.tvUserName.setOnClickListener {
                if (!post.isAnonymous) {
                    val intent = Intent(this@SocialFeedActivity, UserProfileActivity::class.java)
                    intent.putExtra("USER_ID", post.userId)
                    startActivity(intent)
                }
            }

            // 3-dot menu — only for post owner
            if (post.userId == currentUserId) {
                holder.ivPostMenu.visibility = View.VISIBLE
                holder.ivPostMenu.setOnClickListener { view ->
                    val popup = PopupMenu(view.context, view)
                    popup.menu.add(0, 0, 0, "Delete Post")
                    popup.setOnMenuItemClickListener { item ->
                        if (item.itemId == 0) {
                            AlertDialog.Builder(view.context)
                                .setTitle("Delete Post")
                                .setMessage("Are you sure you want to delete this post?")
                                .setPositiveButton("Delete") { _, _ ->
                                    repository.deletePost(post.id,
                                        onSuccess = {
                                            runOnUiThread {
                                                Toast.makeText(
                                                    this@SocialFeedActivity,
                                                    "Post deleted",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        },
                                        onFailure = {
                                            runOnUiThread {
                                                Toast.makeText(
                                                    this@SocialFeedActivity,
                                                    "Failed to delete",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                    )
                                }
                                .setNegativeButton("Cancel", null)
                                .show()
                        }
                        true
                    }
                    popup.show()
                }
            } else {
                holder.ivPostMenu.visibility = View.GONE
            }
        }

        override fun getItemCount() = posts.size
    }
}