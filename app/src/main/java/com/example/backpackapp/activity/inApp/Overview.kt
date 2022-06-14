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
import kotlinx.android.synthetic.main.activity_overview.*

@Suppress("DEPRECATION", "ControlFlowWithEmptyBody", "SameParameterValue")
class Overview : AppCompatActivity() {

    companion object {
        var layoutContainFragment: ViewPager? = null
        var bottomNavigation: BottomNavigationView? = null
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

        val viewPagerOverviewAdapter = ViewPagerOverviewAdapter(
            supportFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )

        bottomNavigation = findViewById(R.id.bottom_navigation_overview_activity)
        layoutContainFragment = findViewById(R.id.layout_contain_fragment_overview_backpack)

        layout_contain_fragment_overview_backpack.setPageTransformer(true, ZoomOutPageTransformer())
        layout_contain_fragment_overview_backpack.adapter = viewPagerOverviewAdapter

        layout_contain_fragment_overview_backpack.addOnPageChangeListener(object :
            OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> bottom_navigation_overview_activity.menu.findItem(R.id.menu_home_overview).isChecked =
                        true
                    1 -> bottom_navigation_overview_activity.menu.findItem(R.id.menu_chat_overview).isChecked =
                        true
                    2 -> bottom_navigation_overview_activity.menu.findItem(R.id.menu_location_overview).isChecked =
                        true
                    3 -> bottom_navigation_overview_activity.menu.findItem(R.id.menu_profile_overview).isChecked =
                        true
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })

        bottom_navigation_overview_activity.setOnNavigationItemSelectedListener(object :
            NavigationView.OnNavigationItemSelectedListener,
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.menu_home_overview -> layout_contain_fragment_overview_backpack.currentItem =
                        0
                    R.id.menu_chat_overview -> layout_contain_fragment_overview_backpack.currentItem =
                        1
                    R.id.menu_location_overview -> layout_contain_fragment_overview_backpack.currentItem =
                        2
                    R.id.menu_profile_overview -> layout_contain_fragment_overview_backpack.currentItem =
                        3
                }
                return true
            }
        })
    }
}