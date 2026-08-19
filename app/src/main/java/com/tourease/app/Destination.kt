package com.tourease.app

data class Destination(
    val id: Int,
    val name: String,
    val category: String,
    val rating: Double,
    val priceRange: String,
    val emoji: String,
    val description: String,
    val bestTime: String,
    val daysNeeded: String,
    val highlights: List<String>,
    val imageUrl: String,
    val drawableRes: Int = 0,
    val entryFee: String,
    val howToReach: List<String>,
    val trendingSpots: List<TrendingSpot>,
    val tips: List<String>
)