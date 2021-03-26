package com.example.itishub.presenter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.itishub.data.repositories.ContentRepository
import com.example.itishub.data.room.entities.Creator
import io.reactivex.disposables.Disposable

class CreatorsViewModel(
    private val contentRepository: ContentRepository
) : ViewModel() {

    private val mCreators: LiveData<List<Creator>> = contentRepository.getCreators()
    private val areCreatorsLoaded: LiveData<Boolean> = contentRepository.areCreatorsLoaded()
    private val error: LiveData<Throwable?> = contentRepository.getCreatorsException()
    private var disposable: Disposable? = null

    fun creators(): LiveData<List<Creator>> = mCreators
    fun areLoaded(): LiveData<Boolean> = areCreatorsLoaded
    fun error(): LiveData<Throwable?> = error
    fun updateCreators() {
        disposable = contentRepository.updateCreators()
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

}