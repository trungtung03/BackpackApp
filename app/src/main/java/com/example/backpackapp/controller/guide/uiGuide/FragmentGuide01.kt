package com.example.backpackapp.controller.guide.uiGuide

import android.view.View
import com.example.backpackapp.base.BaseFragment
import com.example.backpackapp.databinding.FragmentGuide01Binding

class FragmentGuide01 : BaseFragment<FragmentGuide01Binding>() {
    companion object {
        fun newInstance() = FragmentGuide01()
    }

    private lateinit var fragmentGuide01Binding: FragmentGuide01Binding

    override fun initView(view: View) {
        fragmentGuide01Binding = FragmentGuide01Binding.bind(view)
    }

    override fun getBinding(): FragmentGuide01Binding {
        fragmentGuide01Binding = FragmentGuide01Binding.inflate(layoutInflater)
        return fragmentGuide01Binding
    }
}