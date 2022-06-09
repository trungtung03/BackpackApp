package com.example.backpackapp.adapter.adapterProfile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.backpackapp.R
import com.example.backpackapp.`object`.profile.TravelPhotosProfile
import kotlinx.android.synthetic.main.item_rcv_travel_photo_profile.view.*

class TravelPhotosAdapter(
    val context: Context,
    private val listPhoto: ArrayList<TravelPhotosProfile>
) :
    RecyclerView.Adapter<TravelPhotosAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindViewHolder(
            context: Context,
            travelPhotosProfile: ArrayList<TravelPhotosProfile>,
            position: Int
        ) {
            Glide.with(context).load(travelPhotosProfile[position].image)
                .into(itemView.image_item_travel_photo_rcv)
            itemView.tv_item_country_rcv.text = travelPhotosProfile[position].country
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_rcv_travel_photo_profile, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(context, listPhoto, position)
    }

    override fun getItemCount(): Int {
        return listPhoto.size
    }
}