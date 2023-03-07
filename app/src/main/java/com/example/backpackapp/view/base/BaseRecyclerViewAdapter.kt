package com.example.backpackapp.view.base

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.backpackapp.model.music.AttributesMusic

abstract class BaseRecyclerViewAdapter<T : Any, VH : BaseViewHolder<T>>() :
    RecyclerView.Adapter<VH>() {

    interface OnClickItem {
        fun isClickItem(view: View, position: Int, isCheck: Boolean)
    }

    abstract fun setData(list: ArrayList<T>)

    open fun injectInto(context: Context, recyclerView: RecyclerView?) {
//        val layoutManager = LinearLayoutManager(context)
//        recyclerView?.isNestedScrollingEnabled = false
//        recyclerView?.isFocusable = false
//        recyclerView?.layoutManager = layoutManager
//        recyclerView?.adapter = this
        val decoration = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
        recyclerView?.addItemDecoration(decoration)
    }
}