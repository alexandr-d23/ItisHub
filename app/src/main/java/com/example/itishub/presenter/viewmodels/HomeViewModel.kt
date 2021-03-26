package com.example.itishub.presenter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.itishub.data.repositories.ContentRepository
import com.example.itishub.data.retrofit.entities.Review
import io.reactivex.disposables.Disposable

class HomeViewModel(
    private val contentRepository: ContentRepository
) : ViewModel() {

    private var disposable: Disposable? = null
    private val error: LiveData<Throwable?> = contentRepository.getReviewException()


    fun sendReview(email: String, text: String) {
        disposable = contentRepository.sendReview(Review(email, text))
    }

    fun error(): LiveData<Throwable?> = error

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}