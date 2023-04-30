package com.example.backpackapp.view.profile

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide.with
import com.example.backpackapp.R
import com.example.backpackapp.base.recyclerview.BaseRecyclerViewAdapter
import com.example.backpackapp.base.recyclerview.BaseViewHolder
import com.example.backpackapp.model.profile.PreviousTripModel
import com.example.backpackapp.units.Parameters.PATTERN_DATE_FORMAT
import com.example.backpackapp.view.profile.PreviousTripAdapter.ViewHolder
import kotlinx.android.synthetic.main.item_rcv_previous_trip_profile.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PreviousTripAdapter(
    val context: Context,
    val onClick: () -> Unit
) : BaseRecyclerViewAdapter<PreviousTripModel, ViewHolder>() {

    private var mListPrevious: MutableList<PreviousTripModel> = ArrayList()

    class ViewHolder(itemView: View) : BaseViewHolder<PreviousTripModel>(itemView) {
        @SuppressLint("SimpleDateFormat")
        override fun bindViewHolder(context: Context?, data: PreviousTripModel?, position: Int) {
            if (data != null) {
                context?.let { with(it).load(data.photoPreviousTrip) }
                    ?.into(itemView.item_rcv_previous_trip)

                itemView.item_rcv_previous_trip_tv_count.text =
                    data.countOverview.toString()

                context?.let {
                    with(it).load(data.vehicle)
                        .into(itemView.item_rcv_previous_trip_item_airplane_mode_overview)
                }

                SimpleDateFormat(PATTERN_DATE_FORMAT).format(Date.parse(data.dateTime))
                    .toString().also { itemView.item_rcv_previous_trip_tv_date_time.text = it }

                itemView.item_rcv_previous_trip_text_from_country.setCountryForNameCode(
                    data.fromCountry
                )
                itemView.item_rcv_previous_trip_tv_to_country.setCountryForNameCode(
                    data.toCountry
                )
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setData(mList: ArrayList<PreviousTripModel>) {
        mListPrevious = mList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_rcv_previous_trip_profile, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(context, mListPrevious[position], position)
        val mm = PreviousTripModel()
        holder.itemView.setOnClickListener { onClick.invoke() }
    }

    override fun getItemCount(): Int {
        return mListPrevious.size
    }
}