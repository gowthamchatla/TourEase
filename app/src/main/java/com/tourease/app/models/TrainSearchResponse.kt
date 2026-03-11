package com.tourease.app.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TrainSearchResponse(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("timestamp") val timestamp: Long,
    @SerializedName("data") val data: List<TrainInfo>?
)

data class TrainInfo(
    @SerializedName("train_number") val trainNumber: String,
    @SerializedName("train_name") val trainName: String,
    @SerializedName("run_days") val runDays: List<String>,
    @SerializedName("train_src") val sourceStation: String,
    @SerializedName("train_dstn") val destinationStation: String,
    @SerializedName("from_std") val departureTime: String,
    @SerializedName("to_sta") val arrivalTime: String,
    @SerializedName("from_station_name") val fromStationName: String,
    @SerializedName("to_station_name") val toStationName: String,
    @SerializedName("duration") val duration: String,
    @SerializedName("class_type") val classTypes: List<String>,
    @SerializedName("distance") val distance: Double,
    @SerializedName("has_pantry") val hasPantry: Boolean,
    @SerializedName("train_type") val trainType: String
) : Serializable