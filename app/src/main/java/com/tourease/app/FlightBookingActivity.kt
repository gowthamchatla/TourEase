package com.tourease.app

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.util.Calendar

class FlightBookingActivity : AppCompatActivity() {

    private lateinit var etFrom: EditText
    private lateinit var etTo: EditText
    private lateinit var tvDate: TextView
    private lateinit var btnSwap: ImageView
    private lateinit var btnBack: ImageView
    private lateinit var btnSearchFlights: Button
    private lateinit var btnCancelTicket: Button
    private lateinit var chipGroupClass: ChipGroup
    private lateinit var chipGroupFares: ChipGroup
    private lateinit var chipGroupSeat: ChipGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_booking)

        etFrom = findViewById(R.id.etFrom)
        etTo = findViewById(R.id.etTo)
        tvDate = findViewById(R.id.tvDate)
        btnSwap = findViewById(R.id.btnSwap)
        btnBack = findViewById(R.id.btnBack)
        btnSearchFlights = findViewById(R.id.btnSearchFlights)
        btnCancelTicket = findViewById(R.id.btnCancelTicket)
        chipGroupClass = findViewById(R.id.chipGroupClass)
        chipGroupFares = findViewById(R.id.chipGroupFares)
        chipGroupSeat = findViewById(R.id.chipGroupSeat)

        btnBack.setOnClickListener { finish() }

        btnSwap.setOnClickListener {
            val temp = etFrom.text.toString()
            etFrom.setText(etTo.text.toString())
            etTo.setText(temp)
        }

        tvDate.setOnClickListener { showDatePicker() }

        btnSearchFlights.setOnClickListener { searchFlights() }

        btnCancelTicket.setOnClickListener { showCancelDialog() }
    }

    private fun showDatePicker() {
        val cal = Calendar.getInstance()
        DatePickerDialog(
            this,
            R.style.DarkDatePicker,
            { _, year, month, day ->
                tvDate.text = String.format("%02d/%02d/%d", day, month + 1, year)
            },
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun getSelectedChipText(chipGroup: ChipGroup): String {
        val checkedId = chipGroup.checkedChipId
        if (checkedId == -1) return "Not selected"
        return findViewById<Chip>(checkedId).text.toString()
    }

    private fun searchFlights() {
        val from = etFrom.text.toString().trim()
        val to = etTo.text.toString().trim()
        val date = tvDate.text.toString().trim()

        if (from.isEmpty() || to.isEmpty() || date.isEmpty() || date == "Select date") {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val flightClass = getSelectedChipText(chipGroupClass)
        val fare = getSelectedChipText(chipGroupFares)
        val seat = getSelectedChipText(chipGroupSeat)

        Toast.makeText(
            this,
            "Searching: $from → $to\n$date | $flightClass | $seat\nFare: $fare",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showCancelDialog() {
        AlertDialog.Builder(this, R.style.DarkAlertDialog)
            .setTitle("Cancel Ticket")
            .setMessage("Enter your booking reference or PNR to cancel your ticket.")
            .setPositiveButton("Proceed") { _, _ ->
                Toast.makeText(this, "Cancel ticket feature coming soon", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Back", null)
            .show()
    }
}