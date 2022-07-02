@file:Suppress("DEPRECATION")

package com.example.backpackapp.controller

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.backpackapp.view.chat.FragmentChat
import com.example.backpackapp.view.fragment.guide.FragmentHome
import com.example.backpackapp.view.fragment.guide.FragmentLocation
import com.example.backpackapp.view.fragment.guide.FragmentProfile

class ViewPagerOverviewAdapter(fm: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fm, behavior) {

    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> return FragmentHome()
            1 -> return FragmentChat()
            2 -> return FragmentLocation()
            3 -> return FragmentProfile()
            else -> FragmentHome()
        }
    }
}