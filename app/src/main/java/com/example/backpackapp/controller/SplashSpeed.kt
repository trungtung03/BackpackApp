@file:Suppress("CAST_NEVER_SUCCEEDS")

package com.example.backpackapp.controller

import android.os.Handler
import android.view.View
import com.example.backpackapp.R
import com.example.backpackapp.base.BaseActivity
import com.example.backpackapp.databinding.SplashSpeedBinding
import com.example.backpackapp.units.GA.TIME_SPLASH_SPEED
import com.example.backpackapp.units.Parameters

@Suppress("DEPRECATION")
class SplashSpeed : BaseActivity() {
    private lateinit var binding: SplashSpeedBinding

    override fun setFlag() = fullScreen()

    override fun setLayout(): View = binding.root

    override fun initView() {
        binding = SplashSpeedBinding.inflate(layoutInflater)

        Handler().postDelayed({
            openActivity(SplashActivity::class.java)
            animationSwitchActivity(R.anim.slide_in_right, R.anim.slide_in_left)
            finish()
        }, TIME_SPLASH_SPEED.toLong())
    }
}