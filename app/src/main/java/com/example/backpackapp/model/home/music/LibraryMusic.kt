package com.example.backpackapp.model.home.music

import android.annotation.SuppressLint
import com.example.backpackapp.units.Parameters
import com.example.backpackapp.view.home.music.AllTracksAdapter
import com.example.backpackapp.view.home.music.MusicForYouAdapter
import com.google.firebase.database.*

data class LibraryMusic(
    val imageAvatar: String? = null,
    val nameSinger: String? = null,
    val songTitle: String? = null,
    val timeMusic: String? = null,
    val url: String? = null,
    val gifWaves: String? = null
) {
    private val firebaseDatabase: DatabaseReference =
        FirebaseDatabase.getInstance().reference.child(Parameters.LIST_MUSIC)
    private var mDtbMusic: DatabaseReference? = null

    fun getDataMusic(
        mListMusic: ArrayList<LibraryMusic>,
        mMusicFYouAdapter: MusicForYouAdapter,
        child: String
    ) {
        mDtbMusic = firebaseDatabase.child(child)
        mDtbMusic?.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                for (potSnapshot in snapshot.children) {
                    mListMusic.add(potSnapshot.getValue(LibraryMusic::class.java)!!)
                }
                mMusicFYouAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun getDataTracks(
        mListTracks: ArrayList<LibraryMusic>,
        mAllTrackAdapter: AllTracksAdapter,
        child: String
    ) {
        mDtbMusic = firebaseDatabase.child(child)
        mDtbMusic?.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mListTracks.clear()
                for (potSnapshot in snapshot.children) {
                    mListTracks.add(potSnapshot.getValue(LibraryMusic::class.java)!!)
                }
                mAllTrackAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}