@file:Suppress("DEPRECATION")

package com.example.backpackapp.fragment.logIn

import android.app.ProgressDialog
import android.content.Intent
import android.text.TextUtils
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.view.View.GONE
import com.example.backpackapp.R
import com.example.backpackapp.activity.splash.SplashActivity
import com.example.backpackapp.base.BaseFragment
import com.example.backpackapp.databinding.FragmentLogInBinding
import com.example.backpackapp.fragment.guide.FragmentGuide
import com.example.backpackapp.fragment.signUp.FragmentSignUp
import com.example.backpackapp.parameter.Parameters
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_log_in.*

@Suppress("ControlFlowWithEmptyBody", "DEPRECATION")
class FragmentLogin : BaseFragment<FragmentLogInBinding>(), View.OnClickListener {
    private lateinit var fragmentLogInBinding: FragmentLogInBinding

    private var isCheckClick: Boolean = false
    private lateinit var progressDialog: ProgressDialog

    override fun initView(view: View) {
        fragmentLogInBinding = FragmentLogInBinding.bind(view)
        actionView()
    }

    override fun getBinding(): FragmentLogInBinding {
        fragmentLogInBinding = FragmentLogInBinding.inflate(layoutInflater)
        return fragmentLogInBinding
    }

    private fun actionView() {
        round_back_log_in.setOnClickListener(this)
        btn_login_to_sign_up_backpack.setOnClickListener(this)
        img_btn_hide_show_password_log_in.setOnClickListener(this)
        tv_forgot_password_backpack_log_in.setOnClickListener(this)
        btn_login_backpack.setOnClickListener(this)
        activity?.let { progressDialog = ProgressDialog(it) }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.round_back_log_in -> startActivity(Intent(activity, SplashActivity::class.java))
            R.id.btn_login_to_sign_up_backpack -> replaceFragment(
                R.id.splash_activity,
                FragmentSignUp(),
                null
            )
            R.id.img_btn_hide_show_password_log_in -> if (!isCheckClick) {
                img_btn_hide_show_password_log_in.setImageResource(R.drawable.hide_password)
                edt_password_backpack_login.transformationMethod = null
                isCheckClick = true
            } else {
                img_btn_hide_show_password_log_in.setImageResource(R.drawable.show_password)
                edt_password_backpack_login.transformationMethod = PasswordTransformationMethod()
                isCheckClick = false
            }
            R.id.tv_forgot_password_backpack_log_in -> replaceFragment(
                R.id.splash_activity,
                FragmentForgotPassword(),
                null
            )
            R.id.btn_login_backpack -> {
                loginAccounts(
                    edt_email_backpack_login.text.toString(),
                    edt_password_backpack_login.text.toString()
                )
                updateDisplayName(FragmentSignUp.FULL_NAME)
            }
            else -> return
        }
    }

    private fun updateDisplayName(fullName: String?) {
        val user = Firebase.auth.currentUser
        if (user?.displayName == null) {
            val profileUpdates = userProfileChangeRequest {
                displayName = fullName
            }
            user?.updateProfile(profileUpdates)?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(Parameters.TAG, Parameters.UPDATE_PROFILE_SUCCESS)
                }
            }
        } else {
            return
        }
    }

    private fun loginAccounts(email: String, password: String) {
        val auth = FirebaseAuth.getInstance()
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            createCustomToast(
                R.drawable.warning_sign,
                Parameters.CHECK_ENTERED_EMAIL_PASSWORD_LOGIN
            )
        } else {
            progressDialog.setTitle(Parameters.LOADING_DATA)
            progressDialog.setMessage(Parameters.WAIT_DATA_IS_LOADING)
            progressDialog.show()
            activity?.let {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(it) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(
                                Parameters.TAG,
                                Parameters.SIGN_IN_WITH_EMAIL_SUCCESS
                            )
                            createCustomToast(
                                R.drawable.success_blue,
                                Parameters.CHECK_LOGIN_SUCCESS
                            )
                            img_warning_log_in.visibility = GONE
                            requireActivity().supportFragmentManager.beginTransaction()
                                .replace(R.id.splash_activity, FragmentGuide())
                                .remove(FragmentLogin()).addToBackStack(null)
                                .commitAllowingStateLoss()
                            progressDialog.dismiss()
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(
                                Parameters.TAG,
                                Parameters.SIGN_IN_WITH_EMAIL_FAIL,
                                task.exception
                            )
                            createCustomToast(
                                R.drawable.ic_baseline_warning_24,
                                Parameters.CHECK_LOGIN_FAIl
                            )
                            img_warning_log_in.visibility = View.VISIBLE
                            progressDialog.dismiss()
                        }
                    }
            }
        }
    }
}