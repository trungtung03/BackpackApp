@file:Suppress("DEPRECATION")

package com.example.backpackapp.view.guide

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.backpackapp.controller.chat.FragmentChat
import com.example.backpackapp.controller.home.FragmentHome

import com.example.backpackapp.controller.location.FragmentLocation
import com.example.backpackapp.controller.profile.FragmentProfile

class ViewPagerOverviewAdapter(fm: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fm, behavior) {

    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> return FragmentHome.newInstance()
            1 -> return FragmentChat.newInstance()
            2 -> return FragmentLocation.newInstance()
            3 -> return FragmentProfile.newInstance()
            else -> FragmentHome.newInstance()
        }
    }
}