@file:Suppress("DEPRECATION")

package com.example.backpackapp.fragment.guide

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.backpackapp.R
import com.example.backpackapp.activity.inApp.Overview
import com.example.backpackapp.adapter.ViewPagerGuideAdapter
import com.example.backpackapp.base.BaseFragment
import com.example.backpackapp.databinding.FragmentGuideBinding
import com.example.backpackapp.parameter.GA
import kotlinx.android.synthetic.main.fragment_guide.*


@Suppress("DEPRECATION", "DEPRECATION")
class FragmentGuide : BaseFragment<FragmentGuideBinding>() {
    private lateinit var fragmentGuideBinding: FragmentGuideBinding

    private fun actionView() {
        fragmentGuideBinding.viewPagerGuideBackpack.setPagingEnabled(false)
        fragmentGuideBinding.viewPagerGuideBackpack.adapter = ViewPagerGuideAdapter(
            childFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        fragmentGuideBinding.viewPagerGuideBackpack.offscreenPageLimit = 3
        fragmentGuideBinding.guideIndicatorBackpack.setViewPager(view_pager_guide_backpack)
        fragmentGuideBinding.btnNextGuide.setOnClickListener {
            GA.COUNT_CLICK++
            when (GA.COUNT_CLICK) {
                3 -> getString(R.string.text_button_guide_03_backpack).also {
                    fragmentGuideBinding.btnNextGuide.text = it
                }
                4 -> {
                    getString(R.string.text_button_next_bacpack_sign_up_02).also {
                        fragmentGuideBinding.btnNextGuide.text = it
                    }
                    openActivity(Overview::class.java)
                    (activity as AppCompatActivity).overridePendingTransition(
                        R.anim.slide_in_right,
                        R.anim.slide_in_left
                    )
                    activity?.finish()
                }
            }
            fragmentGuideBinding.viewPagerGuideBackpack.currentItem = GA.COUNT_CLICK
        }

        fragmentGuideBinding.tvSkipGuideBackpack.setOnClickListener {
            openActivity(Overview::class.java)
            (activity as AppCompatActivity).overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_in_left
            )
            activity?.finish()
        }
    }

    override fun initView(view: View) {
        fragmentGuideBinding = FragmentGuideBinding.bind(view)
        actionView()
    }

    override fun getBinding(): FragmentGuideBinding {
        fragmentGuideBinding = FragmentGuideBinding.inflate(layoutInflater)
        return fragmentGuideBinding
    }
}