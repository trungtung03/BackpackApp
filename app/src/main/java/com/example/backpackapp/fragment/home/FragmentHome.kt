package com.example.backpackapp.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.backpackapp.R
import com.example.backpackapp.`object`.home.ListData
import com.example.backpackapp.`object`.home.popularDestinations.PopularDestinations
import com.example.backpackapp.`object`.home.posts.Posts
import com.example.backpackapp.adapter.adpterHome.ListDataAdapter
import com.example.backpackapp.parameter.GA
import kotlinx.android.synthetic.main.fragment_home_overview.*

class FragmentHome : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionView()
    }

    private fun actionView() {
        addList()
//        GridLayoutManager(activity, 1).also { rcv_post_home.layoutManager = it }
        LinearLayoutManager(activity).also { rcv_post_home.layoutManager = it }
        val listDataAdapter = activity?.let { ListDataAdapter(it, getListData()) }
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

    private fun addList() {

    }
}