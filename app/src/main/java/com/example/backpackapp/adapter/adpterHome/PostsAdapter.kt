package com.example.backpackapp.adapter.adpterHome

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.backpackapp.R
import com.example.backpackapp.`object`.home.Post
import com.example.backpackapp.`object`.home.service.Service
import com.example.backpackapp.`object`.home.user.UserLikePosts
import com.example.backpackapp.adapter.adpterHome.adapterService.ServiceAdapter
import com.example.backpackapp.adapter.adpterHome.adapterUserLikePosts.UserLikePostsAdapter
import kotlinx.android.synthetic.main.item_rcv_post_home.view.*

class PostsAdapter(
    val context: Context,
    private val post: ArrayList<Post>,
    private val userLikePosts: ArrayList<UserLikePosts>,
    private val service: ArrayList<Service>
) :
    RecyclerView.Adapter<PostsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindViewHolder(
            context: Context,
            post: java.util.ArrayList<Post>,
            position: Int
        ) {
            post[position].name.also { itemView.name_item_post_home.text = it }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_rcv_post_home, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(context, post, position)
        actionView(holder.itemView)
    }

    override fun getItemCount(): Int {
        return post.size
    }

    fun actionView(itemView: View) {
        GridLayoutManager(context, 1).also {
            itemView.rcv_user_like_item_post_home.layoutManager = it
        }

        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false).also {
            itemView.rcv_user_like_item_post_home.layoutManager = it
        }

        UserLikePostsAdapter(
            context,
            userLikePosts
        ).also { itemView.rcv_user_like_item_post_home.adapter = it }

        GridLayoutManager(context, 1).also {
            itemView.rcv_service_item_rcv_post_home.layoutManager = it
        }

        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false).also {
            itemView.rcv_service_item_rcv_post_home.layoutManager = it
        }

        ServiceAdapter(context, service).also {
            itemView.rcv_service_item_rcv_post_home.adapter = it
        }
    }
}