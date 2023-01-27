package com.example.backpackapp.controller.fragment.login

import android.content.Intent
import android.view.View
import com.example.backpackapp.controller.activity.splash.SplashActivity
import com.example.backpackapp.ui.base.BaseFragment
import com.example.backpackapp.databinding.FragmentLogInRecoverBinding
import kotlinx.android.synthetic.main.fragment_log_in_recover.*

class FragmentForgotPassword : BaseFragment<FragmentLogInRecoverBinding>() {
    private lateinit var fragmentLogInRecoverBinding: FragmentLogInRecoverBinding

    override fun initView(view: View) {
        fragmentLogInRecoverBinding = FragmentLogInRecoverBinding.bind(view)
        actionView()
    }

    override fun getBinding(): FragmentLogInRecoverBinding {
        fragmentLogInRecoverBinding = FragmentLogInRecoverBinding.inflate(layoutInflater)
        return fragmentLogInRecoverBinding
    }

    private fun actionView() {
        round_back_log_in_recover.setOnClickListener {
            startActivity(
                Intent(
                    activity,
                    SplashActivity::class.java
                )
            )
        }
    }
}