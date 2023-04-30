package com.example.backpackapp.base.recyclerview

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T : Any, VH : BaseViewHolder<T>>() :
    RecyclerView.Adapter<VH>() {

    interface OnClickItem {
        fun isClickItem(view: View, position: Int, isCheck: Boolean)
    }

    abstract fun setData(mList: ArrayList<T>)

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