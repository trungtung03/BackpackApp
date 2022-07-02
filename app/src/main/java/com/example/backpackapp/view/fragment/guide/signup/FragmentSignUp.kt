@file:Suppress("DEPRECATION")

package com.example.backpackapp.view.fragment.guide.signup

import android.app.ProgressDialog
import android.content.Intent
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.widget.Toast
import com.example.backpackapp.R
import com.example.backpackapp.view.activity.splash.SplashActivity
import com.example.backpackapp.ui.base.BaseFragment
import com.example.backpackapp.databinding.FragmentSignUpBinding
import com.example.backpackapp.view.fragment.guide.login.FragmentLogin
import com.example.backpackapp.util.Parameters
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.custom_toast.view.*
import kotlinx.android.synthetic.main.fragment_sign_up.*
import java.util.regex.Pattern


@Suppress("ControlFlowWithEmptyBody", "UNUSED_EXPRESSION", "DEPRECATION")
class FragmentSignUp : BaseFragment<FragmentSignUpBinding>(), View.OnClickListener {
    private lateinit var fragmentSignUpBinding: FragmentSignUpBinding

    @Suppress("PropertyName")
    var isCheckClick: Boolean = false
    private lateinit var progressDialog: ProgressDialog

    companion object {
        var FULL_NAME: String? = null
    }

    override fun initView(view: View) {
        fragmentSignUpBinding = FragmentSignUpBinding.bind(view)
        actionView()
    }

    override fun getBinding(): FragmentSignUpBinding {
        fragmentSignUpBinding = FragmentSignUpBinding.inflate(layoutInflater)
        return fragmentSignUpBinding
    }

    private fun actionView() {
        round_back_sign_up.setOnClickListener(this)
        btn_sign_up_to_log_in_backpack.setOnClickListener(this)
        img_btn_hide_show_password.setOnClickListener(this)
        btn_sign_up_backpack.setOnClickListener(this)
        progressDialog = ProgressDialog(activity)

        edt_email_backpack.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (Pattern.matches(
                        "[a-zA-Z_0-9]{0,1000}" + "@" + "gmail" + "\\." + "com",
                        s.toString()
                    ) || Pattern.matches(
                        "[a-zA-Z_0-9]{0,1000}" + "\\." + "[a-zA-Z_0-9]{0,1000}" + "@" + "gmail" + "\\." + "com",
                        s.toString()
                    ) && !Pattern.matches("\t", s.toString())
                ) {
                    tv_waring_sign_up_email.visibility = GONE
                    Parameters.IS_EMPTY.also { tv_waring_sign_up_email.text = it }
                } else {
                    tv_waring_sign_up_email.visibility = View.VISIBLE
                    Parameters.CHECK_FORMAT_EMAIL_REGISTER.also {
                        tv_waring_sign_up_email.text = it
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        edt_password_backpack.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (Pattern.matches(
                        "[a-zA-Z_0-9]{6,1000}",
                        s.toString()
                    ) && !Pattern.matches("\t", s.toString())
                ) {
                    tv_waring_sign_up_password.visibility = GONE
                    "".also { tv_waring_sign_up_password.text = it }
                } else {
                    tv_waring_sign_up_password.visibility = View.VISIBLE
                    Parameters.CHECK_PASSWORD.also { tv_waring_sign_up_password.text = it }
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.round_back_sign_up -> startActivity(Intent(activity, SplashActivity::class.java))
            R.id.btn_sign_up_to_log_in_backpack -> requireActivity().supportFragmentManager
                .beginTransaction().replace(R.id.splash_activity, FragmentLogin())
                .remove(FragmentSignUp()).addToBackStack(null).commitAllowingStateLoss()
            R.id.img_btn_hide_show_password -> if (!isCheckClick) {
                img_btn_hide_show_password.setImageResource(R.drawable.hide_password)
                edt_password_backpack.transformationMethod = null
                isCheckClick = true
            } else {
                img_btn_hide_show_password.setImageResource(R.drawable.show_password)
                edt_password_backpack.transformationMethod = PasswordTransformationMethod()
                isCheckClick = false
            }
            R.id.btn_sign_up_backpack -> {
                checkInputInformation(
                    edt_name_backpack.text.toString(),
                    edt_email_backpack.text.toString(),
                    edt_password_backpack.text.toString()
                )
            }
            else -> return
        }
    }

    private fun registerAccount(email: String, password: String) {
        val auth = FirebaseAuth.getInstance()
        progressDialog.setTitle(Parameters.LOADING_DATA)
        progressDialog.setMessage(Parameters.WAIT_DATA_IS_LOADING)
        progressDialog.show()
        activity?.let {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(it) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(Parameters.TAG, Parameters.CREATE_USER_WITH_EMAIL_SUCCESS)
                        requireActivity().supportFragmentManager
                            .beginTransaction().replace(R.id.splash_activity, FragmentSignUp02())
                            .remove(FragmentSignUp()).addToBackStack(null).commitAllowingStateLoss()
                        progressDialog.dismiss()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(
                            Parameters.TAG,
                            Parameters.CREATE_USER_WITH_EMAIL_FAIL,
                            task.exception
                        )
                        createCustomToast(Parameters.REGISTER_FAIL, R.drawable.warning_sign)
                        progressDialog.dismiss()
                    }
                }
        }
    }

    private fun checkInputInformation(name: String, email: String, password: String) {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            createCustomToast(Parameters.CHECK_INPUT_INFORMATION, R.drawable.ic_baseline_warning_24)
        } else if (isEmailValid(edt_email_backpack.text.toString())) {
            registerAccount(email, password)
            FULL_NAME = name
        } else {
            createCustomToast(Parameters.CHECK_FORMAT_EMAIL_REGISTER, R.drawable.warning_sign)
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
            duration = Toast.LENGTH_SHORT
            view = layout
            show()
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}