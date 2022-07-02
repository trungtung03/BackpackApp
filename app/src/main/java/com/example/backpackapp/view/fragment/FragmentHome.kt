package com.example.backpackapp.view.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.backpackapp.R
import com.example.backpackapp.view.activity.inApp.Overview
import com.example.backpackapp.view.activity.inApp.homePostFullSize.PostFullSizeActivity
import com.example.backpackapp.controller.adpterHome.ListDataAdapter
import com.example.backpackapp.ui.base.BaseFragment
import com.example.backpackapp.databinding.FragmentHomeOverviewBinding
import com.example.backpackapp.model.home.ListData
import com.example.backpackapp.model.home.popularDestinations.PopularDestinations
import com.example.backpackapp.model.home.posts.Posts
import com.example.backpackapp.util.GA
import kotlinx.android.synthetic.main.fragment_home_overview.*

class FragmentHome : BaseFragment<FragmentHomeOverviewBinding>() {
    private lateinit var fragmentHomeOverviewBinding: FragmentHomeOverviewBinding

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
        LinearLayoutManager(activity).also { rcv_post_home.layoutManager = it }
        rcv_post_home.isFocusable = false
        val listDataAdapter = activity?.let {
            ListDataAdapter(it, getListData(), onClickJoin = {
                Overview.moveFragment()
            }, onClickItem = {
                openActivity(PostFullSizeActivity::class.java)
                animationSwitchActivity(
                    R.anim.slide_in_right,
                    R.anim.slide_in_left
                )
            })
        }
        listDataAdapter.also { rcv_post_home.adapter = it }
    }

    private fun getListData(): ArrayList<ListData> {
        val listData = arrayListOf<ListData>()
        val listPosts = arrayListOf<Posts>()
        val listPopularDestinations = arrayListOf<PopularDestinations>()
        listPosts.add(Posts(name = "Nguyễn Trung Tùng"))
        listPosts.add(Posts(name = "Trung Tùng"))
        listPosts.add(Posts(name = "Tùng"))

        listPopularDestinations.add(PopularDestinations(nameCountry = "BALI"))
        listPopularDestinations.add(PopularDestinations(nameCountry = "CHINA"))
        listPopularDestinations.add(PopularDestinations(nameCountry = "THAILAND"))

        listData.add(ListData(GA.TYPE_POST, listPosts, null))
        listData.add(ListData(GA.TYPE_POPULAR_DESTINATIONS, null, listPopularDestinations))
        listData.add(ListData(GA.TYPE_POST, listPosts, null))
        return listData
    }
}