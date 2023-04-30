package com.example.backpackapp.view.location.post_item

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.backpackapp.R
import com.example.backpackapp.base.recyclerview.BaseRecyclerViewAdapter
import com.example.backpackapp.base.recyclerview.BaseViewHolder
import com.example.backpackapp.model.location.post_item.PostItemModel
import com.example.backpackapp.units.GA.POSITION_POST
import com.example.backpackapp.view.location.post_item.PostItemAdapter.ViewHolder
import kotlinx.android.synthetic.main.item_rcv_add_post.view.*

class PostItemAdapter(
    private val mContext: Context,
    private val onClickItem: () -> Unit
) :
    BaseRecyclerViewAdapter<PostItemModel, ViewHolder>() {

    private var mListData: ArrayList<PostItemModel> = ArrayList()

    class ViewHolder(itemView: View) : BaseViewHolder<PostItemModel>(itemView) {
        override fun bindViewHolder(context: Context?, data: PostItemModel?, position: Int) {
            data?.image?.let { itemView.item_img_rcv_post.setImageResource(it) }
            itemView.item_tv_rcv_post.text = data?.nameImage
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setData(mList: ArrayList<PostItemModel>) {
        mListData = mList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.item_rcv_add_post, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mListData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(mContext, mListData[position], position)
        holder.itemView.setOnClickListener {
            POSITION_POST = position
            onClickItem.invoke()
        }
    }
}