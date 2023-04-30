package com.example.backpackapp.model.profile

import android.annotation.SuppressLint
import com.example.backpackapp.view.profile.PreviousTripAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

data class PreviousTripModel(
    val photoPreviousTrip: String? = null,
    val countOverview: Int = 0,
    val vehicle: String? = null,
    val dateTime: String? = null,
    val fromCountry: String? = null,
    val toCountry: String? = null
) {
    private var user: FirebaseUser? = null
    private var mReference: DatabaseReference? = null

    fun displayTrip(
        mListPrevious: ArrayList<PreviousTripModel>,
        mPreviousAdapter: PreviousTripAdapter
    ) {
        user = Firebase.auth.currentUser
        mReference = FirebaseDatabase.getInstance().getReference("trip_profile/${user?.uid}")
        mReference?.child("previous")?.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mListPrevious.clear()
                for (potSnapshot in snapshot.children) {
                    val previousTrip = potSnapshot.getValue(this@PreviousTripModel::class.java)
                    if (previousTrip != null) {
                        mListPrevious.add(previousTrip)
                    }
                }
                mPreviousAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    fun uploadPreviousTrip(mListPrevious: ArrayList<PreviousTripModel>) {
        user = Firebase.auth.currentUser
        mReference = FirebaseDatabase.getInstance().getReference("trip_profile/${user?.uid}")
        mReference?.child("previous")?.setValue(mListPrevious)
    }
}