package com.example.itishub.presenter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itishub.databinding.FragmentCreatorsBinding
import com.example.itishub.presenter.adapters.CreatorAdapter
import com.example.itishub.presenter.viewmodels.CreatorsViewModel
import com.example.itishub.presenter.viewmodels.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CreatorsFragment : Fragment() {

    private var _binding : FragmentCreatorsBinding? = null
    private val binding: FragmentCreatorsBinding get() = _binding!!
    private lateinit var adapter : CreatorAdapter
    private lateinit var viewModel: CreatorsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(viewModelStore, viewModelFactory).get(CreatorsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreatorsBinding.inflate(layoutInflater)
        initRecycler()
        initListeners()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecycler(){
        adapter = CreatorAdapter(){
           openLink(it)
        }
        binding.rvCreators.let{
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun openLink(uri: String){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(intent)
    }

    private fun initListeners(){
        with(viewModel){
            creators().observe(viewLifecycleOwner){
                adapter.submitList(it)
            }
            areLoaded().observe(viewLifecycleOwner){
                binding.pbCreators.isVisible = !it
            }
        }
    }

    companion object {
        fun newInstance(): CreatorsFragment {
            return CreatorsFragment()
        }
    }
}