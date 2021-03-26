package com.example.itishub.data.repositories

import androidx.lifecycle.LiveData
import com.example.itishub.data.retrofit.entities.Review
import com.example.itishub.data.room.entities.Creator
import com.example.itishub.data.room.entities.Lesson
import com.example.itishub.data.room.entities.Subject
import io.reactivex.disposables.Disposable

interface ContentRepository {
    fun getCreators(): LiveData<List<Creator>>

    fun areCreatorsLoaded(): LiveData<Boolean>

    fun getCreatorsException(): LiveData<Throwable?>

    fun getSubjects(): LiveData<List<Subject>>

    fun areSubjectsLoaded(): LiveData<Boolean>

    fun getSubjectsException(): LiveData<Throwable?>

    fun sendReview(review: Review): Disposable

    fun updateSubjects(): Disposable

    fun updateCreators(): Disposable

    fun getSubjectWithLesson(id: Int): LiveData<Subject>

    fun getLessonById(id: Int): LiveData<Lesson>

    fun getReviewException(): LiveData<Throwable?>
}