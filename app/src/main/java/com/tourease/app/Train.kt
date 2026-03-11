package com.tourease.app

import java.io.Serializable

data class Train(
    val trainNumber: String,
    val trainName: String,
    val fromStation: String,
    val toStation: String,
    val departureTime: String,
    val arrivalTime: String,
    val duration: String,
    val runsDays: String,
    val sleeperPrice: Int,
    val ac3Price: Int,
    val ac2Price: Int,
    val ac1Price: Int,
    val sleeperAvailable: Int,
    val ac3Available: Int,
    val ac2Available: Int,
    val ac1Available: Int
) : Serializable
