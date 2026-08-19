package com.tourease.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NearbyPlaceAdapter(
    private val places: List<NearbyPlace>,
    private val onPlaceClick: (NearbyPlace) -> Unit
) : RecyclerView.Adapter<NearbyPlaceAdapter.PlaceViewHolder>() {

    inner class PlaceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivPhoto: ImageView = view.findViewById(R.id.ivPlacePhoto)
        val tvName: TextView = view.findViewById(R.id.tvPlaceName)
        val tvRating: TextView = view.findViewById(R.id.tvPlaceRating)
        val tvReviews: TextView = view.findViewById(R.id.tvPlaceReviews)
        val tvVicinity: TextView = view.findViewById(R.id.tvPlaceVicinity)
        val tvOpenStatus: TextView = view.findViewById(R.id.tvOpenStatus)
        val tvTypes: TextView = view.findViewById(R.id.tvPlaceTypes)
        val tvNumber: TextView = view.findViewById(R.id.tvPlaceNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_nearby_place, parent, false)
        return PlaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = places[position]

        holder.tvNumber.text = "${position + 1}"
        holder.tvName.text = place.name
        holder.tvVicinity.text = place.vicinity

        if (place.rating > 0) {
            holder.tvRating.text = "⭐ ${place.rating}"
            holder.tvReviews.text = "(${place.userRatingsTotal} reviews)"
            holder.tvRating.visibility = View.VISIBLE
            holder.tvReviews.visibility = View.VISIBLE
        } else {
            holder.tvRating.visibility = View.GONE
            holder.tvReviews.visibility = View.GONE
        }

        when (place.openNow) {
            true -> {
                holder.tvOpenStatus.text = "● Open Now"
                holder.tvOpenStatus.setTextColor(android.graphics.Color.parseColor("#4CAF50"))
                holder.tvOpenStatus.visibility = View.VISIBLE
            }
            false -> {
                holder.tvOpenStatus.text = "● Closed"
                holder.tvOpenStatus.setTextColor(android.graphics.Color.parseColor("#F44336"))
                holder.tvOpenStatus.visibility = View.VISIBLE
            }
            null -> holder.tvOpenStatus.visibility = View.GONE
        }

        if (place.types.isNotEmpty()) {
            holder.tvTypes.text = place.types.joinToString(" · ")
            holder.tvTypes.visibility = View.VISIBLE
        } else {
            holder.tvTypes.visibility = View.GONE
        }

        if (place.photoRef.isNotEmpty()) {
            val photoUrl = "https://maps.googleapis.com/maps/api/place/photo" +
                    "?maxwidth=600" +
                    "&photo_reference=${place.photoRef}" +
                    "&key=${place.apiKey}"
            Glide.with(holder.ivPhoto.context)
                .load(photoUrl)
                .placeholder(R.drawable.profile_cover_default_bg)
                .centerCrop()
                .into(holder.ivPhoto)
        } else {
            holder.ivPhoto.setImageResource(R.drawable.profile_cover_default_bg)
        }

        holder.itemView.setOnClickListener { onPlaceClick(place) }
    }

    override fun getItemCount() = places.size
}