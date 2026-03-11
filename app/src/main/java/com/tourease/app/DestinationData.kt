package com.tourease.app

object DestinationsData {

    fun getAllDestinations(): List<Destination> {
        return listOf(
            // BEACHES
            Destination(1, "Goa", "Beaches", 4.8, "₹₹", "🏖️",
                "India's party capital with stunning beaches and Portuguese heritage",
                "Nov-Feb", "4-5 days",
                listOf("Beach parties", "Water sports", "Fort Aguada", "Baga Beach")),

            Destination(2, "Andaman Islands", "Beaches", 4.9, "₹₹₹", "🏝️",
                "Crystal clear waters and pristine beaches",
                "Oct-May", "5-7 days",
                listOf("Radhanagar Beach", "Scuba diving", "Cellular Jail", "Neil Island")),

            Destination(3, "Varkala", "Beaches", 4.6, "₹₹", "🌊",
                "Cliffside beaches and ayurvedic treatments",
                "Oct-Mar", "2-3 days",
                listOf("Cliff beaches", "Ayurveda", "Papanasam Beach", "Surfing")),

            Destination(4, "Gokarna", "Beaches", 4.7, "₹", "🏖️",
                "Peaceful beaches and temples",
                "Oct-Mar", "2-3 days",
                listOf("Om Beach", "Paradise Beach", "Beach camping", "Mahabaleshwar Temple")),

            // MOUNTAINS
            Destination(5, "Manali", "Mountains", 4.7, "₹₹₹", "⛰️",
                "Snow-capped peaks and adventure sports",
                "Mar-Jun, Oct-Feb", "4-5 days",
                listOf("Rohtang Pass", "Solang Valley", "Old Manali", "Paragliding")),

            Destination(6, "Leh-Ladakh", "Mountains", 4.9, "₹₹₹₹", "🏔️",
                "High altitude desert with stunning landscapes",
                "May-Sep", "7-10 days",
                listOf("Pangong Lake", "Nubra Valley", "Khardung La", "Monasteries")),

            Destination(7, "Shimla", "Mountains", 4.5, "₹₹", "🏔️",
                "Colonial hill station with pine forests",
                "Mar-Jun, Sep-Dec", "3-4 days",
                listOf("Mall Road", "Jakhu Temple", "Kufri", "Ridge")),

            Destination(8, "Rishikesh", "Mountains", 4.8, "₹₹", "⛰️",
                "Yoga capital and adventure hub",
                "Sep-Nov, Mar-May", "3-4 days",
                listOf("River rafting", "Beatles Ashram", "Lakshman Jhula", "Yoga retreats")),

            Destination(9, "Munnar", "Mountains", 4.7, "₹₹", "🌄",
                "Tea plantations and misty hills",
                "Sep-May", "3-4 days",
                listOf("Tea gardens", "Eravikulam Park", "Mattupetty Dam", "Echo Point")),

            // HERITAGE
            Destination(10, "Jaipur", "Heritage", 4.6, "₹₹", "🏛️",
                "Pink City with majestic forts and palaces",
                "Oct-Mar", "3-4 days",
                listOf("Amber Fort", "City Palace", "Hawa Mahal", "Jantar Mantar")),

            Destination(11, "Agra", "Heritage", 4.8, "₹₹", "🕌",
                "Home to the iconic Taj Mahal",
                "Oct-Mar", "1-2 days",
                listOf("Taj Mahal", "Agra Fort", "Fatehpur Sikri", "Mehtab Bagh")),

            Destination(12, "Udaipur", "Heritage", 4.7, "₹₹₹", "🏰",
                "City of Lakes with romantic palaces",
                "Oct-Mar", "2-3 days",
                listOf("City Palace", "Lake Pichola", "Jag Mandir", "Boat rides")),

            Destination(13, "Hampi", "Heritage", 4.9, "₹", "🗿",
                "UNESCO site with ancient ruins",
                "Oct-Feb", "2-3 days",
                listOf("Virupaksha Temple", "Stone chariot", "Boulder climbing", "Hippie Island")),

            Destination(14, "Varanasi", "Heritage", 4.7, "₹₹", "🕉️",
                "Oldest living city and spiritual capital",
                "Oct-Mar", "2-3 days",
                listOf("Ganga Aarti", "Boat ride", "Kashi Vishwanath", "Sarnath")),

            // NATURE
            Destination(15, "Coorg", "Nature", 4.6, "₹₹", "🌳",
                "Scotland of India with coffee plantations",
                "Oct-Mar", "2-3 days",
                listOf("Coffee estates", "Abbey Falls", "Raja's Seat", "Dubare")),

            Destination(16, "Wayanad", "Nature", 4.7, "₹₹", "🌿",
                "Wildlife and waterfalls",
                "Oct-May", "2-3 days",
                listOf("Chembra Peak", "Edakkal Caves", "Wildlife sanctuary", "Soochipara Falls")),

            Destination(17, "Valley of Flowers", "Nature", 4.9, "₹₹₹", "🌺",
                "UNESCO site with alpine flowers",
                "Jun-Oct", "4-5 days",
                listOf("Trek", "Hemkund Sahib", "Rare flowers", "Mountain views")),

            Destination(18, "Jim Corbett", "Nature", 4.5, "₹₹₹", "🐅",
                "India's oldest national park",
                "Nov-Jun", "2-3 days",
                listOf("Tiger safari", "Wildlife", "Corbett Falls", "River rafting")),

            // SPIRITUAL
            Destination(19, "Tirupati", "Spiritual", 4.8, "₹₹", "🛕",
                "Famous Venkateswara Temple",
                "Sep-Mar", "1-2 days",
                listOf("Balaji Temple", "Akasa Ganga", "Sri Kapileswara Swamy", "Deer Park")),

            Destination(20, "Amritsar", "Spiritual", 4.8, "₹₹", "🕌",
                "Golden Temple and Punjabi culture",
                "Oct-Mar", "2 days",
                listOf("Golden Temple", "Wagah Border", "Jallianwala Bagh", "Langar")),

            Destination(21, "Pushkar", "Spiritual", 4.6, "₹", "🕉️",
                "Sacred lake and camel fair",
                "Oct-Mar", "1-2 days",
                listOf("Brahma Temple", "Pushkar Lake", "Camel Fair", "Desert safari")),

            // ADVENTURE
            Destination(22, "Spiti Valley", "Adventure", 4.9, "₹₹₹", "🏔️",
                "Remote high-altitude desert",
                "May-Oct", "7-10 days",
                listOf("Key Monastery", "Chandratal Lake", "Kaza", "Bike trip")),

            Destination(23, "Bir Billing", "Adventure", 4.8, "₹₹", "🪂",
                "Paragliding capital of India",
                "Mar-Jun, Sep-Nov", "2-3 days",
                listOf("Paragliding", "Monasteries", "Camping", "Trekking")),

            Destination(24, "Auli", "Adventure", 4.6, "₹₹₹", "⛷️",
                "Premier skiing destination",
                "Nov-Mar", "3-4 days",
                listOf("Skiing", "Cable car", "Joshimath", "Mountain views")),

            // FOOD
            Destination(25, "Kolkata", "Food", 4.7, "₹₹", "🍛",
                "City of joy and street food",
                "Oct-Mar", "2-3 days",
                listOf("Bengali food", "Victoria Memorial", "Park Street", "Howrah Bridge")),

            Destination(26, "Lucknow", "Food", 4.6, "₹₹", "🍢",
                "Nawabi cuisine and kebabs",
                "Oct-Mar", "2 days",
                listOf("Tunday Kababi", "Bara Imambara", "Chowk", "Rumi Darwaza")),

            Destination(27, "Amritsar", "Food", 4.8, "₹₹", "🫓",
                "Punjabi food paradise",
                "Oct-Mar", "2 days",
                listOf("Kulcha", "Lassi", "Langar food", "Bharawan Da Dhaba")),

            // SHOPPING
            Destination(28, "Mumbai", "Shopping", 4.5, "₹₹₹₹", "🛍️",
                "Financial capital and Bollywood",
                "Nov-Feb", "3-4 days",
                listOf("Colaba Causeway", "Marine Drive", "Gateway of India", "Fashion Street")),

            Destination(29, "Delhi", "Shopping", 4.6, "₹₹₹", "🏬",
                "Capital city with diverse markets",
                "Oct-Mar", "3-4 days",
                listOf("Chandni Chowk", "Sarojini Nagar", "Dilli Haat", "Khan Market")),

            Destination(30, "Jaipur", "Shopping", 4.7, "₹₹", "💎",
                "Jewelry and handicrafts hub",
                "Oct-Mar", "2-3 days",
                listOf("Johari Bazaar", "Bapu Bazaar", "Gems", "Textiles"))
        )
    }

    fun getByCategory(category: String): List<Destination> {
        return getAllDestinations().filter { it.category == category }
    }

    fun searchDestinations(query: String): List<Destination> {
        return getAllDestinations().filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.description.contains(query, ignoreCase = true)
        }
    }
}
