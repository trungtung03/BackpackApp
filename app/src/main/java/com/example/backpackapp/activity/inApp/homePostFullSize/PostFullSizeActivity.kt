package com.example.backpackapp.activity.inApp.homePostFullSize

import android.content.Intent
import android.view.View
import com.example.backpackapp.R
import com.example.backpackapp.activity.inApp.Overview
import com.example.backpackapp.base.BaseActivity
import com.example.backpackapp.databinding.ActivityPostFullSizeBinding
import com.example.backpackapp.parameter.GA

class PostFullSizeActivity : BaseActivity() {
    private lateinit var binding: ActivityPostFullSizeBinding
    override fun initView() {
        binding = ActivityPostFullSizeBinding.inflate(layoutInflater)
        actionView()
    }

    override fun setLayout(): View = binding.root

    override fun setFlag(): Unit = fullScreen()

    private fun actionView() {
        binding.roundBackPostFullSize.setOnClickListener {
            startActivity(Intent(this, Overview::class.java))
            overridePendingTransition(R.anim.slide_in_left_finish, R.anim.slide_in_right_finish)
            finish()
        }

        binding.imageBtnHeartPostFullSize.setOnClickListener {
            GA.COUNT_CLICK++
            binding.imageBtnHeartPostFullSize.setImageResource(
                R.drawable.heart_enable_item_rcv_post_home
            )
            if (GA.COUNT_CLICK > 1) {
                binding.imageBtnHeartPostFullSize.setImageResource(
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