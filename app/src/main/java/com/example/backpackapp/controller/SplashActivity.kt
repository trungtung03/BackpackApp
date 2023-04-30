@file:Suppress("DEPRECATION")

package com.example.backpackapp.controller

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Handler
import android.view.View
import android.view.View.VISIBLE
import com.example.backpackapp.R
import com.example.backpackapp.base.BaseActivity
import com.example.backpackapp.networking.CheckConnect
import com.example.backpackapp.databinding.SplashActivityBinding
import com.example.backpackapp.controller.login.FragmentComeBack
import com.example.backpackapp.controller.login.FragmentLogin
import com.example.backpackapp.controller.register.FragmentRegister
import com.example.backpackapp.units.GA.TIME_SPLASH
import com.example.backpackapp.units.Parameters
import com.google.firebase.auth.FirebaseAuth


@Suppress("SameParameterValue", "unused", "MemberVisibilityCanBePrivate")
@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var binding: SplashActivityBinding
    }

    override fun setFlag(): Unit = fullScreen()

    override fun setLayout(): View = binding.root

    override fun initView() {
        binding = SplashActivityBinding.inflate(layoutInflater)
        Handler().postDelayed({
            showButton()
            binding.imageEllipseBackpackSplash.setImageResource(R.drawable.ellipse_backpack_splash_1_yellow)
            binding.titleSplashBackpack.setTextColor(Color.WHITE)
        }, TIME_SPLASH.toLong())
        binding.buttonSignUpSplashBackpack.setOnClickListener {
            if (CheckConnect.checkConnection(applicationContext)) {
                addFragment(binding.splashActivity.id, FragmentRegister.newInstance())
            } else {
                createCustomToast(R.drawable.no_internet, Parameters.CHECK_CONNECT)
            }
        }
        binding.buttonLogInSplashBackpack.setOnClickListener {
            if (CheckConnect.checkConnection(applicationContext)) {
                val user = FirebaseAuth.getInstance().currentUser
                if (user != null) {
                    addFragment(binding.splashActivity.id, FragmentComeBack.newInstance())
                } else {
                    addFragment(binding.splashActivity.id, FragmentLogin.newInstance())
                }
            } else {
                createCustomToast(R.drawable.no_internet, Parameters.CHECK_CONNECT)
            }
        }
    }

    private fun showButton() {
        binding.buttonSignUpSplashBackpack.visibility = VISIBLE
        binding.buttonLogInSplashBackpack.visibility = VISIBLE
    }
}