package com.example.backpackapp.view.home.adapterChild

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.*
import com.example.backpackapp.R
import com.example.backpackapp.model.home.modelChild.UserLikePostsModel
import kotlinx.android.synthetic.main.item_user_like_posts_rcv_posts_home.view.*

class UserLikePostsAdapter(
    val context: Context,
    private val userLikePostModels: ArrayList<UserLikePostsModel>
) :
    RecyclerView.Adapter<UserLikePostsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindViewHolder(
            context: Context,
            userLikePostModels: java.util.ArrayList<UserLikePostsModel>,
            position: Int
        ) {
            with(context).load(userLikePostModels[position]).error(R.drawable.avatar_default).into(itemView.avatar_user_like_posts)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_user_like_posts_rcv_posts_home, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(context, userLikePostModels, position)
    }

    override fun getItemCount(): Int {
        return userLikePostModels.size
    }
}