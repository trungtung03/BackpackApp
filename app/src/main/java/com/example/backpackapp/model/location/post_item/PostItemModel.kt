package com.example.backpackapp.model.location.post_item

import android.annotation.SuppressLint
import android.content.res.Resources
import androidx.fragment.app.Fragment
import com.example.backpackapp.R
import com.example.backpackapp.view.location.post_item.PostItemAdapter

data class PostItemModel(
    val image: Int? = 0, val nameImage: String? = null
) {
    private val arrName: Array<Int> = arrayOf(
        R.string.add_image,
        R.string.tag_user,
        R.string.camera,
        R.string.sticker,
        R.string.camera_live
    )

    private val arrImage: Array<Int> = arrayOf(
        R.drawable.add_image,
        R.drawable.tag_user,
        R.drawable.camera,
        R.drawable.sticker,
        R.drawable.camera_live
    )

    @SuppressLint("NotifyDataSetChanged")
    fun addListData(
        mList: ArrayList<PostItemModel>,
        mAdapter: PostItemAdapter,
        resource: Resources
    ) {
        for (i in arrName.indices) {
            mList.add(
                PostItemModel(
                    image = arrImage[i], nameImage = resource.getString(arrName[i])
                )
            )
        }
        mAdapter.notifyDataSetChanged()
    }
}