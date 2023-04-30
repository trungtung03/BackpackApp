package com.example.backpackapp.custom

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import com.example.backpackapp.R
import dmax.dialog.SpotsDialog

class CustomProgressDialog {
    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var mProgressDialog: AlertDialog
        fun createDialog(context: Context) {
            mProgressDialog = SpotsDialog(context, R.style.CustomProgressDialog)
        }

        fun onPreExecute() {
            mProgressDialog.show()
        }

        fun onPostExecute() {
            mProgressDialog.dismiss()
        }
    }
}