package com.example.itishub.presenter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.itishub.data.repositories.ContentRepository
import com.example.itishub.data.room.entities.Subject
import io.reactivex.disposables.Disposable

class SubjectsViewModel(
    private val contentRepository: ContentRepository
) : ViewModel() {

    private val mSubjects: LiveData<List<Subject>> = contentRepository.getSubjects()
    private val areSubjectsLoaded: LiveData<Boolean> = contentRepository.areSubjectsLoaded()
    private val error: LiveData<Throwable?> = contentRepository.getSubjectsException()
    private var disposable: Disposable? = null

    fun subjects(): LiveData<List<Subject>> = mSubjects
    fun areSubjectsLoaded(): LiveData<Boolean> = areSubjectsLoaded
    fun updateSubjects() {
        disposable = contentRepository.updateSubjects()
    }

    fun error(): LiveData<Throwable?> = error

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

}