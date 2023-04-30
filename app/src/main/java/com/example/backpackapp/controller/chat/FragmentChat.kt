package com.example.backpackapp.controller.chat


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.backpackapp.R
import com.example.backpackapp.model.chat.MemberChatModel
import com.example.backpackapp.base.BaseFragment
import com.example.backpackapp.controller.Overview
import com.example.backpackapp.databinding.FragmentChatOverviewBinding
import com.example.backpackapp.units.GA
import com.example.backpackapp.units.Parameters.USERS
import com.example.backpackapp.view.chat.MemberChatAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_chat_overview.*

class FragmentChat : BaseFragment<FragmentChatOverviewBinding>() {
    companion object {
        fun newInstance() = FragmentChat()
    }

    private lateinit var mBinding: FragmentChatOverviewBinding
    private var mListMemberChat = arrayListOf<MemberChatModel>()
    private var mMemberChatAdapter: MemberChatAdapter? = null
    private val mMember = MemberChatModel()
    private val mUser = Firebase.auth.currentUser
    private val myRef = FirebaseDatabase.getInstance().getReference(USERS)
    val mBundle = Bundle()

    override fun initView(view: View) {
        mBinding = FragmentChatOverviewBinding.bind(view)
        actionView()
    }

    override fun getBinding(): FragmentChatOverviewBinding {
        mBinding = FragmentChatOverviewBinding.inflate(layoutInflater)
        return mBinding
    }

    private fun actionView() {
        mUser ?: return
        round_back_chat.setOnClickListener { Overview.roundBack() }
        mMemberChatAdapter = activity?.let {
            MemberChatAdapter(it, onClickUser = {
                replaceFragmentAndPassData(R.id.fragment_chat, "FragmentChat")
            })
        }

        mMemberChatAdapter?.let { mMember.displayUsers(mListMemberChat, it) }
        GridLayoutManager(activity, 1).also { rcv_member_chat.layoutManager = it }
        mMemberChatAdapter?.setData(mListMemberChat)
        rcv_member_chat.adapter = mMemberChatAdapter
    }

    private fun replaceFragmentAndPassData(id: Int, backstack: String? = null) {
        val fragment = FragmentBoxChat.newInstance()
        fragment.arguments = passData()
        (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            ).add(id, fragment)
            .addToBackStack(backstack)
            .commit()
    }

    private fun passData(): Bundle {
        mBundle.putString("uidUser", mListMemberChat[GA.POSITION_USER].idMember.toString())
        return mBundle
    }
}