@file:Suppress("CAST_NEVER_SUCCEEDS")

package com.example.backpackapp.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.backpackapp.activity.splash.SplashActivity
import com.example.backpackapp.parameter.Parameters

@Suppress("DEPRECATION")
class SplashSpeed : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            startActivity(Intent(this, SplashActivity::class.java))
            finish()
        }, Parameters.TIME_SPLASH_SPEED.toLong())
    }
}