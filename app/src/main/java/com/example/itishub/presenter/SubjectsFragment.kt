package com.example.itishub.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.itishub.databinding.FragmentSubjectsBinding
import com.example.itishub.presenter.adapters.SubjectAdapter
import com.example.itishub.presenter.viewmodels.SubjectsViewModel
import com.example.itishub.presenter.viewmodels.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SubjectsFragment : Fragment() {

    private var _binding: FragmentSubjectsBinding? = null
    private val binding : FragmentSubjectsBinding get() = _binding!!
    private lateinit var rvAdapter: SubjectAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: SubjectsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(viewModelStore, viewModelFactory).get(SubjectsViewModel::class.java)
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
        initListeners()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initListeners(){
        with(viewModel){
            areSubjectsLoaded().observe(viewLifecycleOwner){
                binding.pbSubjects.isVisible = !it
            }
            subjects().observe(viewLifecycleOwner){
                rvAdapter.submitList(it)
            }
        }
    }

    private fun initRecycler(){
        rvAdapter = SubjectAdapter(requireContext().applicationContext)
        with(binding.rvSubjects){
            adapter = rvAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    companion object{
        fun newInstance(): SubjectsFragment {
            return SubjectsFragment()
        }
    }

}