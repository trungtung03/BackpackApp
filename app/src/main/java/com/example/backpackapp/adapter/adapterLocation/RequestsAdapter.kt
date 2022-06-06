package com.example.backpackapp.adapter.adapterLocation

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.with
import com.example.backpackapp.R
import com.example.backpackapp.`object`.location.RequestsLocation
import kotlinx.android.synthetic.main.item_rcv_requests_trip_location.view.*
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList

class RequestsAdapter(val context: Context, private val listRequest: ArrayList<RequestsLocation>) :
    RecyclerView.Adapter<RequestsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SimpleDateFormat")
        fun bindViewHolder(
            context: Context,
            requestLocation: ArrayList<RequestsLocation>,
            position: Int
        ) {
            with(context).load(requestLocation[position].avatar).error(R.drawable.avatar_default)
                .into(itemView.item_rcv_image_avatar_requests_location)
            with(context).load(requestLocation[position].photoRequestsTrip)
                .into(itemView.item_rcv_requests_trip_overview_location_background)
            requestLocation[position].countOverview.toString().also {
                itemView.item_rcv_requests_trip_tv_count_overview_location_backpack.text = it
            }
            with(context).load(requestLocation[position].vehicle)
                .into(itemView.item_rcv_requests_trip_item_airplane_mode_overview_location)
            requestLocation[position].dateTime?.let { SimpleDateFormat("dd-mm-yyyy").format(it) }
                .also {
                    itemView.item_rcv_requests_trip_tv_date_time_overview_location_backpack.text =
                        it
                }
            itemView.item_rcv_requests_trip_text_from_country_overview_location_backpack.setCountryForNameCode(
                requestLocation[position].fromCountry
            )
            itemView.item_rcv_requests_trip_tv_to_country_overview_location_backpack.setCountryForNameCode(
                requestLocation[position].toCountry
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = from(context)
                .inflate(R.layout.item_rcv_requests_trip_location, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(context, listRequest, position)
    }

    override fun getItemCount(): Int {
        return listRequest.size
    }
}