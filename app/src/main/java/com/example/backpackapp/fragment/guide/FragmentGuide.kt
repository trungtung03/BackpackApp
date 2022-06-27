@file:Suppress("DEPRECATION")

package com.example.backpackapp.fragment.guide

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.backpackapp.R
import com.example.backpackapp.activity.inApp.Overview
import com.example.backpackapp.activity.splash.SplashActivity
import com.example.backpackapp.adapter.ViewPagerGuideAdapter
import com.example.backpackapp.parameter.GA
import kotlinx.android.synthetic.main.fragment_guide.*


@Suppress("DEPRECATION", "DEPRECATION")
class FragmentGuide : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_guide, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionView()
    }

    private fun actionView() {
        view_pager_guide_backpack.adapter = ViewPagerGuideAdapter(
            childFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        view_pager_guide_backpack.offscreenPageLimit = 3
        guide_indicator_backpack.setViewPager(view_pager_guide_backpack)
        btn_next_guide.setOnClickListener {
            GA.COUNT_CLICK++
            when (GA.COUNT_CLICK) {
                3 -> getString(R.string.text_button_guide_03_backpack).also {
                    btn_next_guide.text = it
                }
                4 -> {
                    getString(R.string.text_button_next_bacpack_sign_up_02).also {
                        btn_next_guide.text = it
                    }
                    startActivity(Intent(activity, Overview::class.java))
                    (activity as AppCompatActivity).overridePendingTransition(
                        R.anim.slide_in_right,
                        R.anim.slide_in_left
                    )
                    activity?.finish()
                }
            }
            view_pager_guide_backpack.currentItem = GA.COUNT_CLICK
        }

        tv_skip_guide_backpack.setOnClickListener {
            startActivity(
                Intent(
                    activity,
                    Overview::class.java
                )
            )
            (activity as AppCompatActivity).overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_in_left
            )
            activity?.finish()
        }
    }
}