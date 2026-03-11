package com.tourease.app.models

import com.tourease.app.Bus

object BusData {

    private val allBuses = listOf(

        // Delhi → Mumbai
        Bus("DL-MU-001", "Rajdhani Express Bus", "MSRTC", "Delhi", "Mumbai",
            "06:00", "32:00", "26h 00m", "Sleeper", "WiFi, Charging, Blanket",
            1200, 950, 700, 12, 18, 30, 4.3f),
        Bus("DL-MU-002", "Night Rider", "Orange Travels", "Delhi", "Mumbai",
            "19:00", "21:00+1", "26h 00m", "Semi-Sleeper", "Charging, Water",
            950, 800, 550, 8, 20, 35, 4.1f),
        Bus("DL-MU-003", "VRL Volvo", "VRL Travels", "Delhi", "Mumbai",
            "21:30", "23:30+1", "26h 00m", "Sleeper", "WiFi, Charging, Blanket, Pillow",
            1400, 1100, 0, 14, 22, 0, 4.5f),

        // Mumbai → Delhi
        Bus("MU-DL-001", "Capital Express", "MSRTC", "Mumbai", "Delhi",
            "07:00", "09:00+1", "26h 00m", "Sleeper", "WiFi, Charging, Blanket",
            1200, 950, 700, 10, 16, 28, 4.2f),
        Bus("MU-DL-002", "Midnight Express", "SRS Travels", "Mumbai", "Delhi",
            "20:00", "22:00+1", "26h 00m", "Semi-Sleeper", "Charging, Water",
            900, 750, 500, 6, 18, 32, 4.0f),

        // Chennai → Bangalore
        Bus("CH-BL-001", "Metro Link", "KSRTC", "Chennai", "Bangalore",
            "06:00", "11:30", "5h 30m", "Sitting", "AC, Charging",
            0, 0, 350, 0, 0, 40, 4.2f),
        Bus("CH-BL-002", "Night Queen", "KPN Travels", "Chennai", "Bangalore",
            "22:00", "03:30+1", "5h 30m", "Sleeper", "WiFi, Charging, Blanket",
            850, 650, 400, 16, 20, 30, 4.4f),
        Bus("CH-BL-003", "Volvo Express", "SRM Travels", "Chennai", "Bangalore",
            "08:30", "14:00", "5h 30m", "Semi-Sleeper", "AC, Charging, Water",
            0, 600, 380, 0, 24, 36, 4.3f),

        // Bangalore → Chennai
        Bus("BL-CH-001", "City Connect", "KSRTC", "Bangalore", "Chennai",
            "07:00", "12:30", "5h 30m", "Sitting", "AC, Charging",
            0, 0, 350, 0, 0, 42, 4.1f),
        Bus("BL-CH-002", "Night Star", "KPN Travels", "Bangalore", "Chennai",
            "21:30", "03:00+1", "5h 30m", "Sleeper", "WiFi, Charging, Blanket",
            850, 650, 400, 14, 18, 28, 4.4f),

        // Hyderabad → Pune
        Bus("HY-PU-001", "Deccan Queen Bus", "APSRTC", "Hyderabad", "Pune",
            "17:00", "06:00+1", "13h 00m", "Sleeper", "WiFi, Charging, Blanket",
            1100, 850, 600, 12, 20, 30, 4.2f),
        Bus("HY-PU-002", "Highway Star", "VRL Travels", "Hyderabad", "Pune",
            "19:30", "08:30+1", "13h 00m", "Semi-Sleeper", "Charging, Water, Snacks",
            0, 800, 550, 0, 22, 35, 4.3f),
        Bus("HY-PU-003", "Midnight Rider", "Orange Travels", "Hyderabad", "Pune",
            "21:00", "10:00+1", "13h 00m", "Sleeper", "WiFi, Charging, Blanket, Pillow",
            1300, 1000, 0, 16, 18, 0, 4.5f),

        // Pune → Hyderabad
        Bus("PU-HY-001", "Nizam Express", "MSRTC", "Pune", "Hyderabad",
            "18:00", "07:00+1", "13h 00m", "Sleeper", "WiFi, Charging, Blanket",
            1100, 850, 600, 10, 18, 28, 4.1f),
        Bus("PU-HY-002", "Road Runner", "SRS Travels", "Pune", "Hyderabad",
            "20:00", "09:00+1", "13h 00m", "Semi-Sleeper", "Charging, Water",
            0, 780, 530, 0, 20, 32, 4.0f),

        // Kolkata → Bhubaneswar
        Bus("KO-BH-001", "East Coast Express", "OSRTC", "Kolkata", "Bhubaneswar",
            "08:00", "14:30", "6h 30m", "Sitting", "AC, Charging",
            0, 0, 400, 0, 0, 38, 4.0f),
        Bus("KO-BH-002", "Night Cruiser", "Greenline", "Kolkata", "Bhubaneswar",
            "22:30", "05:00+1", "6h 30m", "Sleeper", "WiFi, Charging, Blanket",
            900, 700, 450, 14, 20, 30, 4.2f),

        // Bhubaneswar → Kolkata
        Bus("BH-KO-001", "Bay of Bengal Bus", "OSRTC", "Bhubaneswar", "Kolkata",
            "07:30", "14:00", "6h 30m", "Sitting", "AC, Charging",
            0, 0, 400, 0, 0, 36, 3.9f),
        Bus("BH-KO-002", "Temple City Express", "Greenline", "Bhubaneswar", "Kolkata",
            "21:00", "03:30+1", "6h 30m", "Sleeper", "WiFi, Charging, Blanket",
            900, 700, 450, 12, 18, 28, 4.1f),

        // Delhi → Jaipur
        Bus("DL-JP-001", "Pink City Express", "RSRTC", "Delhi", "Jaipur",
            "06:00", "11:00", "5h 00m", "Sitting", "AC, Charging",
            0, 0, 300, 0, 0, 44, 4.3f),
        Bus("DL-JP-002", "Rajputana Volvo", "RSRTC", "Delhi", "Jaipur",
            "08:30", "13:30", "5h 00m", "Semi-Sleeper", "AC, WiFi, Charging",
            0, 550, 350, 0, 26, 0, 4.5f),
        Bus("DL-JP-003", "Desert Storm", "SRS Travels", "Delhi", "Jaipur",
            "23:00", "04:00+1", "5h 00m", "Sleeper", "WiFi, Charging, Blanket",
            800, 600, 380, 18, 22, 0, 4.2f),

        // Mumbai → Goa
        Bus("MU-GO-001", "Konkan Cruiser", "KSRTC", "Mumbai", "Goa",
            "07:00", "17:00", "10h 00m", "Sitting", "AC, Charging, Scenic Route",
            0, 0, 600, 0, 0, 40, 4.4f),
        Bus("MU-GO-002", "Beach Bum Express", "Paulo Travels", "Mumbai", "Goa",
            "21:00", "07:00+1", "10h 00m", "Sleeper", "WiFi, Charging, Blanket, Pillow",
            1200, 950, 0, 16, 20, 0, 4.6f),
        Bus("MU-GO-003", "Coastal King", "VRL Travels", "Mumbai", "Goa",
            "22:30", "08:30+1", "10h 00m", "Semi-Sleeper", "AC, Charging, Water",
            0, 900, 600, 0, 24, 30, 4.3f)
    )

    fun getBuses(from: String, to: String): List<Bus> {
        val fromLower = from.lowercase().trim()
        val toLower = to.lowercase().trim()

        return allBuses.filter { bus ->
            bus.fromCity.lowercase().contains(fromLower) ||
                    fromLower.contains(bus.fromCity.lowercase())
        }.filter { bus ->
            bus.toCity.lowercase().contains(toLower) ||
                    toLower.contains(bus.toCity.lowercase())
        }
    }
}