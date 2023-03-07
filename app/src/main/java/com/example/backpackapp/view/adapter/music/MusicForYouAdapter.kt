package com.example.backpackapp.view.adapter.music

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.backpackapp.R
import com.example.backpackapp.model.music.LibraryMusic
import com.example.backpackapp.view.adapter.music.MusicForYouAdapter.ViewHolder
import com.example.backpackapp.view.base.BaseRecyclerViewAdapter
import com.example.backpackapp.view.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_rcv_library_music.view.*

class MusicForYouAdapter(val context: Context) :
    BaseRecyclerViewAdapter<LibraryMusic, ViewHolder>() {

    var mDataList: MutableList<LibraryMusic> = ArrayList()

    class ViewHolder(itemView: View) : BaseViewHolder<LibraryMusic>(itemView) {

        var onClick: OnClickItem? = null
        fun setClick(clickItem: OnClickItem) {
            onClick = clickItem
        }

        override fun bindViewHolder(context: Context?, data: LibraryMusic?, position: Int) {
            if (context != null) {
                Glide.with(context).load(data?.imageAvatar)
                    .error(R.drawable.avatar_default)
                    .into(itemView.avt_singer_rcv_library_music)
            }
            itemView.tv_name_singer_rcv_library_music.text = data?.nameSinger
            itemView.tv_time_music_rcv_library_music.text = data?.songTitle
            itemView.tv_time_music_rcv_library_music.text = data?.timeMusic
            itemView.setOnClickListener { v ->
                onClick?.isClickItem(v, adapterPosition, false)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_rcv_library_music, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(context, mDataList[position], position)
        holder.setClick(object : OnClickItem {
            override fun isClickItem(view: View, position: Int, isCheck: Boolean) {
                if (!isCheck) {
                    mDataList[position].nameSinger?.let { onClick(it, position) }
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

    private fun onClick(str: String, position: Int) {
//        Log.d("mcnvnmvc", mDataList[position].nameSinger.toString())
//        val database : FirebaseDatabase = FirebaseDatabase.getInstance()
//        val myRef: DatabaseReference = database.getReference("list_music")
//        myRef.setValue(mDataList)
//        myRef.addValueEventListener(object : ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                for (potSnapshot in snapshot.children) {
//
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//
//        })
    }
}