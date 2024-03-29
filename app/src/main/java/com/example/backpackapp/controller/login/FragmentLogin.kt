@file:Suppress("DEPRECATION")

package com.example.backpackapp.controller.login

import android.content.Intent
import android.text.TextUtils
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import com.example.backpackapp.ApiService
import com.example.backpackapp.R
import com.example.backpackapp.controller.SplashActivity
import com.example.backpackapp.databinding.FragmentLogInBinding
import com.example.backpackapp.controller.guide.FragmentGuide
import com.example.backpackapp.controller.register.FragmentRegister
import com.example.backpackapp.units.Parameters
import com.example.backpackapp.units.Parameters.IS_EMPTY
import com.example.backpackapp.custom.CustomProgressDialog.Companion.createDialog
import com.example.backpackapp.custom.CustomProgressDialog.Companion.onPostExecute
import com.example.backpackapp.custom.CustomProgressDialog.Companion.onPreExecute
import com.example.backpackapp.base.BaseFragment
import com.example.backpackapp.local.SharedPreferences
import com.example.backpackapp.model.chat.MemberChatModel
import com.example.backpackapp.units.Parameters.UID
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_log_in.*
import java.util.*
import kotlin.collections.HashMap

@Suppress("ControlFlowWithEmptyBody", "DEPRECATION")
class FragmentLogin : BaseFragment<FragmentLogInBinding>(), View.OnClickListener {
    companion object {
        fun newInstance() = FragmentLogin()
        fun removeInstance() = FragmentLogin()

        fun insertInformation() {
            val mUser = Firebase.auth.currentUser
            ApiService.mApiService.mInsertUserUid(
                userUid = mUser?.uid.toString()
            ).subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<MemberChatModel> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                        Log.e("my_post", e.toString())
                    }

                    override fun onComplete() {
                        Log.e("my_post", "success")
                    }

                    override fun onNext(t: MemberChatModel) {
                    }

                })
        }
    }

    private lateinit var fragmentLogInBinding: FragmentLogInBinding
    private var isCheckClick: Boolean = false

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
        activity?.let { createDialog(it) }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.round_back_log_in -> startActivity(Intent(activity, SplashActivity::class.java))
            R.id.btn_login_to_sign_up_backpack -> replaceFragment(
                R.id.splash_activity,
                FragmentRegister.newInstance(),
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
                FragmentForgotPassword.newInstance(),
                null
            )
            R.id.btn_login_backpack -> {
                loginAccounts(
                    edt_email_backpack_login.text.toString(),
                    edt_password_backpack_login.text.toString()
                )
                updateDisplayName(FragmentRegister.FULL_NAME)
            }
            else -> return
        }
    }

    private fun updateDisplayName(fullName: String?) {
        val mUser = Firebase.auth.currentUser ?: return
        if (mUser.displayName == null) {
            val profileUpdates = userProfileChangeRequest {
                displayName = fullName
                updateUsers(displayName.toString())
            }
            mUser.updateProfile(profileUpdates).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(Parameters.TAG, Parameters.UPDATE_PROFILE_SUCCESS)
                }
            }
        } else {
            return
        }
    }

    private fun updateUsers(displayName: String) {
        val mUser = Firebase.auth.currentUser ?: return
        val myRef = FirebaseDatabase.getInstance().getReference("Users")
        val mapUser = HashMap<String, String>()
        mapUser["idMember"] = mUser.uid
        mapUser["avatarUser"] = IS_EMPTY
        mapUser["nameUser"] = displayName
        mapUser["message"] = IS_EMPTY
        mapUser["timeSendMessage"] = Calendar.getInstance().time.toString()
        mapUser["rating"] = 0.toString()
        myRef.child(mUser.uid).setValue(mapUser)
    }

    private fun loginAccounts(email: String, password: String) {
        val auth = FirebaseAuth.getInstance()
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            createCustomToast(
                R.drawable.warning_sign,
                Parameters.CHECK_ENTERED_EMAIL_PASSWORD_LOGIN
            )
        } else {
            onPreExecute()
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
                                .replace(R.id.splash_activity, FragmentGuide.newInstance())
                                .remove(removeInstance()).addToBackStack(null)
                                .commitAllowingStateLoss()
                            onPostExecute()
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
                            onPostExecute()
                        }
                    }
            }
        }
    }
}