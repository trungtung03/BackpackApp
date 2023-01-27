package com.example.backpackapp.view.adapter.adapterProfile

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.with
import com.example.backpackapp.R
import com.example.backpackapp.model.profile.PreviousTripProfile
import kotlinx.android.synthetic.main.item_rcv_previous_trip_profile.view.*
import java.text.SimpleDateFormat

class PreviousTripAdapter(
    val context: Context,
    private val listPrevious: ArrayList<PreviousTripProfile>
) : RecyclerView.Adapter<PreviousTripAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SimpleDateFormat")
        fun bindViewHolder(
            context: Context,
            previousTripProfile: ArrayList<PreviousTripProfile>,
            position: Int
        ) {
            with(context).load(previousTripProfile[position].photoPreviousTrip)
                .into(itemView.item_rcv_previous_trip_overview_background)
            previousTripProfile[position].countOverview.toString()
                .also { itemView.item_rcv_previous_trip_tv_count_overview_backpack.text = it }
            with(context).load(previousTripProfile[position].vehicle)
                .into(itemView.item_rcv_previous_trip_item_airplane_mode_overview)
            previousTripProfile[position].dateTime?.let { SimpleDateFormat("dd-mm-yyyy").format(it) }
                .also { itemView.item_rcv_previous_trip_tv_date_time_overview_backpack.text = it }
            itemView.item_rcv_previous_trip_text_from_country_overview_backpack.setCountryForNameCode(
                previousTripProfile[position].fromCountry
            )
            itemView.item_rcv_previous_trip_tv_to_country_overview_backpack.setCountryForNameCode(
                previousTripProfile[position].toCountry
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = from(context)
                .inflate(R.layout.item_rcv_previous_trip_profile, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(context, listPrevious, position)
    }

    override fun getItemCount(): Int {
        return listPrevious.size
    }
}