package com.example.backpackapp.controller.register

import android.content.Intent
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.example.backpackapp.R
import com.example.backpackapp.controller.SplashActivity
import com.example.backpackapp.base.BaseFragment
import com.example.backpackapp.databinding.FragmentSignUp02Binding
import com.example.backpackapp.controller.login.FragmentLogin
import com.example.backpackapp.units.Parameters
import kotlinx.android.synthetic.main.button_next.*
import kotlinx.android.synthetic.main.custom_toast.view.*
import kotlinx.android.synthetic.main.fragment_sign_up_02.*

@Suppress("DEPRECATION")
class FragmentRegisterPhoneNumber : BaseFragment<FragmentSignUp02Binding>() {
    companion object {
        fun newInstance() = FragmentRegisterPhoneNumber()
        fun removeInstance() = FragmentRegisterPhoneNumber()
    }

    private lateinit var fragmentSignUp02Binding: FragmentSignUp02Binding

    override fun initView(view: View) {
        fragmentSignUp02Binding = FragmentSignUp02Binding.bind(view)
        actionView()
    }

    override fun getBinding(): FragmentSignUp02Binding {
        fragmentSignUp02Binding = FragmentSignUp02Binding.inflate(layoutInflater)
        return fragmentSignUp02Binding
    }

    private fun actionView() {
        country_picker_sign_up_02.registerCarrierNumberEditText(edt_phone_number_sign_up_02)

        round_back_sign_up_2.setOnClickListener {
            startActivity(
                Intent(
                    activity,
                    SplashActivity::class.java
                )
            )
        }
        btn_next_sign_up_02.setOnClickListener {
            if (TextUtils.isEmpty(edt_phone_number_sign_up_02.text.toString())) {
                createCustomToast(Parameters.EMPTY_PHONE_NUMBER, R.drawable.ic_baseline_warning_24)
            } else {
                if (country_picker_sign_up_02.isValidFullNumber) {
                    createCustomToast(Parameters.SUCCESSFUL_REGISTRATION, R.drawable.success_blue)
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.splash_activity, FragmentLogin.removeInstance())
                        .remove(removeInstance()).addToBackStack(null).commitAllowingStateLoss()
                } else {
                    createCustomToast(
                        Parameters.CHECK_ENTERED_NUMBER_PHONE,
                        androidx.biometric.R.drawable.fingerprint_dialog_error
                    )
                }
            }
        }
    }

    private fun createCustomToast(message: String, image: Int) {
        val toast = Toast(activity)
        toast.apply {
            val layout = layoutInflater.inflate(
                R.layout.custom_toast,
                activity?.findViewById(R.id.constraint_layout_custom_toast)
            )
            layout.img_warning_toast.setImageResource(image)
            layout.tv_message_custom_toast.text = message
            setGravity(Gravity.BOTTOM, 0, 100)
            duration = Toast.LENGTH_SHORT
            view = layout
            show()
        }
    }
}