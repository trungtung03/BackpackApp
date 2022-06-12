package com.example.backpackapp.`object`.home

import com.example.backpackapp.`object`.home.popularDestinations.PopularDestinations
import com.example.backpackapp.`object`.home.posts.Posts

class ListData(
    val type: Int?,
    val listPosts: ArrayList<Posts>?,
    val listPopularDestinations: ArrayList<PopularDestinations>?
)