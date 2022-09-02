package com.example.backpackapp.controller.adpterHome.posts

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.backpackapp.R
import com.example.backpackapp.model.home.posts.Posts
import com.example.backpackapp.util.GA
import kotlinx.android.synthetic.main.item_rcv_post_home.view.*

@Suppress("DEPRECATION")
class PostsAdapter(
    val context: Context,
    private var posts: ArrayList<Posts>,
    private val onClickJoin: () -> Unit,
    private val onClickItem: () -> Unit,

) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindViewHolder(
            context: Context,
            posts: ArrayList<Posts>,
            position: Int
        ) {
            posts[position].name.also { itemView.name_item_post_home.text = it }
            Glide.with(context).load(posts[position].avatar).error(R.drawable.avatar_default)
                .into(itemView.avatar_item_rcv_post_home)
            Glide.with(context).load(posts[position].image).error(R.drawable.not_found)
                .into(itemView.image_trip_item_rcv_post_home)
            itemView.name_country_item_rcv_post_home.text = posts[position].countryName
            itemView.tv_describe_item_rcv_post_home.text = posts[position].describe
            Glide.with(context).load(posts[position].moreImage).error(R.drawable.not_found)
                .into(itemView.image_more_item_rcv_posts_home)
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
        holder.itemView.cv_video_view_item_rcv_post.setOnClickListener { clickVideo(position, holder.itemView, posts) }
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    private fun clickVideo(
        position: Int,
        itemView: View,
        posts: ArrayList<Posts>
    ) {
        if (posts[position].videoTrip != null) {
            itemView.video_view_item_rcv_post.setVideoURI(Uri.parse(posts[position].videoTrip))
            itemView.video_view_item_rcv_post.setOnPreparedListener {
                Handler().postDelayed({
                    itemView.image_btn_pause_or_start_video.setImageResource(
                        R.drawable.pause_video_view_item_rcv_posts_home
                    )
                }, itemView.video_view_item_rcv_post.duration.toLong())
            }
        } else itemView.image_btn_pause_or_start_video.setImageResource(R.drawable.pause_video_view_item_rcv_posts_home)

        itemView.cv_video_view_item_rcv_post.setOnClickListener {
            GA.COUNT_CLICK_VIDEO++
            itemView.video_view_item_rcv_post.start()
            itemView.image_btn_pause_or_start_video.setImageResource(R.drawable.run_video_view_item_rcv_posts_home)
            if (GA.COUNT_CLICK_VIDEO > 1) {
                itemView.video_view_item_rcv_post.pause()
                itemView.image_btn_pause_or_start_video.setImageResource(R.drawable.pause_video_view_item_rcv_posts_home)
                GA.COUNT_CLICK_VIDEO = 0
            }
        }
    }
}