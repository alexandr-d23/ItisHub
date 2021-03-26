package com.example.itishub.presenter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.itishub.data.repositories.ContentRepository
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val contentRepository: ContentRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(CreatorsViewModel::class.java) -> {
                CreatorsViewModel(contentRepository) as? T
                    ?: throw IllegalArgumentException("Unknown viewmodel class")
            }
            modelClass.isAssignableFrom(SubjectsViewModel::class.java) -> {
                SubjectsViewModel(contentRepository) as? T
                    ?: throw IllegalArgumentException("Unknown viewmodel class")
            }
            modelClass.isAssignableFrom(SubjectLessonsViewModel::class.java) -> {
                SubjectLessonsViewModel(contentRepository) as? T
                    ?: throw IllegalArgumentException("Unknown viewmodel class")
            }
            modelClass.isAssignableFrom(LessonViewModel::class.java) -> {
                LessonViewModel(contentRepository) as? T
                    ?: throw IllegalArgumentException("Unknown viewmodel class")
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(contentRepository) as? T
                    ?: throw java.lang.IllegalArgumentException("Unknown viewmodel class")
            }
            else -> {
                throw IllegalArgumentException("Unknown viewmodel class")
            }
        }


}