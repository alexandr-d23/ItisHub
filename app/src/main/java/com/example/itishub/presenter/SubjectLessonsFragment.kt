package com.example.itishub.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.itishub.databinding.FragmentSubjectVideosBinding
import com.example.itishub.presenter.adapters.LessonAdapter

class SubjectVideosFragment : Fragment() {

    private var _binding: FragmentSubjectVideosBinding? = null
    private val binding: FragmentSubjectVideosBinding get() = _binding!!
    private lateinit var adapter: LessonAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSubjectVideosBinding.inflate(
            layoutInflater,
            container,
            false
        )
        initRecycler()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        val id = arguments?.getInt(ARG_SUBJECT_ID) ?: 0
        Toast.makeText(requireContext(), "$id", Toast.LENGTH_SHORT).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecycler(){
        adapter = LessonAdapter {
            navigateToLesson(it)
        }
        binding.rvLessons.adapter = adapter
    }

    fun navigateToLesson(lessonId: Int){
    //TODO     findNavController().navigate()
    }

    companion object{
        const val ARG_SUBJECT_ID = "ARG_SUBJECT_ID_CONST"

        fun bundleArgs(subjectId: Int): Bundle = Bundle().apply {
            putInt(ARG_SUBJECT_ID, subjectId)
        }
    }

}