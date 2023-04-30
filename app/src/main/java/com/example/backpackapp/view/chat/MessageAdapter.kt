package com.example.backpackapp.view.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.backpackapp.R
import com.example.backpackapp.databinding.ItemReceiveMsgBinding
import com.example.backpackapp.databinding.ItemSendMsgBinding
import com.example.backpackapp.model.chat.MessageModel
import com.google.firebase.auth.FirebaseAuth


class MessageAdapter(
    val context: Context,
    senderRoom: String,
    receiverRoom: String,
    mMessage: ArrayList<MessageModel>
) : RecyclerView.Adapter<ViewHolder>() {
    private lateinit var mMessage: ArrayList<MessageModel>

    val ITEM_SENT = 1
    val ITEM_RECEIVE = 2
    private val senderRoom: String
    private val receiverRoom: String

    inner class SenderMsgHolder(itemView: View) : ViewHolder(itemView) {
        var mBinding: ItemSendMsgBinding = ItemSendMsgBinding.bind(itemView)
    }

    inner class ReceiveMsgHolder(itemView: View) : ViewHolder(itemView) {
        var mBinding: ItemReceiveMsgBinding = ItemReceiveMsgBinding.bind(itemView)
    }

    init {
        if (mMessage != null) {
            this.mMessage = mMessage
        }
        this.senderRoom = senderRoom
        this.receiverRoom = receiverRoom
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ITEM_SENT -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_send_msg, parent, false)
                SenderMsgHolder(view)
            }
            ITEM_RECEIVE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_receive_msg, parent, false)
                ReceiveMsgHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        val message = mMessage[position]
        return if (FirebaseAuth.getInstance().uid == message.senderId) {
            ITEM_SENT
        } else {
            ITEM_RECEIVE
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = mMessage[position]
        if (holder.javaClass == SenderMsgHolder::class.java) {
            val viewHolder = holder as SenderMsgHolder
            viewHolder.mBinding.tvSendTextMsg.text = message.messageId
        } else {
            val viewHolder = holder as ReceiveMsgHolder
            viewHolder.mBinding.tvReceiveTextMsg.text = message.messageId
        }
    }

    override fun getItemCount(): Int {
        return mMessage.size
    }
}