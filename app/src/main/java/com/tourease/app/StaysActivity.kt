package com.tourease.app

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class StaysActivity : AppCompatActivity() {

    private var selectedPriceChip = 1
    private var checkInDate: Calendar? = null
    private var checkOutDate: Calendar? = null
    private val dateFormat = SimpleDateFormat("EEE, dd MMM", Locale.getDefault())
    private var rooms = 1
    private var adults = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Fix status bar color to match app background
        window.statusBarColor = android.graphics.Color.parseColor("#0A0A14")

        setContentView(R.layout.activity_stays)

        // Back button
        findViewById<ImageView>(R.id.ivBack).setOnClickListener { finish() }

        // Wishlist button
        findViewById<ImageView>(R.id.ivWishlist).setOnClickListener {
            Toast.makeText(this, "Wishlist coming soon 💜", Toast.LENGTH_SHORT).show()
        }

        // Stay type tabs
        val tabLong = findViewById<TextView>(R.id.tabLongStay)
        val tabHourly = findViewById<TextView>(R.id.tabHourlyStay)

        tabLong.setOnClickListener {
            tabLong.setBackgroundColor(android.graphics.Color.parseColor("#6C63FF"))
            tabLong.setTextColor(android.graphics.Color.WHITE)
            tabHourly.setBackgroundColor(android.graphics.Color.TRANSPARENT)
            tabHourly.setTextColor(android.graphics.Color.parseColor("#9B9BB4"))
        }

        tabHourly.setOnClickListener {
            tabHourly.setBackgroundColor(android.graphics.Color.parseColor("#6C63FF"))
            tabHourly.setTextColor(android.graphics.Color.WHITE)
            tabLong.setBackgroundColor(android.graphics.Color.TRANSPARENT)
            tabLong.setTextColor(android.graphics.Color.parseColor("#9B9BB4"))
        }

        // Near Me button
        findViewById<TextView>(R.id.btnNearMe).setOnClickListener {
            Toast.makeText(this, "📡 Detecting your location...", Toast.LENGTH_SHORT).show()
        }

        // Check-In date picker
        findViewById<LinearLayout>(R.id.layoutCheckIn).setOnClickListener {
            showDatePicker { calendar ->
                checkInDate = calendar
                findViewById<TextView>(R.id.tvCheckIn).text = "📅 ${dateFormat.format(calendar.time)}"
            }
        }

        // Check-Out date picker
        findViewById<LinearLayout>(R.id.layoutCheckOut).setOnClickListener {
            showDatePicker { calendar ->
                checkOutDate = calendar
                findViewById<TextView>(R.id.tvCheckOut).text = "📅 ${dateFormat.format(calendar.time)}"
            }
        }

        // Guests picker - tap to cycle
        findViewById<LinearLayout>(R.id.layoutGuests).setOnClickListener {
            adults = if (adults < 4) adults + 1 else 1
            if (adults == 1) rooms = 1
            findViewById<TextView>(R.id.tvGuests).text = "$rooms Room • $adults Adults"
        }

        // Price filter chips
        setupPriceChips()

        // Search button
        findViewById<TextView>(R.id.btnSearchHotels).setOnClickListener {
            val city = findViewById<android.widget.EditText>(R.id.etCity).text.toString().trim()
            when {
                city.isEmpty() -> {
                    Toast.makeText(this, "Please enter a city or area 📍", Toast.LENGTH_SHORT).show()
                }
                checkInDate == null -> {
                    Toast.makeText(this, "Please select a check-in date 📅", Toast.LENGTH_SHORT).show()
                }
                checkOutDate == null -> {
                    Toast.makeText(this, "Please select a check-out date 📅", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, "🔍 Searching hotels in $city...", Toast.LENGTH_SHORT).show()
                    // TODO: Launch HotelResultsActivity with search params
                }
            }
        }

        // View All offers
        findViewById<TextView>(R.id.tvViewAll).setOnClickListener {
            Toast.makeText(this, "All offers coming soon 🔥", Toast.LENGTH_SHORT).show()
        }

        // Offer type filter
        val filterHotels = findViewById<TextView>(R.id.filterHotels)
        val filterBank = findViewById<TextView>(R.id.filterBankOffers)

        filterHotels.setOnClickListener {
            filterHotels.setBackgroundColor(android.graphics.Color.parseColor("#6C63FF"))
            filterHotels.setTextColor(android.graphics.Color.WHITE)
            filterBank.setBackgroundColor(android.graphics.Color.parseColor("#12122A"))
            filterBank.setTextColor(android.graphics.Color.parseColor("#9B9BB4"))
        }

        filterBank.setOnClickListener {
            filterBank.setBackgroundColor(android.graphics.Color.parseColor("#6C63FF"))
            filterBank.setTextColor(android.graphics.Color.WHITE)
            filterHotels.setBackgroundColor(android.graphics.Color.parseColor("#12122A"))
            filterHotels.setTextColor(android.graphics.Color.parseColor("#9B9BB4"))
        }

        // Popular Cities — tap to auto-fill city field
        val cityToast = { city: String ->
            android.view.View.OnClickListener {
                findViewById<android.widget.EditText>(R.id.etCity).setText(city)
                Toast.makeText(this, "📍 $city selected!", Toast.LENGTH_SHORT).show()
            }
        }
        findViewById<LinearLayout>(R.id.cityMumbai).setOnClickListener(cityToast("Mumbai"))
        findViewById<LinearLayout>(R.id.cityDelhi).setOnClickListener(cityToast("Delhi"))
        findViewById<LinearLayout>(R.id.cityGoa).setOnClickListener(cityToast("Goa"))
        findViewById<LinearLayout>(R.id.cityBangalore).setOnClickListener(cityToast("Bangalore"))
        findViewById<LinearLayout>(R.id.cityJaipur).setOnClickListener(cityToast("Jaipur"))
        findViewById<LinearLayout>(R.id.cityManali).setOnClickListener(cityToast("Manali"))
    }

    private fun showDatePicker(onDateSelected: (Calendar) -> Unit) {
        val today = Calendar.getInstance()
        val dialog = DatePickerDialog(
            this,
            { _, year, month, day ->
                val cal = Calendar.getInstance()
                cal.set(year, month, day)
                onDateSelected(cal)
            },
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        )
        // Don't allow past dates
        dialog.datePicker.minDate = today.timeInMillis
        dialog.show()
    }

    private fun setupPriceChips() {
        val chips = listOf(
            R.id.chip1 to 1,
            R.id.chip2 to 2,
            R.id.chip3 to 3,
            R.id.chip4 to 4
        )

        chips.forEach { (id, index) ->
            findViewById<TextView>(id).setOnClickListener {
                selectedPriceChip = index
                chips.forEach { (chipId, chipIndex) ->
                    val chip = findViewById<TextView>(chipId)
                    if (chipIndex == index) {
                        chip.setBackgroundColor(android.graphics.Color.parseColor("#1A1A3A"))
                        chip.setTextColor(android.graphics.Color.parseColor("#6C63FF"))
                    } else {
                        chip.setBackgroundColor(android.graphics.Color.parseColor("#12122A"))
                        chip.setTextColor(android.graphics.Color.parseColor("#9B9BB4"))
                    }
                }
            }
        }
    }
}