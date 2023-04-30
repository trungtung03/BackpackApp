package com.example.backpackapp.controller.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.backpackapp.R
import com.example.backpackapp.base.BaseFragment
import com.example.backpackapp.controller.Overview
import com.example.backpackapp.controller.PostFullSizeActivity
import com.example.backpackapp.controller.home.music.FragmentLibraryMusic
import com.example.backpackapp.custom.CustomProgressDialog.Companion.createDialog
import com.example.backpackapp.custom.CustomProgressDialog.Companion.onPostExecute
import com.example.backpackapp.custom.CustomProgressDialog.Companion.onPreExecute
import com.example.backpackapp.databinding.FragmentHomeOverviewBinding
import com.example.backpackapp.model.home.modelChild.PostsTripModel
import com.example.backpackapp.units.GA
import com.example.backpackapp.units.GA.POSITION_POST
import com.example.backpackapp.view.home.ListDataAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_home_overview.*
import java.io.Serializable
import java.util.ArrayList

@SuppressLint("NotifyDataSetChanged")
class FragmentHome : BaseFragment<FragmentHomeOverviewBinding>() {
    companion object {
        fun newInstance() = FragmentHome()

    }

    private lateinit var fragmentHomeOverviewBinding: FragmentHomeOverviewBinding
    private val mUser = Firebase.auth.currentUser
    private val mPostsTripModel = PostsTripModel()
    private var mListDataAdapter: ListDataAdapter? = null

    override fun initView(view: View) {
        fragmentHomeOverviewBinding = FragmentHomeOverviewBinding.bind(view)
        actionView()
    }

    override fun getBinding(): FragmentHomeOverviewBinding {
        fragmentHomeOverviewBinding = FragmentHomeOverviewBinding.inflate(layoutInflater)
        return fragmentHomeOverviewBinding
    }

    private fun actionView() {
        fragmentHomeOverviewBinding.edtSearchCityHome.isFocusable = false

        if (mUser == null) {
            return
        }

        activity?.let { createDialog(it) }

        actionIconMusic()
        displayPost()
        eventRefreshData()
        refreshData()
    }

    private fun refreshData() {
        onPreExecute()
        Handler().postDelayed({
            if (!mPostsTripModel.isComplete) {
                mPostsTripModel.getApiTotalList()
                mListDataAdapter?.notifyDataSetChanged()
                onPostExecute()
            }
        }, 10000)
    }

    private fun actionIconMusic() {
        fragmentHomeOverviewBinding.iconLibraryMusicHome.setOnClickListener {
            replaceFragment(
                R.id.fragment_home, FragmentLibraryMusic.newInstance(), null
            )
        }
    }

    private fun displayPost() {
        LinearLayoutManager(activity).also { rcv_post_home.layoutManager = it }
        rcv_post_home.isFocusable = false
        mListDataAdapter = activity?.let {
            ListDataAdapter(it, onClickJoin = {
                Overview.moveFragment()
            }, onClickItem = {
                mClickItem()
            })
        }
        mListDataAdapter?.setData(mPostsTripModel.getApiTotalList())
        mListDataAdapter.also { rcv_post_home.adapter = it }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun mClickItem() {
        val mIntent = Intent(activity, PostFullSizeActivity::class.java)
        val mBundle = Bundle()

        animationSwitchActivity(
            R.anim.slide_in_right, R.anim.slide_in_left
        )

        mBundle.putString(
            "image",
            mPostsTripModel.mDataBundle[POSITION_POST].image.toString()
        )
        mBundle.putString(
            "avatar",
            mPostsTripModel.mDataBundle[POSITION_POST].avatar.toString()
        )
        mBundle.putString(
            "name",
            mPostsTripModel.mDataBundle[POSITION_POST].name.toString()
        )
        mBundle.putString(
            "describe",
            mPostsTripModel.mDataBundle[POSITION_POST].describe.toString()
        )

        mIntent.putExtras(mBundle)
        startActivity(mIntent)
    }

    private fun eventRefreshData() {
        swipe_refresh_rcv.setOnRefreshListener {
            reloadFragment(this@FragmentHome)
            displayPost()
            refreshData()
            if (!mPostsTripModel.isComplete) {
                swipe_refresh_rcv.isRefreshing = false
            }
        }
    }
}