package com.tourease.app

object DestinationsData {

    fun getAllDestinations(): List<Destination> {
        return listOf(

            Destination(
                id = 1, name = "Araku Valley", category = "Mountains",
                rating = 4.7, priceRange = "₹", emoji = "🌄",
                description = "A hidden gem in Andhra Pradesh — lush coffee plantations, tribal culture, and a scenic toy train ride through the Eastern Ghats.",
                bestTime = "October – March", daysNeeded = "2–3 days",
                highlights = listOf("Coffee Museum", "Borra Caves", "Tribal Dance", "Padmapuram Gardens"),
                imageUrl = "https://picsum.photos/id/1004/600/400",
                entryFee = "Borra Caves: ₹60 | Padmapuram Gardens: ₹20",
                howToReach = listOf(
                    "🚆 Take a train to Visakhapatnam (Vizag) — major railway junction",
                    "🚂 Board the Kirandul Passenger train from Vizag to Araku (scenic 5hr ride)",
                    "🛺 Take auto or cab from Araku station to your resort/hotel"
                ),
                trendingSpots = listOf(
                    TrendingSpot("Borra Caves", "Million-year-old limestone caves with stunning stalactite and stalagmite formations, deep inside the Anantagiri Hills.", "October – March", "₹60", listOf("Go in the morning for best lighting", "Guides available at entrance", "Slippery inside — wear grip shoes"), listOf("https://picsum.photos/id/100/800/500", "https://picsum.photos/id/101/800/500", "https://picsum.photos/id/102/800/500")),
                    TrendingSpot("Coffee Museum", "India's first coffee museum showcasing the history of Araku coffee — one of the finest single-origin coffees in the world.", "All year", "Free", listOf("Try the fresh filter coffee here", "Buy coffee powder directly — cheapest price", "Museum has tribal art exhibits too"), listOf("https://picsum.photos/id/110/800/500", "https://picsum.photos/id/111/800/500", "https://picsum.photos/id/112/800/500")),
                    TrendingSpot("Chaparai Water Cascade", "A natural cascade where water flows over flat rocks forming a wide shallow stream — perfect for wading and photography.", "July – February", "Free", listOf("Best visited after monsoon for full flow", "Slippery rocks — walk carefully", "Carry snacks, no shops nearby"), listOf("https://picsum.photos/id/120/800/500", "https://picsum.photos/id/121/800/500", "https://picsum.photos/id/122/800/500")),
                    TrendingSpot("Padmapuram Gardens", "A beautifully maintained botanical garden with a toy train for kids, treehouses, and stunning valley views.", "October – March", "₹20", listOf("Toy train ride inside is fun even for adults", "Reach early — gets crowded by noon", "Great picnic spot"), listOf("https://picsum.photos/id/130/800/500", "https://picsum.photos/id/131/800/500", "https://picsum.photos/id/132/800/500")),
                    TrendingSpot("Ananthagiri Hills", "The misty hills surrounding Araku, covered in dense forest and coffee plantations — best explored by bike.", "October – February", "Free", listOf("Rent a bike in Araku town", "Fog in the morning makes it magical", "Tribal villages on the route worth visiting"), listOf("https://picsum.photos/id/140/800/500", "https://picsum.photos/id/141/800/500", "https://picsum.photos/id/142/800/500"))
                ),
                tips = listOf("Book the Araku toy train in advance — seats fill fast", "Try filter coffee at the Araku Coffee museum", "Visit Borra Caves early morning to avoid crowds", "Carry light woolens — nights get cold Oct–Feb")
            ),

            Destination(
                id = 2, name = "Munnar", category = "Mountains",
                rating = 4.8, priceRange = "₹₹", emoji = "🌿",
                description = "Kerala's crown jewel — endless tea gardens, misty peaks, rare Neelakurinji flowers, and cool mountain air all year.",
                bestTime = "September – May", daysNeeded = "3–4 days",
                highlights = listOf("Tea Plantations", "Eravikulam Park", "Mattupetty Dam", "Echo Point"),
                imageUrl = "https://picsum.photos/id/1016/600/400",
                entryFee = "Eravikulam National Park: ₹110 | Mattupetty Dam: Free",
                howToReach = listOf(
                    "✈️ Fly to Kochi (Cochin) International Airport — nearest major airport",
                    "🚌 Take a KSRTC bus or cab from Ernakulam/Kochi to Munnar (4hrs, ~130km)",
                    "🛺 Use local autos or rent a two-wheeler to explore within Munnar"
                ),
                trendingSpots = listOf(
                    TrendingSpot("Top Station", "The highest point in Munnar at 1700m, offering a panoramic view of the Western Ghats and Tamil Nadu plains below.", "September – May", "Free", listOf("Go early morning for clear views", "Carries woolens — very cold and windy", "35km from Munnar town — hire a cab"), listOf("https://picsum.photos/id/200/800/500", "https://picsum.photos/id/201/800/500", "https://picsum.photos/id/202/800/500")),
                    TrendingSpot("Eravikulam National Park", "Home to the endangered Nilgiri Tahr and the famous Neelakurinji flowers that bloom once every 12 years.", "September – April", "₹110", listOf("Book tickets online — entry is timed", "Nilgiri Tahr are friendly and come close", "Carry binoculars for wildlife spotting"), listOf("https://picsum.photos/id/210/800/500", "https://picsum.photos/id/211/800/500", "https://picsum.photos/id/212/800/500")),
                    TrendingSpot("Mattupetty Dam", "A scenic shutter dam surrounded by tea gardens and the Mattupetty Lake — boating available with beautiful mountain backdrop.", "All year", "Free (boating ₹100)", listOf("Boating slots fill fast — reach early", "Indo-Swiss dairy farm nearby worth a visit", "Beautiful sunrise spot"), listOf("https://picsum.photos/id/220/800/500", "https://picsum.photos/id/221/800/500", "https://picsum.photos/id/222/800/500")),
                    TrendingSpot("Attukal Waterfalls", "A stunning multi-tiered waterfall on the way from Munnar to Top Station — easy roadside access.", "July – January", "Free", listOf("Best after monsoon — full flow", "Safe wading area at the base", "Photo spot is on the bridge above"), listOf("https://picsum.photos/id/230/800/500", "https://picsum.photos/id/231/800/500", "https://picsum.photos/id/232/800/500")),
                    TrendingSpot("Tea Garden Walk", "Walk through endless rows of tea bushes in the Kolukkumalai or TATA Tea estates — misty, green, and therapeutic.", "All year", "₹150 (guided tour)", listOf("Kolukkumalai is the world's highest tea estate", "Buy fresh tea directly from the factory", "Morning mist makes the walk magical"), listOf("https://picsum.photos/id/240/800/500", "https://picsum.photos/id/241/800/500", "https://picsum.photos/id/242/800/500"))
                ),
                tips = listOf("Carry a jacket — temperature drops to 5°C in December", "Rent a bike for the tea garden routes — best experience", "Avoid monsoon months if you want clear views", "Try the local cardamom tea — different from anything you've had")
            ),

            Destination(
                id = 3, name = "Goa", category = "Beaches",
                rating = 4.8, priceRange = "₹₹", emoji = "🏖️",
                description = "India's beach capital — sun, parties, Portuguese forts, seafood, and a vibe that never stops. North Goa hypes, South Goa chills.",
                bestTime = "November – February", daysNeeded = "4–5 days",
                highlights = listOf("Baga Beach", "Fort Aguada", "Dudhsagar Falls", "Old Goa Churches"),
                imageUrl = "https://picsum.photos/id/1011/600/400",
                entryFee = "Dudhsagar Falls: ₹400 | Most beaches: Free",
                howToReach = listOf(
                    "✈️ Fly to Goa International Airport (Dabolim/Mopa) — direct flights from most cities",
                    "🚆 Or take a train to Madgaon (Margao) or Thivim railway station",
                    "🛺 Rent a scooter from the station area — ₹300–400/day, best way to explore"
                ),
                trendingSpots = listOf(
                    TrendingSpot("Baga Beach", "North Goa's most famous beach — beach shacks, water sports, nightlife, and the iconic Tito's Lane right behind the shoreline.", "November – February", "Free", listOf("Water sports are negotiable — bargain hard", "Evening is best for food and drinks", "Avoid peak afternoon sun"), listOf("https://picsum.photos/id/300/800/500", "https://picsum.photos/id/301/800/500", "https://picsum.photos/id/302/800/500")),
                    TrendingSpot("Dudhsagar Falls", "One of India's tallest waterfalls at 310m — a stunning four-tiered cascade deep inside the Bhagwan Mahavir Wildlife Sanctuary.", "June – January", "₹400 (jeep safari included)", listOf("Only accessible by jeep safari from Mollem", "Book jeep in advance — limited slots", "Monsoon is the most dramatic time to visit"), listOf("https://picsum.photos/id/310/800/500", "https://picsum.photos/id/311/800/500", "https://picsum.photos/id/312/800/500")),
                    TrendingSpot("Chapora Fort", "The iconic fort made famous by Dil Chahta Hai — stunning 360° view of the Chapora river and North Goa coastline.", "October – March", "Free", listOf("Sunset view from the top is the best in North Goa", "15 min climb from the base", "Very crowded on weekends"), listOf("https://picsum.photos/id/320/800/500", "https://picsum.photos/id/321/800/500", "https://picsum.photos/id/322/800/500")),
                    TrendingSpot("Anjuna Flea Market", "Goa's most famous market running every Wednesday — handicrafts, clothes, spices, jewelry, and street food from across India.", "November – April (Wednesdays)", "Free entry", listOf("Go before 11AM before the heat hits", "Bargain on everything — prices are inflated", "Try the fresh coconut water and Goan sausage rolls"), listOf("https://picsum.photos/id/330/800/500", "https://picsum.photos/id/331/800/500", "https://picsum.photos/id/332/800/500")),
                    TrendingSpot("Basilica of Bom Jesus", "A UNESCO World Heritage Site — 400-year-old Baroque church housing the remains of St. Francis Xavier.", "All year", "Free", listOf("Photography allowed outside only", "Dress modestly — shoulders and knees covered", "Old Goa area has 5 churches within walking distance"), listOf("https://picsum.photos/id/340/800/500", "https://picsum.photos/id/341/800/500", "https://picsum.photos/id/342/800/500"))
                ),
                tips = listOf("Rent a scooter — it's the real Goa experience", "Book hotels 2 months in advance for Dec–Jan peak season", "South Goa (Palolem, Agonda) is quieter and cleaner than North", "Avoid carrying valuables on the beach")
            ),

            Destination(
                id = 4, name = "Ooty", category = "Mountains",
                rating = 4.6, priceRange = "₹₹", emoji = "🌸",
                description = "The Queen of Hill Stations — toy train rides, rose gardens, tea estates, and colonial bungalows tucked in the Nilgiris.",
                bestTime = "October – June", daysNeeded = "2–3 days",
                highlights = listOf("Nilgiri Toy Train", "Botanical Garden", "Ooty Lake", "Doddabetta Peak"),
                imageUrl = "https://picsum.photos/id/1018/600/400",
                entryFee = "Botanical Garden: ₹30 | Ooty Lake boating: ₹40",
                howToReach = listOf(
                    "🚆 Take a train to Coimbatore — major railway junction in Tamil Nadu",
                    "🚌 From Coimbatore, take a bus or cab to Mettupalayam (45min)",
                    "🚂 Board the Nilgiri Mountain Railway (toy train) from Mettupalayam to Ooty (5hrs — UNESCO heritage ride)",
                    "🛺 Local autos available at Ooty station for sightseeing"
                ),
                trendingSpots = listOf(
                    TrendingSpot("Nilgiri Mountain Railway", "A UNESCO World Heritage toy train that winds through 16 tunnels and 250 bridges across stunning Nilgiri mountain scenery.", "All year", "₹40–290", listOf("Book in advance on IRCTC — fills fast", "Window seat on the right side for best views (Mettupalayam to Ooty)", "The Coonoor–Ooty section is the most scenic"), listOf("https://picsum.photos/id/400/800/500", "https://picsum.photos/id/401/800/500", "https://picsum.photos/id/402/800/500")),
                    TrendingSpot("Doddabetta Peak", "The highest peak in the Nilgiris at 2637m — on a clear day you can see Coimbatore city in the plains far below.", "October – June", "₹10", listOf("Go early morning for clear views", "Telescope house at top for close-up views", "Gets foggy by afternoon"), listOf("https://picsum.photos/id/410/800/500", "https://picsum.photos/id/411/800/500", "https://picsum.photos/id/412/800/500")),
                    TrendingSpot("Botanical Garden", "150-year-old garden with over 650 plant species, including a 20-million-year-old fossilized tree trunk.", "All year", "₹30", listOf("Flower show in May is spectacular", "Fossilized tree is the main highlight", "Good 2-hour walk"), listOf("https://picsum.photos/id/420/800/500", "https://picsum.photos/id/421/800/500", "https://picsum.photos/id/422/800/500")),
                    TrendingSpot("Ooty Lake", "A scenic artificial lake built in 1824 — rowing and pedal boats available with the Nilgiri hills as the backdrop.", "All year", "Boating ₹40", listOf("Morning rowing is the most peaceful", "Horse riding available on the lakeside", "Mini train around the lake for kids"), listOf("https://picsum.photos/id/430/800/500", "https://picsum.photos/id/431/800/500", "https://picsum.photos/id/432/800/500")),
                    TrendingSpot("Pykara Lake & Falls", "A beautiful lake and waterfall 21km from Ooty surrounded by shola forest — far less crowded than Ooty itself.", "June – February", "Free", listOf("Speedboat rides on the lake available", "Waterfall is 15 min trek from the road", "Pack lunch — very few food options"), listOf("https://picsum.photos/id/440/800/500", "https://picsum.photos/id/441/800/500", "https://picsum.photos/id/442/800/500"))
                ),
                tips = listOf("The toy train ride is the highlight — book it in advance on IRCTC", "Weekends are packed — plan weekday visits if possible", "Carry woolens even in summer — it gets cold at night", "Try the homemade chocolate shops near the bus stand")
            ),

            Destination(
                id = 5, name = "Coorg", category = "Nature",
                rating = 4.7, priceRange = "₹₹", emoji = "☕",
                description = "Scotland of India — misty coffee and spice estates, Kodava culture, waterfalls, and the freshest air you'll breathe in South India.",
                bestTime = "October – March", daysNeeded = "2–3 days",
                highlights = listOf("Coffee Estates", "Abbey Falls", "Raja's Seat", "Dubare Elephant Camp"),
                imageUrl = "https://picsum.photos/id/1015/600/400",
                entryFee = "Abbey Falls: ₹20 | Dubare: ₹150",
                howToReach = listOf(
                    "🚆 Take a train to Mysuru (Mysore) — nearest major railway station (95km from Coorg)",
                    "🚌 Board a KSRTC bus or hire a cab from Mysuru to Madikeri, Coorg (3hrs)",
                    "🛺 Rent a bike in Madikeri to explore the coffee estates and waterfalls"
                ),
                trendingSpots = listOf(
                    TrendingSpot("Abbey Falls", "A stunning 70-foot waterfall surrounded by coffee and spice plantations — a short 15-minute walk through a private estate.", "June – March", "₹20", listOf("Best during and after monsoon for full flow", "Hanging bridge at the viewpoint is the photo spot", "Go early — gets crowded by 10AM"), listOf("https://picsum.photos/id/500/800/500", "https://picsum.photos/id/501/800/500", "https://picsum.photos/id/502/800/500")),
                    TrendingSpot("Namdroling Monastery", "The Golden Temple of Bylakuppe — the largest Nyingma Tibetan Buddhist monastery outside Tibet, 35km from Madikeri.", "All year", "Free", listOf("Photography inside the prayer hall is restricted", "Prayer sessions at 8AM and 5PM are peaceful to witness", "Tibetan food available in the market outside"), listOf("https://picsum.photos/id/510/800/500", "https://picsum.photos/id/511/800/500", "https://picsum.photos/id/512/800/500")),
                    TrendingSpot("Dubare Elephant Camp", "An elephant training camp on the banks of the Cauvery river — feed, bathe, and interact with elephants up close.", "October – May", "₹150", listOf("Elephant bathing session is at 8–9AM only", "Book online in advance", "River crossing by coracle is an added bonus"), listOf("https://picsum.photos/id/520/800/500", "https://picsum.photos/id/521/800/500", "https://picsum.photos/id/522/800/500")),
                    TrendingSpot("Raja's Seat", "A royal garden with a musical fountain, toy train, and a breathtaking sunset view over the misty Coorg valleys.", "All year", "₹10", listOf("Sunset here is one of the best in Karnataka", "Musical fountain runs in evenings", "Very crowded on weekends"), listOf("https://picsum.photos/id/530/800/500", "https://picsum.photos/id/531/800/500", "https://picsum.photos/id/532/800/500")),
                    TrendingSpot("Talacauvery", "The sacred origin point of the Cauvery river — a temple, natural spring, and misty hilltop at 1276m altitude.", "All year", "Free", listOf("Sunrise from the hilltop is spectacular", "55km from Madikeri — full day trip", "Water from the spring is considered sacred"), listOf("https://picsum.photos/id/540/800/500", "https://picsum.photos/id/541/800/500", "https://picsum.photos/id/542/800/500"))
                ),
                tips = listOf("Stay in a coffee estate homestay — totally different vibe from regular hotels", "Namdroling Monastery (Bylakuppe) is 35km away — absolutely worth the visit", "Monsoon makes it lush but roads get tricky — avoid biking then", "Buy fresh coffee powder directly from estates — way cheaper")
            ),

            Destination(
                id = 6, name = "Taj Mahal – Agra", category = "Heritage",
                rating = 4.9, priceRange = "₹₹", emoji = "🕌",
                description = "One of the Seven Wonders of the World — the Taj Mahal at sunrise is something no photo can ever do justice to.",
                bestTime = "October – March", daysNeeded = "1–2 days",
                highlights = listOf("Taj Mahal", "Agra Fort", "Mehtab Bagh", "Fatehpur Sikri"),
                imageUrl = "https://picsum.photos/id/1031/600/400",
                entryFee = "Taj Mahal: ₹50 (Indians) | Agra Fort: ₹40",
                howToReach = listOf(
                    "🚆 Take a train to Agra Cantt or Agra Fort railway station — well connected from Delhi, Mumbai, Jaipur",
                    "🚌 From Agra station, take a prepaid auto or e-rickshaw to Taj Mahal East Gate (10–15 min)",
                    "🛺 E-rickshaws only allowed near Taj premises — no private vehicles"
                ),
                trendingSpots = listOf(
                    TrendingSpot("Taj Mahal at Sunrise", "The Taj Mahal glowing golden in the first light of dawn — the most iconic and breathtaking sight in India.", "October – March", "₹50", listOf("Reach East Gate by 6AM sharp", "Full moon nights have special night viewing — book separately", "Friday is closed"), listOf("https://picsum.photos/id/600/800/500", "https://picsum.photos/id/601/800/500", "https://picsum.photos/id/602/800/500")),
                    TrendingSpot("Agra Fort", "A UNESCO World Heritage red sandstone fort that was the main residence of Mughal emperors — stunning Taj view from inside.", "All year", "₹40", listOf("Taj Mahal view from Musamman Burj inside the fort is iconic", "Allow 2–3 hours to explore fully", "Audio guide available at entrance"), listOf("https://picsum.photos/id/610/800/500", "https://picsum.photos/id/611/800/500", "https://picsum.photos/id/612/800/500")),
                    TrendingSpot("Mehtab Bagh", "A garden directly across the Yamuna river from the Taj — the best place to photograph the Taj at sunset for free.", "All year", "₹25", listOf("Best spot for sunset Taj photography", "Much less crowded than inside the Taj", "Reflection of Taj in river is visible in monsoon"), listOf("https://picsum.photos/id/620/800/500", "https://picsum.photos/id/621/800/500", "https://picsum.photos/id/622/800/500")),
                    TrendingSpot("Fatehpur Sikri", "Akbar's abandoned Mughal capital built entirely of red sandstone — ghost city perfectly preserved 40km from Agra.", "October – March", "₹40", listOf("Half day trip from Agra", "Hire a local guide — history here is fascinating", "Buland Darwaza is the world's largest gateway"), listOf("https://picsum.photos/id/630/800/500", "https://picsum.photos/id/631/800/500", "https://picsum.photos/id/632/800/500")),
                    TrendingSpot("Kinari Bazaar", "Agra's old market street — marble inlay work, leather goods, petha sweets, and Mughal-era craft shops.", "All year", "Free", listOf("Buy petha (Agra's famous sweet) fresh from Panchi Petha", "Marble inlay items are expensive — verify quality", "Best explored on foot"), listOf("https://picsum.photos/id/640/800/500", "https://picsum.photos/id/641/800/500", "https://picsum.photos/id/642/800/500"))
                ),
                tips = listOf("Reach the East Gate by 6AM for sunrise — the golden light on Taj is unreal", "Friday is closed — plan accordingly", "Mehtab Bagh gives a free Taj view across the Yamuna — go at sunset", "Full moon nights: Taj is open for night viewing (book separately)")
            ),

            Destination(
                id = 7, name = "Mumbai", category = "Shopping",
                rating = 4.6, priceRange = "₹₹₹", emoji = "🌆",
                description = "The city that never sleeps — street food, Bollywood, colonial architecture, designer stores, and the energy of 20 million dreams.",
                bestTime = "November – February", daysNeeded = "3–4 days",
                highlights = listOf("Gateway of India", "Marine Drive", "Dharavi", "Colaba Causeway"),
                imageUrl = "https://picsum.photos/id/1071/600/400",
                entryFee = "Most attractions: Free | Elephanta Caves: ₹40",
                howToReach = listOf(
                    "✈️ Fly to Chhatrapati Shivaji Maharaj International Airport — connected from everywhere",
                    "🚆 Or take a train to Mumbai CST, Lokmanya Tilak Terminal, or Bandra Terminus",
                    "🚇 Use Mumbai Local Train or Metro for city travel — cheapest and fastest",
                    "🛺 Kaali-Peeli taxis and autos available everywhere"
                ),
                trendingSpots = listOf(
                    TrendingSpot("Marine Drive", "Mumbai's iconic 3.6km Queen's Necklace — a curving seafront promenade best experienced at night when the streetlights glow like a necklace.", "All year", "Free", listOf("Evening walk is the classic Mumbai experience", "Chowpatty Beach at the end has famous bhel puri", "New Year's Eve here is unforgettable"), listOf("https://picsum.photos/id/700/800/500", "https://picsum.photos/id/701/800/500", "https://picsum.photos/id/702/800/500")),
                    TrendingSpot("Colaba Causeway", "Mumbai's most famous street market — antiques, clothes, jewelry, leather goods, and street food all in one stretch.", "All year", "Free", listOf("Go in the morning before it gets crowded", "Bargain on everything", "Leopold Cafe nearby is a Mumbai institution"), listOf("https://picsum.photos/id/710/800/500", "https://picsum.photos/id/711/800/500", "https://picsum.photos/id/712/800/500")),
                    TrendingSpot("Dharavi", "Asia's largest urban slum — a city within a city with a thriving economy of leather, pottery, and recycling industries.", "All year", "Tour: ₹750", listOf("Take a guided tour only — don't wander alone", "Photography restrictions apply inside", "Reality Check tours offer the most respectful experience"), listOf("https://picsum.photos/id/720/800/500", "https://picsum.photos/id/721/800/500", "https://picsum.photos/id/722/800/500")),
                    TrendingSpot("Juhu Beach", "Mumbai's most popular beach — chaotic, colourful, and loaded with street food stalls selling everything from pani puri to corn.", "All year", "Free", listOf("Evening is best for street food", "Sunset views are decent despite the crowds", "Avoid swimming — sea is polluted"), listOf("https://picsum.photos/id/730/800/500", "https://picsum.photos/id/731/800/500", "https://picsum.photos/id/732/800/500")),
                    TrendingSpot("Gateway of India", "The iconic 26-metre arch built in 1924, overlooking the Arabian Sea — the symbolic entry point of Mumbai.", "All year", "Free", listOf("Best photographed at sunrise before crowds arrive", "Ferry to Elephanta Caves departs from here", "Taj Hotel behind it is worth a peek inside"), listOf("https://picsum.photos/id/740/800/500", "https://picsum.photos/id/741/800/500", "https://picsum.photos/id/742/800/500"))
                ),
                tips = listOf("Buy a Mumbai local train day pass — saves money and time", "Vada Pav at Anand Stall near CST is legendary — don't miss", "Avoid peak hours (8–10AM, 5–8PM) on local trains — dangerously crowded", "Colaba Causeway shopping is best in the morning before it gets chaotic")
            ),

            Destination(
                id = 8, name = "Kodaikanal", category = "Mountains",
                rating = 4.6, priceRange = "₹₹", emoji = "🌧️",
                description = "The Princess of Hill Stations — misty lakes, pine forests, and the famous Kodai Lake make this Tamil Nadu's most romantic escape.",
                bestTime = "April – June, September – November", daysNeeded = "2–3 days",
                highlights = listOf("Kodai Lake", "Coaker's Walk", "Pillar Rocks", "Bryant Park"),
                imageUrl = "https://picsum.photos/id/1040/600/400",
                entryFee = "Bryant Park: ₹30 | Boating on Kodai Lake: ₹80",
                howToReach = listOf(
                    "🚆 Take a train to Dindigul or Palani — nearest railway stations (~80km from Kodai)",
                    "🚌 From Dindigul or Palani, take a TNSTC bus or cab to Kodaikanal (2.5–3hrs of winding mountain road)",
                    "🛺 Auto rickshaws and rented bicycles/cycles available in town for local sightseeing"
                ),
                trendingSpots = listOf(
                    TrendingSpot("Kodai Lake", "A star-shaped artificial lake at the heart of Kodaikanal — cycling around its 5km perimeter is the most iconic Kodai experience.", "All year", "Boating ₹80", listOf("Cycle rental available on the lakeside for ₹50/hr", "Early morning cycling in the mist is magical", "Rowboats and pedalboats also available"), listOf("https://picsum.photos/id/800/800/500", "https://picsum.photos/id/801/800/500", "https://picsum.photos/id/802/800/500")),
                    TrendingSpot("Coaker's Walk", "A scenic 1km paved clifftop walk with stunning views of the plains below — on clear days you can see Madurai.", "All year", "₹25", listOf("Best in early morning before fog rolls in", "Telescope available for valley views", "One of the most underrated walks in South India"), listOf("https://picsum.photos/id/810/800/500", "https://picsum.photos/id/811/800/500", "https://picsum.photos/id/812/800/500")),
                    TrendingSpot("Pillar Rocks", "Three giant granite boulders rising 400 feet from the ground — dramatic, foggy, and unlike anything else in Tamil Nadu.", "All year", "₹25", listOf("Most dramatic during monsoon when fog swirls around the rocks", "Garden around the rocks is well maintained", "Combine with nearby Green Valley views"), listOf("https://picsum.photos/id/820/800/500", "https://picsum.photos/id/821/800/500", "https://picsum.photos/id/822/800/500")),
                    TrendingSpot("Bear Shola Falls", "A seasonal waterfall 2km from the town centre — surrounded by dense forest and popular as a quick nature walk.", "June – October", "₹15", listOf("Only flows well during and after monsoon", "Short 20-min walk from the road", "Good birdwatching spot"), listOf("https://picsum.photos/id/830/800/500", "https://picsum.photos/id/831/800/500", "https://picsum.photos/id/832/800/500")),
                    TrendingSpot("Dolphin's Nose", "A rocky promontory shaped like a dolphin's nose — one of the most dramatic viewpoints in Kodaikanal with sheer cliff drops.", "All year", "₹25", listOf("7km from town — hire an auto", "Silver Cascade waterfall is on the way", "Avoid during heavy rain — slippery"), listOf("https://picsum.photos/id/840/800/500", "https://picsum.photos/id/841/800/500", "https://picsum.photos/id/842/800/500"))
                ),
                tips = listOf("Cycle around Kodai Lake — 5km loop, most scenic thing you'll do here", "The road from Palani is more scenic than Dindigul route", "Carry rain gear even in summer — sudden showers are common", "Try the homemade wine and chocolate sold by local shops")
            ),

            Destination(
                id = 9, name = "Jaipur", category = "Heritage",
                rating = 4.7, priceRange = "₹₹", emoji = "🏰",
                description = "The Pink City — majestic Rajput forts, block-printed textiles, blue pottery, and some of the most photogenic streets in India.",
                bestTime = "October – March", daysNeeded = "3–4 days",
                highlights = listOf("Amber Fort", "Hawa Mahal", "City Palace", "Jantar Mantar"),
                imageUrl = "https://picsum.photos/id/1047/600/400",
                entryFee = "Amber Fort: ₹100 | Hawa Mahal: ₹50 | City Palace: ₹130",
                howToReach = listOf(
                    "✈️ Fly to Jaipur International Airport — direct flights from Delhi, Mumbai, Bengaluru",
                    "🚆 Or take a train to Jaipur Junction — on the Delhi–Mumbai and Delhi–Ahmedabad routes",
                    "🛺 Use app-based cabs (Ola/Uber) or hire a local auto for city sightseeing"
                ),
                trendingSpots = listOf(
                    TrendingSpot("Amber Fort", "A massive hilltop fort with stunning mirror work, elephant rides, and a sound and light show after dark.", "October – March", "₹100", listOf("Elephant ride starts at 6AM — book online", "Sound & light show at 7:30PM is worth staying for", "Sheesh Mahal (Hall of Mirrors) is the highlight inside"), listOf("https://picsum.photos/id/900/800/500", "https://picsum.photos/id/901/800/500", "https://picsum.photos/id/902/800/500")),
                    TrendingSpot("Nahargarh Fort", "A fort on the Aravalli ridge with the most spectacular sunset view over Jaipur city — the most underrated spot in the Pink City.", "All year", "₹50", listOf("Sunset here is the best in all of Jaipur", "Open till 8PM — stay for the city lights", "Padao restaurant inside has great food with a view"), listOf("https://picsum.photos/id/910/800/500", "https://picsum.photos/id/911/800/500", "https://picsum.photos/id/912/800/500")),
                    TrendingSpot("Hawa Mahal", "The Palace of Winds — a five-storey pink sandstone facade with 953 small windows built for royal women to observe the street below.", "All year", "₹50", listOf("Best photographed from the tea shop across the street", "Go early morning for golden hour shots", "Interior is surprisingly small — 30 min is enough"), listOf("https://picsum.photos/id/920/800/500", "https://picsum.photos/id/921/800/500", "https://picsum.photos/id/922/800/500")),
                    TrendingSpot("Johri Bazaar", "Jaipur's famous jewelry market — Kundan, Meenakari, and gemstone jewelry sold in hundreds of family-run shops.", "All year", "Free", listOf("Bargain hard — starting price is always 2–3x the real price", "Bapu Bazaar nearby for textiles and lac bangles", "Avoid middlemen who approach on the street"), listOf("https://picsum.photos/id/930/800/500", "https://picsum.photos/id/931/800/500", "https://picsum.photos/id/932/800/500")),
                    TrendingSpot("Jal Mahal", "The Water Palace — a stunning Mughal-style palace sitting in the middle of Man Sagar Lake, best viewed from the lakeside.", "October – March", "Free (exterior only)", listOf("Interior is not open to tourists — only exterior view", "Sunset from the lakeside is beautiful", "Pelicans and birds gather here in winter"), listOf("https://picsum.photos/id/940/800/500", "https://picsum.photos/id/941/800/500", "https://picsum.photos/id/942/800/500"))
                ),
                tips = listOf("Buy a composite ticket — covers 5 major monuments, saves ₹300+", "Nahargarh Fort at sunset is the most underrated view in Jaipur", "Bargain hard at Johri Bazaar — starting price is always inflated", "Laal Maas (mutton curry) and Dal Baati Churma are must-tries")
            ),

            Destination(
                id = 10, name = "Udaipur", category = "Heritage",
                rating = 4.8, priceRange = "₹₹₹", emoji = "🏯",
                description = "The City of Lakes — romantic lakeside palaces, sunset boat rides, and Rajasthani art that makes every corner look like a painting.",
                bestTime = "September – March", daysNeeded = "2–3 days",
                highlights = listOf("City Palace", "Lake Pichola", "Jag Mandir", "Bagore Ki Haveli"),
                imageUrl = "https://picsum.photos/id/1060/600/400",
                entryFee = "City Palace: ₹130 | Boat ride on Lake Pichola: ₹400",
                howToReach = listOf(
                    "✈️ Fly to Maharana Pratap Airport, Udaipur — connected from Delhi, Mumbai, Jaipur",
                    "🚆 Or take a train to Udaipur City railway station — on the Ahmedabad–Udaipur route",
                    "🛺 Hire an auto or use Ola/Uber within the city — city is compact and easy to navigate"
                ),
                trendingSpots = listOf(
                    TrendingSpot("Lake Pichola Boat Ride", "A sunset boat ride on the serene Lake Pichola with views of City Palace, Jag Mandir, and the Aravalli hills.", "October – March", "₹400", listOf("Sunset ride is the most popular — book by 4PM", "Jag Mandir island stop included in some packages", "Camera-worthy from every angle"), listOf("https://picsum.photos/id/1000/800/500", "https://picsum.photos/id/1001/800/500", "https://picsum.photos/id/1002/800/500")),
                    TrendingSpot("City Palace", "The largest palace complex in Rajasthan — built over 400 years by successive maharajas, overlooking Lake Pichola.", "All year", "₹130", listOf("Allow 3–4 hours to explore fully", "Museum inside has royal artifacts and paintings", "Rooftop has the best lake view"), listOf("https://picsum.photos/id/1010/800/500", "https://picsum.photos/id/1011/800/500", "https://picsum.photos/id/1012/800/500")),
                    TrendingSpot("Sajjangarh (Monsoon Palace)", "A white hilltop palace 5km from the city — built to watch monsoon clouds, now offering the most panoramic view of Udaipur.", "All year", "₹80", listOf("Sunset from here is breathtaking", "Jungle safari through wildlife sanctuary to reach it", "Best visited by hired jeep"), listOf("https://picsum.photos/id/1020/800/500", "https://picsum.photos/id/1021/800/500", "https://picsum.photos/id/1022/800/500")),
                    TrendingSpot("Bagore Ki Haveli", "A 300-year-old haveli on the lake ghats — now a museum with royal costumes, and a famous evening Rajasthani cultural show.", "All year", "Museum ₹60 | Show ₹90", listOf("Cultural show at 7PM is excellent — arrive early for seats", "Largest turban in the world is on display here", "Lakeside location makes it very photogenic"), listOf("https://picsum.photos/id/1030/800/500", "https://picsum.photos/id/1031/800/500", "https://picsum.photos/id/1032/800/500")),
                    TrendingSpot("Fateh Sagar Lake", "Udaipur's second major lake — quieter than Pichola, with a solar observatory island, boat rides, and a beautiful promenade.", "All year", "Boat ₹100", listOf("Nehru Island garden in the middle is accessible by boat", "Evening promenade walk is very popular with locals", "Less touristy than Pichola"), listOf("https://picsum.photos/id/1040/800/500", "https://picsum.photos/id/1041/800/500", "https://picsum.photos/id/1042/800/500"))
                ),
                tips = listOf("Sunset boat ride on Lake Pichola is non-negotiable", "Rooftop cafes overlooking the lake are the best places to eat", "Bagore Ki Haveli has a great cultural show at 7PM — worth attending", "Book accommodation near the lake — walking everywhere is the real experience")
            ),

            Destination(
                id = 11, name = "Varanasi", category = "Spiritual",
                rating = 4.7, priceRange = "₹₹", emoji = "🕉️",
                description = "The oldest living city on Earth — Ganga ghats, evening aarti fire, chai at sunrise, and a spiritual energy that is impossible to describe.",
                bestTime = "October – March", daysNeeded = "2–3 days",
                highlights = listOf("Ganga Aarti", "Boat Ride at Sunrise", "Kashi Vishwanath Temple", "Sarnath"),
                imageUrl = "https://picsum.photos/id/1037/600/400",
                entryFee = "Most ghats: Free | Sarnath Museum: ₹15",
                howToReach = listOf(
                    "✈️ Fly to Lal Bahadur Shastri International Airport, Varanasi — direct flights from Delhi, Mumbai",
                    "🚆 Or take a train to Varanasi Junction — connected from most major cities",
                    "🛺 E-rickshaws and cycle rickshaws from station to ghats — autos not allowed on narrow ghat lanes"
                ),
                trendingSpots = listOf(
                    TrendingSpot("Dashashwamedh Ghat – Ganga Aarti", "The most spectacular religious ceremony in India — 7 priests perform a synchronized fire aarti to the Ganga every evening at sunset.", "All year", "Free", listOf("Reach by 6PM for a good viewing spot", "Watching from a boat on the river is the best perspective", "Photography allowed freely"), listOf("https://picsum.photos/id/1100/800/500", "https://picsum.photos/id/1101/800/500", "https://picsum.photos/id/1102/800/500")),
                    TrendingSpot("Sunrise Boat Ride on Ganga", "A wooden rowboat gliding past 84 ghats at dawn — the most peaceful and profound experience in Varanasi.", "October – March", "₹300–500 (negotiate)", listOf("Start from Assi Ghat at 5:30AM for full sunrise experience", "Negotiate price before boarding", "Pass by Manikarnika cremation ghat — be respectful"), listOf("https://picsum.photos/id/1110/800/500", "https://picsum.photos/id/1111/800/500", "https://picsum.photos/id/1112/800/500")),
                    TrendingSpot("Kashi Vishwanath Temple", "One of the twelve Jyotirlingas — the holiest Shiva temple in India, recently renovated into a grand temple corridor.", "All year", "Free", listOf("Separate queues for different darshan types", "New Kashi Vishwanath Corridor is architecturally stunning", "Avoid carrying mobile phone inside — lockers available"), listOf("https://picsum.photos/id/1120/800/500", "https://picsum.photos/id/1121/800/500", "https://picsum.photos/id/1122/800/500")),
                    TrendingSpot("Manikarnika Ghat", "The most sacred cremation ghat in Hinduism — pyres burn 24 hours a day, 365 days a year in an ancient ritual unchanged for millennia.", "All year", "Free", listOf("Be respectful — no photography of the cremations", "A sacred and deeply moving experience", "Local guides explain the rituals thoughtfully"), listOf("https://picsum.photos/id/1130/800/500", "https://picsum.photos/id/1131/800/500", "https://picsum.photos/id/1132/800/500")),
                    TrendingSpot("Sarnath", "Where Buddha gave his first sermon after enlightenment — ancient ruins, Dhamek Stupa, and a world-class museum 10km from Varanasi.", "All year", "₹15 (museum)", listOf("Dhamek Stupa is 2000 years old — incredibly preserved", "Museum has original Ashoka Lion Capital", "Very peaceful compared to busy Varanasi"), listOf("https://picsum.photos/id/1140/800/500", "https://picsum.photos/id/1141/800/500", "https://picsum.photos/id/1142/800/500"))
                ),
                tips = listOf("Sunrise boat ride on the Ganga is the single best experience in Varanasi", "Ganga Aarti at Dashashwamedh Ghat starts at 6:30PM — reach by 6PM for a good spot", "Walk the ghats on foot — Google Maps won't help in the narrow lanes", "Try the famous Banarasi paan and lassi in a matka (clay pot)")
            ),

            Destination(
                id = 12, name = "Puducherry", category = "Heritage",
                rating = 4.6, priceRange = "₹₹", emoji = "🇫🇷",
                description = "A slice of France on the Indian coast — yellow colonial buildings, Tamil temples side by side, rock beach promenade, and Auroville meditation.",
                bestTime = "October – March", daysNeeded = "2–3 days",
                highlights = listOf("French Quarter", "Auroville", "Promenade Beach", "Sri Aurobindo Ashram"),
                imageUrl = "https://picsum.photos/id/1025/600/400",
                entryFee = "Auroville Matrimandir: Free (pass required) | Ashram: Free",
                howToReach = listOf(
                    "🚆 Take a train to Chennai Central or Villupuram Junction",
                    "🚌 From Chennai, take a TNSTC/private bus to Puducherry (3hrs, ~160km) — frequent services",
                    "🚌 From Villupuram (30km), frequent local buses and autos to Puducherry",
                    "🛺 Rent a bicycle or scooter in Pondicherry — the French Quarter is best explored slowly"
                ),
                trendingSpots = listOf(
                    TrendingSpot("White Town (French Quarter)", "Pondicherry's iconic colonial quarter — yellow and white buildings with bougainvillea, French street names, and a completely different India.", "All year", "Free", listOf("Best explored by bicycle at 6AM before traffic", "Rue Suffren and Rue Dumas are the most photogenic streets", "Many cafes open early for breakfast — try Baker Street"), listOf("https://picsum.photos/id/1200/800/500", "https://picsum.photos/id/1201/800/500", "https://picsum.photos/id/1202/800/500")),
                    TrendingSpot("Auroville Matrimandir", "A giant golden sphere — the meditation center at the heart of Auroville, the international township founded by The Mother.", "All year", "Free (pass required)", listOf("Collect visitor pass from Auroville Visitors Centre the previous day", "Inner chamber meditation requires separate reservation", "The surrounding gardens are open freely"), listOf("https://picsum.photos/id/1210/800/500", "https://picsum.photos/id/1211/800/500", "https://picsum.photos/id/1212/800/500")),
                    TrendingSpot("Paradise Beach", "An isolated beach accessible only by a 10-minute boat ride from Chunnambar — clean, uncrowded, and strikingly beautiful.", "October – March", "₹150 (boat)", listOf("Boat from Chunnambar Boat House — 8km from Pondicherry", "Carry food and water — no shops on the beach", "Arrive early — beach gets crowded by noon"), listOf("https://picsum.photos/id/1220/800/500", "https://picsum.photos/id/1221/800/500", "https://picsum.photos/id/1222/800/500")),
                    TrendingSpot("Sri Aurobindo Ashram", "A spiritual community founded in 1926 by Sri Aurobindo and The Mother — a place of deep calm in the middle of the busy town.", "All year", "Free", listOf("Dress modestly and maintain silence inside", "Samadhi (shrine) is the main spot for meditation", "Ashram bakery sells excellent bread and sweets"), listOf("https://picsum.photos/id/1230/800/500", "https://picsum.photos/id/1231/800/500", "https://picsum.photos/id/1232/800/500")),
                    TrendingSpot("Promenade Beach", "Pondicherry's main seafront promenade — a wide, clean boulevard along the Bay of Bengal, best at sunrise and late evening.", "All year", "Free", listOf("Sunrise walk here is the classic Pondy morning", "War Memorial and French consulate are along the promenade", "Rock Beach nearby is rockier but quieter"), listOf("https://picsum.photos/id/1240/800/500", "https://picsum.photos/id/1241/800/500", "https://picsum.photos/id/1242/800/500"))
                ),
                tips = listOf("Rent a bicycle and explore White Town at 6AM before the crowds hit", "Auroville Matrimandir visit requires a free pass — collect it the day before", "Paradise Beach is accessible only by boat from Chunnambar — worth it", "Alcohol is heavily discounted in Puducherry vs rest of TN — famous for it")
            ),

            Destination(
                id = 13, name = "Hampi", category = "Heritage",
                rating = 4.9, priceRange = "₹", emoji = "🗿",
                description = "A UNESCO World Heritage Site — ancient Vijayanagara empire ruins scattered across surreal boulder landscapes. Hippie island, history, and magic.",
                bestTime = "October – February", daysNeeded = "2–3 days",
                highlights = listOf("Virupaksha Temple", "Stone Chariot", "Matanga Hill Sunrise", "Hippie Island"),
                imageUrl = "https://picsum.photos/id/1059/600/400",
                entryFee = "Vittala Temple Complex: ₹40 | Most sites: Free",
                howToReach = listOf(
                    "🚆 Take a train to Hospet Junction (Hosapete) — nearest major station (13km from Hampi)",
                    "🛺 Auto or cab from Hospet to Hampi Bazaar (20min, ₹150–200)",
                    "🚤 Cross the Tungabhadra river by coracle boat to reach Virupapur Gadde (Hippie Island)"
                ),
                trendingSpots = listOf(
                    TrendingSpot("Vittala Temple & Stone Chariot", "The crown jewel of Hampi — a 15th-century temple with musical pillars and the iconic stone chariot that is the symbol of Karnataka.", "October – February", "₹40", listOf("Musical pillars produce different notes when tapped", "Stone chariot is best photographed at golden hour", "Electric vehicle from Hampi Bazaar to Vittala — no walking allowed"), listOf("https://picsum.photos/id/1300/800/500", "https://picsum.photos/id/1301/800/500", "https://picsum.photos/id/1302/800/500")),
                    TrendingSpot("Matanga Hill Sunrise", "The highest point in Hampi — a 20-minute rocky climb rewarded with a 360° sunrise view over the entire ruined city and boulderscape.", "October – February", "Free", listOf("Start climbing 45 min before sunrise", "Carry a torch — path is dark early morning", "One of the best sunrise views in South India"), listOf("https://picsum.photos/id/1310/800/500", "https://picsum.photos/id/1311/800/500", "https://picsum.photos/id/1312/800/500")),
                    TrendingSpot("Hippie Island (Virupapur Gadde)", "A laid-back village across the Tungabhadra river — banana pancake cafes, hammocks, and an entirely different backpacker energy.", "October – February", "Coracle ₹30", listOf("Coracle crossing closes after dark", "Stay on the island for at least one night for the real vibe", "Sunset from island looking at Hampi ruins is special"), listOf("https://picsum.photos/id/1320/800/500", "https://picsum.photos/id/1321/800/500", "https://picsum.photos/id/1322/800/500")),
                    TrendingSpot("Virupaksha Temple", "A living temple continuously functioning since the 7th century — with a resident elephant named Lakshmi who blesses visitors every morning.", "All year", "Free", listOf("Elephant Lakshmi gives blessings at 8AM", "Climb to the top of the gopuram for free Hampi views", "Market street in front is great for local snacks"), listOf("https://picsum.photos/id/1330/800/500", "https://picsum.photos/id/1331/800/500", "https://picsum.photos/id/1332/800/500")),
                    TrendingSpot("Elephant Stables", "A grand 15th-century structure with 11 domed chambers that once housed the royal elephants of the Vijayanagara empire.", "All year", "Included in complex ticket", listOf("Excellent example of Indo-Islamic architecture", "Combine with nearby Zenana Enclosure visit", "Very photogenic at golden hour"), listOf("https://picsum.photos/id/1340/800/500", "https://picsum.photos/id/1341/800/500", "https://picsum.photos/id/1342/800/500"))
                ),
                tips = listOf("Climb Matanga Hill before sunrise — 360° view of the entire ruins landscape", "Rent a bicycle — best way to hop between monuments", "Stay on Hippie Island (Virupapur Gadde) for a completely different vibe", "The coracle river crossing closes after dark")
            ),

            Destination(
                id = 14, name = "Leh – Ladakh", category = "Adventure",
                rating = 4.9, priceRange = "₹₹₹₹", emoji = "🏔️",
                description = "The rooftop of India — barren moonscapes, turquoise lakes, ancient monasteries, and the most epic bike roads on the planet.",
                bestTime = "May – September", daysNeeded = "7–10 days",
                highlights = listOf("Pangong Lake", "Nubra Valley", "Khardung La Pass", "Thiksey Monastery"),
                imageUrl = "https://picsum.photos/id/1043/600/400",
                entryFee = "Inner Line Permit: ₹500 | Pangong Lake: ₹100",
                howToReach = listOf(
                    "✈️ Fly directly to Kushok Bakula Rimpochee Airport, Leh — flights from Delhi, Mumbai, Srinagar",
                    "🏍️ Or ride the Manali–Leh Highway (2 days, 479km) — legendary biker route",
                    "🚌 HRTC bus from Manali to Leh (2 days) for budget travelers",
                    "🛺 Hire a local taxi or bike in Leh for all further sightseeing"
                ),
                trendingSpots = listOf(
                    TrendingSpot("Pangong Tso Lake", "The iconic high-altitude lake at 4350m — the water changes color from blue to green to red through the day.", "June – September", "₹100", listOf("Stay overnight in a tent on the lakeside", "Best colors at sunrise and sunset", "Inner Line Permit required — get in Leh"), listOf("https://picsum.photos/id/1400/800/500", "https://picsum.photos/id/1401/800/500", "https://picsum.photos/id/1402/800/500")),
                    TrendingSpot("Nubra Valley", "A high-altitude cold desert with Bactrian double-humped camels, sand dunes, and the confluence of Shyok and Nubra rivers.", "June – September", "ILP required", listOf("Double-humped camel ride at Hunder dunes is unmissable", "Diskit Monastery has a giant Maitreya Buddha statue", "Reach via Khardung La — one of world's highest motorable roads"), listOf("https://picsum.photos/id/1410/800/500", "https://picsum.photos/id/1411/800/500", "https://picsum.photos/id/1412/800/500")),
                    TrendingSpot("Khardung La Pass", "At 5359m, one of the world's highest motorable mountain passes — a bucket list stop on every Ladakh road trip.", "May – October", "Free", listOf("Spend max 30 min here — altitude causes headaches", "ISBT hot tea stall at the top is legendary", "Take photos fast — weather changes quickly"), listOf("https://picsum.photos/id/1420/800/500", "https://picsum.photos/id/1421/800/500", "https://picsum.photos/id/1422/800/500")),
                    TrendingSpot("Thiksey Monastery", "A 12-storey monastery perched on a hill — one of the largest in Ladakh with a stunning 15m Maitreya Buddha statue inside.", "All year", "₹50", listOf("Morning prayer at 6AM is open to visitors", "Best photographed from the road below at sunrise", "Combine with Hemis Monastery same day"), listOf("https://picsum.photos/id/1430/800/500", "https://picsum.photos/id/1431/800/500", "https://picsum.photos/id/1432/800/500")),
                    TrendingSpot("Magnetic Hill", "A gravity-defying optical illusion on the Leh-Kargil highway — vehicles appear to roll uphill on their own.", "May – October", "Free", listOf("Park your vehicle at the yellow box marked on road", "It's an optical illusion — but very convincing", "Indus–Zanskar confluence viewpoint is 2km ahead"), listOf("https://picsum.photos/id/1440/800/500", "https://picsum.photos/id/1441/800/500", "https://picsum.photos/id/1442/800/500"))
                ),
                tips = listOf("Spend 2 days in Leh acclimatizing before any high-altitude sightseeing", "Carry cash — ATMs in remote areas are unreliable", "Inner Line Permit is mandatory for Pangong, Nubra — get it in Leh", "Avoid if you have heart or respiratory conditions — altitude is serious")
            ),

            Destination(
                id = 15, name = "Andaman Islands", category = "Beaches",
                rating = 4.9, priceRange = "₹₹₹", emoji = "🏝️",
                description = "Crystal clear waters, white sand beaches, world-class scuba diving, and the haunting Cellular Jail — India's best beach destination, no contest.",
                bestTime = "October – May", daysNeeded = "5–7 days",
                highlights = listOf("Radhanagar Beach", "Scuba Diving", "Cellular Jail", "Neil Island"),
                imageUrl = "https://picsum.photos/id/1039/600/400",
                entryFee = "Cellular Jail: ₹30 | Water sports vary",
                howToReach = listOf(
                    "✈️ Fly to Veer Savarkar International Airport, Port Blair — flights from Chennai, Kolkata, Delhi",
                    "🚢 Or take a ship from Chennai/Kolkata to Port Blair (2–3 days) — budget option, book weeks ahead",
                    "⛴️ From Port Blair, take ferries to Havelock (Neil Island, Ross Island) — book government ferry in advance"
                ),
                trendingSpots = listOf(
                    TrendingSpot("Radhanagar Beach", "Ranked one of Asia's best beaches — pristine white sand, turquoise water, and a stunning sunset that redefines the word beautiful.", "October – May", "Free", listOf("Sunset here is unmissable — arrive by 4PM", "Swimming is safe in the designated zones", "Carry your own food — limited options nearby"), listOf("https://picsum.photos/id/1500/800/500", "https://picsum.photos/id/1501/800/500", "https://picsum.photos/id/1502/800/500")),
                    TrendingSpot("Elephant Beach", "The best snorkeling and scuba spot in Andaman — shallow clear water with vibrant coral reefs just meters from the shore.", "October – May", "₹500 (glass boat + snorkeling)", listOf("Book water sports from certified operators at the beach", "Go in the morning — water is clearest before noon", "Reachable by boat from Havelock or a 45-min jungle trek"), listOf("https://picsum.photos/id/1510/800/500", "https://picsum.photos/id/1511/800/500", "https://picsum.photos/id/1512/800/500")),
                    TrendingSpot("Cellular Jail", "The colonial British prison where freedom fighters were sent — now a national memorial with a deeply moving sound and light show every evening.", "All year", "₹30", listOf("Sound and light show is at 6PM and 7:15PM — book in advance", "Self-guided audio tour available", "Very emotional experience — understand the history before going"), listOf("https://picsum.photos/id/1520/800/500", "https://picsum.photos/id/1521/800/500", "https://picsum.photos/id/1522/800/500")),
                    TrendingSpot("Neil Island (Shaheed Dweep)", "A quiet, unhurried island — natural rock formations, empty beaches, and a slower pace than Havelock. Perfect for 2 days of nothing.", "October – May", "Free", listOf("Bharatpur and Laxmanpur beaches are the best here", "Natural rock bridge at Laxmanpur Beach at low tide", "One day is enough — combine with Havelock trip"), listOf("https://picsum.photos/id/1530/800/500", "https://picsum.photos/id/1531/800/500", "https://picsum.photos/id/1532/800/500")),
                    TrendingSpot("Ross Island", "Abandoned British colonial headquarters — crumbling churches, headquarters buildings, and deer roaming freely through the ruins.", "All year", "₹50", listOf("Just 20 min boat ride from Port Blair jetty", "Half day is enough to explore", "Deer are completely unafraid of people here"), listOf("https://picsum.photos/id/1540/800/500", "https://picsum.photos/id/1541/800/500", "https://picsum.photos/id/1542/800/500"))
                ),
                tips = listOf("Book government ferries in advance — private speed boats are 3x the cost", "Scuba diving at Elephant Beach is the best entry-level experience", "Cellular Jail sound and light show runs in evenings — very moving", "Carry sufficient cash — card payments not widely accepted on islands")
            ),

            Destination(
                id = 16, name = "Manali", category = "Mountains",
                rating = 4.7, priceRange = "₹₹", emoji = "⛰️",
                description = "Snow peaks, river valleys, apple orchards, and the gateway to Leh — Manali is where every North India trip begins and ends.",
                bestTime = "March – June | December – February (snow)", daysNeeded = "4–5 days",
                highlights = listOf("Rohtang Pass", "Solang Valley", "Old Manali", "Hadimba Temple"),
                imageUrl = "https://picsum.photos/id/1018/600/400",
                entryFee = "Rohtang Pass permit: ₹550 | Hadimba Temple: Free",
                howToReach = listOf(
                    "🚆 Take a train to Chandigarh or Ambala — nearest major railway stations",
                    "🚌 From Chandigarh, take a Volvo AC bus to Manali (9–10hrs overnight — most popular route)",
                    "✈️ Or fly to Bhuntar Airport (50km from Manali) — small airport with limited flights from Delhi",
                    "🛺 Hire local taxis or cabs within Manali for sightseeing"
                ),
                trendingSpots = listOf(
                    TrendingSpot("Solang Valley", "A wide snow valley 14km from Manali — skiing, snowboarding, zorbing, and paragliding depending on the season.", "December – June", "Activity costs vary", listOf("Skiing lessons available for beginners", "Zorbing and rope activities in summer", "Very crowded on weekends — go on weekdays"), listOf("https://picsum.photos/id/1600/800/500", "https://picsum.photos/id/1601/800/500", "https://picsum.photos/id/1602/800/500")),
                    TrendingSpot("Rohtang Pass", "A high mountain pass at 3978m — snow-covered most of the year, with dramatic views of glaciers, valleys, and the Himalayan range.", "May – October (open)", "Permit ₹550", listOf("Book Rohtang permit online the night before — quota fills by 8AM", "Carry warm clothes regardless of season", "The road to Rohtang is itself very scenic"), listOf("https://picsum.photos/id/1610/800/500", "https://picsum.photos/id/1611/800/500", "https://picsum.photos/id/1612/800/500")),
                    TrendingSpot("Old Manali", "The original village above the main town — wooden cafes, apple orchards, backpacker guesthouses, and a completely chilled vibe.", "March – November", "Free", listOf("Best area for budget stays with mountain views", "Manu Temple at the top is worth the walk", "Evening cafe culture here is very Goa-like"), listOf("https://picsum.photos/id/1620/800/500", "https://picsum.photos/id/1621/800/500", "https://picsum.photos/id/1622/800/500")),
                    TrendingSpot("Hadimba Devi Temple", "A 16th-century pagoda-style wooden temple in a cedar forest — dedicated to Hadimba, a character from the Mahabharata.", "All year", "Free", listOf("Unique pagoda architecture unlike any other Indian temple", "Yak rides available outside for photos", "Quiet forest setting is very peaceful"), listOf("https://picsum.photos/id/1630/800/500", "https://picsum.photos/id/1631/800/500", "https://picsum.photos/id/1632/800/500")),
                    TrendingSpot("Beas River Rafting", "White-water rafting on the Beas river through rocky gorges just outside Manali — Grade 2–3 rapids perfect for beginners.", "April – June", "₹600–800", listOf("Book through certified operators only", "8–12km stretch takes about 2 hours", "Wear the safety gear provided — take it seriously"), listOf("https://picsum.photos/id/1640/800/500", "https://picsum.photos/id/1641/800/500", "https://picsum.photos/id/1642/800/500"))
                ),
                tips = listOf("Book Rohtang Pass permit online the night before — daily quota fills fast", "Old Manali has a completely different backpacker vibe from the main town", "Carry warm clothes even in summer — Rohtang is snow-covered all year", "Avoid visiting during Dussehra–Diwali — roads get dangerously crowded")
            ),

            Destination(
                id = 17, name = "Rishikesh", category = "Adventure",
                rating = 4.8, priceRange = "₹₹", emoji = "🌊",
                description = "Yoga capital of the world meets India's adventure hub — white-water rafting, bungee jumping, Beatles Ashram, and Ganga at your doorstep.",
                bestTime = "September – November | March – May", daysNeeded = "3–4 days",
                highlights = listOf("White-water Rafting", "Beatles Ashram", "Lakshman Jhula", "Yoga Retreats"),
                imageUrl = "https://picsum.photos/id/1002/600/400",
                entryFee = "Beatles Ashram: ₹150 | Rafting: ₹600–1500",
                howToReach = listOf(
                    "🚆 Take a train to Haridwar Junction — nearest major station (25km from Rishikesh)",
                    "🚌 From Haridwar, take a shared jeep or bus to Rishikesh (45min, ₹30–50)",
                    "🛺 Auto rickshaws and walking are the best ways to navigate Rishikesh — it's compact"
                ),
                trendingSpots = listOf(
                    TrendingSpot("White-water Rafting on Ganga", "16–36km of Grade 2–4 rapids through the Himalayan foothills — Rishikesh rafting is India's best river adventure.", "September – June", "₹600–1500", listOf("Book Grade 3–4 for the full experience (Marine Drive section)", "Wear a life jacket properly — the Ganga is powerful", "Camp on the riverbank overnight for the full experience"), listOf("https://picsum.photos/id/1700/800/500", "https://picsum.photos/id/1701/800/500", "https://picsum.photos/id/1702/800/500")),
                    TrendingSpot("Beatles Ashram (Chaurasi Kutia)", "The ashram where The Beatles stayed in 1968 to study Transcendental Meditation — now abandoned and covered in stunning street art.", "All year", "₹150", listOf("The psychedelic murals inside are incredible — bring a camera", "Guided tours explain the Beatles connection", "Best in the morning before crowds"), listOf("https://picsum.photos/id/1710/800/500", "https://picsum.photos/id/1711/800/500", "https://picsum.photos/id/1712/800/500")),
                    TrendingSpot("Lakshman Jhula", "The iconic suspension bridge over the Ganga — temples, ashrams, and chai shops on both sides, with river views that are pure Rishikesh.", "All year", "Free", listOf("Cross early morning when it's quiet", "Many yoga ashrams and cafes clustered around both ends", "Ram Jhula nearby is less crowded"), listOf("https://picsum.photos/id/1720/800/500", "https://picsum.photos/id/1721/800/500", "https://picsum.photos/id/1722/800/500")),
                    TrendingSpot("Jumpin Heights Bungee", "India's highest fixed bungee jump at 83m — a terrifying and exhilarating leap over a forest gorge in Mohan Chatti.", "All year", "₹3,550", listOf("Book well in advance — slots fill weeks ahead", "Weight limit: 40–110kg", "Flying fox and giant swing also available"), listOf("https://picsum.photos/id/1730/800/500", "https://picsum.photos/id/1731/800/500", "https://picsum.photos/id/1732/800/500")),
                    TrendingSpot("Triveni Ghat Evening Aarti", "The largest ghat in Rishikesh — the evening aarti here is smaller than Varanasi's but more intimate and deeply moving.", "All year", "Free", listOf("Aarti starts at 6PM — arrive by 5:45PM", "Float a diya (lamp) on the Ganga — deeply spiritual", "Much less crowded than Varanasi"), listOf("https://picsum.photos/id/1740/800/500", "https://picsum.photos/id/1741/800/500", "https://picsum.photos/id/1742/800/500"))
                ),
                tips = listOf("Book rafting through certified operators only — Grade 4+ rapids are serious", "Rishikesh is alcohol-free zone — respect it", "Beatles Ashram at sunset with street art everywhere is surreal", "Yoga ashrams need advance registration — don't just walk in")
            ),

            Destination(
                id = 18, name = "Spiti Valley", category = "Adventure",
                rating = 4.9, priceRange = "₹₹₹", emoji = "🏜️",
                description = "Cold desert at 12,000 feet — ancient monasteries on clifftops, zero phone signal, star-filled skies, and roads that will test your soul.",
                bestTime = "May – October", daysNeeded = "7–10 days",
                highlights = listOf("Key Monastery", "Chandratal Lake", "Kaza Town", "Dhankar Gompa"),
                imageUrl = "https://picsum.photos/id/1054/600/400",
                entryFee = "Inner Line Permit: ₹500 | Most monasteries: Donation",
                howToReach = listOf(
                    "🚌 Take a Volvo bus from Chandigarh to Shimla, then a connecting HRTC bus to Kaza (2 days total)",
                    "🏍️ Or ride from Shimla via Kinnaur Valley to Kaza — the classic Spiti circuit",
                    "🚌 Alternatively enter via Manali–Rohtang–Kunzum La route (open only June–October)",
                    "🛺 Local shared jeeps and bikes for within Spiti — no Ola/Uber here"
                ),
                trendingSpots = listOf(
                    TrendingSpot("Key Monastery", "A 1000-year-old Buddhist monastery perched dramatically on a cliff at 4166m — the most iconic image of Spiti Valley.", "May – October", "Donation", listOf("Morning prayer at 6AM is open to visitors", "Rooftop has 360° views of the Spiti river valley", "Monks are welcoming — be respectful"), listOf("https://picsum.photos/id/1800/800/500", "https://picsum.photos/id/1801/800/500", "https://picsum.photos/id/1802/800/500")),
                    TrendingSpot("Chandratal Lake", "A crescent-shaped lake at 4300m — one of the most beautiful high-altitude lakes in India, surrounded by snow peaks.", "June – October", "₹200", listOf("Camp overnight here for the star-gazing experience of your life", "No motorized vehicles near the lake — walk the last 1km", "Come prepared for freezing nights even in summer"), listOf("https://picsum.photos/id/1810/800/500", "https://picsum.photos/id/1811/800/500", "https://picsum.photos/id/1812/800/500")),
                    TrendingSpot("Hikkim Post Office", "The world's highest post office at 4400m — send a postcard to anyone in the world from here.", "May – October", "Postcard cost only", listOf("A true bucket list experience for travelers", "Bring your own pen — sometimes not available", "Combine with Komic monastery visit nearby"), listOf("https://picsum.photos/id/1820/800/500", "https://picsum.photos/id/1821/800/500", "https://picsum.photos/id/1822/800/500")),
                    TrendingSpot("Dhankar Gompa", "A precarious monastery balanced on a cliff 300m above the Spiti river confluence — one of the most dramatic settings in all of India.", "May – October", "Donation", listOf("Old monastery + new monastery — visit both", "Trek to Dhankar Lake above (3hrs) for more views", "One of the most photogenic spots in Spiti"), listOf("https://picsum.photos/id/1830/800/500", "https://picsum.photos/id/1831/800/500", "https://picsum.photos/id/1832/800/500")),
                    TrendingSpot("Pin Valley National Park", "A remote valley with rare snow leopards, ibex, and bar-headed geese — one of India's most untouched wildlife reserves.", "May – September", "₹100", listOf("Snow leopard sightings in winter (only for experienced trekkers)", "Mudh village is the last settlement before the park", "Basic homestays available in Mudh"), listOf("https://picsum.photos/id/1840/800/500", "https://picsum.photos/id/1841/800/500", "https://picsum.photos/id/1842/800/500"))
                ),
                tips = listOf("Download offline maps — there's zero internet in most of Spiti", "Carry cash for at least 10 days — no ATMs between Kaza and Manali", "Acclimatize properly — altitude sickness at 12,000ft is very real", "Write a postcard from Hikkim Post Office — it's a traveler rite of passage")
            ),

            Destination(
                id = 19, name = "Darjeeling", category = "Mountains",
                rating = 4.7, priceRange = "₹₹", emoji = "🍵",
                description = "Tea gardens, the Toy Train, Tiger Hill sunrise over Kanchenjunga, and colonial charm — Darjeeling is timeless.",
                bestTime = "March – May | September – November", daysNeeded = "3–4 days",
                highlights = listOf("Tiger Hill Sunrise", "Toy Train", "Tea Gardens", "Batasia Loop"),
                imageUrl = "https://picsum.photos/id/1023/600/400",
                entryFee = "Tiger Hill entry: ₹50 | Toy Train joy ride: ₹250",
                howToReach = listOf(
                    "✈️ Fly to Bagdogra Airport (90km from Darjeeling) — flights from Kolkata, Delhi",
                    "🚆 Or take a train to New Jalpaiguri (NJP) station — from Kolkata, Delhi, and other cities",
                    "🚌 From NJP or Bagdogra, take a shared jeep to Darjeeling (3hrs, ₹200) — most popular option",
                    "🚂 The Darjeeling Himalayan Railway Toy Train runs from NJP to Darjeeling (8hrs — scenic but slow)"
                ),
                trendingSpots = listOf(
                    TrendingSpot("Tiger Hill Sunrise", "Watch the first light of dawn illuminate Kanchenjunga — the world's third highest peak — in shades of gold and pink.", "March – May, September – November", "₹50", listOf("Wake up at 3:30AM and reach by 4:15AM", "Clear days are rare — check weather forecast", "Carry very warm clothes — below 5°C before sunrise"), listOf("https://picsum.photos/id/1900/800/500", "https://picsum.photos/id/1901/800/500", "https://picsum.photos/id/1902/800/500")),
                    TrendingSpot("Darjeeling Himalayan Railway", "A UNESCO World Heritage toy train winding through tea gardens, loops, and mountain villages — one of the world's great railway journeys.", "All year", "₹250 (joy ride)", listOf("Joy ride from Darjeeling to Ghoom and back (2hrs) is better than the full journey", "Book at Darjeeling station in advance", "Window seat for the best views"), listOf("https://picsum.photos/id/1910/800/500", "https://picsum.photos/id/1911/800/500", "https://picsum.photos/id/1912/800/500")),
                    TrendingSpot("Happy Valley Tea Estate", "One of the oldest tea estates in Darjeeling, open for guided factory tours — see the entire tea-making process from leaf to cup.", "March – November", "₹100", listOf("Factory tour takes about 45 min", "Buy first-flush tea directly here — way better than shops", "Located just 3km from town"), listOf("https://picsum.photos/id/1920/800/500", "https://picsum.photos/id/1921/800/500", "https://picsum.photos/id/1922/800/500")),
                    TrendingSpot("Batasia Loop", "An engineering marvel — the toy train spirals down through a loop, with a war memorial and panoramic Himalayan views at the center.", "All year", "₹20", listOf("Best viewed when the toy train passes through — check schedule", "War memorial is well maintained and worth a visit", "Flower garden inside is very photogenic"), listOf("https://picsum.photos/id/1930/800/500", "https://picsum.photos/id/1931/800/500", "https://picsum.photos/id/1932/800/500")),
                    TrendingSpot("Ghoom Monastery", "The oldest monastery in Darjeeling at 2550m — home to a 5-metre Maitreya Buddha statue and ancient Buddhist manuscripts.", "All year", "Free", listOf("Very peaceful and rarely crowded", "Morning prayers at 6AM are open to visitors", "Combine with Tiger Hill visit same morning"), listOf("https://picsum.photos/id/1940/800/500", "https://picsum.photos/id/1941/800/500", "https://picsum.photos/id/1942/800/500"))
                ),
                tips = listOf("Wake up at 4AM for Tiger Hill — the Kanchenjunga sunrise is worth every second", "Buy first-flush Darjeeling tea directly from the estate — way better than shops", "The toy train joy ride (Darjeeling to Ghoom) is better than the full journey", "It's cold all year — always pack layers")
            ),

            Destination(
                id = 20, name = "Wayanad", category = "Nature",
                rating = 4.7, priceRange = "₹₹", emoji = "🌿",
                description = "Kerala's wild heartland — dense forests, wildlife, ancient caves, waterfalls, and mist-covered mountains that feel like another world.",
                bestTime = "October – May", daysNeeded = "2–3 days",
                highlights = listOf("Chembra Peak", "Edakkal Caves", "Wildlife Sanctuary", "Soochipara Falls"),
                imageUrl = "https://picsum.photos/id/1003/600/400",
                entryFee = "Chembra Trek: ₹150 | Edakkal Caves: ₹25",
                howToReach = listOf(
                    "🚆 Take a train to Kozhikode (Calicut) — nearest major railway station (75km from Kalpetta)",
                    "🚌 From Kozhikode, take a KSRTC bus or cab to Kalpetta, Wayanad (2.5hrs)",
                    "🛺 Hire a jeep or rent a bike in Kalpetta to explore waterfalls and trekking spots"
                ),
                trendingSpots = listOf(
                    TrendingSpot("Chembra Peak", "Wayanad's highest peak at 2100m — a challenging 3-hour trek rewarded with the famous heart-shaped lake and panoramic views.", "October – May", "₹150 + guide fee", listOf("Mandatory guide from the forest office in Meppadi", "Start by 7AM — trek takes 6hrs return", "Heart-shaped lake is about halfway up"), listOf("https://picsum.photos/id/2000/800/500", "https://picsum.photos/id/2001/800/500", "https://picsum.photos/id/2002/800/500")),
                    TrendingSpot("Edakkal Caves", "Ancient caves with 6000-year-old prehistoric rock carvings — one of the most significant archaeological sites in South India.", "All year", "₹25", listOf("Long staircase — not suitable for people with knee problems", "Carvings are fragile — no touching", "Go on weekdays — very crowded on weekends"), listOf("https://picsum.photos/id/2010/800/500", "https://picsum.photos/id/2011/800/500", "https://picsum.photos/id/2012/800/500")),
                    TrendingSpot("Soochipara Falls", "A stunning three-tiered waterfall in the middle of the forest — with a pool at the base perfect for swimming.", "June – January", "₹20", listOf("Swimming allowed in the pool at base", "1km trek from the parking area", "Best after monsoon for full water flow"), listOf("https://picsum.photos/id/2020/800/500", "https://picsum.photos/id/2021/800/500", "https://picsum.photos/id/2022/800/500")),
                    TrendingSpot("Muthanga Wildlife Sanctuary", "Part of the Nilgiri Biosphere Reserve — jeep safaris through dense forest with sightings of elephants, deer, and bison.", "October – May", "₹150 (jeep extra)", listOf("Book jeep safari online in advance", "Early morning (6AM) safari has best wildlife sightings", "Elephant sightings are common here"), listOf("https://picsum.photos/id/2030/800/500", "https://picsum.photos/id/2031/800/500", "https://picsum.photos/id/2032/800/500")),
                    TrendingSpot("Pookode Lake", "A natural freshwater lake at 770m altitude surrounded by forest — boating and a small aquarium make it a relaxed half-day visit.", "All year", "₹20 (boating ₹80)", listOf("Paddle boating is the main activity", "Small aquarium has local freshwater species", "Very peaceful early morning"), listOf("https://picsum.photos/id/2040/800/500", "https://picsum.photos/id/2041/800/500", "https://picsum.photos/id/2042/800/500"))
                ),
                tips = listOf("Chembra Peak trek requires a guide and permit — book at the forest office in Meppadi", "The heart-shaped lake at Chembra summit is the most instagrammed spot in Wayanad", "Monsoon makes waterfalls stunning but trekking risky — choose accordingly", "Stay in a treehouse homestay — some of the best in South India are here")
            ),

            Destination(
                id = 21, name = "Kolkata", category = "Food",
                rating = 4.7, priceRange = "₹₹", emoji = "🍛",
                description = "The City of Joy — Bengali street food, colonial architecture, tram rides, art galleries, and the warmest people in India.",
                bestTime = "October – March", daysNeeded = "2–3 days",
                highlights = listOf("Victoria Memorial", "Howrah Bridge", "Park Street", "Durga Puja"),
                imageUrl = "https://picsum.photos/id/1048/600/400",
                entryFee = "Victoria Memorial: ₹30 | Indian Museum: ₹20",
                howToReach = listOf(
                    "✈️ Fly to Netaji Subhas Chandra Bose International Airport — direct flights from most cities",
                    "🚆 Or take a train to Howrah Junction or Sealdah Station — connected from all over India",
                    "🚇 Kolkata Metro is the cheapest and fastest city transport — buy a token"
                ),
                trendingSpots = listOf(
                    TrendingSpot("Victoria Memorial", "A magnificent white marble monument built in 1921 — part palace, part museum, surrounded by beautiful gardens in the heart of Kolkata.", "October – March", "₹30", listOf("Sound and light show in evenings is excellent", "Museum inside has colonial-era artifacts", "Gardens are free and beautiful for an evening walk"), listOf("https://picsum.photos/id/2100/800/500", "https://picsum.photos/id/2101/800/500", "https://picsum.photos/id/2102/800/500")),
                    TrendingSpot("Howrah Bridge at Dawn", "The iconic 705-metre cantilever bridge over the Hooghly river — no nuts or bolts used in construction, entirely riveted.", "All year", "Free", listOf("Pre-dawn visit gives the most dramatic photos with mist", "Flower market below the bridge at 4–6AM is spectacular", "Walk across for the river views"), listOf("https://picsum.photos/id/2110/800/500", "https://picsum.photos/id/2111/800/500", "https://picsum.photos/id/2112/800/500")),
                    TrendingSpot("College Street & Coffee House", "The world's largest second-hand book market — and the legendary Indian Coffee House where intellectuals have debated for 150 years.", "All year", "Free", listOf("Best on weekday mornings when it's less chaotic", "Indian Coffee House is on the 1st floor — don't miss a chai here", "Rare and out-of-print books available at throwaway prices"), listOf("https://picsum.photos/id/2120/800/500", "https://picsum.photos/id/2121/800/500", "https://picsum.photos/id/2122/800/500")),
                    TrendingSpot("Kumartuli", "The potters' quarter — where thousands of artisans craft the massive clay idols of Durga and other deities throughout the year.", "All year", "Free", listOf("Most active August–October before Durga Puja", "Artisans welcome visitors and photography", "A truly unique insight into Bengali culture"), listOf("https://picsum.photos/id/2130/800/500", "https://picsum.photos/id/2131/800/500", "https://picsum.photos/id/2132/800/500")),
                    TrendingSpot("Princep Ghat Sunset", "A beautiful colonial-era riverside ghat with Roman arches and boat rides on the Hooghly — the most romantic sunset spot in Kolkata.", "All year", "Free", listOf("Evening is the best time — locals gather here too", "Tram stops right outside — take one for the experience", "Strand Road food stalls nearby are excellent"), listOf("https://picsum.photos/id/2140/800/500", "https://picsum.photos/id/2141/800/500", "https://picsum.photos/id/2142/800/500"))
                ),
                tips = listOf("Eat kathi rolls at Nizam's on New Market — the original, since 1932", "Take a tram ride — Kolkata is the only city with functioning trams in India", "Visit during Durga Puja (Oct) — nothing like it anywhere in the world", "Rabindra Sarani and College Street are chaotic but unmissable")
            ),

            Destination(
                id = 22, name = "Amritsar", category = "Spiritual",
                rating = 4.9, priceRange = "₹₹", emoji = "🕌",
                description = "The spiritual and cultural heart of Punjab — Golden Temple's serenity, langar for thousands, Wagah Border energy, and food that wrecks all diets.",
                bestTime = "October – March", daysNeeded = "2 days",
                highlights = listOf("Golden Temple", "Wagah Border", "Jallianwala Bagh", "Langar"),
                imageUrl = "https://picsum.photos/id/1082/600/400",
                entryFee = "All major sites: Free",
                howToReach = listOf(
                    "✈️ Fly to Sri Guru Ram Dass Jee International Airport, Amritsar — direct flights from Delhi, Mumbai",
                    "🚆 Or take a train to Amritsar Junction — connected from Delhi (5hrs), Mumbai (overnight)",
                    "🛺 Auto rickshaws are the main transport within the city — Uber also works"
                ),
                trendingSpots = listOf(
                    TrendingSpot("Golden Temple (Harmandir Sahib)", "The holiest Sikh shrine — a breathtaking gold-plated temple rising from a sacred pool, open to people of all faiths 24 hours a day.", "All year", "Free", listOf("Visit at 4AM for the most peaceful and magical experience", "Cover your head and remove shoes before entering", "Langar (free community kitchen) serves 100,000 people daily"), listOf("https://picsum.photos/id/2200/800/500", "https://picsum.photos/id/2201/800/500", "https://picsum.photos/id/2202/800/500")),
                    TrendingSpot("Wagah Border Ceremony", "The daily flag-lowering ceremony at the India-Pakistan border — a dramatic, patriotic, high-energy spectacle with soldiers and thousands of cheering crowds.", "All year", "Free", listOf("Reach by 4PM for a good seat — ceremony at sunset", "Arrive early — the crowd fills up completely", "Pakistani side does the same ceremony simultaneously"), listOf("https://picsum.photos/id/2210/800/500", "https://picsum.photos/id/2211/800/500", "https://picsum.photos/id/2212/800/500")),
                    TrendingSpot("Jallianwala Bagh", "The memorial garden of the 1919 massacre — the bullet marks on the walls and the well where people jumped to escape are deeply moving.", "All year", "Free", listOf("The bullet holes in the walls are still preserved", "Sound and light show in the evening", "A very solemn place — be respectful"), listOf("https://picsum.photos/id/2220/800/500", "https://picsum.photos/id/2221/800/500", "https://picsum.photos/id/2222/800/500")),
                    TrendingSpot("Partition Museum", "The world's first museum dedicated to the 1947 Partition of India — deeply powerful, emotional, and important.", "All year", "₹200", listOf("Allow 2–3 hours for the full experience", "Audio guide strongly recommended", "One of the most important museums in India — don't skip"), listOf("https://picsum.photos/id/2230/800/500", "https://picsum.photos/id/2231/800/500", "https://picsum.photos/id/2232/800/500")),
                    TrendingSpot("Hall Bazaar & Punjabi Food", "Amritsar's main market street — and the best place to eat kulcha, lassi, pinni, and all the Punjabi food you can handle.", "All year", "Free", listOf("Bharawan Da Dhaba for kulcha is legendary", "Kanha Sweets for pinni and jalebi", "Best explored in the evening when all stalls are open"), listOf("https://picsum.photos/id/2240/800/500", "https://picsum.photos/id/2241/800/500", "https://picsum.photos/id/2242/800/500"))
                ),
                tips = listOf("Golden Temple at 4AM is the most peaceful experience — totally different from daytime", "Wagah Border ceremony is at sunset — reach by 4PM to get a good seat", "Cover your head and remove shoes before entering the Golden Temple", "Kulcha at Bharawan Da Dhaba and lassi at Ahuja Milk Bhandar are legendary")
            ),

            Destination(
                id = 23, name = "Mcleod Ganj", category = "Spiritual",
                rating = 4.7, priceRange = "₹", emoji = "🏔️",
                description = "Little Lhasa in India — home of the Dalai Lama, Tibetan culture, momos, mountains, and the most peaceful energy in the Himalayas.",
                bestTime = "March – June | September – November", daysNeeded = "3–4 days",
                highlights = listOf("Tsuglagkhang Complex", "Bhagsu Falls", "Dal Lake", "Tibetan Markets"),
                imageUrl = "https://picsum.photos/id/1057/600/400",
                entryFee = "Tsuglagkhang: Free | Bhagsu Falls: Free",
                howToReach = listOf(
                    "🚆 Take a train to Pathankot Junction — nearest major station (90km from Mcleod Ganj)",
                    "🚌 From Pathankot, take an HRTC bus to Dharamsala/Mcleod Ganj (3hrs)",
                    "🚌 Or take a direct overnight Volvo bus from Delhi to Mcleod Ganj (12hrs) — most convenient",
                    "🛺 Walking is the best way within Mcleod Ganj — it's a small hill town"
                ),
                trendingSpots = listOf(
                    TrendingSpot("Tsuglagkhang Complex", "The Dalai Lama's official residence and temple — a place of deep spiritual significance for Tibetan Buddhists worldwide.", "All year", "Free", listOf("Check dalailama.com for public teaching schedules", "Photography inside the main temple is restricted", "Tibet Museum inside the complex is excellent"), listOf("https://picsum.photos/id/2300/800/500", "https://picsum.photos/id/2301/800/500", "https://picsum.photos/id/2302/800/500")),
                    TrendingSpot("Triund Trek", "A 9km trek to a magical meadow at 2842m — one of the most accessible and rewarding day treks in the Himalayas.", "March – June, September – November", "Free", listOf("Start early morning from McLeod Ganj", "Camping at the top is allowed — carry a tent or hire one", "Snow can block the trail November onwards"), listOf("https://picsum.photos/id/2310/800/500", "https://picsum.photos/id/2311/800/500", "https://picsum.photos/id/2312/800/500")),
                    TrendingSpot("Bhagsunaag Waterfall", "A popular waterfall 2km from Mcleod Ganj — easy walk through the village, with cafes perched right next to the falls.", "March – October", "Free", listOf("The cafes next to the waterfall serve excellent Tibetan food", "Very crowded on weekends — go on a weekday", "Continue past the falls for a quieter trek"), listOf("https://picsum.photos/id/2320/800/500", "https://picsum.photos/id/2321/800/500", "https://picsum.photos/id/2322/800/500")),
                    TrendingSpot("Tibetan Market", "A bustling market selling Tibetan thangkas, singing bowls, prayer flags, handmade jewelry, and winter woolens.", "All year", "Free", listOf("Best for authentic Tibetan crafts and souvenirs", "Singing bowls make excellent gifts", "Warm woolens here are much cheaper than in the plains"), listOf("https://picsum.photos/id/2330/800/500", "https://picsum.photos/id/2331/800/500", "https://picsum.photos/id/2332/800/500")),
                    TrendingSpot("Dal Lake Dharamsala", "A small sacred lake 11km from Mcleod Ganj surrounded by cedar trees — peaceful, uncrowded, and great for an evening walk.", "All year", "Free", listOf("Very different from Dal Lake in Kashmir — much smaller and quieter", "Cedar forest walk around the lake is beautiful", "Combine with Triund base visit same day"), listOf("https://picsum.photos/id/2340/800/500", "https://picsum.photos/id/2341/800/500", "https://picsum.photos/id/2342/800/500"))
                ),
                tips = listOf("Triund trek (6km) gives one of the best Himalayan views — do it", "Momos everywhere but best at the Tibetan-run stalls near the monastery", "Check if Dalai Lama is giving public teachings — attend if he is", "The town shuts down early — evenings are quiet and meditative")
            ),

            Destination(
                id = 24, name = "Alleppey (Alappuzha)", category = "Nature",
                rating = 4.8, priceRange = "₹₹", emoji = "🚤",
                description = "The Venice of the East — overnight houseboat stays in Kerala backwaters, Chinese fishing nets, coconut lagoons, and total stillness.",
                bestTime = "November – February", daysNeeded = "2–3 days",
                highlights = listOf("Houseboat Stay", "Backwater Cruise", "Alappuzha Beach", "Nehru Trophy Race"),
                imageUrl = "https://picsum.photos/id/1080/600/400",
                entryFee = "Houseboat: ₹6,000–15,000/night | Canoe tours: ₹400",
                howToReach = listOf(
                    "🚆 Take a train to Alleppey (Alappuzha) railway station — on the Chennai–Trivandrum coastal line",
                    "🚌 Or take a KSRTC bus from Kochi (Ernakulam) to Alleppey (1.5hrs, 60km)",
                    "⛵ Board your houseboat from DTPC jetty or Finishing Point — operator will guide you"
                ),
                trendingSpots = listOf(
                    TrendingSpot("Backwater Houseboat Stay", "An overnight stay on a traditional Kerala kettuvallam (rice boat) converted into a floating bedroom — drifting through silent coconut-lined canals.", "November – February", "₹6,000–15,000/night", listOf("Book at least 2 weeks in advance", "Premium houseboats include AC, hot water, and chef", "Sunrise from the houseboat deck is unmissable"), listOf("https://picsum.photos/id/2400/800/500", "https://picsum.photos/id/2401/800/500", "https://picsum.photos/id/2402/800/500")),
                    TrendingSpot("Village Canoe Tour", "Narrow canoe through tiny village waterways not accessible to big houseboats — real Kerala life, paddy fields, and local fishermen.", "All year", "₹400–600", listOf("More authentic than houseboats", "Morning tours have the most activity", "Guides narrate local life and history"), listOf("https://picsum.photos/id/2410/800/500", "https://picsum.photos/id/2411/800/500", "https://picsum.photos/id/2412/800/500")),
                    TrendingSpot("Punnamada Lake", "The vast lake that hosts the famous Nehru Trophy Boat Race every August — rowing and backwater views year-round.", "All year", "Free", listOf("Nehru Trophy race in August is a massive event — book stay 3 months ahead", "Quiet at other times — great for sunrise photography", "Lake view from the bund road is beautiful"), listOf("https://picsum.photos/id/2420/800/500", "https://picsum.photos/id/2421/800/500", "https://picsum.photos/id/2422/800/500")),
                    TrendingSpot("Alappuzha Beach", "A wide, quiet beach with an old British-era pier — more local than tourist, great for watching fishing boats at dawn.", "October – March", "Free", listOf("Sunrise is the best time — fishermen bring in the catch", "Old lighthouse nearby is worth a look", "Much cleaner and quieter than many Kerala beaches"), listOf("https://picsum.photos/id/2430/800/500", "https://picsum.photos/id/2431/800/500", "https://picsum.photos/id/2432/800/500")),
                    TrendingSpot("Krishnapuram Palace", "A well-preserved 18th-century Kerala-style palace 47km from Alleppey — housing the famous Gajendra Moksham mural painting.", "All year", "₹20", listOf("One of the finest examples of Kerala architecture", "Gajendra Moksham painting is 49 sq ft — massive and stunning", "Combine with a Kayamkulam trip"), listOf("https://picsum.photos/id/2440/800/500", "https://picsum.photos/id/2441/800/500", "https://picsum.photos/id/2442/800/500"))
                ),
                tips = listOf("Book houseboat at least a week in advance — quality ones fill fast", "Budget tip: canoe tours through narrow village canals show more than big houseboats", "Nehru Trophy Boat Race is in August — plan around it for a once-in-a-lifetime experience", "Sunrise on the backwaters from the houseboat deck is magic — set an alarm")
            ),

            Destination(
                id = 25, name = "Jodhpur", category = "Heritage",
                rating = 4.6, priceRange = "₹₹", emoji = "🔵",
                description = "The Blue City — Mehrangarh Fort looming over a sea of indigo houses, vintage clock towers, and Rajasthani food that hits different.",
                bestTime = "October – March", daysNeeded = "2 days",
                highlights = listOf("Mehrangarh Fort", "Blue City View", "Jaswant Thada", "Sardar Market"),
                imageUrl = "https://picsum.photos/id/1069/600/400",
                entryFee = "Mehrangarh Fort: ₹100 | Jaswant Thada: ₹30",
                howToReach = listOf(
                    "✈️ Fly to Jodhpur Airport — direct flights from Delhi, Mumbai, Jaipur",
                    "🚆 Or take a train to Jodhpur Junction — well connected on the Rajasthan railway network",
                    "🛺 Auto rickshaws are everywhere — bargain or use Ola/Uber"
                ),
                trendingSpots = listOf(
                    TrendingSpot("Mehrangarh Fort", "One of India's largest forts — a massive sandstone citadel rising 120m above the blue city, with priceless artifacts and cannon-scarred walls.", "October – March", "₹100", listOf("Museum inside has the best royal artifact collection in Rajasthan", "Rooftop has the iconic blue city view", "Sound and light show at 7PM is excellent"), listOf("https://picsum.photos/id/2500/800/500", "https://picsum.photos/id/2501/800/500", "https://picsum.photos/id/2502/800/500")),
                    TrendingSpot("Blue City Lanes", "The narrow indigo-painted lanes below Mehrangarh — a maze of blue walls, chai stalls, and artisan workshops.", "All year", "Free", listOf("Get lost deliberately — every lane has something interesting", "Best photographed from the fort walls above", "Navchokiya and Brahmpuri are the most photogenic areas"), listOf("https://picsum.photos/id/2510/800/500", "https://picsum.photos/id/2511/800/500", "https://picsum.photos/id/2512/800/500")),
                    TrendingSpot("Jaswant Thada", "A white marble cenotaph built in 1899 — strikingly beautiful against the blue sky, with delicate marble screens that glow when backlit.", "All year", "₹30", listOf("Best in late afternoon when the marble turns golden", "Very peaceful compared to the fort", "Portrait gallery of Jodhpur rulers inside"), listOf("https://picsum.photos/id/2520/800/500", "https://picsum.photos/id/2521/800/500", "https://picsum.photos/id/2522/800/500")),
                    TrendingSpot("Ghanta Ghar (Clock Tower)", "Jodhpur's iconic Victorian clock tower at the center of the city — surrounded by the chaotic, colorful Sardar Market.", "All year", "Free", listOf("Market is best in the evening", "Spice shops around the tower are excellent", "Makhaniya Lassi stalls right here are legendary"), listOf("https://picsum.photos/id/2530/800/500", "https://picsum.photos/id/2531/800/500", "https://picsum.photos/id/2532/800/500")),
                    TrendingSpot("Umaid Bhawan Palace", "A stunning Art Deco palace — part royal residence, part heritage hotel, part museum. Still home to the Jodhpur royal family.", "All year", "Museum ₹50", listOf("Museum section is open to visitors", "Exterior photography is allowed freely", "One of the world's finest Art Deco buildings"), listOf("https://picsum.photos/id/2540/800/500", "https://picsum.photos/id/2541/800/500", "https://picsum.photos/id/2542/800/500"))
                ),
                tips = listOf("The blue city view from Mehrangarh Fort at sunset is the postcard shot of Rajasthan", "Climb to Chamunda Devi Temple inside the fort for the best aerial view", "Makhaniya Lassi near Clock Tower is famous — try it", "Rajasthani thali at Gypsy restaurant is the best value meal in Jodhpur")
            ),

            Destination(
                id = 26, name = "Shimla", category = "Mountains",
                rating = 4.5, priceRange = "₹₹", emoji = "❄️",
                description = "India's colonial hill station — Mall Road, pine forests, the Kalka–Shimla toy train, and snow in winter that transforms the whole city.",
                bestTime = "March – June | December – January (snow)", daysNeeded = "3–4 days",
                highlights = listOf("Mall Road", "Jakhu Temple", "Kufri", "Kalka-Shimla Toy Train"),
                imageUrl = "https://picsum.photos/id/1046/600/400",
                entryFee = "Most attractions: Free | Toy Train: ₹290",
                howToReach = listOf(
                    "🚆 Take a train to Kalka Junction — on the Delhi–Ambala–Kalka route",
                    "🚂 Board the Kalka–Shimla Heritage Toy Train from Kalka (5hrs, UNESCO listed)",
                    "🚌 Or take a direct Volvo bus from Delhi/Chandigarh to Shimla (5–6hrs) — faster option",
                    "🛺 Mall Road area is pedestrian only — walk or take local taxis"
                ),
                trendingSpots = listOf(
                    TrendingSpot("Mall Road", "Shimla's iconic pedestrian promenade — colonial-era shops, cafes, Gaiety Theatre, and stunning valley views on both sides.", "All year", "Free", listOf("Evening walk is the classic Shimla experience", "Gaiety Theatre has regular cultural performances", "Scandal Point at the end has the best view"), listOf("https://picsum.photos/id/2600/800/500", "https://picsum.photos/id/2601/800/500", "https://picsum.photos/id/2602/800/500")),
                    TrendingSpot("Jakhu Temple", "A famous Hanuman temple at 2455m — the highest point in Shimla, accessible by a 2km trek through a forest full of aggressive monkeys.", "All year", "Free", listOf("Guard your food, glasses, and phones — monkeys snatch everything", "Ropeway also available to avoid the monkey gauntlet", "Views of Shimla and snow peaks from top are excellent"), listOf("https://picsum.photos/id/2610/800/500", "https://picsum.photos/id/2611/800/500", "https://picsum.photos/id/2612/800/500")),
                    TrendingSpot("Kalka–Shimla Toy Train", "A UNESCO World Heritage mountain railway — 96km through 103 tunnels and 800 bridges across the Himalayan foothills.", "All year", "₹290", listOf("Morning Shivalik Deluxe Express has the best timings", "Window seat on the left side going up to Shimla for best views", "Book on IRCTC at least a week in advance"), listOf("https://picsum.photos/id/2620/800/500", "https://picsum.photos/id/2621/800/500", "https://picsum.photos/id/2622/800/500")),
                    TrendingSpot("Kufri", "A small hill resort 13km from Shimla — skiing in winter, yak rides, and Himalayan wildlife park with Himalayan bear and snow leopard.", "December – March (snow)", "₹50 (park)", listOf("Best in January–February for deep snow", "Horse and yak rides available on the slopes", "Wildlife park has rescued Himalayan animals"), listOf("https://picsum.photos/id/2630/800/500", "https://picsum.photos/id/2631/800/500", "https://picsum.photos/id/2632/800/500")),
                    TrendingSpot("Christ Church", "The second oldest church in North India built in 1857 — stunning Neo-Gothic architecture with original stained glass windows.", "All year", "Free", listOf("Interior photography allowed", "Sunday morning service is open to visitors", "Ridge area around the church is very scenic"), listOf("https://picsum.photos/id/2640/800/500", "https://picsum.photos/id/2641/800/500", "https://picsum.photos/id/2642/800/500"))
                ),
                tips = listOf("The Kalka–Shimla toy train journey is more beautiful than Shimla itself — don't skip it", "Watch your pockets and food near Jakhu Temple — monkeys are aggressive", "Kufri (13km from Shimla) has better snow than Shimla itself", "Avoid summer peak season weekends — Mall Road becomes unbearably crowded")
            ),

            Destination(
                id = 27, name = "Varkala", category = "Beaches",
                rating = 4.6, priceRange = "₹₹", emoji = "🌊",
                description = "Kerala's cliff beach — restaurants and shacks perched on red laterite cliffs overlooking the Arabian Sea, with a sacred beach below.",
                bestTime = "October – March", daysNeeded = "2–3 days",
                highlights = listOf("Papanasam Beach", "Cliff Walk", "Ayurveda", "Janardanaswamy Temple"),
                imageUrl = "https://picsum.photos/id/1050/600/400",
                entryFee = "Temple: Free | Ayurveda sessions: ₹500–2000",
                howToReach = listOf(
                    "🚆 Take a train to Varkala Sivagiri station — on the Chennai–Thiruvananthapuram coastal line",
                    "🛺 Auto from Varkala station to Cliff/Helipad area (10min, ₹80–100)",
                    "🚌 Or take a bus from Thiruvananthapuram to Varkala (1hr, 50km)"
                ),
                trendingSpots = listOf(
                    TrendingSpot("North Cliff", "Varkala's main clifftop strip — a 1km walk lined with restaurants, yoga studios, and shops perched 30m above the sea.", "October – March", "Free", listOf("Best at sunset when the cliff glows red", "Many restaurants have cliff-edge seating — book ahead", "Sunrise from the cliff is also stunning"), listOf("https://picsum.photos/id/2700/800/500", "https://picsum.photos/id/2701/800/500", "https://picsum.photos/id/2702/800/500")),
                    TrendingSpot("Papanasam Beach", "The sacred beach below the cliff — bathing here is believed to wash away sins, with pilgrims and sunbathers sharing the same shore.", "October – March", "Free", listOf("Swimming is safe in the middle section", "Mineral water springs seep from the cliff base", "Avoid the temple end for swimming — it's restricted"), listOf("https://picsum.photos/id/2710/800/500", "https://picsum.photos/id/2711/800/500", "https://picsum.photos/id/2712/800/500")),
                    TrendingSpot("Janardanaswamy Temple", "A 2000-year-old Vishnu temple right at the beach — one of the few temples in Kerala where the deity faces the sea.", "All year", "Free", listOf("Non-Hindus cannot enter the inner sanctum", "Temple festival in March-April is spectacular", "The location right on the beach is unique"), listOf("https://picsum.photos/id/2720/800/500", "https://picsum.photos/id/2721/800/500", "https://picsum.photos/id/2722/800/500")),
                    TrendingSpot("Kappil Lake & Beach", "A tranquil lake separated from the sea by a narrow strip of land — backwater boat rides and a completely uncrowded beach.", "October – March", "Free", listOf("5km from Varkala — hire an auto", "Boat rides across the lake to the beach", "Far less crowded than Papanasam"), listOf("https://picsum.photos/id/2730/800/500", "https://picsum.photos/id/2731/800/500", "https://picsum.photos/id/2732/800/500")),
                    TrendingSpot("Ayurveda Treatment", "Varkala is one of Kerala's best places for authentic Ayurvedic treatments — Abhyangam, Shirodhara, and full Panchakarma packages.", "All year", "₹500–2000/session", listOf("Choose established clinics over random beach stalls", "Minimum 3-day treatment recommended for real benefits", "Book morning slots when therapists are freshest"), listOf("https://picsum.photos/id/2740/800/500", "https://picsum.photos/id/2741/800/500", "https://picsum.photos/id/2742/800/500"))
                ),
                tips = listOf("The Papanasam Beach is sacred — swimming is restricted near the temple end", "Cliff cafes at sunset with the sea below are the highlight of Varkala", "Book Ayurveda treatments at established clinics, not random beach stalls", "It's quieter and cheaper than Goa — ideal for a slow travel week")
            ),

            Destination(
                id = 28, name = "Rann of Kutch", category = "Nature",
                rating = 4.8, priceRange = "₹₹", emoji = "🌕",
                description = "The Great White Desert — an endless salt flat that turns silver under the full moon. Rann Utsav, folk music, and zero-gravity silence.",
                bestTime = "November – February (Rann Utsav season)", daysNeeded = "2–3 days",
                highlights = listOf("Full Moon at White Rann", "Rann Utsav", "Kalo Dungar", "Craft Villages"),
                imageUrl = "https://picsum.photos/id/1074/600/400",
                entryFee = "White Rann entry: ₹100 | Rann Utsav tent packages vary",
                howToReach = listOf(
                    "✈️ Fly to Bhuj Airport — nearest airport (80km from Dhordo/White Rann)",
                    "🚆 Or take a train to Bhuj railway station — from Ahmedabad (7hrs), Mumbai (overnight)",
                    "🚌 From Bhuj, take a cab or bus to Dhordo village (2hrs) — government buses are limited",
                    "🛺 Jeep safari within the Rann — arrange through your tent resort"
                ),
                trendingSpots = listOf(
                    TrendingSpot("White Rann at Full Moon", "Walking on the endless white salt flat under a full moon — one of the most surreal and otherworldly experiences in India.", "November – February", "₹100", listOf("Time your visit for 2–3 days around the full moon", "The salt crunches under your feet like snow", "Carry warm clothes — desert nights drop below 5°C"), listOf("https://picsum.photos/id/2800/800/500", "https://picsum.photos/id/2801/800/500", "https://picsum.photos/id/2802/800/500")),
                    TrendingSpot("Rann Utsav Festival", "A massive cultural festival from November to February — folk music, dance, camel rides, handicraft exhibitions, and hot air balloons.", "November – February", "Tent packages ₹3000–15000/night", listOf("Book 2 months in advance — tents sell out completely", "Cultural performances every evening in the tent city", "Hot air balloon rides at sunrise are available"), listOf("https://picsum.photos/id/2810/800/500", "https://picsum.photos/id/2811/800/500", "https://picsum.photos/id/2812/800/500")),
                    TrendingSpot("Kalo Dungar (Black Hill)", "The highest point in Kutch at 462m — panoramic view of the Rann and the India-Pakistan border, with a 400-year-old temple at the top.", "All year", "Free", listOf("Sunset view of the Rann from here is spectacular", "Temple feeds wild foxes every evening — very unusual", "23km from Dhordo — hire a cab"), listOf("https://picsum.photos/id/2820/800/500", "https://picsum.photos/id/2821/800/500", "https://picsum.photos/id/2822/800/500")),
                    TrendingSpot("Kutchi Craft Villages", "Villages like Hodka, Nirona, and Bhirandiyara where artisans practice 5000-year-old crafts — Rogan painting, lacquerwork, and embroidery.", "All year", "Free", listOf("Buy directly from the artisan — no middlemen", "Rogan art uses castor oil paint — extremely rare craft", "Village homestays available for an authentic experience"), listOf("https://picsum.photos/id/2830/800/500", "https://picsum.photos/id/2831/800/500", "https://picsum.photos/id/2832/800/500")),
                    TrendingSpot("Flamingo Point", "A seasonal congregation of thousands of flamingos in the Rann — one of the largest flamingo gatherings in Asia.", "November – February", "Free", listOf("Early morning visit for the most birds", "Binoculars essential for good views", "Best between December and January at peak season"), listOf("https://picsum.photos/id/2840/800/500", "https://picsum.photos/id/2841/800/500", "https://picsum.photos/id/2842/800/500"))
                ),
                tips = listOf("Time your visit for a full moon night — the white salt reflecting moonlight is otherworldly", "Book Rann Utsav tents 2 months in advance — they sell out completely", "Carry windcheater and warm clothes — desert nights are freezing", "Buy Kutchi embroidery directly from craft villages — much cheaper than shops")
            ),

            Destination(
                id = 29, name = "Coimbatore", category = "Food",
                rating = 4.4, priceRange = "₹", emoji = "🏭",
                description = "South India's underrated food city — idli, dosa, and filter coffee at its finest, plus the Isha Yoga Center and gateway to Ooty/Munnar.",
                bestTime = "October – March", daysNeeded = "1–2 days",
                highlights = listOf("Isha Yoga Center", "Marudhamalai Temple", "VOC Park", "Textile Markets"),
                imageUrl = "https://picsum.photos/id/1072/600/400",
                entryFee = "Isha Yoga Center: Free | Temple: Free",
                howToReach = listOf(
                    "✈️ Fly to Coimbatore International Airport — direct flights from Chennai, Bengaluru, Mumbai, Delhi",
                    "🚆 Or take a train to Coimbatore Junction — well connected on the Chennai–Palakkad–Ernakulam route",
                    "🛺 City buses and autos available; Ola/Uber works well within the city"
                ),
                trendingSpots = listOf(
                    TrendingSpot("Isha Yoga Center & Adiyogi", "Sadhguru's world-famous yoga center in the Velliangiri foothills — and the 112-foot Adiyogi Shiva statue, the world's largest bust.", "All year", "Free", listOf("Take the guided tour for the full experience", "Dhyanalinga meditation dome is open to all faiths", "30km from city — hire a cab for the day"), listOf("https://picsum.photos/id/2900/800/500", "https://picsum.photos/id/2901/800/500", "https://picsum.photos/id/2902/800/500")),
                    TrendingSpot("Marudhamalai Murugan Temple", "A famous hilltop Murugan temple 12km from Coimbatore — reached by steps or rope car, with panoramic city views.", "All year", "Free", listOf("Rope car (ropeway) operates 7AM–6PM", "Very crowded on Fridays and festival days", "Sunrise from the hilltop is beautiful"), listOf("https://picsum.photos/id/2910/800/500", "https://picsum.photos/id/2911/800/500", "https://picsum.photos/id/2912/800/500")),
                    TrendingSpot("RS Puram Food Street", "Coimbatore's most famous food area — traditional South Indian breakfast spots, filter coffee joints, and evening street food.", "All year", "Free", listOf("Annapoorna restaurant is the legendary breakfast stop", "Best filter coffee in Tamil Nadu is served here", "Go between 7–9AM for the full breakfast culture"), listOf("https://picsum.photos/id/2920/800/500", "https://picsum.photos/id/2921/800/500", "https://picsum.photos/id/2922/800/500")),
                    TrendingSpot("Siruvani Waterfalls", "One of the sweetest natural water sources in India — a waterfall and reservoir in the forest 35km from Coimbatore.", "June – January", "₹25", listOf("Water here is genuinely sweet — drink it fresh", "Kerala permit required as it's on the border", "Very crowded on weekends — go on weekdays"), listOf("https://picsum.photos/id/2930/800/500", "https://picsum.photos/id/2931/800/500", "https://picsum.photos/id/2932/800/500")),
                    TrendingSpot("VOC Park & Zoo", "A large urban park with a zoo, toy train, and a mini amusement park — popular family destination in the heart of Coimbatore.", "All year", "₹20", listOf("Zoo has good big cat and primate sections", "Toy train for kids is a big hit", "Good evening walk spot"), listOf("https://picsum.photos/id/2940/800/500", "https://picsum.photos/id/2941/800/500", "https://picsum.photos/id/2942/800/500"))
                ),
                tips = listOf("Isha Yoga Center is 30km from city — plan a half day visit minimum", "The Adiyogi statue at Isha is 112 feet tall — incredible at dusk", "Eat breakfast at Annapoorna or Shree Annapoorna restaurants — Coimbatore classics", "Use Coimbatore as a base — Ooty and Munnar are both within 3hrs")
            ),

            Destination(
                id = 30, name = "Tirupati", category = "Spiritual",
                rating = 4.8, priceRange = "₹₹", emoji = "🛕",
                description = "The richest temple in the world — Balaji's blessings, the tonsure ritual, and a spiritual energy felt by millions of devotees every single day.",
                bestTime = "September – March", daysNeeded = "1–2 days",
                highlights = listOf("Venkateswara Temple", "Akasha Ganga", "Silathoranam", "ISKCON Temple"),
                imageUrl = "https://picsum.photos/id/1049/600/400",
                entryFee = "Darshan: Free (wait 6–12hrs) or Special entry ₹300",
                howToReach = listOf(
                    "✈️ Fly to Tirupati Airport — flights from Chennai, Hyderabad, Bengaluru",
                    "🚆 Or take a train to Tirupati railway station — well connected from Chennai (3hrs), Hyderabad (7hrs)",
                    "🚌 TTD operates free buses from Tirupati town to Tirumala hill temple every 15min",
                    "🚡 Or take the TTD ropeway (Srivari Mettu) — scenic aerial view of the hills"
                ),
                trendingSpots = listOf(
                    TrendingSpot("Sri Venkateswara Temple", "The richest and most visited religious site in the world — over 50,000 devotees visit daily to seek blessings of Lord Balaji.", "All year", "Free / ₹300 special", listOf("Book special darshan at ttdsevaonline.com", "Dress code strictly enforced — traditional Indian attire", "Laddoo prasadam counter is right outside the temple"), listOf("https://picsum.photos/id/3000/800/500", "https://picsum.photos/id/3001/800/500", "https://picsum.photos/id/3002/800/500")),
                    TrendingSpot("Akasha Ganga", "A sacred waterfall inside the Tirumala hills — pilgrims bathe here before visiting the main temple.", "All year", "Free", listOf("Short walk from the main temple complex", "Water is believed to have medicinal properties", "Very peaceful compared to the main temple crowds"), listOf("https://picsum.photos/id/3010/800/500", "https://picsum.photos/id/3011/800/500", "https://picsum.photos/id/3012/800/500")),
                    TrendingSpot("Silathoranam", "A rare natural arch formation in the rocks — considered sacred and one of the only natural arches in Asia on a hillside.", "All year", "Free", listOf("1km walk from Tirumala bus stand", "Natural quartzite arch formed over millions of years", "Very few tourists know about this — peaceful spot"), listOf("https://picsum.photos/id/3020/800/500", "https://picsum.photos/id/3021/800/500", "https://picsum.photos/id/3022/800/500")),
                    TrendingSpot("Sri Padmavathi Temple – Tiruchanur", "The temple of Goddess Padmavathi, consort of Venkateswara — considered equally important to visit along with Tirumala.", "All year", "Free", listOf("10km from Tirupati town — take TTD bus", "Much shorter queue than the main temple", "Very ornate Dravidian temple architecture"), listOf("https://picsum.photos/id/3030/800/500", "https://picsum.photos/id/3031/800/500", "https://picsum.photos/id/3032/800/500")),
                    TrendingSpot("Chandragiri Fort", "A 16th-century fort 11km from Tirupati — where the kings of Vijayanagara empire granted land to build the Taj Mahal.", "All year", "₹25", listOf("Sound and light show in the evening is excellent", "Raja Mahal and Rani Mahal palaces inside", "Very few tourists visit — peaceful exploration"), listOf("https://picsum.photos/id/3040/800/500", "https://picsum.photos/id/3041/800/500", "https://picsum.photos/id/3042/800/500"))
                ),
                tips = listOf("Book special darshan tickets online at ttdsevaonline.com — avoid the free queue which is 8–12hrs", "Arrive at Tirupati town a night before — temple is 30km uphill at Tirumala", "Laddoo prasadam is available at counters — buy extra, it's divine", "Dress code: men in dhoti/kurta, women in saree/salwar — strictly enforced")
            )
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