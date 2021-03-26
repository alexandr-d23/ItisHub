package com.example.itishub.presenter.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.itishub.data.room.entities.Lesson
import com.example.itishub.databinding.FragmentLessonBinding
import com.example.itishub.presenter.adapters.UsefulLinkAdapter
import com.example.itishub.presenter.viewmodels.LessonViewModel
import com.example.itishub.presenter.viewmodels.ViewModelFactory
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LessonFragment() : Fragment() {

    private var bottomNM: BottomNavigationManager? = null
    private var VIDEO_ID: Int = 0
    private var _binding: FragmentLessonBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LessonViewModel
    private lateinit var linkAdapter: UsefulLinkAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    interface BottomNavigationManager {
        fun enterFullScreen()
        fun exitFullScreen()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        bottomNM = context as? BottomNavigationManager
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLessonBinding.inflate(inflater, container, false)
        VIDEO_ID = arguments?.let {
            LessonFragmentArgs.fromBundle(it).lessonId
        } ?: throw IllegalStateException("lesson id required")
        viewModel =
            ViewModelProvider(viewModelStore, viewModelFactory).get(LessonViewModel::class.java)
        initRecycler()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initLiveData()
    }

    private fun initRecycler() {
        linkAdapter = UsefulLinkAdapter()
        binding.rvLinks!!.adapter = linkAdapter //TODO
        Log.d("MYTAG", "LessonFragment_initRecycler: init")
    }

    private fun initLiveData() {
        with(viewModel) {
            getLesson(VIDEO_ID).observe(viewLifecycleOwner) { lesson ->
                initYoutubeVideo(lesson.getYoutubeId())
                bindLesson(lesson)
            }
        }
    }

    private fun bindLesson(lesson: Lesson) {
        with(binding) {
            tvTitle?.text = lesson.title
            tvDescription?.text = lesson.description
            linkAdapter.submitList(lesson.links.also {
                Log.d("MYTAG", "LessonFragment_bindLesson: " + lesson.links.toString())
            })
        }
    }

    private fun initYoutubeVideo(id: String) {
        val view = binding.youtubePlayerView
        lifecycle.addObserver(view)
        view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {
                youTubePlayer.loadVideo(id, 0F)
                super.onReady(youTubePlayer)
            }
        })
        view.addFullScreenListener(object : YouTubePlayerFullScreenListener {
            override fun onYouTubePlayerEnterFullScreen() {
                bottomNM?.enterFullScreen()
            }

            override fun onYouTubePlayerExitFullScreen() {
                bottomNM?.exitFullScreen()
            }

        })

    }

}