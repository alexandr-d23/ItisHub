package com.example.itishub.domain

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.itishub.data.room.entities.Creator
import com.example.itishub.data.room.entities.Subject
import javax.inject.Inject

class ContentUseCaseImpl @Inject constructor(
    private val contentRepository: ContentRepository
) : ContentUseCase {

    override fun getCreators(): LiveData<List<Creator>> = contentRepository.getCreators()

    override fun areCreatorsLoaded(): LiveData<Boolean> = contentRepository.areCreatorsLoaded()

    override fun getSubjects(): LiveData<List<Subject>> = contentRepository.getSubjects()

    override fun areSubjectsLoaded(): LiveData<Boolean> = contentRepository.areSubjectsLoaded()

}