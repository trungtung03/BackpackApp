package com.example.backpackapp.model.location

import com.example.backpackapp.view.location.InvitesAdapter
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

data class LocationTripModel(
    val avatar: String? = null,
    val photoTrip: String? = null,
    val countOverview: Int? = null,
    val vehicle: String? = null,
    val dateTime: String? = null,
    val fromCountry: String? = null,
    val toCountry: String? = null
) {
    private var mUser: FirebaseUser? = null
    var myRef: DatabaseReference? = null

    fun <T> displayTrip(mListData: ArrayList<T>, mAdapter: InvitesAdapter) {
        mUser = Firebase.auth.currentUser ?: return
        myRef = FirebaseDatabase.getInstance().getReference("")
        myRef?.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun <T> uploadTrip(mListData: ArrayList<T>) {
        mUser = Firebase.auth.currentUser ?: return
        myRef = FirebaseDatabase.getInstance().getReference("trip")
    }
}
