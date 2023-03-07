package com.example.backpackapp.controller.fragment

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.backpackapp.R
import com.example.backpackapp.controller.activity.inApp.Overview
import com.example.backpackapp.controller.activity.inApp.homePostFullSize.PostFullSizeActivity
import com.example.backpackapp.view.adapter.adpterHome.ListDataAdapter
import com.example.backpackapp.databinding.FragmentHomeOverviewBinding
import com.example.backpackapp.model.home.ListData
import com.example.backpackapp.model.home.popularDestinations.PopularDestinations
import com.example.backpackapp.model.home.posts.Posts
import com.example.backpackapp.util.GA
import com.example.backpackapp.controller.fragment.music.FragmentLibraryMusic
import com.example.backpackapp.view.base.BaseFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_home_overview.*

private val user = Firebase.auth.currentUser

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
        if (user == null) {
            return
        }
        listDataAdapter.also { rcv_post_home.adapter = it }
        actionIconMusic()
    }

    private fun actionIconMusic() {
        fragmentHomeOverviewBinding.iconLibraryMusicHome.setOnClickListener {
            replaceFragment(R.id.fragment_home,
                FragmentLibraryMusic(),
                null)
        }
    }

    private fun getListData(): ArrayList<ListData> {
        val listData = arrayListOf<ListData>()
        val listPosts = arrayListOf<Posts>()
        val listPopularDestinations = arrayListOf<PopularDestinations>()
        Log.d("photo_url_user", user?.photoUrl.toString())
        listPosts.add(
            Posts(
                avatar = user?.photoUrl.toString(),
                name = user?.displayName,
                image = "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/image_trip_item_rcv_post_home_02.png?alt=media&token=3de4d70f-4293-40ff-81ee-1a49e7f5db9b",
                countryName = "VIETNAM",
                describe = "null",
                moreImage = null,
                videoTrip = "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/Snaptik_7115774874399804699_tung-nguyen-trung.mp4?alt=media&token=109c381b-59c4-40bd-a416-c5958d01137a"
            )
        )
        listPosts.add(
            Posts(
                avatar = null,
                name = "Trung Tùng",
                image = null,
                countryName = "HANQUOC",
                describe = "null 1",
                moreImage = null,
                videoTrip = null
            )
        )
        listPosts.add(
            Posts(
                avatar = null,
                name = "Tùng",
                image = null,
                countryName = "CHINA",
                describe = "null 2",
                moreImage = null,
                videoTrip = "https://media.geeksforgeeks.org/wp-content/uploads/20201217192146/Screenrecorder-2020-12-17-19-17-36-828.mp4?_=1"
            )
        )

        listPopularDestinations.add(PopularDestinations(nameCountry = "BALI"))
        listPopularDestinations.add(PopularDestinations(nameCountry = "CHINA"))
        listPopularDestinations.add(PopularDestinations(nameCountry = "THAILAND"))

        listData.add(ListData(GA.TYPE_POST, listPosts, null))
        listData.add(ListData(GA.TYPE_POPULAR_DESTINATIONS, null, listPopularDestinations))
        listData.add(ListData(GA.TYPE_POST, listPosts, null))
        return listData
    }
}