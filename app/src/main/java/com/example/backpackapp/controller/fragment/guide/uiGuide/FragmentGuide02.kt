package com.example.backpackapp.controller.fragment.guide.uiGuide

import android.view.View
import com.example.backpackapp.view.base.BaseFragment
import com.example.backpackapp.databinding.FragmentGuide02Binding

class FragmentGuide02 : BaseFragment<FragmentGuide02Binding>() {
    private lateinit var fragmentGuide02Binding: FragmentGuide02Binding

    override fun initView(view: View) {
        fragmentGuide02Binding = FragmentGuide02Binding.bind(view)
    }

    override fun getBinding(): FragmentGuide02Binding {
        fragmentGuide02Binding = FragmentGuide02Binding.inflate(layoutInflater)
        return fragmentGuide02Binding
    }
}