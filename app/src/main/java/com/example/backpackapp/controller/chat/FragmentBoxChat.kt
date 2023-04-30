package com.example.backpackapp.controller.chat

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.backpackapp.base.BaseFragment
import com.example.backpackapp.databinding.FragmentBoxChatOverviewBinding
import com.example.backpackapp.model.chat.MessageModel
import com.example.backpackapp.units.GA
import com.example.backpackapp.units.Parameters.USERS
import com.example.backpackapp.view.chat.MessageAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FragmentBoxChat : BaseFragment<FragmentBoxChatOverviewBinding>() {

    companion object {
        fun newInstance() = FragmentBoxChat()
    }

    private lateinit var mBinding: FragmentBoxChatOverviewBinding
    private var sendRoom: String? = null
    private var receiveRoom: String? = null
    private var messageAdapter: MessageAdapter? = null
    private var database: FirebaseDatabase? = null
    private var sendUid: String? = null
    private var receiveUid: String? = null
    private var mMessage: ArrayList<MessageModel>? = null
    private var _receiveUid: String? = null

    private fun chat() {
        database = FirebaseDatabase.getInstance() ?: return
        mMessage = ArrayList<MessageModel>() ?: return
        receiveUid = _receiveUid
        sendUid = FirebaseAuth.getInstance().uid.toString()

        sendRoom = sendUid + receiveUid
        receiveRoom = receiveUid + sendUid
        messageAdapter = activity?.let { MessageAdapter(it, sendRoom!!, receiveRoom!!, mMessage!!) }
        mBinding.rcvMessageContent.layoutManager = LinearLayoutManager(activity)
        mBinding.rcvMessageContent.adapter = messageAdapter

        database?.reference?.child("chats")
            ?.child(sendRoom!!)
            ?.child("messages")
            ?.addValueEventListener(object : ValueEventListener {
                @SuppressLint("NotifyDataSetChanged")
                override fun onDataChange(snapshot: DataSnapshot) {
                    mMessage!!.clear()
                    for (potSnapshot in snapshot.children) {
                        val message: MessageModel? = potSnapshot.getValue(MessageModel::class.java)
                        message?.messageId = potSnapshot.key
                        mMessage!!.add(message!!)
                    }
                    messageAdapter?.notifyDataSetChanged()
                    Log.d("vcxvx", mMessage!!.size.toString())
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

        mBinding.iconSendMessage.setOnClickListener {
            val messageTxt: String = mBinding.edtBoxChat.text.toString().trim()
            val message = MessageModel(messageTxt, sendUid)

            mBinding.edtBoxChat.setText("")
            val randomKey = database?.reference?.push()?.key
            val lastMsgObj = HashMap<String, Any>()
            lastMsgObj["lastMsg"] = message.message!!

            database?.reference?.child("chats")?.child(sendRoom!!)
                ?.updateChildren(lastMsgObj)
            database?.reference?.child("chats")?.child(receiveRoom!!)
                ?.updateChildren(lastMsgObj)
            database?.reference?.child("chats")?.child(sendRoom!!)
                ?.child("messages")
                ?.child(randomKey!!)
                ?.setValue(message)?.addOnSuccessListener {
                    database?.reference?.child("chats")
                        ?.child(receiveRoom!!)
                        ?.child("message")
                        ?.child(randomKey)
                        ?.setValue(message)
                        ?.addOnSuccessListener {  }
                }
        }
    }

    override fun initView(view: View) {
        mBinding = FragmentBoxChatOverviewBinding.bind(view)
        actionView()
    }

    override fun getBinding(): FragmentBoxChatOverviewBinding {
        mBinding = FragmentBoxChatOverviewBinding.inflate(layoutInflater)
        return mBinding
    }

    private fun actionView() {
        mBinding.iconMoreUtilities.setOnClickListener {
            GA.COUNT_CLICK_MORE++
            mBinding.layoutContainsIcon.visibility = View.VISIBLE
            if (GA.COUNT_CLICK_MORE > 1) {
                mBinding.layoutContainsIcon.visibility = View.GONE
                GA.COUNT_CLICK_MORE = 0
            }
        }
        displayNameUser()
        chat()
    }

    private fun displayNameUser() {
        val mBundle = arguments ?: return
        val myRef = FirebaseDatabase.getInstance().getReference(USERS)
        _receiveUid = mBundle.getString("uidUser").toString()
        myRef.child(mBundle.getString("uidUser").toString())
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    mBinding.tvNameBoxChat.text = snapshot.child("nameUser").value.toString()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
    }
}