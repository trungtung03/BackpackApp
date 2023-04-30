package com.example.backpackapp.controller.location

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.backpackapp.R
import com.example.backpackapp.model.location.LocationTripModel
import com.example.backpackapp.base.BaseFragment
import com.example.backpackapp.controller.Overview
import com.example.backpackapp.controller.home.FragmentHome
import com.example.backpackapp.controller.location.post.PostFragment
import com.example.backpackapp.databinding.FragmentLocationOverviewBinding
import com.example.backpackapp.units.GA.CHECK_SEE_ALL_INVITES
import com.example.backpackapp.units.GA.CHECK_SEE_ALL_REQUESTS
import com.example.backpackapp.view.location.InvitesAdapter
import com.example.backpackapp.view.location.RequestsAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_location_overview.*
import java.util.*

class FragmentLocation : BaseFragment<FragmentLocationOverviewBinding>(), View.OnClickListener {
    companion object {
        fun newInstance() = FragmentLocation()
    }

    private lateinit var fragmentLocationOverviewBinding: FragmentLocationOverviewBinding

    private val mUser = Firebase.auth.currentUser
    private val listRequestsLocation = ArrayList<LocationTripModel>()
    private val listInvitesLocation = ArrayList<LocationTripModel>()
    private var mInvitesAdapter: InvitesAdapter? = null
    private var mRequestsAdapter: RequestsAdapter? = null

    override fun initView(view: View) {
        fragmentLocationOverviewBinding = FragmentLocationOverviewBinding.bind(view)
        actionView()
    }

    override fun getBinding(): FragmentLocationOverviewBinding {
        fragmentLocationOverviewBinding = FragmentLocationOverviewBinding.inflate(layoutInflater)
        return fragmentLocationOverviewBinding
    }

    private fun actionView() {
        round_backpack_location.setOnClickListener { Overview.roundBack() }

        if (mUser == null) {
            return
        }
        activity?.let {
            Glide.with(it).load(mUser.photoUrl).error(R.drawable.avatar_default)
                .into(image_avatar_invites_comment)
        }
        addList()


        displayTripRequests()
        displayTripInvites()

        fragmentLocationOverviewBinding.imageButtonAddTripLocation.setOnClickListener(this)
        fragmentLocationOverviewBinding.tvSeeAllLocation.setOnClickListener(this)
        fragmentLocationOverviewBinding.tvSeeAllInvitesLocation.setOnClickListener(this)
    }

    private fun displayTripRequests() {
        mRequestsAdapter = activity?.let { RequestsAdapter(it) }
        mRequestsAdapter?.setData(listRequestsLocation)
        GridLayoutManager(activity, 1).also { rcv_requests_location.layoutManager = it }
        LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL,
            false
        ).also { rcv_requests_location.layoutManager = it }
        rcv_requests_location.adapter = mRequestsAdapter
    }

    private fun displayTripInvites() {
        mInvitesAdapter = activity?.let { InvitesAdapter(it) }
        mInvitesAdapter?.setData(listInvitesLocation)
        GridLayoutManager(activity, 1).also { rcv_invites_location.layoutManager = it }
        LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL,
            false
        ).also { rcv_invites_location.layoutManager = it }
        rcv_invites_location.adapter = mInvitesAdapter
    }
    private fun addList() {
        listRequestsLocation.add(
            LocationTripModel(
                mUser?.photoUrl.toString(),
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/kalen-emsley-100240-unsplash.png?alt=media&token=c404e37b-9e2e-4246-b661-27d643473d3b",
                7,
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/round-airplanemode_active-24px.png?alt=media&token=30e7a624-c87a-4ac6-bf12-4f567318c305",
                Calendar.getInstance().time.toString(),
                "US",
                "SA"
            )
        )
        listRequestsLocation.add(
            LocationTripModel(
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/guilherme-stecanella-587579-unsplash.png?alt=media&token=d11abf78-7e16-4163-97e3-d44ee0ded829",
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/guillaume-baudusseau-399124-unsplash.png?alt=media&token=3ed90cdd-1c26-4e1e-bf96-58ea057a9301",
                10,
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/round-airplanemode_active-24px.png?alt=media&token=30e7a624-c87a-4ac6-bf12-4f567318c305",
                Calendar.getInstance().time.toString(),
                "VN",
                "RU"
            )
        )

        listInvitesLocation.add(
            LocationTripModel(
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/elizeu-dias-520676-unsplash.png?alt=media&token=e7fb0fba-504d-4a77-ae56-1f585cad018a",
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/guillaume-baudusseau-399124-unsplash.png?alt=media&token=3ed90cdd-1c26-4e1e-bf96-58ea057a9301",
                5,
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/round-airplanemode_active-24px.png?alt=media&token=30e7a624-c87a-4ac6-bf12-4f567318c305",
                Calendar.getInstance().time.toString(),
                "VN",
                "RU"
            )
        )

        listInvitesLocation.add(
            LocationTripModel(
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/bekir-donmez-372498-unsplash.png?alt=media&token=d29dfbff-08d8-4e0a-9ce7-302881dac7da",
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/guillaume-baudusseau-399124-unsplash.png?alt=media&token=3ed90cdd-1c26-4e1e-bf96-58ea057a9301",
                4,
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/round-airplanemode_active-24px.png?alt=media&token=30e7a624-c87a-4ac6-bf12-4f567318c305",
                Calendar.getInstance().time.toString(),
                "VN",
                "RU"
            )
        )
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.image_button_add_trip_location -> {
                replaceFragment(R.id.fragment_location, PostFragment.newInstance(), "FragmentLocation")
            }
            R.id.tv_see_all_location -> {
                CHECK_SEE_ALL_REQUESTS += 1
                LinearLayoutManager(
                    activity,
                    LinearLayoutManager.VERTICAL,
                    false
                ).also { rcv_requests_location.layoutManager = it }
                if (CHECK_SEE_ALL_REQUESTS > 1) {
                    LinearLayoutManager(
                        activity,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    ).also { rcv_requests_location.layoutManager = it }
                    CHECK_SEE_ALL_REQUESTS = 0
                }
            }
            R.id.tv_see_all_invites_location -> {
                CHECK_SEE_ALL_INVITES += 1
                LinearLayoutManager(
                    activity,
                    LinearLayoutManager.VERTICAL,
                    false
                ).also { rcv_invites_location.layoutManager = it }
                if (CHECK_SEE_ALL_INVITES > 1) {
                    LinearLayoutManager(
                        activity,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    ).also { rcv_invites_location.layoutManager = it }
                    CHECK_SEE_ALL_INVITES = 0
                }
            }
        }
    }
}