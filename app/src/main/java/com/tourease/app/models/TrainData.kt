package com.tourease.app.models

import com.tourease.app.Train

object TrainData {

    fun getTrains(from: String, to: String): List<Train> {
        val allTrains = listOf(
            // Delhi to Mumbai
            Train(
                "12951",
                "Mumbai Rajdhani",
                "New Delhi",
                "Mumbai Central",
                "16:55",
                "08:35",
                "15h 40m",
                "Daily",
                850,
                2200,
                3100,
                4500,
                120,
                85,
                45,
                20
            ),
            Train(
                "12909",
                "Maharashtra Express",
                "New Delhi",
                "Mumbai CST",
                "17:20",
                "10:05",
                "16h 45m",
                "Daily",
                750,
                2050,
                2900,
                4200,
                150,
                95,
                50,
                25
            ),

            // Mumbai to Delhi
            Train(
                "12952",
                "New Delhi Rajdhani",
                "Mumbai Central",
                "New Delhi",
                "17:00",
                "08:35",
                "15h 35m",
                "Daily",
                850,
                2200,
                3100,
                4500,
                110,
                80,
                40,
                18
            ),

            // Chennai to Bangalore
            Train(
                "12639",
                "Brindavan Express",
                "Chennai Central",
                "Bangalore City",
                "07:40",
                "13:45",
                "6h 05m",
                "Daily",
                250,
                750,
                1100,
                1650,
                200,
                120,
                60,
                30
            ),
            Train(
                "12608",
                "Lalbagh Express",
                "Chennai Central",
                "Bangalore City",
                "06:00",
                "11:45",
                "5h 45m",
                "Daily",
                230,
                720,
                1050,
                1600,
                180,
                110,
                55,
                28
            ),

            // Bangalore to Chennai
            Train(
                "12640",
                "Brindavan Express",
                "Bangalore City",
                "Chennai Central",
                "14:00",
                "20:00",
                "6h 00m",
                "Daily",
                250,
                750,
                1100,
                1650,
                195,
                115,
                58,
                29
            ),

            // Delhi to Kolkata
            Train(
                "12301",
                "Kolkata Rajdhani",
                "New Delhi",
                "Howrah Jn",
                "16:55",
                "10:05",
                "17h 10m",
                "Daily",
                900,
                2350,
                3300,
                4800,
                100,
                75,
                38,
                15
            ),

            // Mumbai to Goa
            Train(
                "10103",
                "Mandovi Express",
                "Mumbai CST",
                "Madgaon",
                "07:10",
                "18:45",
                "11h 35m",
                "Daily",
                400,
                1150,
                1650,
                2400,
                160,
                90,
                48,
                22
            ),

            // Delhi to Jaipur
            Train(
                "12015",
                "Ajmer Shatabdi",
                "New Delhi",
                "Jaipur",
                "06:05",
                "10:30",
                "4h 25m",
                "Daily",
                0,
                650,
                1200,
                0,
                0,
                180,
                90,
                0
            ),

            // Bangalore to Goa
            Train(
                "12779",
                "Goa Express",
                "Bangalore City",
                "Madgaon",
                "20:00",
                "12:30",
                "16h 30m",
                "Daily",
                450,
                1250,
                1800,
                2600,
                140,
                85,
                42,
                20
            ),

            // Hyderabad to Bangalore
            Train(
                "12785",
                "KSR Bengaluru SF",
                "Secunderabad",
                "Bangalore City",
                "19:35",
                "06:00",
                "10h 25m",
                "Daily",
                380,
                1100,
                1550,
                2250,
                165,
                95,
                50,
                24
            )
        )

        // Filter trains based on from and to stations
        return allTrains.filter { train ->
            train.fromStation.contains(from, ignoreCase = true) &&
                    train.toStation.contains(to, ignoreCase = true)
        }
    }

    fun searchStations(query: String): List<String> {
        val stations = listOf(
            "New Delhi", "Mumbai Central", "Mumbai CST", "Bangalore City",
            "Chennai Central", "Hyderabad Deccan", "Kolkata Howrah",
            "Pune Junction", "Jaipur", "Ahmedabad", "Madgaon",
            "Secunderabad", "Howrah Jn", "Bhopal", "Indore"
        )

        return if (query.isEmpty()) {
            stations
        } else {
            stations.filter { it.contains(query, ignoreCase = true) }
        }
    }
}