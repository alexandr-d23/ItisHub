package com.example.itishub.presenter.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.itishub.R
import com.example.itishub.databinding.FragmentSubjectsBinding
import com.example.itishub.presenter.adapters.SubjectAdapter
import com.example.itishub.presenter.viewmodels.SubjectsViewModel
import com.example.itishub.presenter.viewmodels.ViewModelFactory
import com.example.itishub.utils.FragmentExt.Companion.waitForTransition
import com.example.itishub.utils.getErrorMessage
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SubjectsFragment : Fragment() {

    private var _binding: FragmentSubjectsBinding? = null
    private val binding: FragmentSubjectsBinding get() = _binding!!
    private lateinit var rvAdapter: SubjectAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: SubjectsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(viewModelStore, viewModelFactory).get(SubjectsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSubjectsBinding.inflate(
            layoutInflater,
            container,
            false
        )
        initRecycler()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        waitForTransition(binding.rvSubjects)
    }

    override fun onStart() {
        super.onStart()
        initLiveDataListeners()
        initListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initListeners() {
        with(binding) {
            srlUpdate.setOnRefreshListener {
                viewModel.updateSubjects()
                srlUpdate.isRefreshing = false
            }
        }
    }

    private fun showSnackbar(text: String) {
        Log.d("MYTAG", text)
        activity?.let {
            val snackbar =
                Snackbar.make(it.findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG)
                    .setAnchorView(R.id.bnv_menu)
            snackbar.setAction("close") {
                snackbar.dismiss()
            }
            snackbar.show()
        }
    }

    private fun initLiveDataListeners() {
        with(viewModel) {
            areSubjectsLoaded().observe(viewLifecycleOwner) {
                binding.pbSubjects.isVisible = !it
            }
            subjects().observe(viewLifecycleOwner) {
                rvAdapter.submitList(it)
            }
            error().observe(viewLifecycleOwner) { throwable ->
                throwable?.let {
                    showSnackbar(it.getErrorMessage(resources))
                }
            }
        }
    }

    private fun initRecycler() {
        rvAdapter = SubjectAdapter(requireContext().applicationContext) { id, extras, uri ->
            navigateToSubject(id, extras, uri)
        }
        with(binding.rvSubjects) {
            adapter = rvAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun navigateToSubject(
        subjectId: Int,
        extras: FragmentNavigator.Extras,
        imageUri: String
    ) {
        val action = SubjectsFragmentDirections.actionToSubjectVideosFragment()
        action.subjectId = subjectId
        action.imageUri = imageUri
        val controller = findNavController()
        if(controller.currentDestination?.id == R.id.i_subjects){
            controller.navigate(
                action,
                extras
            )
        }
    }

}