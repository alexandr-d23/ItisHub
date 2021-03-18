package com.example.itishub.domain

import androidx.lifecycle.LiveData
import com.example.itishub.data.retrofit.entities.CourseResponse
import com.example.itishub.data.retrofit.entities.CreatorResponse
import com.example.itishub.data.room.entities.Creator
import com.example.itishub.data.room.entities.Subject
import io.reactivex.Single

interface ContentRepository {
    fun getCreators(): LiveData<List<Creator>>

    fun areCreatorsLoaded(): LiveData<Boolean>

    fun getSubjects(): LiveData<List<Subject>>

    fun areSubjectsLoaded(): LiveData<Boolean>
}