@file:Suppress("DEPRECATION")

package com.example.backpackapp.view.activity.inApp

import android.view.MenuItem
import android.view.View
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.backpackapp.R
import com.example.backpackapp.controller.ViewPagerOverviewAdapter
import com.example.backpackapp.ui.base.BaseActivity
import com.example.backpackapp.ui.animation.ZoomOutPageTransformer
import com.example.backpackapp.databinding.ActivityOverviewBinding
import com.example.backpackapp.util.GA
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

var layoutContainFragment: ViewPager? = null
var bottomNavigation: BottomNavigationView? = null

@Suppress("DEPRECATION", "ControlFlowWithEmptyBody", "SameParameterValue")
class Overview : BaseActivity() {
    private lateinit var binding: ActivityOverviewBinding

    companion object {
        fun roundBack() {
            layoutContainFragment?.currentItem = 0
            bottomNavigation?.menu?.findItem(R.id.menu_home_overview)?.isChecked = true
        }

        fun moveFragment() {
            layoutContainFragment?.currentItem = 2
            bottomNavigation?.menu?.findItem(R.id.menu_location_overview)?.isChecked = true
        }
    }

    override fun setFlag(): Unit = fullScreen()

    override fun setLayout(): View = binding.root

    override fun initView() {
        binding = ActivityOverviewBinding.inflate(layoutInflater)

        GA.COUNT_CLICK = 0

        bottomNavigation = binding.bottomNavigationOverviewActivity
        layoutContainFragment = binding.layoutContainFragmentOverviewBackpack

        layoutContainFragment?.setPageTransformer(true, ZoomOutPageTransformer())
        layoutContainFragment?.adapter = ViewPagerOverviewAdapter(
            supportFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        layoutContainFragment?.offscreenPageLimit = 3

        layoutContainFragment?.addOnPageChangeListener(object :
            OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> bottomNavigation?.menu?.findItem(R.id.menu_home_overview)?.isChecked =
                        true
                    1 -> bottomNavigation?.menu?.findItem(R.id.menu_chat_overview)?.isChecked =
                        true
                    2 -> bottomNavigation?.menu?.findItem(R.id.menu_location_overview)?.isChecked =
                        true
                    3 -> bottomNavigation?.menu?.findItem(R.id.menu_profile_overview)?.isChecked =
                        true
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })

        bottomNavigation?.setOnNavigationItemSelectedListener(object :
            NavigationView.OnNavigationItemSelectedListener,
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.menu_home_overview -> layoutContainFragment?.currentItem =
                        0
                    R.id.menu_chat_overview -> layoutContainFragment?.currentItem =
                        1
                    R.id.menu_location_overview -> layoutContainFragment?.currentItem =
                        2
                    R.id.menu_profile_overview -> layoutContainFragment?.currentItem =
                        3
                }
                return true
            }
        })
    }
}