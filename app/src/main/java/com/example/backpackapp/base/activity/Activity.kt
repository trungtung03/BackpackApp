package com.example.backpackapp.base.activity

import android.view.Window
import android.view.WindowManager

@Suppress("DEPRECATION")
class Activity {
    companion object {
        fun fullScreen(window: Window) {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }
}