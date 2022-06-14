package com.example.backpackapp.adapter.adpterHome.adapterService

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.backpackapp.R
import com.example.backpackapp.model.home.service.Service
import kotlinx.android.synthetic.main.item_service_rcv_post_home.view.*

class ServiceAdapter(
    val context: Context,
    private val service: ArrayList<Service>
) : RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindViewHolder(
            context: Context,
            service: java.util.ArrayList<Service>,
            position: Int
        ) {
            service[position].service.also { itemView.item_tv_service.text = it }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_service_rcv_post_home, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(context, service, position)
    }

    override fun getItemCount(): Int {
        return service.size
    }
}