package com.tourease.app

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class NotificationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        val ivBack: ImageView = findViewById(R.id.ivBack)
        ivBack.setOnClickListener { finish() }

        // Sample notifications
        addNotification("🎉", "Welcome to TourEase!", "Start planning your dream trip today!", "2 hours ago")
        addNotification("✈️", "New destination added", "Check out Leh-Ladakh in Explore Places!", "5 hours ago")
        addNotification("💰", "Student discount!", "Get 20% off on your first booking", "1 day ago")
    }

    private fun addNotification(emoji: String, title: String, message: String, time: String) {
        val container: LinearLayout = findViewById(R.id.notificationsContainer)

        val card = CardView(this)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 0, 32)
        card.layoutParams = params
        card.radius = 40f
        card.cardElevation = 8f
        card.setCardBackgroundColor(android.graphics.Color.WHITE)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.HORIZONTAL
        layout.setPadding(40, 30, 40, 30)

        val emojiView = android.widget.TextView(this)
        emojiView.text = emoji
        emojiView.textSize = 32f
        emojiView.setPadding(0, 0, 30, 0)

        val textLayout = LinearLayout(this)
        textLayout.orientation = LinearLayout.VERTICAL

        val titleView = android.widget.TextView(this)
        titleView.text = title
        titleView.textSize = 16f
        titleView.setTypeface(null, android.graphics.Typeface.BOLD)
        titleView.setTextColor(android.graphics.Color.parseColor("#333333"))

        val messageView = android.widget.TextView(this)
        messageView.text = message
        messageView.textSize = 14f
        messageView.setTextColor(android.graphics.Color.parseColor("#666666"))
        messageView.setPadding(0, 8, 0, 8)

        val timeView = android.widget.TextView(this)
        timeView.text = time
        timeView.textSize = 12f
        timeView.setTextColor(android.graphics.Color.parseColor("#999999"))

        textLayout.addView(titleView)
        textLayout.addView(messageView)
        textLayout.addView(timeView)

        layout.addView(emojiView)
        layout.addView(textLayout)
        card.addView(layout)

        container.addView(card)
    }
}