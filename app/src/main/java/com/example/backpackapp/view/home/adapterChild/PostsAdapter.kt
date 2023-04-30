package com.example.backpackapp.view.home.adapterChild

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.backpackapp.R
import com.example.backpackapp.base.recyclerview.BaseRecyclerViewAdapter
import com.example.backpackapp.base.recyclerview.BaseViewHolder
import com.example.backpackapp.model.home.modelChild.PostsTripModel
import com.example.backpackapp.units.GA
import com.example.backpackapp.units.GA.COUNT_CLICK_VIDEO
import com.example.backpackapp.units.GA.POSITION_POST
import com.example.backpackapp.view.home.adapterChild.PostsAdapter.ViewHolder
import kotlinx.android.synthetic.main.item_rcv_post_home.view.*

@Suppress("DEPRECATION")
class PostsAdapter(
    private val mContext: Context,
    private val onClickJoin: () -> Unit,
    private val onClickItem: () -> Unit,
) : BaseRecyclerViewAdapter<PostsTripModel, ViewHolder>() {

    private var mPosts: ArrayList<PostsTripModel> = ArrayList()

    class ViewHolder(itemView: View) : BaseViewHolder<PostsTripModel>(itemView) {
        override fun bindViewHolder(context: Context?, data: PostsTripModel?, position: Int) {
            data?.name.also { itemView.name_item_post_home.text = it }
            if (context != null) {
                Glide.with(context).load(data?.avatar).error(R.drawable.avatar_default)
                    .into(itemView.avatar_item_rcv_post_home)
                Glide.with(context).load(data?.image).error(R.drawable.not_found)
                    .into(itemView.image_trip_item_rcv_post_home)

                Glide.with(context).load(data?.moreImage).error(R.drawable.not_found)
                    .into(itemView.image_more_item_rcv_posts_home)
            }
            itemView.name_country_item_rcv_post.text = data?.countryName
            itemView.tv_describe_item_rcv_post.text = data?.describe
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.item_rcv_post_home, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(mContext, mPosts[position], position)
        holder.itemView.btn_join_item_rcv_post.setOnClickListener { onClickJoin.invoke() }
        holder.itemView.setOnClickListener {
            POSITION_POST = position
            onClickItem.invoke()
        }
        holder.itemView.image_btn_heart_item_post
        holder.itemView.cv_video_view_item_rcv_post.setOnClickListener {
            clickVideo(
                position,
                holder.itemView,
                mPosts
            )
        }
        clickHeart(holder.itemView)
    }

    override fun getItemCount(): Int {
        return mPosts.size
    }

    private fun clickVideo(
        position: Int,
        itemView: View,
        posts: ArrayList<PostsTripModel>
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
            COUNT_CLICK_VIDEO++
            itemView.video_view_item_rcv_post.start()
            itemView.image_btn_pause_or_start_video.setImageResource(R.drawable.run_video_view_item_rcv_posts_home)
            if (COUNT_CLICK_VIDEO > 1) {
                itemView.video_view_item_rcv_post.pause()
                itemView.image_btn_pause_or_start_video.setImageResource(R.drawable.pause_video_view_item_rcv_posts_home)
                COUNT_CLICK_VIDEO = 0
            }
        }
    }

    private fun clickHeart(
        itemView: View
    ) {
        itemView.image_btn_heart_item_post.setOnClickListener {
            GA.COUNT_CLICK++
            itemView.image_btn_heart_item_post.setImageResource(
                R.drawable.heart_enable_item_rcv_post_home
            )
            if (GA.COUNT_CLICK > 1) {
                itemView.image_btn_heart_item_post.setImageResource(
                    R.drawable.heart_disable_item_rcv_posts_home
                )
                GA.COUNT_CLICK = 0
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setData(mList: ArrayList<PostsTripModel>) {
        mPosts = mList
        notifyDataSetChanged()
    }
}