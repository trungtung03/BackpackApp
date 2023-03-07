package com.example.backpackapp.view

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import com.example.backpackapp.R
import dmax.dialog.SpotsDialog

open class CustomProgressDialog(context: Context) {
    private val progressDialog: AlertDialog = SpotsDialog(context, R.style.CustomProgressDialog)

    open fun onPreExecute() {
        progressDialog.show()
    }

    open fun onPostExecute() {
        progressDialog.dismiss()
        Log.d("dismiss", "true")
    }

    //status bar
}