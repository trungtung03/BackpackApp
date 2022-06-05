package com.example.backpackapp.fragment.logIn

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.backpackapp.R
import com.example.backpackapp.activity.splash.SplashActivity
import kotlinx.android.synthetic.main.fragment_log_in_recover.*

class FragmentForgotPassword : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_log_in_recover, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionView()
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