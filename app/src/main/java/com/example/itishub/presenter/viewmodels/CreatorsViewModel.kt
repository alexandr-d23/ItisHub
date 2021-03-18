package com.example.itishub.presenter.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.itishub.data.room.entities.Creator
import com.example.itishub.domain.ContentUseCase

class CreatorsViewModel(
    private val contentUseCase: ContentUseCase
) : ViewModel() {

    init {
        Log.d("MYTAG", "INITIALIZED")
    }

    private val mCreators: LiveData<List<Creator>> = contentUseCase.getCreators()
    private val areCreatorsLoaded: LiveData<Boolean> = contentUseCase.areCreatorsLoaded()

    fun creators(): LiveData<List<Creator>> = mCreators
    fun areLoaded(): LiveData<Boolean> = areCreatorsLoaded

    override fun onCleared() {
        super.onCleared()
    }

}