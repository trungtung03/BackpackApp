package com.example.backpackapp.adapter.adapterLocation

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.backpackapp.R
import com.example.backpackapp.`object`.location.InvitesLocation
import kotlinx.android.synthetic.main.item_rcv_invites_trip_location.view.*
import java.text.SimpleDateFormat

class InvitesAdapter(val context: Context, private val listRequest: ArrayList<InvitesLocation>) :
    RecyclerView.Adapter<InvitesAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatar = itemView.item_rcv_image_avatar_invites_location!!
        val photoInvitesTrip = itemView.item_rcv_invites_trip_overview_location_background!!
        val countOverview = itemView.item_rcv_invites_trip_tv_count_overview_location_backpack!!
        val vehicle = itemView.item_rcv_invites_trip_item_airplane_mode_overview_location!!
        val dateTime = itemView.item_rcv_invites_trip_tv_date_time_overview_location_backpack!!
        val fromCountry = itemView.item_rcv_invites_trip_text_from_country_overview_location_backpack!!
        val toCountry = itemView.item_rcv_invites_trip_tv_to_country_overview_location_backpack!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_rcv_invites_trip_location, parent, false)
        )
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val invitesLocation = listRequest[position]
        Glide.with(context).load(invitesLocation.avatar).error(R.drawable.avatar_default).into(holder.avatar)
        Glide.with(context).load(invitesLocation.photoInvitesTrip).into(holder.photoInvitesTrip)
        invitesLocation.countOverview.toString().also { holder.countOverview.text = it }
        Glide.with(context).load(invitesLocation.vehicle).into(holder.vehicle)
        invitesLocation.dateTime?.let { SimpleDateFormat("dd-mm-yyyy").format(it) }
            .also { holder.dateTime.text = it }
        holder.fromCountry.setCountryForNameCode(invitesLocation.fromCountry)
        holder.toCountry.setCountryForNameCode(invitesLocation.toCountry)
    }

    override fun getItemCount(): Int {
        return listRequest.size
    }
}