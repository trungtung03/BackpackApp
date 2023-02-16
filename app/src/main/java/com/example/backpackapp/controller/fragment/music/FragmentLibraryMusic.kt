package com.example.backpackapp.controller.fragment.music

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.backpackapp.R
import com.example.backpackapp.view.adapter.music.AllTracksAdapter
import com.example.backpackapp.view.adapter.music.MusicForYouAdapter
import com.example.backpackapp.databinding.FragmentLibraryMusicBinding
import com.example.backpackapp.model.music.LibraryMusic
import com.example.backpackapp.view.base.BaseFragment
import com.example.backpackapp.util.GA
import com.example.backpackapp.controller.fragment.FragmentHome
import kotlinx.android.synthetic.main.fragment_library_music.*

class FragmentLibraryMusic : BaseFragment<FragmentLibraryMusicBinding>() {

    private lateinit var fragmentLibraryMusicBinding: FragmentLibraryMusicBinding
    private val listMusic = arrayListOf<LibraryMusic>()
    private val listTracks = arrayListOf<LibraryMusic>()

    override fun initView(view: View) {
        fragmentLibraryMusicBinding = FragmentLibraryMusicBinding.bind(view)
        actionView()
    }

    override fun getBinding(): FragmentLibraryMusicBinding {
        fragmentLibraryMusicBinding = FragmentLibraryMusicBinding.inflate(layoutInflater)
        return fragmentLibraryMusicBinding
    }

    private fun actionView() {
        round_backpack_library_music.setOnClickListener {
            replaceFragment(
                R.id.fragment_library_music,
                FragmentHome(),
                null
            )
        }
        actionPlayMusic()
        setListMusicForYou()
        setListTracks()
    }

    private fun actionPlayMusic() {
        fragmentLibraryMusicBinding
            .iconPauseResumeLibraryMusic.setOnClickListener {
                if (GA.CHECK_CLICK) {
                    fragmentLibraryMusicBinding
                        .iconPauseResumeLibraryMusic.setImageResource(
                            R.drawable.resume_music
                        )
                    GA.CHECK_CLICK = false
                } else {
                    fragmentLibraryMusicBinding
                        .iconPauseResumeLibraryMusic.setImageResource(
                            R.drawable.round_pause_circle_filled
                        )
                    GA.CHECK_CLICK = true
                }
            }
    }

    private fun setListMusicForYou() {
        addList()

        LinearLayoutManager(
            activity,
            RecyclerView.HORIZONTAL,
            false
        ).also {
            fragmentLibraryMusicBinding
                .rcvMusicForYouLibraryMusic
                .layoutManager = it
        }

        GridLayoutManager(activity, 1)
            .also {
                rcv_list_music_library_music.layoutManager = it
            }

        activity?.let { MusicForYouAdapter(it, listMusic) }
            .also {
                fragmentLibraryMusicBinding
                    .rcvMusicForYouLibraryMusic
                    .adapter = it
            }
    }

    private fun setListTracks() {
        addTracks()

        LinearLayoutManager(
            activity,
            RecyclerView.VERTICAL,
            false
        ).also {
            fragmentLibraryMusicBinding
                .rcvListMusicLibraryMusic
                .layoutManager = it
        }

        GridLayoutManager(activity, 1)
            .also {
                rcv_list_music_library_music.layoutManager = it
            }

        fragmentLibraryMusicBinding
            .rcvListMusicLibraryMusic
            .adapter = activity?.let { AllTracksAdapter(
            it,
            listTracks) }
    }

    private fun addTracks() {
        listTracks.add(
            LibraryMusic(
                imageAvatar = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Anime_Girl_upright_version.svg/724px-Anime_Girl_upright_version.svg.png",
                nameSinger = "Michael Tùng",
                songTitle = "Có chàng trai viết lên cây",
                timeMusic = "05:21"
            )
        )
        listTracks.add(
            LibraryMusic(
                imageAvatar = "https://upload.wikimedia.org/wikipedia/commons/c/c2/Anime_Girl.svg",
                nameSinger = "Tùng Đẹp Trai",
                songTitle = "Có chàng trai viết lên cây",
                timeMusic = "05:21"
            )
        )
        listTracks.add(
            LibraryMusic(
                imageAvatar = "https://upload.wikimedia.org/wikipedia/commons/c/c2/Anime_Girl.svg",
                nameSinger = "Tùng Đẹp Trai",
                songTitle = "Có chàng trai viết lên cây",
                timeMusic = "05:21"
            )
        )
        listTracks.add(
            LibraryMusic(
                imageAvatar = "https://upload.wikimedia.org/wikipedia/commons/c/c2/Anime_Girl.svg",
                nameSinger = "Tùng Đẹp Trai",
                songTitle = "Có chàng trai viết lên cây",
                timeMusic = "05:21"
            )
        )
        listTracks.add(
            LibraryMusic(
                imageAvatar = "https://upload.wikimedia.org/wikipedia/commons/c/c2/Anime_Girl.svg",
                nameSinger = "Tùng Đẹp Trai",
                songTitle = "Có chàng trai viết lên cây",
                timeMusic = "05:21"
            )
        )
    }

    private fun addList() {
        listMusic.add(
            LibraryMusic(
                imageAvatar = "https://www.shutterstock.com/image-photo/top-view-woman-photographer-working-600w-1398389924.jpg",
                nameSinger = "Tùng",
                songTitle = "Chắc ai đó sẽ khóc",
                timeMusic = "05:34"
            )
        )
        listMusic.add(
            LibraryMusic(
                imageAvatar = "https://www.shutterstock.com/image-photo/top-view-woman-photographer-working-600w-1398389924.jpg",
                nameSinger = "Tùng",
                songTitle = "Chắc ai đó sẽ khóc",
                timeMusic = "05:34"
            )
        )
        listMusic.add(
            LibraryMusic(
                imageAvatar = "https://www.shutterstock.com/image-photo/top-view-woman-photographer-working-600w-1398389924.jpg",
                nameSinger = "Tùng",
                songTitle = "Chắc ai đó sẽ khóc",
                timeMusic = "05:34"
            )
        )
        listMusic.add(
            LibraryMusic(
                imageAvatar = "https://www.shutterstock.com/image-photo/top-view-woman-photographer-working-600w-1398389924.jpg",
                nameSinger = "Tùng",
                songTitle = "Chắc ai đó sẽ khóc",
                timeMusic = "05:34"
            )
        )
        listMusic.add(
            LibraryMusic(
                imageAvatar = "https://www.shutterstock.com/image-photo/top-view-woman-photographer-working-600w-1398389924.jpg",
                nameSinger = "Tùng",
                songTitle = "Chắc ai đó sẽ khóc",
                timeMusic = "05:34"
            )
        )
    }
}