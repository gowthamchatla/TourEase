package com.tourease.app.network

import com.tourease.app.models.TrainSearchResponse
import com.tourease.app.models.FareResponse
import com.tourease.app.models.SeatAvailabilityResponse
import com.tourease.app.models.PnrResponse
import com.tourease.app.models.LiveStatusResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RailwayApiService {

    // Search trains between stations
    @GET("api/v3/trainBetweenStations")
    suspend fun getTrainsBetweenStations(
        @Query("fromStationCode") from: String,
        @Query("toStationCode") to: String,
        @Query("dateOfJourney") date: String
    ): Response<TrainSearchResponse>

    // Get fare for a train
    @GET("api/v2/getFare")
    suspend fun getFare(
        @Query("trainNo") trainNo: String,
        @Query("fromStationCode") from: String,
        @Query("toStationCode") to: String
    ): Response<FareResponse>

    // Check seat availability
    @GET("api/v2/checkSeatAvailability")
    suspend fun checkSeatAvailability(
        @Query("classType") classType: String,
        @Query("fromStationCode") from: String,
        @Query("toStationCode") to: String,
        @Query("trainNo") trainNo: String,
        @Query("date") date: String,
        @Query("quota") quota: String
    ): Response<SeatAvailabilityResponse>

    // PNR Status
    @GET("api/v3/getPNRStatus")
    suspend fun getPnrStatus(
        @Query("pnrNumber") pnrNumber: String
    ): Response<PnrResponse>

    // Live Train Status
    @GET("api/v1/liveTrainStatus")
    suspend fun getLiveTrainStatus(
        @Query("trainNo") trainNo: String,
        @Query("startDay") startDay: String
    ): Response<LiveStatusResponse>
}
