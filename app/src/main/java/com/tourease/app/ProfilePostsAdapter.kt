package com.tourease.app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProfilePostsAdapter(
    private val context: Context,
    private val posts: List<Map<String, Any>>
) : RecyclerView.Adapter<ProfilePostsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvPostContent: TextView = view.findViewById(R.id.tvPostContent)
        val tvVibeTag: TextView = view.findViewById(R.id.tvVibeTag)
        val tvFireCount: TextView = view.findViewById(R.id.tvFireCount)
        val tvCommentCount: TextView = view.findViewById(R.id.tvCommentCount)
        val tvTimeAgo: TextView = view.findViewById(R.id.tvTimeAgo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_profile_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]

        holder.tvPostContent.text = (post["content"] as? String) ?: ""

        val vibeTag = (post["vibeTag"] as? String) ?: ""
        if (vibeTag.isNotEmpty()) {
            holder.tvVibeTag.text = vibeTag
            holder.tvVibeTag.visibility = View.VISIBLE
        } else {
            holder.tvVibeTag.visibility = View.GONE
        }

        val fireCount = (post["fireCount"] as? Long) ?: 0
        holder.tvFireCount.text = "🔥 $fireCount"

        val commentCount = (post["commentCount"] as? Long) ?: 0
        holder.tvCommentCount.text = "💬 $commentCount"

        // Time ago
        val timestamp = (post["timestamp"] as? com.google.firebase.Timestamp)
        if (timestamp != null) {
            holder.tvTimeAgo.text = getTimeAgo(timestamp.toDate().time)
        } else {
            holder.tvTimeAgo.text = ""
        }
    }

    override fun getItemCount() = posts.size

    private fun getTimeAgo(time: Long): String {
        val diff = System.currentTimeMillis() - time
        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24

        return when {
            days > 0 -> "${days}d ago"
            hours > 0 -> "${hours}h ago"
            minutes > 0 -> "${minutes}m ago"
            else -> "Just now"
        }
    }
}