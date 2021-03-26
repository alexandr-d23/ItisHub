package com.example.itishub.presenter.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.itishub.R
import com.example.itishub.databinding.FragmentHomeBinding
import com.example.itishub.presenter.viewmodels.HomeViewModel
import com.example.itishub.presenter.viewmodels.ViewModelFactory
import com.example.itishub.utils.getErrorMessage
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(viewModelStore, viewModelFactory).get(HomeViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        initListeners()
        initLiveData()
    }

    private fun initListeners() {
        with(binding) {
            btnReview.setOnClickListener {
                ReviewDialogFragment.show(parentFragmentManager,
                    positiveAction = { email, text ->
                        viewModel.sendReview(email, text)
                    },
                    negativeAction = {

                    })
            }
        }
    }

    private fun initLiveData() {
        viewModel.error().observe(viewLifecycleOwner) { throwable ->
            throwable?.let {
                showSnackbar(it.getErrorMessage(resources))
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}