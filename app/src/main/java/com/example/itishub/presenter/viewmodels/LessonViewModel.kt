package com.example.itishub.presenter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.itishub.data.repositories.ContentRepository
import com.example.itishub.data.room.entities.Lesson

class LessonViewModel(
    private val contentRepository: ContentRepository
) : ViewModel() {
    fun getLesson(id: Int): LiveData<Lesson> = contentRepository.getLessonById(id)
}