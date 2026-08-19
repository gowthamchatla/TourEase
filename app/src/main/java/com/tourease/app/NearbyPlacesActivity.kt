package com.tourease.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class NearbyPlacesActivity : AppCompatActivity() {

    private val API_KEY = "AIzaSyAXhqLRPga5DYglR1vTNTOG1oRPU507-oA"
    private val client = OkHttpClient()
    private val handler = Handler(Looper.getMainLooper())

    private lateinit var etCity: EditText
    private lateinit var btnSearch: android.widget.Button
    private lateinit var rvPlaces: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvStatus: TextView
    private lateinit var tvCityName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nearby_places)

        val statusBarSpacer: View = findViewById(R.id.statusBarSpacer)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            statusBarSpacer.layoutParams.height = systemBars.top
            statusBarSpacer.requestLayout()
            insets
        }

        val ivBack: ImageView = findViewById(R.id.ivBack)
        etCity = findViewById(R.id.etCity)
        btnSearch = findViewById(R.id.btnSearch)
        rvPlaces = findViewById(R.id.rvPlaces)
        progressBar = findViewById(R.id.progressBar)
        tvStatus = findViewById(R.id.tvStatus)
        tvCityName = findViewById(R.id.tvCityName)

        rvPlaces.layoutManager = LinearLayoutManager(this)

        ivBack.setOnClickListener { finish() }

        btnSearch.setOnClickListener {
            val city = etCity.text.toString().trim()
            if (city.isEmpty()) {
                Toast.makeText(this, "Enter a city name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            searchPlaces(city)
        }

        etCity.setText("Goa")
        searchPlaces("Goa")
    }

    private fun searchPlaces(city: String) {
        progressBar.visibility = View.VISIBLE
        tvStatus.visibility = View.GONE
        rvPlaces.visibility = View.GONE
        tvCityName.text = "Tourist Places in $city"

        val geocodeUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=${Uri.encode(city)}&key=$API_KEY"
        val geocodeRequest = Request.Builder().url(geocodeUrl).build()

        client.newCall(geocodeRequest).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                handler.post {
                    progressBar.visibility = View.GONE
                    tvStatus.text = "❌ Network error. Check your internet connection."
                    tvStatus.visibility = View.VISIBLE
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string() ?: ""
                try {
                    val json = JSONObject(body)
                    val results = json.getJSONArray("results")
                    if (results.length() == 0) {
                        handler.post {
                            progressBar.visibility = View.GONE
                            tvStatus.text = "❌ City not found. Try another name."
                            tvStatus.visibility = View.VISIBLE
                        }
                        return
                    }

                    val location = results.getJSONObject(0)
                        .getJSONObject("geometry")
                        .getJSONObject("location")
                    val lat = location.getDouble("lat")
                    val lng = location.getDouble("lng")

                    fetchTouristPlaces(lat, lng, city)

                } catch (e: Exception) {
                    handler.post {
                        progressBar.visibility = View.GONE
                        tvStatus.text = "❌ Error parsing response. Try again."
                        tvStatus.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun fetchTouristPlaces(lat: Double, lng: Double, city: String) {
        val query = Uri.encode("tourist attractions in $city India")
        val placesUrl = "https://maps.googleapis.com/maps/api/place/textsearch/json" +
                "?query=$query" +
                "&location=$lat,$lng" +
                "&radius=20000" +
                "&key=$API_KEY"

        val request = Request.Builder().url(placesUrl).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                handler.post {
                    progressBar.visibility = View.GONE
                    tvStatus.text = "❌ Failed to fetch places. Try again."
                    tvStatus.visibility = View.VISIBLE
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string() ?: ""
                try {
                    val json = JSONObject(body)
                    val status = json.optString("status")

                    if (status != "OK" && status != "ZERO_RESULTS") {
                        handler.post {
                            progressBar.visibility = View.GONE
                            tvStatus.text = "❌ API Error: $status"
                            tvStatus.visibility = View.VISIBLE
                        }
                        return
                    }

                    val results = json.getJSONArray("results")
                    val places = mutableListOf<NearbyPlace>()

                    for (i in 0 until minOf(results.length(), 20)) {
                        val place = results.getJSONObject(i)
                        val name = place.optString("name", "Unknown Place")
                        val rating = place.optDouble("rating", 0.0)
                        val userRatingsTotal = place.optInt("user_ratings_total", 0)
                        val vicinity = place.optString("formatted_address", "")
                        val placeId = place.optString("place_id", "")
                        val openNow = place.optJSONObject("opening_hours")?.optBoolean("open_now")

                        var photoRef = ""
                        val photos = place.optJSONArray("photos")
                        if (photos != null && photos.length() > 0) {
                            photoRef = photos.getJSONObject(0).optString("photo_reference", "")
                        }

                        val typesArray = place.optJSONArray("types")
                        val types = mutableListOf<String>()
                        if (typesArray != null) {
                            for (j in 0 until minOf(typesArray.length(), 2)) {
                                val t = typesArray.getString(j)
                                    .replace("_", " ")
                                    .replaceFirstChar { it.uppercase() }
                                if (t != "Tourist attraction" && t != "Point of interest" && t != "Establishment") {
                                    types.add(t)
                                }
                            }
                        }

                        places.add(
                            NearbyPlace(
                                name = name,
                                rating = rating,
                                userRatingsTotal = userRatingsTotal,
                                vicinity = vicinity,
                                placeId = placeId,
                                photoRef = photoRef,
                                openNow = openNow,
                                types = types,
                                apiKey = API_KEY
                            )
                        )
                    }

                    handler.post {
                        progressBar.visibility = View.GONE
                        if (places.isEmpty()) {
                            tvStatus.text = "No tourist places found for $city"
                            tvStatus.visibility = View.VISIBLE
                        } else {
                            rvPlaces.adapter = NearbyPlaceAdapter(places) { place ->
                                val uri = Uri.parse("https://www.google.com/maps/search/?api=1&query=${Uri.encode(place.name)}&query_place_id=${place.placeId}")
                                startActivity(Intent(Intent.ACTION_VIEW, uri))
                            }
                            rvPlaces.visibility = View.VISIBLE
                        }
                    }

                } catch (e: Exception) {
                    handler.post {
                        progressBar.visibility = View.GONE
                        tvStatus.text = "❌ Error: ${e.message}"
                        tvStatus.visibility = View.VISIBLE
                    }
                }
            }
        })
    }
} // ← END OF CLASS

// NearbyPlace data class is OUTSIDE the class
data class NearbyPlace(
    val name: String,
    val rating: Double,
    val userRatingsTotal: Int,
    val vicinity: String,
    val placeId: String,
    val photoRef: String,
    val openNow: Boolean?,
    val types: List<String>,
    val apiKey: String
)