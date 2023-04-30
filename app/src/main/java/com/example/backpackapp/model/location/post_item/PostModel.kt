package com.example.backpackapp.model.location.post_item

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.EditText
import com.example.backpackapp.ApiService
import com.example.backpackapp.units.Parameters
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.google.gson.annotations.SerializedName
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_add_post.*

class PostModel {
    private var mUser: FirebaseUser? = null
    private var mStorage: StorageReference? = null

    var mUserAvatar: String? = Parameters.IS_EMPTY
    var mUserName: String? = Parameters.IS_EMPTY

    fun uploadStorage(privateUri: Uri) {
        mUser = Firebase.auth.currentUser
        mStorage = FirebaseStorage.getInstance().getReference("image_post/${mUser?.uid}")
        mStorage!!.putFile(privateUri).addOnCompleteListener {
            OnSuccessListener<UploadTask.TaskSnapshot> {

            }
        }
    }

    fun uploadPosts(mEdt: EditText, postComplete: () -> Unit) {
        val mUser: FirebaseUser = Firebase.auth.currentUser ?: return
        val myRef: DatabaseReference =
            FirebaseDatabase.getInstance().getReference("Users/${mUser.uid}")
        val mStorage: StorageReference =
            FirebaseStorage.getInstance().getReference("image_post/${mUser.uid}")


        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                mUserAvatar = snapshot.child("avatarUser").value.toString()
                mUserName = snapshot.child("nameUser").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        mStorage.downloadUrl.addOnSuccessListener { uri ->
            if (mUserAvatar != Parameters.IS_EMPTY && mUserName != Parameters.IS_EMPTY) {
                ApiService.mApiService.mInsertPostTrip(
                    urlAvatar = mUserAvatar!!,
                    userName = mUserName!!,
                    urlImage = uri.toString(),
                    country = "Hà Nội",
                    describes = mEdt.text.toString(),
                    moreImage = 10,
                    urlVideo = "vxcvvxc"
                ).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<List<PostModel>> {
                        override fun onSubscribe(d: Disposable) {
                        }

                        override fun onError(e: Throwable) {
                            postComplete()
                        }

                        @SuppressLint("CommitTransaction")
                        override fun onComplete() {
                            postComplete()
                        }

                        override fun onNext(t: List<PostModel>) {
                        }

                    })
            }
        }
    }
}