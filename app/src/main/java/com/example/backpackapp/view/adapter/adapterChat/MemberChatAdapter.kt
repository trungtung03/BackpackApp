package com.example.backpackapp.view.adapter.adapterChat

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater.*
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide.with
import com.example.backpackapp.R
import com.example.backpackapp.model.chat.MemberChat
import com.example.backpackapp.util.Parameters
import com.example.backpackapp.view.adapter.adapterChat.MemberChatAdapter.ViewHolder
import com.example.backpackapp.view.base.BaseRecyclerViewAdapter
import com.example.backpackapp.view.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_member_chat.view.*
import java.text.SimpleDateFormat

class MemberChatAdapter(val context: Context) :
    BaseRecyclerViewAdapter<MemberChat, ViewHolder>() {
    var mDataList: MutableList<MemberChat> = ArrayList()

    @SuppressLint("CheckResult", "SimpleDateFormat")
    class ViewHolder(itemView: View) : BaseViewHolder<MemberChat>(itemView) {

        var onClick: OnClickItem? = null
        fun setClick(clickItem: OnClickItem) {
            onClick = clickItem
        }

        override fun bindViewHolder(context: Context?, data: MemberChat?, position: Int) {
            if (data != null) {
                with(context!!).load(data.avatarUser).error(R.drawable.avatar_default)
                    .into(itemView.avatar_user_item_rcv_member_chat)
                data.nameUser.also { itemView.name_user_item_rcv_member_chat.text = it }
                data.message.also {
                    itemView.message_user_item_rcv_member_chat.text = it
                }
                SimpleDateFormat(Parameters.PATTERN_DATE_FORMAT).format(data.timeSendMessage)
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
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setData(list: ArrayList<MemberChat>) {
        mDataList = list
        notifyDataSetChanged()
    }

    private fun onClick(str: String) {
        Log.d("mcnvnmvc", str)
    }
}