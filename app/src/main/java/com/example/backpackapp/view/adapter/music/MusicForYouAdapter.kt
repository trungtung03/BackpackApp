package com.example.backpackapp.view.adapter.music

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.backpackapp.R
import com.example.backpackapp.model.music.LibraryMusic
import kotlinx.android.synthetic.main.item_rcv_library_music.view.*

class MusicForYouAdapter(val context: Context, private val listMusic: ArrayList<LibraryMusic>) :
    RecyclerView.Adapter<MusicForYouAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindViewHolder(
            context: Context,
            libraryMusic: ArrayList<LibraryMusic>,
            position: Int
        ) {
            Glide.with(context).load(libraryMusic[position].imageAvatar)
                .error(R.drawable.avatar_default)
                .into(itemView.avt_singer_rcv_library_music)
            itemView.tv_name_singer_rcv_library_music.text = libraryMusic[position].nameSinger
            itemView.tv_time_music_rcv_library_music.text = libraryMusic[position].songTitle
            itemView.tv_time_music_rcv_library_music.text = libraryMusic[position].timeMusic
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_rcv_library_music, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(context, listMusic, position)
    }

    override fun getItemCount(): Int {
        return listMusic.size
    }
}