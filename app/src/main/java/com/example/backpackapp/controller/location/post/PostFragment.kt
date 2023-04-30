package com.example.backpackapp.controller.location.post

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.backpackapp.R
import com.example.backpackapp.base.BaseFragment
import com.example.backpackapp.controller.Overview
import com.example.backpackapp.controller.location.FragmentLocation
import com.example.backpackapp.databinding.FragmentAddPostBinding
import com.example.backpackapp.model.location.post_item.PostItemModel
import com.example.backpackapp.model.location.post_item.PostModel
import com.example.backpackapp.units.GA.MY_REQUEST_CODE
import com.example.backpackapp.units.GA.POSITION_POST
import com.example.backpackapp.units.Parameters
import com.example.backpackapp.units.Parameters.IS_EMPTY
import com.example.backpackapp.units.Parameters.PERMISSION_FAIL
import com.example.backpackapp.view.location.post_item.PostItemAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_add_post.*


@Suppress("DEPRECATION")
class PostFragment : BaseFragment<FragmentAddPostBinding>(), View.OnClickListener {
    companion object {
        fun newInstance() = PostFragment()
    }

    private lateinit var fragmentAddPostBinding: FragmentAddPostBinding
    private var mListItem = ArrayList<PostItemModel>()
    private var mItemAdapter: PostItemAdapter? = null
    private val mItemModel = PostItemModel()
    private var mUri: Uri? = null
    private val mPostModel = PostModel()

    private val mActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val intent = result.data
            if (intent == null) {
                return@registerForActivityResult
            } else {
                mUri = intent.data
                val bitmap = MediaStore.Images.Media.getBitmap(
                    (activity as AppCompatActivity).contentResolver,
                    mUri
                )
                fragmentAddPostBinding.imgPostStatusDialog.setImageBitmap(
                    bitmap
                )
                fragmentAddPostBinding.tvContinueDialog.setTextColor(resources.getColor(R.color.blue))
                mUri?.let { mPostModel.uploadStorage(it) }
            }
        }
    }

    override fun initView(view: View) {
        fragmentAddPostBinding = FragmentAddPostBinding.bind(view)
        actionView()
    }

    private fun actionView() {
        displayItem()
        edt_status_dialog.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString() == IS_EMPTY) {
                    fragmentAddPostBinding.tvContinueDialog.setTextColor(resources.getColor(R.color.text_not_you_color))
                } else {
                    fragmentAddPostBinding.tvContinueDialog.setTextColor(resources.getColor(R.color.blue))
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        back_dialog_post.setOnClickListener(this)
        fragmentAddPostBinding.tvContinueDialog.setOnClickListener(this@PostFragment)
    }


    private fun eventContinue() {
        mPostModel.uploadPosts(edt_status_dialog) { postComplete() }
    }

    private fun postComplete() {
        hideSoftInput(edt_status_dialog)
        Overview.roundBack()
        replaceFragment(R.id.fragment_post, FragmentLocation.newInstance(), null)
        removeFragment(newInstance())
        onDestroyView()
        createCustomToast(R.drawable.success_blue, "Post successful, please reload the page")
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            } else {
                createCustomToast(
                    androidx.biometric.R.drawable.fingerprint_dialog_error,
                    PERMISSION_FAIL
                )
            }
        }
    }

    private fun openGallery() {
        val intent = Intent()
        with(mActivityResultLauncher) {
            "image/*".also { intent.type = it }
            Intent.ACTION_GET_CONTENT.also { intent.action = it }
            launch(
                Intent.createChooser(
                    intent,
                    Parameters.TITLE_INTENT_RESULT_LAUNCHER
                )
            )
        }
    }

    private fun displayItem() {
        mItemAdapter = context?.let { PostItemAdapter(it, onClickItem = { onClickItem() }) }
        mItemAdapter?.let {
            activity?.let { it1 ->
                mItemModel.addListData(
                    mListItem,
                    it,
                    it1.resources
                )
            }
        }
        mItemAdapter?.setData(mListItem)
        rcv_item_add_post.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rcv_item_add_post.layoutManager = GridLayoutManager(activity, 1)

        rcv_item_add_post.adapter = mItemAdapter
    }

    override fun getBinding(): FragmentAddPostBinding {
        fragmentAddPostBinding = FragmentAddPostBinding.inflate(layoutInflater)
        return fragmentAddPostBinding
    }

    private fun onClickItem() {
        when (POSITION_POST) {
            0 -> {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    return
                }
                if (activity?.checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    openGallery()
                } else {
                    val arrPermission = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    activity?.requestPermissions(arrPermission, MY_REQUEST_CODE)
                }
            }
            1 -> {}
            2 -> {}
            3 -> {}
            4 -> {}
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.back_dialog_post -> {
                replaceFragment(R.id.fragment_post, FragmentLocation.newInstance(), null)
                removeFragment(newInstance())
                onDestroyView()
            }
            R.id.tv_continue_dialog -> {
                eventContinue()
            }
        }
    }

    private fun hideSoftInput(view: View) {
        val inputMethodManager =
            (activity as AppCompatActivity).getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onPause() {
        super.onPause()
        removeFragment(newInstance())
    }
}