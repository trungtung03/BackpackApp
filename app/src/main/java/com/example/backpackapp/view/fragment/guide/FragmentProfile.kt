package com.example.backpackapp.view.fragment.guide

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.backpackapp.R
import com.example.backpackapp.model.profile.PreviousTripProfile
import com.example.backpackapp.model.profile.TravelPhotosProfile
import com.example.backpackapp.view.activity.inApp.Overview
import com.example.backpackapp.controller.adapterProfile.PreviousTripAdapter
import com.example.backpackapp.controller.adapterProfile.TravelPhotosAdapter
import com.example.backpackapp.ui.base.BaseFragment
import com.example.backpackapp.databinding.FragmentProfileOverviewBinding
import com.example.backpackapp.view.fragment.guide.signup.FragmentSignUp
import com.example.backpackapp.util.Parameters
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_profile_overview.*
import java.util.*


@Suppress("ControlFlowWithEmptyBody", "SENSELESS_COMPARISON", "DEPRECATION", "SameParameterValue")
class FragmentProfile : BaseFragment<FragmentProfileOverviewBinding>() {
    private lateinit var fragmentProfileOverviewBinding: FragmentProfileOverviewBinding

    private val listTravelPhoto = ArrayList<TravelPhotosProfile>()
    private val listPreviousTripProfile = ArrayList<PreviousTripProfile>()

    private var uri: Uri? = null

    private val mActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val intent = result.data
            if (intent == null) {
                return@registerForActivityResult
            } else {
                uri = intent.data
                val bitmap = MediaStore.Images.Media.getBitmap(
                    (activity as AppCompatActivity).contentResolver,
                    uri
                )
                fragmentProfileOverviewBinding.circleImageAvatarProfileOverview.setImageBitmap(
                    bitmap
                )
                updateProfile()
            }
        }
    }

    private fun updateProfile() {
        val user = Firebase.auth.currentUser ?: return
        if (user.photoUrl == null) {
            val profileUpdates = userProfileChangeRequest {
                displayName = FragmentSignUp.FULL_NAME.toString()
                photoUri = Uri.parse(uri.toString())
            }
            user.updateProfile(profileUpdates)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        createCustomToast(
                            R.drawable.success_blue,
                            Parameters.UPDATE_PROFILE_SUCCESS
                        )
                        Log.d(Parameters.TAG, Parameters.UPDATE_PROFILE_SUCCESS)
                        activity?.let {
                            Glide.with(it).load(user.photoUrl).error(R.drawable.avatar_default)
                                .into(circle_image_avatar_profile_overview)
                        }
                    } else {
                        Log.d(Parameters.TAG, task.toString())
                    }
                }
        }
    }

    override fun initView(view: View) {
        fragmentProfileOverviewBinding = FragmentProfileOverviewBinding.bind(view)
        addList()
        rcv_photo_from_previous_trip.layoutManager = GridLayoutManager(activity, 1)
        rcv_photo_from_previous_trip.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        activity?.let { TravelPhotosAdapter(it, listTravelPhoto) }
            .also { rcv_photo_from_previous_trip.adapter = it }

        rcv_trips_taken_in_past_months.layoutManager = GridLayoutManager(activity, 1)
        rcv_trips_taken_in_past_months.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        activity?.let { PreviousTripAdapter(it, listPreviousTripProfile) }
            .also { rcv_trips_taken_in_past_months.adapter = it }

        actionView()
    }

    override fun getBinding(): FragmentProfileOverviewBinding {
        fragmentProfileOverviewBinding = FragmentProfileOverviewBinding.inflate(layoutInflater)
        return fragmentProfileOverviewBinding
    }

    private fun addList() {

        listTravelPhoto.add(
            TravelPhotosProfile(
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/seth-doyle-151914-unsplash.png?alt=media&token=2487654c-28e3-4c5b-878a-819472d0bf8d",
                "BALI"
            )
        )
        listTravelPhoto.add(
            TravelPhotosProfile(
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/steve-roe-678030-unsplash.png?alt=media&token=06fdeb87-25da-49e7-b44b-c07c04be361c",
                "Japan"
            )
        )
        listTravelPhoto.add(
            TravelPhotosProfile(
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/alex-block-300193-unsplash.png?alt=media&token=4922ca57-fe09-4011-9eb0-1422f2499de9",
                "RUSSIA"
            )
        )

        listPreviousTripProfile.add(
            PreviousTripProfile(
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/kalen-emsley-100240-unsplash.png?alt=media&token=c404e37b-9e2e-4246-b661-27d643473d3b",
                7,
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/round-airplanemode_active-24px.png?alt=media&token=30e7a624-c87a-4ac6-bf12-4f567318c305",
                Calendar.getInstance().time,
                "US",
                "SA"
            )
        )
        listPreviousTripProfile.add(
            PreviousTripProfile(
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/guillaume-baudusseau-399124-unsplash.png?alt=media&token=3ed90cdd-1c26-4e1e-bf96-58ea057a9301",
                10,
                "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/round-airplanemode_active-24px.png?alt=media&token=30e7a624-c87a-4ac6-bf12-4f567318c305",
                Calendar.getInstance().time,
                "VN",
                "RU"
            )
        )
    }

    private fun actionView() {
        val overview: Overview = activity as Overview
        if (overview == null) {
            return
        } else {
            circle_image_avatar_profile_overview.setOnClickListener {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    return@setOnClickListener
                }
                if (activity?.checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    openGallery()
                } else {
                    val arrPermission = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    activity?.requestPermissions(arrPermission, Parameters.MY_REQUEST_CODE)
                }
            }
        }
        val user = Firebase.auth.currentUser ?: return
        activity?.let {
            Glide.with(it).load(user.photoUrl).error(R.drawable.avatar_default)
                .into(circle_image_avatar_profile_overview)
        }
        user.displayName.toString().also { tv_name_profile_overview.text = it }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Parameters.MY_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            } else {
                createCustomToast(
                    androidx.biometric.R.drawable.fingerprint_dialog_error,
                    Parameters.CHECK_PERMISSION
                )
            }
        }
    }

    private fun openGallery() {
        val intent = Intent()
        with(mActivityResultLauncher) {
            "image/*".also { intent.type = it }
            Intent.ACTION_GET_CONTENT.also { intent.action = it }
            launch(
                Intent.createChooser(
                    intent,
                    Parameters.TITLE_INTENT_RESULT_LAUNCHER
                )
            )
        }
    }
}