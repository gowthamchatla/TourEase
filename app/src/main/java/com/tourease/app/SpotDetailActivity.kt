package com.tourease.app

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide

class SpotDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spot_detail)

        val statusBarSpacer: android.view.View = findViewById(R.id.statusBarSpacer)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            statusBarSpacer.layoutParams.height = systemBars.top
            statusBarSpacer.requestLayout()
            insets
        }

        val spotName = intent.getStringExtra("SPOT_NAME") ?: ""
        val spotDescription = intent.getStringExtra("SPOT_DESCRIPTION") ?: ""
        val spotBestTime = intent.getStringExtra("SPOT_BEST_TIME") ?: ""
        val spotEntryFee = intent.getStringExtra("SPOT_ENTRY_FEE") ?: ""
        val spotTips = intent.getStringArrayListExtra("SPOT_TIPS") ?: arrayListOf()
        val spotImages = intent.getStringArrayListExtra("SPOT_IMAGES") ?: arrayListOf()

        val ivBack: ImageView = findViewById(R.id.ivBack)
        val tvSpotName: TextView = findViewById(R.id.tvSpotName)
        val tvDescription: TextView = findViewById(R.id.tvDescription)
        val tvBestTime: TextView = findViewById(R.id.tvBestTime)
        val tvEntryFee: TextView = findViewById(R.id.tvEntryFee)
        val tvTips: TextView = findViewById(R.id.tvTips)
        val viewPager: ViewPager2 = findViewById(R.id.viewPagerImages)
        val tvImageCounter: TextView = findViewById(R.id.tvImageCounter)

        tvSpotName.text = spotName
        tvDescription.text = spotDescription
        tvBestTime.text = spotBestTime
        tvEntryFee.text = spotEntryFee
        tvTips.text = spotTips.joinToString("\n\n") { "💡 $it" }

        // Image slider adapter
        val adapter = SpotImageAdapter(spotImages)
        viewPager.adapter = adapter

        if (spotImages.isNotEmpty()) {
            tvImageCounter.text = "1 / ${spotImages.size}"
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tvImageCounter.text = "${position + 1} / ${spotImages.size}"
            }
        })

        ivBack.setOnClickListener { finish() }
    }
}