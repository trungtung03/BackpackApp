package com.example.backpackapp.view.adapter.adpterHome.popularDestinations

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.backpackapp.R
import com.example.backpackapp.model.home.popularDestinations.PopularDestinations
import kotlinx.android.synthetic.main.item_rcv_popular_destinations_home.view.*

class PopularDestinationsAdapter(
    val context: Context,
    private val listPopularDestinations: ArrayList<PopularDestinations>
) : RecyclerView.Adapter<PopularDestinationsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindViewHolder(
            context: Context,
            listPopularDestinations: ArrayList<PopularDestinations>,
            position: Int
        ) {
            listPopularDestinations[position].nameCountry.also { itemView.tv_item_rcv_popular_destinations_home.text = it }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_rcv_popular_destinations_home, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(context, listPopularDestinations, position)
    }

    override fun getItemCount(): Int {
        return listPopularDestinations.size
    }
}