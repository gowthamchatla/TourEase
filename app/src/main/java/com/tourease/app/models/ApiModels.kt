package com.tourease.app.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

// ==================== FARE ====================
data class FareResponse(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: FareData?
)

data class FareData(
    @SerializedName("general") val general: List<FareClass>,
    @SerializedName("tatkal") val tatkal: List<FareClass>
)

data class FareClass(
    @SerializedName("classType") val classType: String,
    @SerializedName("fare") val fare: Int,
    @SerializedName("breakup") val breakup: List<FareBreakup>
)

data class FareBreakup(
    @SerializedName("title") val title: String,
    @SerializedName("key") val key: String,
    @SerializedName("cost") val cost: Int
)

// ==================== SEAT AVAILABILITY ====================
data class SeatAvailabilityResponse(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<SeatAvailability>?
)

data class SeatAvailability(
    @SerializedName("date") val date: String,
    @SerializedName("status") val status: String,    // e.g. "AVL-120", "WL-5", "RAC-3"
    @SerializedName("confirm_probability") val confirmProbability: String?,
    @SerializedName("confirm_probability_percent") val confirmProbabilityPercent: String?
)

// ==================== PNR STATUS ====================
data class PnrResponse(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: PnrData?
)

data class PnrData(
    @SerializedName("pnrNumber") val pnrNumber: String,
    @SerializedName("dateOfJourney") val dateOfJourney: String,
    @SerializedName("trainNumber") val trainNumber: String,
    @SerializedName("trainName") val trainName: String,
    @SerializedName("sourceStation") val sourceStation: String,
    @SerializedName("destinationStation") val destinationStation: String,
    @SerializedName("from") val from: String,
    @SerializedName("to") val to: String,
    @SerializedName("chartStatus") val chartStatus: String,
    @SerializedName("boardingPoint") val boardingPoint: String,
    @SerializedName("journeyClass") val journeyClass: String,
    @SerializedName("numberOfpassenger") val numberOfPassenger: Int,
    @SerializedName("passengerList") val passengerList: List<PnrPassenger>
)

data class PnrPassenger(
    @SerializedName("currentBerthNo") val currentBerthNo: String,
    @SerializedName("currentStatus") val currentStatus: String,
    @SerializedName("bookingStatus") val bookingStatus: String,
    @SerializedName("coachPosition") val coachPosition: Int
) : Serializable

// ==================== LIVE STATUS ====================
data class LiveStatusResponse(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: LiveStatusData?
)

data class LiveStatusData(
    @SerializedName("train_number") val trainNumber: String,
    @SerializedName("train_name") val trainName: String,
    @SerializedName("updated_time") val updatedTime: String,
    @SerializedName("current_station_name") val currentStationName: String,
    @SerializedName("current_station_code") val currentStationCode: String,
    @SerializedName("status") val statusText: String,
    @SerializedName("delay") val delay: String,
    @SerializedName("previous_stations") val previousStations: List<LiveStation>?,
    @SerializedName("upcoming_stations") val upcomingStations: List<LiveStation>?
)

data class LiveStation(
    @SerializedName("station_name") val stationName: String,
    @SerializedName("station_code") val stationCode: String,
    @SerializedName("arrives") val arrives: String,
    @SerializedName("departs") val departs: String,
    @SerializedName("delay") val delay: String,
    @SerializedName("halt") val halt: String,
    @SerializedName("distance") val distance: String,
    @SerializedName("platform") val platform: String
) : Serializable


