package com.example.itishub.presenter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.itishub.data.room.entities.Subject
import com.example.itishub.domain.ContentUseCase

class SubjectsViewModel(
    private val contentUseCase: ContentUseCase
) : ViewModel() {

    private val mSubjects: LiveData<List<Subject>> = contentUseCase.getSubjects()
    private val areSubjectsLoaded: LiveData<Boolean> = contentUseCase.areSubjectsLoaded()

    fun subjects() : LiveData<List<Subject>> = mSubjects
    fun areSubjectsLoaded(): LiveData<Boolean> = areSubjectsLoaded
}