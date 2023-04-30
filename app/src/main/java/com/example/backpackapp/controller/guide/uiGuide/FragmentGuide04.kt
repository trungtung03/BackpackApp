package com.example.backpackapp.controller.guide.uiGuide

import android.view.View
import com.example.backpackapp.databinding.FragmentGuide04Binding
import com.example.backpackapp.base.BaseFragment

class FragmentGuide04 : BaseFragment<FragmentGuide04Binding>() {
    companion object {
        fun newInstance() = FragmentGuide04()
    }

    private lateinit var fragmentGuide04Binding: FragmentGuide04Binding

    override fun initView(view: View) {
        fragmentGuide04Binding = FragmentGuide04Binding.bind(view)
    }

    override fun getBinding(): FragmentGuide04Binding {
        fragmentGuide04Binding = FragmentGuide04Binding.inflate(layoutInflater)
        return fragmentGuide04Binding
    }
}