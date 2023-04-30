package com.example.backpackapp.view.location

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide.with
import com.example.backpackapp.R
import com.example.backpackapp.base.recyclerview.BaseRecyclerViewAdapter
import com.example.backpackapp.base.recyclerview.BaseViewHolder
import com.example.backpackapp.model.location.LocationTripModel
import com.example.backpackapp.units.Parameters.PATTERN_DATE_FORMAT
import com.example.backpackapp.view.location.InvitesAdapter.ViewHolder
import kotlinx.android.synthetic.main.item_rcv_invites_trip_location.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class InvitesAdapter(private val mContext: Context) :
    BaseRecyclerViewAdapter<LocationTripModel, ViewHolder>() {

    private var mListData: ArrayList<LocationTripModel> = ArrayList()

    class ViewHolder(itemView: View) : BaseViewHolder<LocationTripModel>(itemView) {
        @SuppressLint("SimpleDateFormat")
        override fun bindViewHolder(context: Context?, data: LocationTripModel?, position: Int) {
            if (context != null) {
                with(context).load(data?.avatar).error(R.drawable.avatar_default)
                    .into(itemView.item_rcv_image_avatar_invites_location)
                with(context).load(data?.photoTrip)
                    .into(itemView.item_rcv_invites_trip_overview_location_background)

                with(context).load(data?.vehicle)
                    .into(itemView.item_rcv_invites_trip_item_airplane_mode_overview_location)
            }

            data?.countOverview.toString().also {
                itemView.item_rcv_invites_trip_tv_count_overview_location_backpack.text = it
            }
            Date.parse(data?.dateTime).let { SimpleDateFormat(PATTERN_DATE_FORMAT).format(it) }
                .also {
                    itemView.item_rcv_invites_trip_tv_date_time_overview_location_backpack.text = it
                }
            itemView.item_rcv_invites_trip_text_from_country_overview_location_backpack.setCountryForNameCode(
                data?.fromCountry
            )
            itemView.item_rcv_invites_trip_tv_to_country_overview_location_backpack.setCountryForNameCode(
                data?.toCountry
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = from(mContext)
                .inflate(R.layout.item_rcv_invites_trip_location, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(mContext, mListData[position], position)
    }

    override fun getItemCount(): Int {
        return mListData.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setData(mList: ArrayList<LocationTripModel>) {
        mListData = mList
        notifyDataSetChanged()
    }
}