package com.example.backpackapp.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle

abstract class BaseDialog(mContext: Context) : Dialog(mContext) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResourceId())
        setupViews()
        setupListeners()
    }

    protected abstract fun getLayoutResourceId():Int

    protected abstract fun setupViews()

    protected abstract fun setupListeners()
}