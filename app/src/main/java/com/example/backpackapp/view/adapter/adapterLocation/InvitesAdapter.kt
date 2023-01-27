package com.example.backpackapp.view.adapter.adapterLocation

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.with
import com.example.backpackapp.R
import com.example.backpackapp.model.location.InvitesLocation
import kotlinx.android.synthetic.main.item_rcv_invites_trip_location.view.*
import java.text.SimpleDateFormat

class InvitesAdapter(val context: Context, private val listRequest: ArrayList<InvitesLocation>) :
    RecyclerView.Adapter<InvitesAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SimpleDateFormat")
        fun bindViewHolder(
            context: Context,
            invitesLocation: ArrayList<InvitesLocation>,
            position: Int
        ) {
            with(context).load(invitesLocation[position].avatar).error(R.drawable.avatar_default)
                .into(itemView.item_rcv_image_avatar_invites_location)
            with(context).load(invitesLocation[position].photoInvitesTrip)
                .into(itemView.item_rcv_invites_trip_overview_location_background)
            invitesLocation[position].countOverview.toString().also {
                itemView.item_rcv_invites_trip_tv_count_overview_location_backpack.text = it
            }
            with(context).load(invitesLocation[position].vehicle)
                .into(itemView.item_rcv_invites_trip_item_airplane_mode_overview_location)
            invitesLocation[position].dateTime?.let { SimpleDateFormat("dd-mm-yyyy").format(it) }
                .also {
                    itemView.item_rcv_invites_trip_tv_date_time_overview_location_backpack.text = it
                }
            itemView.item_rcv_invites_trip_text_from_country_overview_location_backpack.setCountryForNameCode(
                invitesLocation[position].fromCountry
            )
            itemView.item_rcv_invites_trip_tv_to_country_overview_location_backpack.setCountryForNameCode(
                invitesLocation[position].toCountry
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = from(context)
                .inflate(R.layout.item_rcv_invites_trip_location, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(context, listRequest, position)
    }

    override fun getItemCount(): Int {
        return listRequest.size
    }
}