package com.example.backpackapp.adapter.adapterProfile

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.backpackapp.R
import com.example.backpackapp.`object`.profile.PreviousTripProfile
import kotlinx.android.synthetic.main.item_rcv_previous_trip.view.*
import java.text.SimpleDateFormat

class PreviousTripAdapter(
    val context: Context,
    private val listPrevious: ArrayList<PreviousTripProfile>
) : RecyclerView.Adapter<PreviousTripAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photoPreviousTrip = itemView.item_rcv_previous_trip_overview_background!!
        val amount = itemView.item_rcv_previous_trip_tv_count_overview_backpack!!
        val vehicle = itemView.item_rcv_previous_trip_item_airplane_mode_overview!!
        val dateTime = itemView.item_rcv_previous_trip_tv_date_time_overview_backpack!!
        val fromCountry = itemView.item_rcv_previous_trip_text_from_country_overview_backpack!!
        val toCountry = itemView.item_rcv_previous_trip_tv_to_country_overview_backpack!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_rcv_previous_trip, parent, false)
        )
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val previousTripProfile = listPrevious[position]
        Glide.with(context).load(previousTripProfile.photoPreviousTrip)
            .into(holder.photoPreviousTrip)
        previousTripProfile.countOverview.toString().also { holder.amount.text = it }
        Glide.with(context).load(previousTripProfile.vehicle).into(holder.vehicle)
        previousTripProfile.dateTime?.let { SimpleDateFormat("dd-mm-yyyy").format(it) }
            .also { holder.dateTime.text = it }
        holder.fromCountry.setCountryForNameCode(previousTripProfile.fromCountry)
        holder.toCountry.setCountryForNameCode(previousTripProfile.toCountry)
    }

    override fun getItemCount(): Int {
        return listPrevious.size
    }
}