package com.example.backpackapp.fragment.signUp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.backpackapp.R
import com.example.backpackapp.activity.splash.SplashActivity
import com.example.backpackapp.fragment.logIn.FragmentLogin
import com.example.backpackapp.parameter.Parameters
import kotlinx.android.synthetic.main.button_next.*
import kotlinx.android.synthetic.main.custom_toast.view.*
import kotlinx.android.synthetic.main.fragment_sign_up_02.*

@Suppress("DEPRECATION")
class FragmentSignUp02 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up_02, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionView()
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
                        .beginTransaction().replace(R.id.splash_activity, FragmentLogin())
                        .remove(FragmentSignUp02()).addToBackStack(null).commitAllowingStateLoss()
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