package com.example.backpackapp.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.backpackapp.R
import com.example.backpackapp.`object`.home.Post
import com.example.backpackapp.`object`.home.service.Service
import com.example.backpackapp.`object`.home.user.UserLikePosts
import com.example.backpackapp.adapter.adpterHome.PostsAdapter
import kotlinx.android.synthetic.main.fragment_home_overview.*

class FragmentHome : Fragment() {

    private val post = arrayListOf<Post>()
    private val userLikePosts = arrayListOf<UserLikePosts>()
    private val service = arrayListOf<Service>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionView()
    }

    private fun actionView() {
        addList()
        GridLayoutManager(activity, 1).also { rcv_post_home.layoutManager = it }
        rcv_post_home.adapter = activity?.let { PostsAdapter(it, post, userLikePosts, service) }
    }

    private fun addList() {
        post.add(Post(name = "Jenifer"))
        userLikePosts.add(UserLikePosts(imageUser = "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/member2.png?alt=media&token=a9c4f776-fe26-4a69-ba67-d0754603fcbd"))
        service.add(Service(service = "Ticket"))

        post.add(Post(name = "Mike"))
        userLikePosts.add(UserLikePosts(imageUser = "https://firebasestorage.googleapis.com/v0/b/backpack-app-d7709.appspot.com/o/member4.png?alt=media&token=aed59a90-f438-4c37-bb5a-136715ab041a"))
        service.add(Service(service = "Ticket"))
        service.add(Service(service = "Shared"))
    }
}