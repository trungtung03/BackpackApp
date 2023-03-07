package com.example.backpackapp.controller.fragment.guide.uiGuide

import android.view.View
import com.example.backpackapp.databinding.FragmentGuide04Binding
import com.example.backpackapp.view.base.BaseFragment

class FragmentGuide04 : BaseFragment<FragmentGuide04Binding>() {
    private lateinit var fragmentGuide04Binding: FragmentGuide04Binding

    override fun initView(view: View) {
        fragmentGuide04Binding = FragmentGuide04Binding.bind(view)
    }

    override fun getBinding(): FragmentGuide04Binding {
        fragmentGuide04Binding = FragmentGuide04Binding.inflate(layoutInflater)
        return fragmentGuide04Binding
    }
}