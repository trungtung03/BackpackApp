package com.example.backpackapp.fragment.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.backpackapp.R
import com.example.backpackapp.`object`.location.InvitesLocation
import com.example.backpackapp.`object`.location.RequestsLocation
import com.example.backpackapp.activity.inApp.Overview
import com.example.backpackapp.adapter.adapterLocation.InvitesAdapter
import com.example.backpackapp.adapter.adapterLocation.RequestsAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_location_overview.*
import java.util.*

class FragmentLocation : Fragment() {

    private val user = Firebase.auth.currentUser
    private val listRequestsLocation = ArrayList<RequestsLocation>()
    private val listInvitesLocation = ArrayList<InvitesLocation>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_location_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionView()
    }

    private fun actionView() {
        round_backpack_location.setOnClickListener { Overview.roundBack((activity as AppCompatActivity).supportFragmentManager) }

        if (user == null) {
            return
        }
        activity?.let {
            Glide.with(it).load(user.photoUrl).error(R.drawable.avatar_default)
                .into(image_avatar_invites_comment)
        }

        addList()
        GridLayoutManager(activity, 1).also { rcv_requests_location.layoutManager = it }
        LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL,
            false
        ).also { rcv_requests_location.layoutManager = it }
        activity?.let { RequestsAdapter(it, listRequestsLocation) }.also { rcv_requests_location.adapter = it }

        GridLayoutManager(activity, 1).also { rcv_invites_location.layoutManager = it }
        LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL,
            false
        ).also { rcv_invites_location.layoutManager = it }
        activity?.let { InvitesAdapter(it, listInvitesLocation) }.also { rcv_invites_location.adapter = it }

        actionSeeAll()

    }

    private fun actionSeeAll() {
        var count = 0
        var count2 = 0
        tv_see_all_location.setOnClickListener {
            count += 1
            LinearLayoutManager(
                activity,
                LinearLayoutManager.VERTICAL,
                false
            ).also { rcv_requests_location.layoutManager = it }
            if (count > 1) {
                LinearLayoutManager(
                    activity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                ).also { rcv_requests_location.layoutManager = it }
                count = 0
            }
        }
        tv_see_all_invites_location.setOnClickListener {
            count2 += 1
            LinearLayoutManager(
                activity,
                LinearLayoutManager.VERTICAL,
                false
            ).also { rcv_invites_location.layoutManager = it }
            if (count2 > 1) {
                LinearLayoutManager(
                    activity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                ).also { rcv_invites_location.layoutManager = it }
                count2 = 0
            }
        }
    }

    private fun addList() {
        listRequestsLocation.add(
            RequestsLocation(
                user?.photoUrl.toString(),
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/kalen-emsley-100240-unsplash.png?alt=media&token=c404e37b-9e2e-4246-b661-27d643473d3b",
                7,
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/round-airplanemode_active-24px.png?alt=media&token=30e7a624-c87a-4ac6-bf12-4f567318c305",
                Calendar.getInstance().time,
                "US",
                "SA"
            )
        )
        listRequestsLocation.add(
            RequestsLocation(
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/guilherme-stecanella-587579-unsplash.png?alt=media&token=d11abf78-7e16-4163-97e3-d44ee0ded829",
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/guillaume-baudusseau-399124-unsplash.png?alt=media&token=3ed90cdd-1c26-4e1e-bf96-58ea057a9301",
                10,
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/round-airplanemode_active-24px.png?alt=media&token=30e7a624-c87a-4ac6-bf12-4f567318c305",
                Calendar.getInstance().time,
                "VN",
                "RU"
            )
        )

        listInvitesLocation.add(
            InvitesLocation(
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/elizeu-dias-520676-unsplash.png?alt=media&token=e7fb0fba-504d-4a77-ae56-1f585cad018a",
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/guillaume-baudusseau-399124-unsplash.png?alt=media&token=3ed90cdd-1c26-4e1e-bf96-58ea057a9301",
                5,
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/round-airplanemode_active-24px.png?alt=media&token=30e7a624-c87a-4ac6-bf12-4f567318c305",
                Calendar.getInstance().time,
                "VN",
                "RU"
            )
        )

        listInvitesLocation.add(
            InvitesLocation(
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/bekir-donmez-372498-unsplash.png?alt=media&token=d29dfbff-08d8-4e0a-9ce7-302881dac7da",
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/guillaume-baudusseau-399124-unsplash.png?alt=media&token=3ed90cdd-1c26-4e1e-bf96-58ea057a9301",
                4,
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/round-airplanemode_active-24px.png?alt=media&token=30e7a624-c87a-4ac6-bf12-4f567318c305",
                Calendar.getInstance().time,
                "VN",
                "RU"
            )
        )
    }
}