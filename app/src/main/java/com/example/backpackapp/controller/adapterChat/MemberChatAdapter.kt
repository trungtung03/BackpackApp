package com.example.backpackapp.controller.adapterChat

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater.*
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.with
import com.example.backpackapp.R
import com.example.backpackapp.model.chat.MemberChat
import kotlinx.android.synthetic.main.item_member_chat.view.*
import java.text.SimpleDateFormat

class MemberChatAdapter(val context: Context, private val memberChat: ArrayList<MemberChat>) :
    RecyclerView.Adapter<MemberChatAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SimpleDateFormat")
        fun bindViewHolder(
            context: Context,
            memberChat: ArrayList<MemberChat>,
            position: Int
        ) {
            with(context).load(memberChat[position].avatarUser).error(R.drawable.avatar_default)
                .into(itemView.avatar_user_item_rcv_member_chat)
            memberChat[position].nameUser.also { itemView.name_user_item_rcv_member_chat.text = it }
            memberChat[position].message.also {
                itemView.message_user_item_rcv_member_chat.text = it
            }
            SimpleDateFormat("hh:mma").format(memberChat[position].timeSendMessage)
                .also { itemView.time_user_send_message_item_rcv_member_chat.text = it }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = from(context).inflate(R.layout.item_member_chat, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(context, memberChat, position)
    }

    override fun getItemCount(): Int {
        return memberChat.size
    }
}