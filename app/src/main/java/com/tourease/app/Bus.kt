package com.tourease.app

import java.io.Serializable

data class Bus(
    val busNumber: String,
    val busName: String,
    val operatorName: String,
    val fromCity: String,
    val toCity: String,
    val departureTime: String,
    val arrivalTime: String,
    val duration: String,
    val busType: String,          // "Sleeper", "Semi-Sleeper", "Sitting"
    val amenities: String,        // e.g. "WiFi, Charging, Water"
    val sleeperPrice: Int,
    val semiSleeperPrice: Int,
    val sittingPrice: Int,
    val sleeperAvailable: Int,
    val semiSleeperAvailable: Int,
    val sittingAvailable: Int,
    val rating: Float
) : Serializable