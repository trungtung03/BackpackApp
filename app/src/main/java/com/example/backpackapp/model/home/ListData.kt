package com.example.backpackapp.model.home

import com.example.backpackapp.model.home.popularDestinations.PopularDestinations
import com.example.backpackapp.model.home.posts.Posts

class ListData(
    val type: Int?,
    val listPosts: ArrayList<Posts>?,
    val listPopularDestinations: ArrayList<PopularDestinations>?
)