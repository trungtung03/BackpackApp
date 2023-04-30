package com.example.backpackapp.controller.home.music

import android.media.MediaPlayer
import android.os.Handler
import android.view.View
import android.widget.SeekBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.backpackapp.R
import com.example.backpackapp.databinding.FragmentLibraryMusicBinding
import com.example.backpackapp.model.home.music.AttributesMusic
import com.example.backpackapp.model.home.music.LibraryMusic
import com.example.backpackapp.units.GA.CHECK_REPEAT
import com.example.backpackapp.units.GA.CHECK_VOLUME
import com.example.backpackapp.units.GA.POSITION_ATTRIBUTES
import com.example.backpackapp.units.GA.POSITION_SONGS
import com.example.backpackapp.units.Parameters.IS_EMPTY
import com.example.backpackapp.base.BaseFragment
import com.example.backpackapp.controller.home.FragmentHome
import com.example.backpackapp.units.Parameters.ALL_TRACKS
import com.example.backpackapp.units.Parameters.MUSIC_FOR_YOU
import com.example.backpackapp.view.home.music.AllTracksAdapter
import com.example.backpackapp.view.home.music.MusicForYouAdapter
import kotlinx.android.synthetic.main.fragment_library_music.*

class FragmentLibraryMusic : BaseFragment<FragmentLibraryMusicBinding>(), View.OnClickListener {
    companion object {
        fun newInstance() = FragmentLibraryMusic()
    }

    private lateinit var fragmentLibraryMusicBinding: FragmentLibraryMusicBinding
    private var mListMusic = arrayListOf<LibraryMusic>()

    private val mListTracks = arrayListOf<LibraryMusic>()
    private var mMusicFYouAdapter: MusicForYouAdapter? = null
    private var mAllTrackAdapter: AllTracksAdapter? = null
    private var mListAttributesSong = arrayListOf<AttributesMusic>()
    private var mMedia: MediaPlayer? = null
    private var lbrMusic = LibraryMusic()

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
                FragmentHome.newInstance(),
                null
            )
        }
        seek_bar_library_music.progress = 0
        setListMusicForYou()
        setListTracks()
        seekbarWithMedia()
        fragmentLibraryMusicBinding.iconPauseResumeLibraryMusic.setOnClickListener(this)
        fragmentLibraryMusicBinding.layoutRepeatMusic.setOnClickListener(this)
        fragmentLibraryMusicBinding.iconPreviousLibraryMusic.setOnClickListener(this)
        fragmentLibraryMusicBinding.iconNextLibraryMusic.setOnClickListener(this)
        fragmentLibraryMusicBinding.layoutVolumeMusic.setOnClickListener(this)
    }

    private fun setDataAttributesSong(
        listDataAttributes: ArrayList<AttributesMusic>,
        listDataSongs: ArrayList<LibraryMusic>
    ) {
        listDataAttributes.clear()
        listDataAttributes.add(
            AttributesMusic(
                avatarSinger = listDataSongs[POSITION_SONGS].imageAvatar,
                songName = listDataSongs[POSITION_SONGS].songTitle
            )
        )
        setAttributesTracks(
            mListAttributesSong[POSITION_ATTRIBUTES].avatarSinger.toString(),
            mListAttributesSong[POSITION_ATTRIBUTES].songName.toString()
        )
    }

    private fun playMusicATrack() {
        if (mMedia != null) {
            mMedia?.release()
            mMedia = null
            mMedia = MediaPlayer()
        } else {
            mMedia = MediaPlayer()
        }
        playCycle()
    }

    private fun playCycle() {
        mMedia?.setDataSource(mListTracks[POSITION_SONGS].url)
        if (!mMedia?.isPlaying!!) {
            mMedia?.prepare()
            mMedia?.start()
            mediaDelay()
        } else {
            mMedia?.pause()
        }
    }

    private fun mediaDelay() {
        seek_bar_library_music.progress = mMedia!!.currentPosition
        seek_bar_library_music.max = mMedia!!.duration
        tv_time_music_start.text = getTimeFormatted(mMedia!!.currentPosition)
        tv_time_music_end.text = getTimeFormatted(mMedia!!.duration)
        if (mMedia != null) {
            Handler().postDelayed({ mediaDelay() }, 100)
        }
        repeatSongs()
    }

    private fun getTimeFormatted(milliSeconds: Int): String {
        var finalTimerString = IS_EMPTY
        val secondsString: String

        /** Converting total duration into time */
        val hours = (milliSeconds / 3600000)
        val minutes = (milliSeconds % 3600000) / 60000
        val seconds = (milliSeconds % 3600000 % 60000 / 1000)

        /** Adding hours if any */
        if (hours > 0) finalTimerString = "$hours:"

        /** Prepending 0 to seconds if it is one digit */
        secondsString = if (seconds < 10) "0$seconds" else "" + seconds
        finalTimerString = "$finalTimerString$minutes:$secondsString"
        return finalTimerString
    }

    private fun setListMusicForYou() {
        mMusicFYouAdapter = activity?.let { MusicForYouAdapter(it) }
        mMusicFYouAdapter?.let { lbrMusic.getDataMusic(mListMusic, it, MUSIC_FOR_YOU) }
        LinearLayoutManager(
            activity,
            RecyclerView.HORIZONTAL, false
        ).also {
            fragmentLibraryMusicBinding.rcvMusicForYouLibraryMusic.layoutManager = it
        }

        GridLayoutManager(activity, 1)
            .also {
                rcv_list_music_library_music.layoutManager = it
            }
        mMusicFYouAdapter?.setData(mListMusic)
        fragmentLibraryMusicBinding.rcvMusicForYouLibraryMusic.adapter = mMusicFYouAdapter
    }

    private fun setListTracks() {
        mAllTrackAdapter = activity?.let {
            AllTracksAdapter(it, onClickItem = {
                playMusicATrack()
                icon_pause_resume_library_music.setImageResource(R.drawable.round_pause_circle_filled)
                setDataAttributesSong(
                    mListAttributesSong, mListTracks
                )
            }
            )
        }
        mAllTrackAdapter?.let { lbrMusic.getDataTracks(mListTracks, it, ALL_TRACKS) }
        LinearLayoutManager(
            activity,
            RecyclerView.VERTICAL, false
        ).also {
            fragmentLibraryMusicBinding.rcvListMusicLibraryMusic.layoutManager = it
        }

        GridLayoutManager(activity, 1)
            .also {
                rcv_list_music_library_music.layoutManager = it
            }
        mAllTrackAdapter?.setData(mListTracks)
        fragmentLibraryMusicBinding.rcvListMusicLibraryMusic.adapter = mAllTrackAdapter
    }

    private fun setAttributesTracks(
        urlImage: String? = null,
        songName: String? = null
    ) {
        activity?.let {
            Glide.with(it).load(urlImage)
                .error(R.mipmap.icon_launcher_round).into(img_avt_singer_library_music)
        }

        tv_name_song_library_music.text = songName
    }

    private fun repeatSongs() {
        mMedia?.setOnCompletionListener {
            if (CHECK_REPEAT) {
                mMedia?.reset()
                playCycle()
            }
        }
    }

    private fun seekbarWithMedia() {
        seek_bar_library_music.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p2) mMedia?.seekTo(p1)
                tv_time_music_start.text = getTimeFormatted(p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
    }

    override fun onClick(p0: View?) {
        when (p0) {
            icon_pause_resume_library_music -> {
                if (mMedia!!.isPlaying) {
                    mMedia!!.pause()
                    icon_pause_resume_library_music.setImageResource(R.drawable.resume_music)
                } else {
                    icon_pause_resume_library_music.setImageResource(R.drawable.round_pause_circle_filled)
                    mMedia!!.start()
                }
            }

            layout_repeat_music -> {
                CHECK_REPEAT = if (!CHECK_REPEAT) {
                    fragmentLibraryMusicBinding.iconRepeatLibraryMusic.setImageResource(R.drawable.repeat_one_48)
                    true
                } else {
                    fragmentLibraryMusicBinding.iconRepeatLibraryMusic.setImageResource(R.drawable.repeat_one_48)
                    false
                }
            }

            icon_previous_library_music -> {
                POSITION_SONGS -= 1
                if (POSITION_SONGS < 0) {
                    POSITION_SONGS = (mListTracks.size - 1)
                }
            }

            icon_next_library_music -> {
                POSITION_SONGS += 1
                if (POSITION_SONGS > (mListTracks.size - 1)) {
                    POSITION_SONGS = 0
                }
            }

            layout_volume_music -> {
                CHECK_VOLUME = if (!CHECK_VOLUME) {
                    fragmentLibraryMusicBinding.iconVolumeLibraryMusic.setImageResource(R.drawable.volume_off_48)
                    mMedia?.setVolume(0F, 0F)
                    true
                } else {
                    fragmentLibraryMusicBinding.iconVolumeLibraryMusic.setImageResource(R.drawable.volume_48)
                    mMedia?.setVolume(1F, 1F)
                    false
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mMedia!!.stop()
    }

    override fun onPause() {
        super.onPause()
        mMedia!!.stop()
    }
}