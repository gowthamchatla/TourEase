package com.tourease.app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AiOgActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_og)

        val ivBack: ImageView = findViewById(R.id.ivBack)
        val etMessage: EditText = findViewById(R.id.etMessage)
        val btnSend: ImageView = findViewById(R.id.btnSend)
        val chatContainer: LinearLayout = findViewById(R.id.chatContainer)

        ivBack.setOnClickListener { finish() }

        // Quick action buttons
        findViewById<Button>(R.id.btnBestPlaces).setOnClickListener {
            addMessage("Best beaches in India?", true)
            addMessage("Here are the top beaches:\n🏖️ Goa - Party central\n🏝️ Andaman - Crystal waters\n🌊 Varkala - Cliff beaches", false)
        }

        findViewById<Button>(R.id.btnBudget).setOnClickListener {
            addMessage("Budget travel tips?", true)
            addMessage("💰 Budget Tips:\n• Travel off-season\n• Use student discounts\n• Book trains instead of flights\n• Try hostels or homestays", false)
        }

        findViewById<Button>(R.id.btnWeather).setOnClickListener {
            addMessage("Best time to visit Manali?", true)
            addMessage("⛰️ Best time for Manali:\n• Summer: March-June (pleasant)\n• Winter: Dec-Feb (snow!)\n• Avoid: July-Sep (heavy rain)", false)
        }

        btnSend.setOnClickListener {
            val message = etMessage.text.toString().trim()
            if (message.isNotEmpty()) {
                addMessage(message, true)
                etMessage.text.clear()

                android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                    addMessage("AI OG here! 🤖\n\nI'm still learning! Full AI integration coming soon!\n\nFor now, try the quick action buttons above! 😊", false)
                }, 1000)
            }
        }
    }

    private fun addMessage(text: String, isUser: Boolean) {
        val chatContainer: LinearLayout = findViewById(R.id.chatContainer)

        val card = androidx.cardview.widget.CardView(this)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 0, 32)

        if (isUser) {
            params.gravity = android.view.Gravity.END
            card.setCardBackgroundColor(android.graphics.Color.parseColor("#FF6B35"))
        } else {
            params.gravity = android.view.Gravity.START
            card.setCardBackgroundColor(android.graphics.Color.parseColor("#FFFFFF"))
        }

        card.layoutParams = params
        card.radius = 40f
        card.cardElevation = 8f
        card.useCompatPadding = true

        val textView = android.widget.TextView(this)
        textView.text = text
        textView.textSize = 14f
        textView.setPadding(40, 30, 40, 30)
        textView.setTextColor(if (isUser) android.graphics.Color.WHITE else android.graphics.Color.parseColor("#333333"))

        card.addView(textView)
        chatContainer.addView(card)

        findViewById<android.widget.ScrollView>(R.id.chatScrollView).post {
            findViewById<android.widget.ScrollView>(R.id.chatScrollView).fullScroll(android.view.View.FOCUS_DOWN)
        }
    }
}