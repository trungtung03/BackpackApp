package com.example.backpackapp.controller.fragment


import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.backpackapp.model.chat.MemberChat
import com.example.backpackapp.controller.activity.inApp.Overview
import com.example.backpackapp.view.adapter.adapterChat.MemberChatAdapter
import com.example.backpackapp.view.base.BaseFragment
import com.example.backpackapp.databinding.FragmentChatOverviewBinding
import com.google.firebase.auth.ktx.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_chat_overview.*
import java.util.*

class FragmentChat : BaseFragment<FragmentChatOverviewBinding>() {
    private lateinit var fragmentChatOverviewBinding: FragmentChatOverviewBinding
    private val listMemberChat by lazy { arrayListOf<MemberChat>() }
    private var mMemberChatAdapter: MemberChatAdapter? = null

    override fun initView(view: View) {
        fragmentChatOverviewBinding = FragmentChatOverviewBinding.bind(view)
        actionView()
    }

    override fun getBinding(): FragmentChatOverviewBinding {
        fragmentChatOverviewBinding = FragmentChatOverviewBinding.inflate(layoutInflater)
        return fragmentChatOverviewBinding
    }

    private fun actionView() {
        round_back_chat.setOnClickListener { Overview.roundBack() }

        addList()
        GridLayoutManager(activity, 1).also { rcv_member_chat.layoutManager = it }
        mMemberChatAdapter = activity?.let { MemberChatAdapter(it) }
        mMemberChatAdapter?.setData(listMemberChat)
        rcv_member_chat.adapter = mMemberChatAdapter
    }

    private fun addList() {
        val user = Firebase.auth.currentUser ?: return
        listMemberChat.add(
            MemberChat(
                avatarUser = user.photoUrl.toString(),
                nameUser = user.displayName.toString(),
                message = "Hello!!",
                timeSendMessage = Calendar.getInstance().time
            )
        )
        listMemberChat.add(
            MemberChat(
                avatarUser = "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/member1.png?alt=media&token=1ad6d5a8-3df4-4f26-87e9-10aaf61aa200",
                nameUser = "Alex",
                message = "Hey when are you going?",
                timeSendMessage = Calendar.getInstance().time
            )
        )
        listMemberChat.add(
            MemberChat(
                avatarUser = "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/member2.png?alt=media&token=a9c4f776-fe26-4a69-ba67-d0754603fcbd",
                nameUser = "Sandra",
                message = "I would love to take this trip with ...",
                timeSendMessage = Calendar.getInstance().time
            )
        )
        listMemberChat.add(
            MemberChat(
                avatarUser = "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/member3.png?alt=media&token=16a1429b-9f87-4e32-a0f7-755f963f976b",
                nameUser = "Lisa",
                message = "Sure, lets do it.",
                timeSendMessage = Calendar.getInstance().time
            )
        )
        listMemberChat.add(
            MemberChat(
                avatarUser = "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/member4.png?alt=media&token=aed59a90-f438-4c37-bb5a-136715ab041a",
                nameUser = "Mike",
                message = "Yes, it was an amazing experience",
                timeSendMessage = Calendar.getInstance().time
            )
        )
        listMemberChat.add(
            MemberChat(
                avatarUser = "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/member5.png?alt=media&token=911a0510-a925-40be-9166-e1bb7bd15225",
                nameUser = "Jennifer",
                message = "Loved it out there.",
                timeSendMessage = Calendar.getInstance().time
            )
        )
        listMemberChat.add(
            MemberChat(
                avatarUser = "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/member6.png?alt=media&token=29872873-6fb2-4777-9e86-b4ee32baa147",
                nameUser = "Travis",
                message = "Can't wait to do it again",
                timeSendMessage = Calendar.getInstance().time
            )
        )
    }
}