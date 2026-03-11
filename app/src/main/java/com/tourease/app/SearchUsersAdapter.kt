package com.tourease.app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SearchUsersAdapter(
    private val context: Context,
    private val users: List<Map<String, Any>>,
    private val onUserClick: (String) -> Unit
) : RecyclerView.Adapter<SearchUsersAdapter.ViewHolder>() {

    private val firestore = FirebaseFirestore.getInstance()
    private val currentUserId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivUserPic: ImageView = view.findViewById(R.id.ivUserPic)
        val tvUsername: TextView = view.findViewById(R.id.tvUsername)
        val tvBio: TextView = view.findViewById(R.id.tvBio)
        val btnFollow: Button = view.findViewById(R.id.btnFollow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_search_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        val userId = user["userId"] as? String ?: ""
        val username = user["username"] as? String ?: "User"
        val bio = user["bio"] as? String ?: ""
        val photoUrl = user["photoUrl"] as? String ?: ""

        holder.tvUsername.text = username
        holder.tvBio.text = if (bio.isNotEmpty()) bio else "No bio"

        if (photoUrl.isNotEmpty()) {
            Glide.with(context)
                .load(photoUrl)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .circleCrop()
                .into(holder.ivUserPic)
        } else {
            holder.ivUserPic.setImageResource(android.R.drawable.sym_def_app_icon)
        }

        // Check follow status
        checkFollowStatus(userId, holder.btnFollow)

        // Follow/Unfollow toggle
        holder.btnFollow.setOnClickListener {
            toggleFollow(userId, holder.btnFollow)
        }

        // Click to open profile
        holder.itemView.setOnClickListener {
            onUserClick(userId)
        }
    }

    override fun getItemCount() = users.size

    private fun checkFollowStatus(userId: String, btnFollow: Button) {
        firestore.collection("users").document(currentUserId)
            .collection("following").document(userId)
            .get()
            .addOnSuccessListener { doc ->
                if (doc.exists()) {
                    btnFollow.text = "Following"
                    btnFollow.backgroundTintList = android.content.res.ColorStateList.valueOf(
                        android.graphics.Color.parseColor("#2A2A40")
                    )
                } else {
                    btnFollow.text = "Follow"
                    btnFollow.backgroundTintList = android.content.res.ColorStateList.valueOf(
                        android.graphics.Color.parseColor("#6C63FF")
                    )
                }
            }
    }

    private fun toggleFollow(userId: String, btnFollow: Button) {
        val followingRef = firestore.collection("users").document(currentUserId)
            .collection("following").document(userId)
        val followerRef = firestore.collection("users").document(userId)
            .collection("followers").document(currentUserId)

        followingRef.get().addOnSuccessListener { doc ->
            if (doc.exists()) {
                followingRef.delete()
                followerRef.delete()
                btnFollow.text = "Follow"
                btnFollow.backgroundTintList = android.content.res.ColorStateList.valueOf(
                    android.graphics.Color.parseColor("#6C63FF")
                )
            } else {
                followingRef.set(mapOf("timestamp" to System.currentTimeMillis()))
                followerRef.set(mapOf("timestamp" to System.currentTimeMillis()))
                btnFollow.text = "Following"
                btnFollow.backgroundTintList = android.content.res.ColorStateList.valueOf(
                    android.graphics.Color.parseColor("#2A2A40")
                )
            }
        }
    }
}