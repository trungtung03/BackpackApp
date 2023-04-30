package com.example.backpackapp.controller.profile

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
import com.example.backpackapp.model.profile.PreviousTripModel
import com.example.backpackapp.model.profile.TravelPhotosModel
import com.example.backpackapp.base.BaseFragment
import com.example.backpackapp.controller.Overview
import com.example.backpackapp.databinding.FragmentProfileOverviewBinding
import com.example.backpackapp.controller.register.FragmentRegister
import com.example.backpackapp.units.Parameters
import com.example.backpackapp.units.Parameters.AVATAR_USERS
import com.example.backpackapp.units.Parameters.NAME_USERS
import com.example.backpackapp.units.Parameters.USERS
import com.example.backpackapp.local.SharedPreferences
import com.example.backpackapp.units.GA.MY_REQUEST_CODE
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.fragment_profile_overview.*
import java.util.*
import com.example.backpackapp.units.Parameters.RATING_START
import com.example.backpackapp.units.Parameters.URL
import com.example.backpackapp.view.profile.PreviousTripAdapter
import com.example.backpackapp.view.profile.TravelPhotosAdapter
import com.google.firebase.database.*


@Suppress("ControlFlowWithEmptyBody", "SENSELESS_COMPARISON", "DEPRECATION", "SameParameterValue")
class FragmentProfile : BaseFragment<FragmentProfileOverviewBinding>() {
    companion object {
        fun newInstance() = FragmentProfile()
    }

    private lateinit var fragmentProfileOverviewBinding: FragmentProfileOverviewBinding

    private val mListTravelPhoto = ArrayList<TravelPhotosModel>()
    private val mListPreviousTrip = ArrayList<PreviousTripModel>()
    private val myRef = FirebaseDatabase.getInstance().getReference(USERS)
    private var mUri: Uri? = null
    private val mUser = Firebase.auth.currentUser
    private var mPreviousTripAdapter: PreviousTripAdapter? = null
    private var mTravelPhotosAdapter: TravelPhotosAdapter? = null
    private var mStorage: StorageReference? = null
    private val mPreviousTripModel = PreviousTripModel()
    private val mTravelPhotosModel = TravelPhotosModel()

    private val mActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val intent = result.data
            if (intent == null) {
                return@registerForActivityResult
            } else {
                mUri = intent.data
                val bitmap = MediaStore.Images.Media.getBitmap(
                    (activity as AppCompatActivity).contentResolver, mUri
                )
                fragmentProfileOverviewBinding.circleImageAvatarProfileOverview.setImageBitmap(
                    bitmap
                )
                updateProfile()
                mUri?.let { uploadImage(it) }
            }
        }
    }

    private fun rating() {
        if (mUser != null) {
            rating_start.setOnRatingBarChangeListener { _, fl, _ ->
                myRef.child(mUser.uid).child(RATING_START).setValue(fl)
            }

            myRef.child("${mUser.uid}/$RATING_START")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        rating_start.rating = snapshot.value.toString().toFloat()
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }

                })
        }
    }

    private fun uploadImage(privateUri: Uri) {
        mStorage = FirebaseStorage.getInstance().getReference("images/${mUser?.uid}")
        mStorage!!.putFile(privateUri).addOnCompleteListener {
            OnSuccessListener<UploadTask.TaskSnapshot> {

            }
        }

        mStorage!!.downloadUrl.addOnSuccessListener { uri ->
            myRef.child(mUser!!.uid).child(AVATAR_USERS).setValue(uri.toString())
        }
    }

    private fun updateProfile() {
        if (mUser?.photoUrl == null) {
            val profileUpdates = userProfileChangeRequest {
                displayName = FragmentRegister.FULL_NAME.toString()
                photoUri = Uri.parse(mUri.toString())
            }
            mUser?.updateProfile(profileUpdates)?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        createCustomToast(
                            R.drawable.success_blue, Parameters.UPDATE_PROFILE_SUCCESS
                        )
                        Log.d(Parameters.TAG, Parameters.UPDATE_PROFILE_SUCCESS)
                        activity?.let {
                            Glide.with(it).load(mUser.photoUrl).error(R.drawable.avatar_default)
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

        dataPreviousTrip()
        dataTravelTrip()
        actionView()
        rating()
    }

    private fun dataPreviousTrip() {
        mPreviousTripAdapter = activity?.let {
            PreviousTripAdapter(it, onClick = {
                mPreviousTripModel.uploadPreviousTrip(mListPreviousTrip)
            })
        }
        mPreviousTripAdapter?.let { mPreviousTripModel.displayTrip(mListPreviousTrip, it) }
        mPreviousTripAdapter?.setData(mListPreviousTrip)
        rcv_previous_trip.layoutManager = GridLayoutManager(activity, 1)
        rcv_previous_trip.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rcv_previous_trip.adapter = mPreviousTripAdapter
    }

    private fun dataTravelTrip() {
        mTravelPhotosAdapter = context?.let {
            TravelPhotosAdapter(it, onClick = {
                mTravelPhotosModel.uploadTravel(mListTravelPhoto)
            })
        }
        mTravelPhotosAdapter?.let { mTravelPhotosModel.displayTravel(mListTravelPhoto, it) }
        rcv_travel_photo.layoutManager = GridLayoutManager(activity, 1)
        rcv_travel_photo.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        mTravelPhotosAdapter?.setData(mListTravelPhoto)
        rcv_travel_photo.adapter = mTravelPhotosAdapter
    }

    override fun getBinding(): FragmentProfileOverviewBinding {
        fragmentProfileOverviewBinding = FragmentProfileOverviewBinding.inflate(layoutInflater)
        return fragmentProfileOverviewBinding
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
                    activity?.requestPermissions(arrPermission, MY_REQUEST_CODE)
                }
            }
        }

        myRef.child(mUser!!.uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                activity?.let {
                    Glide.with(it).load(
                        snapshot.child(AVATAR_USERS).value.toString()
                    ).error(R.drawable.avatar_default).into(circle_image_avatar_profile_overview)
                }
                snapshot.child(NAME_USERS).value.toString().also { tv_name_profile.text = it }
                val preferences = SharedPreferences(activity as Overview, URL)
                preferences.urlAvatar = snapshot.child(AVATAR_USERS).value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_REQUEST_CODE) {
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
                    intent, Parameters.TITLE_INTENT_RESULT_LAUNCHER
                )
            )
        }
    }
}