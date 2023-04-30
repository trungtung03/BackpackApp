package com.example.backpackapp.model.profile

import android.annotation.SuppressLint
import com.example.backpackapp.view.profile.TravelPhotosAdapter
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

data class TravelPhotosModel(
    var image: String? = null,
    var country: String? = null
) {
    private var user: FirebaseUser? = null
    private var mReference: DatabaseReference? = null

    fun displayTravel(
        mListTravel: ArrayList<TravelPhotosModel>,
        mTravelAdapter: TravelPhotosAdapter
    ) {
        user = Firebase.auth.currentUser
        mReference = FirebaseDatabase.getInstance().getReference("trip_profile/${user?.uid}")

        mReference?.child("travel")?.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mListTravel.clear()
                for (potSnapshot in snapshot.children) {
                    val travelPhoto = potSnapshot.getValue(this@TravelPhotosModel::class.java)
                    if (travelPhoto != null) {
                        mListTravel.add(travelPhoto)
                    }
                }
                mTravelAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    fun uploadTravel(mListTravel: ArrayList<TravelPhotosModel>) {
        user = Firebase.auth.currentUser
        mReference = FirebaseDatabase.getInstance().getReference("trip_profile/${user?.uid}")
        mReference?.child("travel")?.setValue(mListTravel)
    }
}