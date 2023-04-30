@file:Suppress("DEPRECATION")

package com.example.backpackapp.view.guide

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.backpackapp.controller.guide.uiGuide.FragmentGuide01
import com.example.backpackapp.controller.guide.uiGuide.FragmentGuide02
import com.example.backpackapp.controller.guide.uiGuide.FragmentGuide03
import com.example.backpackapp.controller.guide.uiGuide.FragmentGuide04

@Suppress("DEPRECATION")
class ViewPagerGuideAdapter(fm: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fm, behavior) {

    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> return FragmentGuide01.newInstance()
            1 -> return FragmentGuide02.newInstance()
            2 -> return FragmentGuide03.newInstance()
            3 -> return FragmentGuide04.newInstance()
            else -> FragmentGuide01.newInstance()
        }
    }
}