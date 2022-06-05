package com.example.backpackapp.adapter.adapterLocation

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.backpackapp.R
import com.example.backpackapp.`object`.location.RequestsLocation
import kotlinx.android.synthetic.main.item_rcv_requests_trip_location.view.*
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList

class RequestsAdapter(val context: Context, private val listRequest: ArrayList<RequestsLocation>) :
    RecyclerView.Adapter<RequestsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatar = itemView.item_rcv_image_avatar_requests_location!!
        val photoRequestsTrip = itemView.item_rcv_requests_trip_overview_location_background!!
        val countOverview = itemView.item_rcv_requests_trip_tv_count_overview_location_backpack!!
        val vehicle = itemView.item_rcv_requests_trip_item_airplane_mode_overview_location!!
        val dateTime = itemView.item_rcv_requests_trip_tv_date_time_overview_location_backpack!!
        val fromCountry = itemView.item_rcv_requests_trip_text_from_country_overview_location_backpack!!
        val toCountry = itemView.item_rcv_requests_trip_tv_to_country_overview_location_backpack!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_rcv_requests_trip_location, parent, false)
        )
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val requestsLocation = listRequest[position]
        Glide.with(context).load(requestsLocation.avatar).error(R.drawable.avatar_default).into(holder.avatar)
        Glide.with(context).load(requestsLocation.photoRequestsTrip).into(holder.photoRequestsTrip)
        requestsLocation.countOverview.toString().also { holder.countOverview.text = it }
        Glide.with(context).load(requestsLocation.vehicle).into(holder.vehicle)
        requestsLocation.dateTime?.let { SimpleDateFormat("dd-mm-yyyy").format(it) }
            .also { holder.dateTime.text = it }
        holder.fromCountry.setCountryForNameCode(requestsLocation.fromCountry)
        holder.toCountry.setCountryForNameCode(requestsLocation.toCountry)
    }

    override fun getItemCount(): Int {
        return listRequest.size
    }
}