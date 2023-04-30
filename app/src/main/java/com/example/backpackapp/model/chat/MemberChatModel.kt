package com.example.backpackapp.model.chat

import android.annotation.SuppressLint
import com.example.backpackapp.units.Parameters
import com.example.backpackapp.view.chat.MemberChatAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class MemberChatModel(
    var idMember: String? = null,
    var avatarUser: String? = null,
    var nameUser: String? = null,
    var message: String? = null,
    var timeSendMessage: String? = null
) {
    private var mReference = FirebaseDatabase.getInstance().getReference(Parameters.USERS)
    private val mUser = Firebase.auth.currentUser
    private var mUpdate = mReference.child("${mUser?.uid}")

    fun displayUsers(mList: ArrayList<MemberChatModel>, mAdapter: MemberChatAdapter) {
        mReference.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mList.clear()
                mUpdate.child(Parameters.TIME_SEND_MESSAGE)
                    .setValue(Calendar.getInstance().time.toString())
                for (potSnapshot in snapshot.children) {
                    val member = potSnapshot.getValue(this@MemberChatModel::class.java)
                    if (member != null) {
                        mList.add(member)
                    }
                }
                mAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}