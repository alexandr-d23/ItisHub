package com.example.itishub.presenter.ui

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.itishub.BuildConfig
import com.example.itishub.R
import com.example.itishub.databinding.FragmentSubjectLessonsBinding
import com.example.itishub.presenter.adapters.LessonAdapter
import com.example.itishub.presenter.viewmodels.SubjectLessonsViewModel
import com.example.itishub.presenter.viewmodels.ViewModelFactory
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SubjectLessonsFragment : Fragment() {

    private var _binding: FragmentSubjectLessonsBinding? = null
    private val binding: FragmentSubjectLessonsBinding get() = _binding!!
    private lateinit var subjectAdapter: LessonAdapter
    private lateinit var viewModel: SubjectLessonsViewModel
    private var subjectId: Int = 0
    private var imageUri: String = ""

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animation = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        )
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
        postponeEnterTransition()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSubjectLessonsBinding.inflate(
            layoutInflater,
            container,
            false
        )
        initTransitionName()
        initViewModel()
        initRecycler()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initLiveDataListeners(subjectId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViewModel() {
        viewModel =
            ViewModelProvider(
                viewModelStore,
                viewModelFactory
            ).get(SubjectLessonsViewModel::class.java)
    }

    private fun initTransitionName() {
        arguments?.let {
            subjectId = SubjectLessonsFragmentArgs.fromBundle(it).subjectId
            imageUri = SubjectLessonsFragmentArgs.fromBundle(it).imageUri
            binding.ivImage.transitionName = "$subjectId"
            loadImage(imageUri)
        }
    }


    private fun initRecycler() {
        subjectAdapter = LessonAdapter {
            navigateToLesson(it)
        }
        with(binding.rvLessons) {
            adapter = subjectAdapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun initLiveDataListeners(id: Int) {
        with(viewModel) {
            getSubjectWithLessons(id).observe(viewLifecycleOwner) { subject ->
                subjectAdapter.submitList(subject.lessons)
                subject.image?.let {
                    loadImage(it)
                }
            }
        }
    }

    private fun loadImage(uri: String) {
        Picasso.get()
            .load("${BuildConfig.API_URI_HOME}${uri}")
            .fit()
            .into(binding.ivImage, object : Callback {
                override fun onSuccess() {
                    startPostponedEnterTransition()
                }

                override fun onError(e: Exception?) {
                    startPostponedEnterTransition()
                }

            })
    }

    private fun navigateToLesson(lessonId: Int) {
        val action = SubjectLessonsFragmentDirections.actionSubjectVideosFragmentToYoutubeFragment()
        action.lessonId = lessonId
        val controller = findNavController()
        if (controller.currentDestination?.id == R.id.subjectVideosFragment) {
            controller.navigate(action)
        }
    }

}