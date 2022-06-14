@file:Suppress("DEPRECATION")

package com.example.backpackapp.activity.inApp

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.backpackapp.R
import com.example.backpackapp.adapter.ViewPagerOverviewAdapter
import com.example.backpackapp.base.viewPager.ZoomOutPageTransformer
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

var layoutContainFragment: ViewPager? = null
var bottomNavigation: BottomNavigationView? = null

@Suppress("DEPRECATION", "ControlFlowWithEmptyBody", "SameParameterValue")
class Overview : AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overview)

        bottomNavigation = findViewById(R.id.bottom_navigation_overview_activity)
        layoutContainFragment = findViewById(R.id.layout_contain_fragment_overview_backpack)

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