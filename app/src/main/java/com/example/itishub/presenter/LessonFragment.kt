package com.example.itishub.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.itishub.databinding.FragmentVideoBinding

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class VideoFragment() : Fragment() {

    private var listener: buttonNavigationManager? = null
    private lateinit var VIDEO_ID : String
    private val API_KEY = "AIzaSyDvr0CLEiO_wi0Yo0ANn66jhNDWWAGgEkI"
    private var _binding: FragmentVideoBinding? = null
    private val binding get() = _binding!!


    interface buttonNavigationManager{
        fun hide()
        fun show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        VIDEO_ID = arguments?.getString(ARG_ID)?: ""
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initYoutubeVideo()
    }

    override fun onResume() {
        super.onResume()
        listener?.hide()
    }

    override fun onStop() {
        super.onStop()
        listener?.show()
    }

    private fun initYoutubeVideo(){
        val view = binding.youtubePlayerView
        lifecycle.addObserver(view)
        view.addYouTubePlayerListener(object :AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {
                youTubePlayer.loadVideo("L64_32xzR2M",0F)
                super.onReady(youTubePlayer)
            }
        })
    }

    companion object {
        const val ARG_ID = "ARGUMENT_ID"

        fun bundleArgs(lessonId: Int):Bundle =
            Bundle().apply {
                putInt(ARG_ID, lessonId)
            }
    }


}