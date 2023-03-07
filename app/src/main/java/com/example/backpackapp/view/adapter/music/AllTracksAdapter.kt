package com.example.backpackapp.view.adapter.music

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.backpackapp.R
import com.example.backpackapp.model.music.LibraryMusic
import com.example.backpackapp.util.GA.POSITION_SONGS
import com.example.backpackapp.view.base.BaseRecyclerViewAdapter
import com.example.backpackapp.view.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_rcv_all_track.view.*
import pl.droidsonroids.gif.GifImageView

class AllTracksAdapter(
    val context: Context,
    val onClickItem: () -> Unit
) : BaseRecyclerViewAdapter<LibraryMusic, AllTracksAdapter.ViewHolder>() {

    private var mDataList: MutableList<LibraryMusic> = ArrayList()

    class ViewHolder(val itemView: View) : BaseViewHolder<LibraryMusic>(itemView) {
        var gifWaves: GifImageView? = null
        private var onClick: OnClickItem? = null

        fun setClick(clickItem: OnClickItem) {
            onClick = clickItem
        }

        override fun bindViewHolder(context: Context?, data: LibraryMusic?, position: Int) {
            context?.let {
                Glide.with(it).load(data?.imageAvatar)
                    .error(R.drawable.avatar_default)
                    .into(itemView.avt_singer_rcv_all_track)
            }
            itemView.tv_name_singer_rcv_all_track.text = data?.nameSinger
            itemView.tv_title_song_rcv_all_tracks.text = data?.songTitle
            itemView.tv_time_music_rcv_all_track.text = data?.timeMusic
            gifWaves = itemView.findViewById(R.id.gif_music_waves)
            itemView.setOnClickListener { v ->
                onClick?.isClickItem(v, adapterPosition, false)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_rcv_all_track, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.bindViewHolder(context, mDataList[position], position)
        holder.setClick(object : OnClickItem {
            override fun isClickItem(view: View, position: Int, isCheck: Boolean) {
                if (!isCheck) {
                    POSITION_SONGS = position
                    context.let {
                        Glide.with(it).load(mDataList[position].gifWaves).into(holder.gifWaves!!)
                    }
                    onClickItem.invoke()
                }
            }
        })
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setData(list: ArrayList<LibraryMusic>) {
        mDataList = list
        notifyDataSetChanged()
    }
}