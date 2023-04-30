package com.example.backpackapp.view.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.backpackapp.R
import com.example.backpackapp.base.recyclerview.BaseRecyclerViewAdapter
import com.example.backpackapp.base.recyclerview.BaseViewHolder
import com.example.backpackapp.model.home.ListDataModel
import com.example.backpackapp.view.home.adapterChild.PopularDestinationsAdapter
import com.example.backpackapp.view.home.adapterChild.PostsAdapter
import com.example.backpackapp.units.GA
import com.example.backpackapp.view.home.ListDataAdapter.ViewHolder
import kotlinx.android.synthetic.main.item_rcv_list_data.view.*

@Suppress("DUPLICATE_LABEL_IN_WHEN")
class ListDataAdapter(
    private val mContext: Context,
    private val onClickJoin: () -> Unit,
    private val onClickItem: () -> Unit,
) : BaseRecyclerViewAdapter<ListDataModel, ViewHolder>() {

    private var mModelListData: ArrayList<ListDataModel> = ArrayList()

    override fun getItemViewType(position: Int): Int {
        return mModelListData[position].type!!
    }

    class ViewHolder(
        itemView: View,
        private val onClickJoin: () -> Unit,
        private val onClickItem: () -> Unit,
    ) : BaseViewHolder<ListDataModel>(itemView) {
        override fun bindViewHolder(context: Context?, data: ListDataModel?, position: Int) {
            when (data?.type) {
                // Type RecyclerView Post
                GA.TYPE_POST -> {
                    val mPostAdapter = context?.let {
                        PostsAdapter(
                            it, onClickItem =
                            onClickItem, onClickJoin = onClickJoin
                        )
                    }

                    LinearLayoutManager(
                        context,
                        RecyclerView.VERTICAL,
                        false
                    ).also { itemView.rcv_list_data.layoutManager = it }

                    data.mListPosts?.let {
                        mPostAdapter?.setData(it)
                    }
                    itemView.rcv_list_data.adapter = mPostAdapter
                }

                // Type RecyclerView POPULAR DESTINATIONS
                GA.TYPE_POPULAR_DESTINATIONS -> {
                    val mPopularAdapter = context?.let { PopularDestinationsAdapter(it) }

                    GridLayoutManager(context, 1).also { itemView.rcv_list_data.layoutManager = it }
                    LinearLayoutManager(
                        context,
                        RecyclerView.HORIZONTAL,
                        false
                    ).also { itemView.rcv_list_data.layoutManager = it }

                    data.mListPopularDestinationModels?.let {
                        mPopularAdapter?.setData(it)
                    }
                    itemView.rcv_list_data.adapter = mPopularAdapter
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.item_rcv_list_data, parent, false),
            onClickJoin,
            onClickItem
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(mContext, mModelListData[position], position)
    }

    override fun getItemCount(): Int {
        return mModelListData.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setData(mList: ArrayList<ListDataModel>) {
        mModelListData = mList
        notifyDataSetChanged()
    }
}