@file:Suppress("DEPRECATION")

package com.example.backpackapp.activity.splash

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.backpackapp.R
import com.example.backpackapp.base.checkConnect.CheckConnect
import com.example.backpackapp.fragment.logIn.FragmentComeBack
import com.example.backpackapp.fragment.logIn.FragmentLogin
import com.example.backpackapp.fragment.signUp.FragmentSignUp
import com.example.backpackapp.parameter.Parameters
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.custom_toast.view.*
import kotlinx.android.synthetic.main.splash_activity.*


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        Handler().postDelayed({
            showButton()
            image_ellipse_backpack_splash.setImageResource(R.drawable.ellipse_backpack_splash_1_yellow)
            title_splash_backpack.setTextColor(Color.WHITE)
        }, Parameters.TIME_SPLASH.toLong())
        button_sign_up_splash_backpack.setOnClickListener {
            if (CheckConnect.checkConnection(applicationContext)) {
                addFragment(R.id.splash_activity, FragmentSignUp())
            } else {
                createCustomToast(R.drawable.no_internet, Parameters.CHECK_CONNECT)
            }
        }
        button_log_in_splash_backpack.setOnClickListener {
            if (CheckConnect.checkConnection(applicationContext)) {
                val user = FirebaseAuth.getInstance().currentUser
                if (user != null) {
                    addFragment(R.id.splash_activity, FragmentComeBack())
                } else {
                    addFragment(R.id.splash_activity, FragmentLogin())
                }
            } else {
                createCustomToast(R.drawable.no_internet, Parameters.CHECK_CONNECT)
            }
        }
    }

    private fun addFragment(id: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(id, fragment).addToBackStack(null).commit()
    }

    private fun showButton() {
        button_sign_up_splash_backpack.visibility = VISIBLE
        button_log_in_splash_backpack.visibility = VISIBLE
    }

    private fun createCustomToast(image: Int, message: String) {
        val toast = Toast(this)
        toast.apply {
            val layout = layoutInflater.inflate(
                R.layout.custom_toast,
                findViewById(R.id.constraint_layout_custom_toast)
            )
            layout.img_warning_toast.setImageResource(image)
            layout.tv_message_custom_toast.text = message
            duration = Toast.LENGTH_SHORT
            view = layout
            show()
        }
    }
}