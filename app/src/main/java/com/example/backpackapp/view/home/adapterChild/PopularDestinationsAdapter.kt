package com.example.backpackapp.view.home.adapterChild

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.backpackapp.R
import com.example.backpackapp.base.recyclerview.BaseRecyclerViewAdapter
import com.example.backpackapp.base.recyclerview.BaseViewHolder
import com.example.backpackapp.model.home.modelChild.PopularDestinationsModel
import com.example.backpackapp.view.home.adapterChild.PopularDestinationsAdapter.ViewHolder
import kotlinx.android.synthetic.main.item_rcv_popular_destinations_home.view.*

class PopularDestinationsAdapter(
    private val mContext: Context
) : BaseRecyclerViewAdapter<PopularDestinationsModel, ViewHolder>() {

    private var mListPopularDestination: ArrayList<PopularDestinationsModel> = ArrayList()

    class ViewHolder(itemView: View) : BaseViewHolder<PopularDestinationsModel>(itemView) {
        override fun bindViewHolder(
            context: Context?,
            data: PopularDestinationsModel?,
            position: Int
        ) {
            context?.let {
                Glide.with(it).load(data?.imagePost).error(R.drawable.not_found)
                    .into(itemView.image_item_rcv_popular_destinations)
            }
            data?.nameCountry.also { itemView.tv_item_rcv_popular_destinations.text = it }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.item_rcv_popular_destinations_home, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(mContext, mListPopularDestination[position], position)
    }

    override fun getItemCount(): Int {
        return mListPopularDestination.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setData(mList: ArrayList<PopularDestinationsModel>) {
        mListPopularDestination = mList
        notifyDataSetChanged()
    }
}