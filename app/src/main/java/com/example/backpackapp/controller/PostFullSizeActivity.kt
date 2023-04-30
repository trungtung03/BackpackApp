package com.example.backpackapp.controller

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.example.backpackapp.R
import com.example.backpackapp.base.BaseActivity
import com.example.backpackapp.databinding.ActivityPostFullSizeBinding
import com.example.backpackapp.units.GA
import dev.skomlach.biometric.compat.R.drawable.fingerprint_error
import kotlinx.android.synthetic.main.activity_post_full_size.*

class PostFullSizeActivity : BaseActivity() {
    private lateinit var binding: ActivityPostFullSizeBinding
    override fun initView() {
        binding = ActivityPostFullSizeBinding.inflate(layoutInflater)
        actionView()
    }

    override fun setLayout(): View = binding.root

    override fun setFlag(): Unit = fullScreen()

    @SuppressLint("CheckResult")
    private fun actionView() {
        val mDataUser = intent.extras ?: return
        Glide.with(this@PostFullSizeActivity).load(mDataUser.getString("image"))
            .error(fingerprint_error).into(binding.imagePostFullSize)
        Glide.with(this@PostFullSizeActivity).load(mDataUser.getString("avatar"))
            .error(R.drawable.avatar_default).into(binding.imageAvatarPostFullSize)
        binding.tvNameAccountPostFullSize.text = mDataUser.getString("name")
        binding.tvDescribePostFullSize.text = mDataUser.getString("describe")

        binding.roundBackPostFullSize.setOnClickListener {
            openActivity(Overview::class.java)
            overridePendingTransition(R.anim.slide_in_left_finish, R.anim.slide_in_right_finish)
            finish()
        }

        binding.imageBtnHeartPostFullSize.setOnClickListener {
            GA.COUNT_CLICK++
            binding.imageBtnHeartPostFullSize.setImageResource(
                R.drawable.heart_enable_item_rcv_post_home
            )
            if (GA.COUNT_CLICK > 1) {
                binding.imageBtnHeartPostFullSize.setImageResource(
                    R.drawable.heart_disable_item_rcv_posts_home
                )
                GA.COUNT_CLICK = 0
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        overridePendingTransition(R.anim.slide_in_left_finish, R.anim.slide_in_right_finish)
    }
}