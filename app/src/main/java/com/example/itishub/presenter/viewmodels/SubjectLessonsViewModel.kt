package com.example.itishub.presenter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.itishub.data.repositories.ContentRepository
import com.example.itishub.data.room.entities.Subject

class SubjectLessonsViewModel(
    private val contentRepository: ContentRepository
) : ViewModel() {
    private var subjectWithLessons: LiveData<Subject>? = null

    fun getSubjectWithLessons(id: Int): LiveData<Subject> =
        subjectWithLessons ?: contentRepository.getSubjectWithLesson(id).also {
            subjectWithLessons = it
        }

}