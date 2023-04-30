package com.example.backpackapp.base.recyclerview

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<DATA>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bindViewHolder(context: Context?, data: DATA?, position: Int)
}