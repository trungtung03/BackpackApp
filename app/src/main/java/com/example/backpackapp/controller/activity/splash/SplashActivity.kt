@file:Suppress("DEPRECATION")

package com.example.backpackapp.controller.activity.splash

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Handler
import android.view.View
import android.view.View.VISIBLE
import com.example.backpackapp.R
import com.example.backpackapp.ui.base.BaseActivity
import com.example.backpackapp.ui.base.CheckConnect
import com.example.backpackapp.databinding.SplashActivityBinding
import com.example.backpackapp.controller.fragment.login.FragmentComeBack
import com.example.backpackapp.controller.fragment.login.FragmentLogin
import com.example.backpackapp.controller.fragment.signup.FragmentSignUp
import com.example.backpackapp.util.Parameters
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
        }, Parameters.TIME_SPLASH.toLong())
        binding.buttonSignUpSplashBackpack.setOnClickListener {
            if (CheckConnect.checkConnection(applicationContext)) {
                addFragment(binding.splashActivity.id, FragmentSignUp())
            } else {
                createCustomToast(R.drawable.no_internet, Parameters.CHECK_CONNECT)
            }
        }
        binding.buttonLogInSplashBackpack.setOnClickListener {
            if (CheckConnect.checkConnection(applicationContext)) {
                val user = FirebaseAuth.getInstance().currentUser
                if (user != null) {
                    addFragment(binding.splashActivity.id, FragmentComeBack())
                } else {
                    addFragment(binding.splashActivity.id, FragmentLogin())
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