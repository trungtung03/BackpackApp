package com.example.backpackapp.adapter.adpterHome.adapterUserLikePosts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.*
import com.example.backpackapp.R
import com.example.backpackapp.model.home.user.UserLikePosts
import kotlinx.android.synthetic.main.item_user_like_posts_rcv_posts_home.view.*

class UserLikePostsAdapter(
    val context: Context,
    private val userLikePosts: ArrayList<UserLikePosts>
) :
    RecyclerView.Adapter<UserLikePostsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindViewHolder(
            context: Context,
            userLikePosts: java.util.ArrayList<UserLikePosts>,
            position: Int
        ) {
            with(context).load(userLikePosts[position]).error(R.drawable.avatar_default).into(itemView.avatar_user_like_posts)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_user_like_posts_rcv_posts_home, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(context, userLikePosts, position)
    }

    override fun getItemCount(): Int {
        return userLikePosts.size
    }
}