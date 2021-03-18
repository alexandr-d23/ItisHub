package com.example.itishub.presenter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.itishub.domain.ContentUseCase
import javax.inject.Inject
import javax.inject.Singleton

class ViewModelFactory @Inject constructor(
    private val contentUseCase: ContentUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(CreatorsViewModel::class.java) -> {
                CreatorsViewModel(contentUseCase) as? T
                    ?: throw IllegalArgumentException("Unknown viewmodel class")
            }
            modelClass.isAssignableFrom(SubjectsViewModel::class.java) -> {
                SubjectsViewModel(contentUseCase) as? T
                    ?: throw java.lang.IllegalArgumentException("Unknown viewmodel class")
            }
            else -> {
                throw IllegalArgumentException("Unknown viewmodel class")
            }
        }


}