package com.example.backpackapp.view.fragment.guide.uiGuide

import android.view.View
import com.example.backpackapp.ui.base.BaseFragment
import com.example.backpackapp.databinding.FragmentGuide03Binding

class FragmentGuide03 : BaseFragment<FragmentGuide03Binding>() {
    private lateinit var fragmentGuide03Binding: FragmentGuide03Binding

    override fun initView(view: View) {
        fragmentGuide03Binding = FragmentGuide03Binding.bind(view)
    }

    override fun getBinding(): FragmentGuide03Binding {
        fragmentGuide03Binding = FragmentGuide03Binding.inflate(layoutInflater)
        return fragmentGuide03Binding
    }

}