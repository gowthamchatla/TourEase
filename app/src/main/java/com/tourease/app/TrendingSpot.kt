package com.tourease.app

data class TrendingSpot(
    val name: String,
    val description: String,
    val bestTime: String,
    val entryFee: String,
    val tips: List<String>,
    val imageUrls: List<String>  // 3 URLs per spot
)