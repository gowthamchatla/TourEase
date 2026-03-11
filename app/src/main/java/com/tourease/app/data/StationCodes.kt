package com.tourease.app.data

object StationCodes {

    val stations = mapOf(
        // Metro Cities
        "New Delhi" to "NDLS",
        "Delhi Junction" to "DLI",
        "Delhi Hazrat Nizamuddin" to "NZM",
        "Delhi Sarai Rohilla" to "DEE",
        "Mumbai Central" to "MMCT",
        "Mumbai CSMT" to "CSMT",
        "Mumbai Bandra Terminus" to "BDTS",
        "Mumbai Lokmanya Tilak" to "LTT",
        "Mumbai Dadar" to "DR",
        "Chennai Central" to "MAS",
        "Chennai Egmore" to "MS",
        "Chennai" to "MAS",
        "Kolkata Howrah" to "HWH",
        "Howrah Jn" to "HWH",
        "Kolkata Sealdah" to "SDAH",
        "Bangalore City" to "SBC",
        "Bangalore" to "SBC",
        "Bengaluru Cantonment" to "BNC",
        "Hyderabad Deccan" to "HYB",
        "Secunderabad" to "SC",
        "Hyderabad" to "SC",

        // Andhra Pradesh
        "Nellore" to "NLR",
        "Vijayawada" to "BZA",
        "Visakhapatnam" to "VSKP",
        "Tirupati" to "TPTY",
        "Guntur" to "GNT",
        "Rajahmundry" to "RJY",
        "Kakinada" to "CCT",
        "Kurnool" to "KRNT",
        "Anantapur" to "ATP",
        "Ongole" to "OGL",
        "Eluru" to "EE",
        "Tenali" to "TEL",
        "Gudur" to "GDR",
        "Chirala" to "CLX",
        "Kadapa" to "HX",

        // Tamil Nadu
        "Madurai" to "MDU",
        "Coimbatore" to "CBE",
        "Salem" to "SA",
        "Trichy" to "TPJ",
        "Tirunelveli" to "TEN",
        "Erode" to "ED",
        "Vellore" to "KPD",
        "Thanjavur" to "TJ",
        "Kumbakonam" to "KMU",
        "Rameswaram" to "RMM",
        "Kanyakumari" to "CAPE",
        "Nagercoil" to "NCJ",
        "Karur" to "KRR",
        "Villupuram" to "VM",

        // Kerala
        "Trivandrum" to "TVC",
        "Kochi" to "ERS",
        "Ernakulam" to "ERS",
        "Kozhikode" to "CLT",
        "Thrissur" to "TCR",
        "Kannur" to "CAN",
        "Alappuzha" to "ALLP",
        "Kollam" to "QLN",
        "Palakkad" to "PGT",
        "Kottayam" to "KTYM",

        // Karnataka
        "Mysore" to "MYS",
        "Mangalore" to "MAQ",
        "Hubli" to "UBL",
        "Belgaum" to "BGM",
        "Gulbarga" to "GR",
        "Davangere" to "DVG",
        "Shimoga" to "SMET",
        "Tumkur" to "TK",
        "Hassan" to "HAS",
        "Bijapur" to "BJP",

        // Maharashtra
        "Pune" to "PUNE",
        "Nagpur" to "NGP",
        "Nashik" to "NK",
        "Aurangabad" to "AWB",
        "Solapur" to "SUR",
        "Kolhapur" to "KOP",
        "Thane" to "TNA",
        "Panvel" to "PNVL",
        "Kalyan" to "KYN",
        "Nanded" to "NED",
        "Amravati" to "AMI",
        "Akola" to "AK",
        "Bhusaval" to "BSL",

        // Gujarat
        "Ahmedabad" to "ADI",
        "Surat" to "ST",
        "Vadodara" to "BRC",
        "Rajkot" to "RJT",
        "Bhavnagar" to "BVP",
        "Jamnagar" to "JAM",
        "Junagadh" to "JND",
        "Gandhidham" to "GIMB",
        "Porbandar" to "PBR",
        "Bhuj" to "BHUJ",

        // Rajasthan
        "Jaipur" to "JP",
        "Jodhpur" to "JU",
        "Udaipur" to "UDZ",
        "Ajmer" to "AII",
        "Bikaner" to "BKN",
        "Kota" to "KOTA",
        "Alwar" to "AWR",
        "Bharatpur" to "BTE",
        "Abu Road" to "ABR",
        "Jaisalmer" to "JSM",

        // Uttar Pradesh
        "Lucknow" to "LKO",
        "Varanasi" to "BSB",
        "Agra" to "AGC",
        "Agra Cantt" to "AGC",
        "Kanpur Central" to "CNB",
        "Allahabad" to "ALD",
        "Prayagraj" to "PRYJ",
        "Gorakhpur" to "GKP",
        "Meerut" to "MTC",
        "Aligarh" to "ALJN",
        "Mathura" to "MTJ",
        "Bareilly" to "BE",
        "Moradabad" to "MB",
        "Jhansi" to "JHS",
        "Ayodhya" to "AY",
        "Ghaziabad" to "GZB",

        // Madhya Pradesh
        "Bhopal" to "BPL",
        "Indore" to "INDB",
        "Gwalior" to "GWL",
        "Jabalpur" to "JBP",
        "Ujjain" to "UJN",
        "Ratlam" to "RTM",
        "Satna" to "STA",
        "Itarsi" to "ET",

        // West Bengal
        "Asansol" to "ASN",
        "Durgapur" to "DGR",
        "Siliguri" to "SGUJ",
        "New Jalpaiguri" to "NJP",
        "Kharagpur" to "KGP",
        "Malda Town" to "MLDT",
        "Bolpur" to "BHP",

        // Bihar
        "Patna" to "PNBE",
        "Gaya" to "GAYA",
        "Muzaffarpur" to "MFP",
        "Bhagalpur" to "BGP",
        "Darbhanga" to "DBG",
        "Rajgir" to "RGD",

        // Jharkhand
        "Ranchi" to "RNC",
        "Jamshedpur" to "TATA",
        "Dhanbad" to "DHN",
        "Bokaro" to "BKSC",
        "Hazaribagh" to "HZD",

        // Odisha
        "Bhubaneswar" to "BBS",
        "Puri" to "PURI",
        "Cuttack" to "CTC",
        "Sambalpur" to "SBP",
        "Berhampur" to "BAM",
        "Rourkela" to "ROU",

        // Punjab
        "Amritsar" to "ASR",
        "Chandigarh" to "CDG",
        "Ludhiana" to "LDH",
        "Jalandhar" to "JUC",
        "Patiala" to "PTA",
        "Pathankot" to "PTK",
        "Bathinda" to "BTI",
        "Firozpur" to "FZR",

        // Haryana
        "Ambala" to "UMB",
        "Karnal" to "KUN",
        "Hisar" to "HSR",
        "Rohtak" to "ROK",
        "Panipat" to "PNP",
        "Kurukshetra" to "KKDE",

        // Uttarakhand
        "Dehradun" to "DDN",
        "Haridwar" to "HW",
        "Rishikesh" to "RSH",
        "Roorkee" to "RK",
        "Kathgodam" to "KGM",

        // Goa
        "Goa" to "MAO",
        "Madgaon" to "MAO",
        "Vasco Da Gama" to "VSG",

        // North East
        "Guwahati" to "GHY",
        "Dibrugarh" to "DBRG",
        "Jorhat" to "JTTN",
        "Silchar" to "SCL",
        "Agartala" to "AGTL",

        // Chhattisgarh
        "Raipur" to "R",
        "Bilaspur" to "BSP",
        "Durg" to "DURG",

        // Telangana
        "Warangal" to "WL",
        "Nizamabad" to "NZB",
        "Karimnagar" to "KRMR",
        "Khammam" to "KMT",

        // Jammu & Kashmir
        "Jammu Tawi" to "JAT",
        "Katra" to "SVDK",
        "Udhampur" to "UHP"
    )

    fun getCode(stationName: String): String? {
        return stations.entries.find {
            it.key.equals(stationName, ignoreCase = true)
        }?.value
    }

    fun getStationNames(): List<String> = stations.keys.toList().sorted()

    // Autocomplete - search stations by partial name
    fun searchStations(query: String): List<String> {
        if (query.isEmpty()) return emptyList()
        return stations.keys
            .filter { it.contains(query, ignoreCase = true) }
            .sorted()
            .take(5)  // Show max 5 suggestions
    }
}