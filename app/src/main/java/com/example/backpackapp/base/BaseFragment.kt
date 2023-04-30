package com.example.backpackapp.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.backpackapp.R
import kotlinx.android.synthetic.main.custom_toast.view.*

@Suppress("DEPRECATION")
abstract class BaseFragment<T : ViewBinding> : Fragment() {
    private var binding: T? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getBinding()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    protected abstract fun initView(view: View)
    abstract fun getBinding(): T

    open fun openActivityFullSize(destinationClass: Class<*>?) {
        startActivity(Intent(activity, destinationClass))
    }

    open fun animationSwitchActivity(input: Int, output: Int) {
        (activity as AppCompatActivity).overridePendingTransition(
            input,
            output
        )
    }

    open fun createCustomToast(image: Int, message: String) {
        val toast = Toast(activity)
        toast.apply {
            val layout = layoutInflater.inflate(
                R.layout.custom_toast,
                activity?.findViewById(R.id.constraint_layout_custom_toast)
            )
            layout.img_warning_toast.setImageResource(image)
            layout.tv_message_custom_toast.text = message
            duration = Toast.LENGTH_SHORT
            view = layout
            show()
        }
    }

    open fun replaceFragment(id: Int, fragment: Fragment, backstack: String? = null) {
        (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            ).replace(id, fragment)
            .addToBackStack(backstack)
            .commit()
    }

    open fun removeFragment(fragment: Fragment) {
        (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
            .remove(fragment).commit()
    }

    open fun reloadFragment(fragment: Fragment) {
        (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
            .detach(fragment)
            .attach(fragment)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.root
    }
}
