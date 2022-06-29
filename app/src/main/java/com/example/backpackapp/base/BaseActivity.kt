package com.example.backpackapp.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.backpackapp.R
import kotlinx.android.synthetic.main.custom_toast.view.*

@Suppress("DEPRECATION")
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFlag()
        initView()
        setContentView(setLayout())
    }

    protected abstract fun setFlag()
    protected abstract fun setLayout(): View
    protected abstract fun initView()

    open fun fullScreen() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    open fun animationSwitchActivity(input: Int, output: Int) {
        overridePendingTransition(
            input,
            output
        )
    }

    open fun addFragment(id: Int, fragment: Fragment, backstack: String? = null) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            ).add(id, fragment)
            .addToBackStack(backstack)
            .commit()
    }

    open fun createCustomToast(image: Int, message: String) {
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

    open fun openActivity(destinationClass: Class<*>) {
        startActivity(Intent(this@BaseActivity, destinationClass))
    }
}