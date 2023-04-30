package com.example.backpackapp.view.profile

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.backpackapp.R
import com.example.backpackapp.base.recyclerview.BaseRecyclerViewAdapter
import com.example.backpackapp.base.recyclerview.BaseViewHolder
import com.example.backpackapp.model.profile.TravelPhotosModel
import com.example.backpackapp.view.profile.TravelPhotosAdapter.ViewHolder
import kotlinx.android.synthetic.main.item_rcv_travel_photo_profile.view.*

class TravelPhotosAdapter(
    val context: Context,
    val onClick: () -> Unit
) : BaseRecyclerViewAdapter<TravelPhotosModel, ViewHolder>() {

    private var mListPhoto: ArrayList<TravelPhotosModel> = ArrayList()

    class ViewHolder(itemView: View) : BaseViewHolder<TravelPhotosModel>(itemView) {
        override fun bindViewHolder(context: Context?, data: TravelPhotosModel?, position: Int) {
            if (context != null) {
                Glide.with(context).load(data?.image)
                    .into(itemView.image_item_travel_photo_rcv)
            }
            itemView.tv_item_country_rcv.text = data?.country
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_rcv_travel_photo_profile, parent, false)
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setData(mList: ArrayList<TravelPhotosModel>) {
        mListPhoto = mList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(context, mListPhoto[position], position)
        holder.itemView.setOnClickListener { onClick.invoke() }
    }

    override fun getItemCount(): Int {
        return mListPhoto.size
    }


}