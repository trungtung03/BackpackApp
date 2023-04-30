package com.example.backpackapp.view.chat

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater.*
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide.with
import com.example.backpackapp.R
import com.example.backpackapp.units.Parameters.PATTERN_TIME_FORMAT
import com.example.backpackapp.view.chat.MemberChatAdapter.ViewHolder
import com.example.backpackapp.base.recyclerview.BaseRecyclerViewAdapter
import com.example.backpackapp.base.recyclerview.BaseViewHolder
import com.example.backpackapp.model.chat.MemberChatModel
import com.example.backpackapp.units.GA.POSITION_USER
import kotlinx.android.synthetic.main.item_member_chat.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MemberChatAdapter(
    val context: Context,
    val onClickUser: () -> Unit
) : BaseRecyclerViewAdapter<MemberChatModel, ViewHolder>() {
    var mDataList: MutableList<MemberChatModel> = ArrayList()

    @SuppressLint("CheckResult", "SimpleDateFormat")
    class ViewHolder(itemView: View) : BaseViewHolder<MemberChatModel>(itemView) {

        var onClick: OnClickItem? = null
        fun setClick(clickItem: OnClickItem) {
            onClick = clickItem
        }

        override fun bindViewHolder(context: Context?, data: MemberChatModel?, position: Int) {
            if (data != null) {
                with(context!!).load(data.avatarUser).error(R.drawable.avatar_default)
                    .into(itemView.avatar_user_item_rcv_member_chat)
                data.nameUser.also { itemView.name_user_item_rcv_member_chat.text = it }
                data.message.also {
                    itemView.message_user_item_rcv_member_chat.text = it
                }

                SimpleDateFormat(PATTERN_TIME_FORMAT).format(Date.parse(data.timeSendMessage))
                    .toString()
                    .also { itemView.time_user_send_message_item_rcv_member_chat.text = it }

                itemView.setOnClickListener { v ->
                    onClick?.isClickItem(v, adapterPosition, false)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = from(context).inflate(R.layout.item_member_chat, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(context, mDataList[position], position)
        holder.setClick(object : OnClickItem {
            override fun isClickItem(view: View, position: Int, isCheck: Boolean) {
                if (!isCheck) {
                    onClick(mDataList[position].nameUser.toString())
                }
            }
        })

        holder.itemView.setOnClickListener {
            POSITION_USER = position
            onClickUser.invoke()
        }
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setData(mList: ArrayList<MemberChatModel>) {
        mDataList = mList
        notifyDataSetChanged()
    }

    private fun onClick(str: String) {
        // Write Code
    }
}