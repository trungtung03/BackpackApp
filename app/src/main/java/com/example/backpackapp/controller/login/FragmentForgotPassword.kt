package com.example.backpackapp.controller.login

import android.content.Intent
import android.view.View
import com.example.backpackapp.controller.SplashActivity
import com.example.backpackapp.base.BaseFragment
import com.example.backpackapp.databinding.FragmentLogInRecoverBinding
import kotlinx.android.synthetic.main.fragment_log_in_recover.*

class FragmentForgotPassword : BaseFragment<FragmentLogInRecoverBinding>() {
    companion object {
        fun newInstance() = FragmentForgotPassword()
    }

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