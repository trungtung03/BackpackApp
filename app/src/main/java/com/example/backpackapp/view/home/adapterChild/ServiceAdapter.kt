package com.example.backpackapp.view.home.adapterChild

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.backpackapp.R
import com.example.backpackapp.model.home.modelChild.ServiceModel
import kotlinx.android.synthetic.main.item_service_rcv_post_home.view.*

class ServiceAdapter(
    val context: Context,
    private val serviceModel: ArrayList<ServiceModel>
) : RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindViewHolder(
            context: Context,
            serviceModel: java.util.ArrayList<ServiceModel>,
            position: Int
        ) {
            serviceModel[position].service.also { itemView.item_tv_service.text = it }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_service_rcv_post_home, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(context, serviceModel, position)
    }

    override fun getItemCount(): Int {
        return serviceModel.size
    }
}