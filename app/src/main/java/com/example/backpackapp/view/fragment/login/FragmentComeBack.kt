package com.example.backpackapp.view.fragment.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.backpackapp.R
import com.example.backpackapp.ui.base.BaseFragment
import com.example.backpackapp.databinding.FragmentComebackBinding
import com.example.backpackapp.view.fragment.guide.FragmentGuide
import com.example.backpackapp.util.Parameters
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_comeback.*

@Suppress("SameParameterValue", "DEPRECATION")
class FragmentComeBack : BaseFragment<FragmentComebackBinding>() {
    private lateinit var fragmentComebackBinding: FragmentComebackBinding

    private lateinit var biometricPrompt: BiometricPrompt

    private fun dialogMetric(): BiometricPrompt.PromptInfo.Builder {
        return BiometricPrompt.PromptInfo.Builder().setTitle(Parameters.BIOMETRIC_TITLE)
            .setSubtitle(Parameters.BIOMETRIC_LOGIN_SUBTITLE)
    }

    override fun initView(view: View) {
        fragmentComebackBinding = FragmentComebackBinding.bind(view)
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

    override fun getBinding(): FragmentComebackBinding {
        fragmentComebackBinding = FragmentComebackBinding.inflate(layoutInflater)
        return fragmentComebackBinding
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
}