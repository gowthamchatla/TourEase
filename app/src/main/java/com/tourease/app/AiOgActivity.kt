package com.tourease.app

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class AiOgActivity : AppCompatActivity() {

    private lateinit var chatContainer: LinearLayout
    private lateinit var chatScrollView: ScrollView
    private lateinit var etMessage: EditText
    private lateinit var btnSend: ImageView
    private lateinit var loadingIndicator: ProgressBar

    private val responses = mapOf(
        "beach" to "🏖️ Top beaches in India:\n\n1. Radhanagar Beach, Andaman\n2. Palolem, Goa\n3. Varkala, Kerala\n4. Tarkarli, Maharashtra\n5. Om Beach, Gokarna\n\nGokarna is the most budget-friendly for students! 🤑",
        "budget" to "💸 Budget Travel Tips for India:\n\n1. Travel by sleeper class trains — cheapest!\n2. Stay in hostels (₹300–600/night)\n3. Eat at local dhabas\n4. Travel offseason (20–40% cheaper)\n5. Use student ID for discounts\n6. Book train tickets 120 days in advance\n\nYou can explore India for ₹500/day easily! 🎒",
        "manali" to "🏔️ Best time to visit Manali:\n\n❄️ Dec–Feb: Snow, very cold (-15°C), Solang Valley skiing\n🌸 Mar–May: Pleasant weather, great for trekking\n☀️ Jun–Aug: Rohtang Pass opens, but monsoon hits\n🍂 Sep–Nov: Clear skies, best views!\n\nFor snow: January\nFor trekking: October\nFor budget: September 🎯",
        "goa" to "🌴 Goa Travel Guide:\n\n💰 Budget: ₹1500–2500/day\n🏖️ Best beaches: Anjuna, Arambol (North) & Palolem (South)\n🍺 Nightlife: Tito's Lane, Curlies\n🛵 Rent a scooter: ₹300–400/day\n\nBest time: Nov–Feb\nAvoid: June–Sept (monsoon) 🌧️",
        "delhi" to "🏛️ Delhi Travel Tips:\n\n🎯 Must visit: Red Fort, Qutub Minar, India Gate, Chandni Chowk\n🍜 Food: Paranthe Wali Gali, Karim's, Dilli Haat\n🚇 Use Metro — cheapest way to travel\n💰 Budget: ₹800–1500/day\n\nBest time: Oct–Mar ❄️",
        "kerala" to "🌿 Kerala — God's Own Country:\n\n✨ Must do: Alleppey houseboat, Munnar tea gardens, Kovalam beach\n💰 Budget: ₹1500–3000/day\n🚢 Houseboat: ₹3000–8000/night\n🌶️ Food: Appam + stew, fish curry, puttu\n\nBest time: Sept–March 🌺",
        "rajasthan" to "🏰 Rajasthan Highlights:\n\n🎪 Jaipur: Amber Fort, Hawa Mahal\n🌅 Jaisalmer: Desert safari, Sam Sand Dunes\n🦅 Jodhpur: Mehrangarh Fort, blue city views\n💰 Budget: ₹1000–2000/day\n\nBest time: Oct–Feb\nAvoid summers — it hits 45°C! 🥵",
        "train" to "🚂 Train Travel Tips India:\n\n1. Book on IRCTC 120 days in advance\n2. Tatkal quota opens 1 day before\n3. Sleeper class: cheapest, good for short trips\n4. 3AC: Best comfort–cost balance\n5. Use RailYatri app for live tracking\n6. Student concession: 50% on some trains!\n\nAlways carry a printout of ticket 🎫",
        "himachal" to "🏔️ Himachal Pradesh Guide:\n\n🌟 Top spots: Manali, Kasol, Spiti Valley, Dharamshala\n💰 Kasol budget: ₹700–1200/day\n🥾 Kheerganga trek: 2 days, stunning hot springs!\n🚌 Volvo buses from Delhi: ₹700–1200\n\nBest time: May–June & Sept–Oct 🎒",
        "default" to "Hey! I'm AI OG, your travel buddy! 🤙\n\nAsk me about:\n🏖️ Best beaches in India\n💸 Budget travel tips\n🏔️ Hill stations & treks\n🚂 Train travel hacks\n🌴 Goa, Kerala, Rajasthan\n🏛️ Delhi, Himachal & more!\n\nWhat do you want to explore? ✈️"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_og)

        chatContainer = findViewById(R.id.chatContainer)
        chatScrollView = findViewById(R.id.chatScrollView)
        etMessage = findViewById(R.id.etMessage)
        btnSend = findViewById(R.id.btnSend)
        loadingIndicator = findViewById(R.id.loadingIndicator)

        findViewById<ImageView>(R.id.ivBack).setOnClickListener { finish() }

        findViewById<Button>(R.id.btnBestPlaces).setOnClickListener {
            sendMessage("Best beaches in India?")
        }
        findViewById<Button>(R.id.btnBudget).setOnClickListener {
            sendMessage("Give me budget travel tips for India")
        }
        findViewById<Button>(R.id.btnWeather).setOnClickListener {
            sendMessage("Best time to visit Manali?")
        }

        btnSend.setOnClickListener {
            val message = etMessage.text.toString().trim()
            if (message.isNotEmpty()) {
                sendMessage(message)
                etMessage.text.clear()
            }
        }

        // Welcome message
        addMessageBubble("Hey! I'm AI OG, your Gen Z travel buddy! 🤙\n\nAsk me anything about Indian travel — beaches, budgets, trains, hill stations, food & more! ✈️", false)
    }

    private fun sendMessage(userText: String) {
        addMessageBubble(userText, true)
        loadingIndicator.visibility = View.VISIBLE

        // Simulate typing delay
        chatScrollView.postDelayed({
            loadingIndicator.visibility = View.GONE
            val reply = getResponse(userText.lowercase())
            addMessageBubble(reply, false)
        }, 800)
    }

    private fun getResponse(input: String): String {
        return when {
            input.contains("beach") || input.contains("sea") || input.contains("coastal") -> responses["beach"]!!
            input.contains("budget") || input.contains("cheap") || input.contains("money") || input.contains("cost") -> responses["budget"]!!
            input.contains("manali") || input.contains("snow") || input.contains("skiing") -> responses["manali"]!!
            input.contains("goa") || input.contains("party") || input.contains("nightlife") -> responses["goa"]!!
            input.contains("delhi") || input.contains("capital") -> responses["delhi"]!!
            input.contains("kerala") || input.contains("houseboat") || input.contains("backwater") -> responses["kerala"]!!
            input.contains("rajasthan") || input.contains("jaipur") || input.contains("desert") || input.contains("jaisalmer") -> responses["rajasthan"]!!
            input.contains("train") || input.contains("irctc") || input.contains("railway") -> responses["train"]!!
            input.contains("himachal") || input.contains("kasol") || input.contains("spiti") || input.contains("dharamshala") -> responses["himachal"]!!
            input.contains("best time") || input.contains("weather") || input.contains("when to visit") -> responses["manali"]!!
            else -> responses["default"]!!
        }
    }

    private fun addMessageBubble(text: String, isUser: Boolean) {
        val card = CardView(this)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(0, 0, 0, 24)
            gravity = if (isUser) Gravity.END else Gravity.START
            if (isUser) marginStart = 80 else marginEnd = 80
        }
        card.layoutParams = params
        card.radius = 40f
        card.cardElevation = 4f
        card.useCompatPadding = true
        card.setCardBackgroundColor(
            if (isUser) Color.parseColor("#FF6B35") else Color.parseColor("#1E1E2E")
        )
        val tv = TextView(this).apply {
            this.text = text
            textSize = 14f
            setPadding(40, 28, 40, 28)
            setTextColor(if (isUser) Color.WHITE else Color.parseColor("#E0E0E0"))
        }
        card.addView(tv)
        chatContainer.addView(card)
        chatScrollView.post { chatScrollView.fullScroll(View.FOCUS_DOWN) }
    }
}