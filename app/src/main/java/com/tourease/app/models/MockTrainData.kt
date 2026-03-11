package com.tourease.app.models

/**
 * Provides realistic mock data for all train features.
 * When ready to go live, replace mock calls with real API calls in RailwayRepository.
 */
object MockTrainData {

    // ==================== MOCK FARE DATA ====================
    fun getMockFare(trainNo: String, fromCode: String, toCode: String): FareResponse {
        // Generate realistic fares based on distance heuristic
        val baseFares = mapOf(
            "SL" to (150..450),
            "3A" to (400..1200),
            "2A" to (600..1800),
            "1A" to (1000..3200),
            "2S" to (80..200),
            "CC" to (350..900),
            "3E" to (300..900)
        )

        val generalFares = mutableListOf<FareClass>()
        val tatkalFares = mutableListOf<FareClass>()

        for ((classType, range) in baseFares) {
            val baseFare = range.random()
            val reservationCharge = when (classType) {
                "SL" -> 20
                "2S" -> 15
                "3A", "3E" -> 40
                "2A", "CC" -> 50
                "1A" -> 60
                else -> 30
            }
            val gst = (baseFare * 0.05).toInt()
            val totalFare = baseFare + reservationCharge + gst

            generalFares.add(
                FareClass(
                    classType = classType,
                    fare = totalFare,
                    breakup = listOf(
                        FareBreakup("Base Charges", "baseFare", baseFare),
                        FareBreakup("Reservation Charges", "reservationCharges", reservationCharge),
                        FareBreakup("GST", "serviceTax", gst),
                        FareBreakup("Total Amount", "total", totalFare)
                    )
                )
            )

            // Tatkal = general + tatkal surcharge
            val tatkalCharge = when (classType) {
                "SL" -> 100
                "2S" -> 15
                "3A", "3E" -> 300
                "2A", "CC" -> 400
                "1A" -> 500
                else -> 200
            }
            val tatkalTotal = totalFare + tatkalCharge

            tatkalFares.add(
                FareClass(
                    classType = classType,
                    fare = tatkalTotal,
                    breakup = listOf(
                        FareBreakup("Base Charges", "baseFare", baseFare),
                        FareBreakup("Reservation Charges", "reservationCharges", reservationCharge),
                        FareBreakup("GST", "serviceTax", gst),
                        FareBreakup("Tatkal Charges", "tatkalCharges", tatkalCharge),
                        FareBreakup("Total Amount", "total", tatkalTotal)
                    )
                )
            )
        }

        return FareResponse(
            status = true,
            message = "Success",
            data = FareData(general = generalFares, tatkal = tatkalFares)
        )
    }

    // ==================== MOCK SEAT AVAILABILITY ====================
    fun getMockSeatAvailability(trainNo: String, classType: String): SeatAvailabilityResponse {
        val statuses = listOf(
            "AVL-${(10..250).random()}",
            "AVL-${(1..50).random()}",
            "RAC-${(1..15).random()}",
            "WL-${(1..30).random()}",
            "REGRET/WL",
            "AVL-${(50..200).random()}"
        )

        val dates = listOf(
            SeatAvailability("03-03-2026", statuses.random(), "High", "85%"),
            SeatAvailability("04-03-2026", statuses.random(), "Medium", "62%"),
            SeatAvailability("05-03-2026", statuses.random(), "High", "78%"),
            SeatAvailability("06-03-2026", statuses.random(), "Low", "35%"),
            SeatAvailability("07-03-2026", statuses.random(), "High", "90%"),
            SeatAvailability("08-03-2026", statuses.random(), "Medium", "55%")
        )

        return SeatAvailabilityResponse(
            status = true,
            message = "Success",
            data = dates
        )
    }

    // ==================== MOCK PNR STATUS ====================
    fun getMockPnrStatus(pnrNumber: String): PnrResponse {
        val passengers = listOf(
            PnrPassenger("B3-42", "CNF/B3/42", "CNF/B3/42", 5),
            PnrPassenger("B3-43", "CNF/B3/43", "CNF/B3/43", 5)
        )

        return PnrResponse(
            status = true,
            message = "Success",
            data = PnrData(
                pnrNumber = pnrNumber,
                dateOfJourney = "05-03-2026",
                trainNumber = "12952",
                trainName = "New Delhi - Mumbai Central Rajdhani Express",
                sourceStation = "NDLS",
                destinationStation = "MMCT",
                from = "NEW DELHI",
                to = "MUMBAI CENTRAL",
                chartStatus = "Chart Not Prepared",
                boardingPoint = "NDLS",
                journeyClass = "3A",
                numberOfPassenger = 2,
                passengerList = passengers
            )
        )
    }

    // ==================== MOCK LIVE STATUS ====================
    fun getMockLiveStatus(trainNo: String): LiveStatusResponse {
        val previousStations = listOf(
            LiveStation("NEW DELHI", "NDLS", "16:55", "16:55", "0", "-", "0", "16"),
            LiveStation("MATHURA JN", "MTJ", "19:12", "19:14", "5", "2 min", "141", "3"),
            LiveStation("KOTA JN", "KOTA", "23:48", "23:53", "8", "5 min", "463", "1"),
            LiveStation("RATLAM JN", "RTM", "02:20", "02:25", "12", "5 min", "618", "2")
        )

        val upcomingStations = listOf(
            LiveStation("VADODARA JN", "BRC", "05:45", "05:50", "-", "5 min", "844", "4"),
            LiveStation("SURAT", "ST", "07:32", "07:34", "-", "2 min", "992", "2"),
            LiveStation("BORIVALI", "BVI", "11:30", "11:32", "-", "2 min", "1325", "1"),
            LiveStation("MUMBAI CENTRAL", "MMCT", "12:15", "12:15", "-", "-", "1384", "5")
        )

        return LiveStatusResponse(
            status = true,
            message = "Success",
            data = LiveStatusData(
                trainNumber = trainNo,
                trainName = "New Delhi - Mumbai Central Rajdhani Express",
                updatedTime = "03:45 AM, 03 Mar 2026",
                currentStationName = "RATLAM JN",
                currentStationCode = "RTM",
                statusText = "Train is running late by 12 minutes",
                delay = "12",
                previousStations = previousStations,
                upcomingStations = upcomingStations
            )
        )
    }
}

