package com.example.backpackapp.fragment.logIn

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.backpackapp.R
import com.example.backpackapp.fragment.guide.FragmentGuide
import com.example.backpackapp.parameter.Parameters
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.custom_toast.view.*
import kotlinx.android.synthetic.main.fragment_comeback.*

@Suppress("SameParameterValue", "DEPRECATION")
class FragmentComeBack : Fragment() {

    private lateinit var biometricPrompt: BiometricPrompt

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comeback, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkBioMetricSupport()
        activity?.let {
            val executor = ContextCompat.getMainExecutor(it)
            biometricPrompt = BiometricPrompt(
                it,
                executor,
                object : BiometricPrompt.AuthenticationCallback() {

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        createCustomToast(R.drawable.success_blue, Parameters.CHECK_LOGIN_SUCCESS)
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.splash_activity, FragmentGuide())
                            .remove(FragmentLogin()).addToBackStack(null).commitAllowingStateLoss()
                    }

                })
        }
        img_fingerprint_comeback_backpack.setOnClickListener {
            val prompt: BiometricPrompt.PromptInfo.Builder = dialogMetric()
            prompt.setNegativeButtonText(Parameters.TEXT_NEGATIVE_BUTTON)
            biometricPrompt.authenticate(prompt.build())
        }
        tv_not_you_comeback.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction().replace(R.id.splash_activity, FragmentLogin())
                .remove(FragmentComeBack()).addToBackStack(null).commitAllowingStateLoss()
        }

        val user = Firebase.auth.currentUser ?: return
        activity?.let {
            Glide.with(it).load(user.photoUrl).error(R.drawable.avatar_default)
                .into(img_avt_comeback_backpack)
        }
    }

    private fun dialogMetric(): BiometricPrompt.PromptInfo.Builder {
        return BiometricPrompt.PromptInfo.Builder().setTitle(Parameters.BIOMETRIC_TITLE)
            .setSubtitle(Parameters.BIOMETRIC_LOGIN_SUBTITLE)
    }

    @SuppressLint("SwitchIntDef")
    private fun checkBioMetricSupport() {
        activity?.let {
            val biometricManager = BiometricManager.from(it)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.BIOMETRIC_WEAK)) {
                    BiometricManager.BIOMETRIC_SUCCESS -> {
                        Log.d(Parameters.TAG_COMEBACK, Parameters.BIOMETRIC_SUCCESS)
                        enableButton(true)
                    }
                    BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                        Log.e(Parameters.TAG_COMEBACK, Parameters.BIOMETRIC_ERROR_NO_HARDWARE)
                        enableButton(false)
                    }
                    BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                        Log.e(Parameters.TAG_COMEBACK, Parameters.BIOMETRIC_ERROR_HW_UNAVAILABLE)
                        enableButton(false)
                    }
                    BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                        // Prompts the user to create credentials that your app accepts.
                        enableButton(boolean = false, error = true)
                    }
                }
            }
        }
    }

    private fun enableButton(boolean: Boolean) {
        img_fingerprint_comeback_backpack.isEnabled = boolean
    }

    private fun enableButton(boolean: Boolean, error: Boolean) {
        enableButton(boolean)
        if (!error) return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val intent = Intent(Settings.ACTION_BIOMETRIC_ENROLL)
            intent.putExtra(
                Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.BIOMETRIC_WEAK
            )
            startActivity(intent)
        } else {
            return
        }
    }

    fun createCustomToast(image: Int, message: String) {
        val toast = Toast(activity)
        toast.apply {
            val layout = layoutInflater.inflate(
                R.layout.custom_toast,
                activity?.findViewById(R.id.constraint_layout_custom_toast)
            )
            layout.img_warning_toast.setImageResource(image)
            layout.tv_message_custom_toast.text = message
            duration = Toast.LENGTH_SHORT
            view = layout
            show()
        }
    }
}