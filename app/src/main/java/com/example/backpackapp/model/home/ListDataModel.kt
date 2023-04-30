package com.example.backpackapp.model.home

import com.example.backpackapp.model.home.modelChild.PopularDestinationsModel
import com.example.backpackapp.model.home.modelChild.PostsTripModel

class ListDataModel(
    val type: Int?,
    val mListPosts: ArrayList<PostsTripModel>?,
    val mListPopularDestinationModels: ArrayList<PopularDestinationsModel>?
)