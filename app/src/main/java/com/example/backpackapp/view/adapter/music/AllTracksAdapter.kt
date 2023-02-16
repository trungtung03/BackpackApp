package com.example.backpackapp.view.adapter.music

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.backpackapp.R
import com.example.backpackapp.model.music.LibraryMusic
import kotlinx.android.synthetic.main.item_rcv_all_track.view.*

class AllTracksAdapter(
    val context: Context,
    private val listTracks: ArrayList<LibraryMusic>
) :
    RecyclerView.Adapter<AllTracksAdapter.ViewHolder>() {
    class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindViewHolder(
            context: Context,
            listTracks: ArrayList<LibraryMusic>,
            position: Int
        ) {
            Glide.with(context).load(listTracks[position].imageAvatar)
                .error(R.drawable.avatar_default)
                .into(itemView.avt_singer_rcv_all_track)
            itemView.tv_name_singer_rcv_all_track.text = listTracks[position].nameSinger
            itemView.tv_title_song_rcv_all_tracks.text = listTracks[position].songTitle
            itemView.tv_time_music_rcv_all_track.text = listTracks[position].timeMusic
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_rcv_all_track, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(context, listTracks, position)
    }

    override fun getItemCount(): Int {
        return listTracks.size
    }
}