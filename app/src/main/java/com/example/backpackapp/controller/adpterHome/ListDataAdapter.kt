package com.example.backpackapp.controller.adpterHome

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.backpackapp.R
import com.example.backpackapp.model.home.ListData
import com.example.backpackapp.controller.adpterHome.popularDestinations.PopularDestinationsAdapter
import com.example.backpackapp.controller.adpterHome.posts.PostsAdapter
import com.example.backpackapp.util.GA
import kotlinx.android.synthetic.main.item_rcv_list_data.view.*

@Suppress("DUPLICATE_LABEL_IN_WHEN")
class ListDataAdapter(
    val context: Context,
    private val listData: ArrayList<ListData>,
    private val onClickJoin: () -> Unit,
    private val onClickItem: () -> Unit
) : RecyclerView.Adapter<ListDataAdapter.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return listData[position].type!!
    }

    class ViewHolder(itemView: View, private val onClickJoin: () -> Unit, private val onClickItem: () -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        fun bindViewHolder(
            context: Context,
            listData: ArrayList<ListData>,
            position: Int
        ) {
            when (listData[position].type) {
                GA.TYPE_POST -> {
                    LinearLayoutManager(
                        context,
                        RecyclerView.VERTICAL,
                        false
                    ).also { itemView.rcv_list_data.layoutManager = it }
                    itemView.rcv_list_data.adapter =
                        listData[position].listPosts?.let { PostsAdapter(context, it, onClickJoin, onClickItem) }
                }
                GA.TYPE_POPULAR_DESTINATIONS -> {
                    GridLayoutManager(context, 1).also { itemView.rcv_list_data.layoutManager = it }
                    LinearLayoutManager(
                        context,
                        RecyclerView.HORIZONTAL,
                        false
                    ).also { itemView.rcv_list_data.layoutManager = it }
                    itemView.rcv_list_data.adapter =
                        listData[position].listPopularDestinations?.let {
                            PopularDestinationsAdapter(
                                context,
                                it
                            )
                        }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_rcv_list_data, parent, false), onClickJoin, onClickItem
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(context, listData, position)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}