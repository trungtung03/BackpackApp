package com.example.backpackapp.controller.adpterHome.posts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.backpackapp.R
import com.example.backpackapp.model.home.posts.Posts
import kotlinx.android.synthetic.main.item_rcv_post_home.view.*

class PostsAdapter(
    val context: Context,
    private var posts: ArrayList<Posts>,
    private val onClickJoin: () -> Unit,
    private val onClickItem: () -> Unit
) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindViewHolder(
            context: Context,
            posts: ArrayList<Posts>,
            position: Int
        ) {
            posts[position].name.also { itemView.name_item_post_home.text = it }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_rcv_post_home, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(context, posts, position)
        holder.itemView.btn_join_item_rcv_post_home.setOnClickListener { onClickJoin.invoke() }
        holder.itemView.setOnClickListener { onClickItem.invoke() }
        holder.itemView.image_btn_heart_item_post_home
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}