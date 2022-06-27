package com.example.backpackapp.activity.inApp.homePostFullSize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.backpackapp.R
import com.example.backpackapp.activity.inApp.Overview
import com.example.backpackapp.base.activity.Activity
import com.example.backpackapp.parameter.GA
import kotlinx.android.synthetic.main.activity_post_full_size.*

class PostFullSizeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_full_size)
        Activity.fullScreen(window)
        actionView()
    }

    private fun actionView() {
        round_back_post_full_size.setOnClickListener {
            startActivity(Intent(this, Overview::class.java))
            overridePendingTransition(R.anim.slide_in_left_finish, R.anim.slide_in_right_finish)
            finish()
        }

        image_btn_heart_post_full_size.setOnClickListener {
            GA.COUNT_CLICK++
            image_btn_heart_post_full_size.setImageResource(
                R.drawable.heart_enable_item_rcv_post_home
            )
            if (GA.COUNT_CLICK > 1) {
                image_btn_heart_post_full_size.setImageResource(
                    R.drawable.heart_disable_item_rcv_posts_home
                )
                GA.COUNT_CLICK = 0
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        overridePendingTransition(R.anim.slide_in_left_finish, R.anim.slide_in_right_finish)
    }
}