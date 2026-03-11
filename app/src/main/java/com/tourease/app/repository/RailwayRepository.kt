package com.tourease.app.repository

import android.util.Log
import com.tourease.app.models.TrainInfo
import com.tourease.app.network.RailwayApiService
import com.tourease.app.network.RetrofitClient

class RailwayRepository {

    private val api = RetrofitClient.instance.create(RailwayApiService::class.java)

    suspend fun searchTrains(
        fromCode: String,
        toCode: String,
        date: String
    ): Result<List<TrainInfo>> {
        return try {
            Log.d("RailwayRepo", "Calling API: from=$fromCode, to=$toCode, date=$date")
            val response = api.getTrainsBetweenStations(fromCode, toCode, date)
            Log.d("RailwayRepo", "Response code: ${response.code()}")

            if (response.isSuccessful) {
                val body = response.body()
                Log.d("RailwayRepo", "Status: ${body?.status}, Message: ${body?.message}")

                if (body?.status == true && body.data != null) {
                    Log.d("RailwayRepo", "Found ${body.data.size} trains")
                    Result.success(body.data)
                } else {
                    Log.e("RailwayRepo", "API returned status=false: ${body?.message}")
                    Result.failure(Exception(body?.message ?: "No data"))
                }
            } else {
                Log.e("RailwayRepo", "HTTP error: ${response.code()}")
                Result.failure(Exception("HTTP ${response.code()}"))
            }
        } catch (e: Exception) {
            Log.e("RailwayRepo", "Network error", e)
            Result.failure(e)
        }
    }
}