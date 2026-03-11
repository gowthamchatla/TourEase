package com.tourease.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tourease.app.models.Comment
import com.tourease.app.repository.SocialRepository

class CommentsActivity : AppCompatActivity() {

    private val repository = SocialRepository()
    private lateinit var rvComments: RecyclerView
    private var postId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)
        supportActionBar?.hide()

        postId = intent.getStringExtra("POST_ID") ?: ""

        val ivBack: ImageView = findViewById(R.id.ivBack)
        rvComments = findViewById(R.id.rvComments)
        val etComment: EditText = findViewById(R.id.etComment)
        val btnSend: Button = findViewById(R.id.btnSend)

        ivBack.setOnClickListener { finish() }

        rvComments.layoutManager = LinearLayoutManager(this)

        // Load comments in real-time
        repository.getComments(postId) { comments ->
            runOnUiThread {
                rvComments.adapter = CommentAdapter(comments)
                // Scroll to bottom
                if (comments.isNotEmpty()) {
                    rvComments.scrollToPosition(comments.size - 1)
                }
            }
        }

        btnSend.setOnClickListener {
            val text = etComment.text.toString().trim()
            if (text.isEmpty()) return@setOnClickListener

            btnSend.isEnabled = false
            repository.addComment(postId, text) {
                runOnUiThread {
                    etComment.text.clear()
                    btnSend.isEnabled = true
                }
            }
        }
    }

    inner class CommentAdapter(private val comments: List<Comment>) :
        RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvUser: TextView = view.findViewById(R.id.tvCommentUser)
            val tvTime: TextView = view.findViewById(R.id.tvCommentTime)
            val tvText: TextView = view.findViewById(R.id.tvCommentText)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_comment, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val comment = comments[position]
            holder.tvUser.text = comment.userName
            holder.tvText.text = comment.text

            val timeDiff = System.currentTimeMillis() - comment.timestamp.toDate().time
            val minutes = timeDiff / 60000
            val hours = minutes / 60
            val days = hours / 24
            holder.tvTime.text = when {
                minutes < 1 -> "now"
                minutes < 60 -> "${minutes}m"
                hours < 24 -> "${hours}h"
                else -> "${days}d"
            }
        }

        override fun getItemCount() = comments.size
    }
}