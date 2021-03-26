package com.example.itishub.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.itishub.data.retrofit.ContentService
import com.example.itishub.data.retrofit.entities.CourseResponse
import com.example.itishub.data.retrofit.entities.Review
import com.example.itishub.data.room.ContentDao
import com.example.itishub.data.room.entities.Creator
import com.example.itishub.data.room.entities.Lesson
import com.example.itishub.data.room.entities.Subject
import com.example.itishub.utils.Converter
import com.example.itishub.utils.RxUtils
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentService: ContentService,
    private val contentDao: ContentDao
) : ContentRepository {

    private val areCreatorsLoaded: MutableLiveData<Boolean> = MutableLiveData(true)
    private val areSubjectsLoaded: MutableLiveData<Boolean> = MutableLiveData(true)
    private var mCreators: LiveData<List<Creator>>? = null
    private val creatorsRequestError: MutableLiveData<Throwable?> = MutableLiveData(null)
    private var mSubjects: LiveData<List<Subject>>? = null
    private val subjectsRequestError: MutableLiveData<Throwable?> = MutableLiveData(null)
    private val reviewError: MutableLiveData<Throwable?> = MutableLiveData(null)

    override fun getCreators(): LiveData<List<Creator>> {
        return mCreators ?: contentDao.getCreators().also {
            mCreators = it
            updateCreators()
        }
    }

    override fun updateCreators(): Disposable {
        return contentService.getCreators().map { list ->
            Converter.getCreatorsFromResponse(list)
        }.compose(RxUtils.asyncSingleBoth())
            .doOnSubscribe {
                areCreatorsLoaded.postValue(false)
            }
            .doAfterTerminate {
                areCreatorsLoaded.postValue(true)
            }
            .subscribeBy(onSuccess = { list ->
                contentDao.insertCreators(list)
                creatorsRequestError.postValue(null)
            }, onError = { throwable ->
                creatorsRequestError.postValue(throwable)
            })
    }

    override fun getSubjectWithLesson(id: Int): LiveData<Subject> =
        Transformations.map(contentDao.getSubjectWithLessons(id)) {
            it.subject.apply {
                lessons = it.lessons
            }
        }

    override fun getLessonById(id: Int): LiveData<Lesson> =
        Transformations.map(contentDao.getLessonWithLinks(id)) { lessonWithLinks ->
            lessonWithLinks.lesson.apply {
                links = lessonWithLinks.links
            }
        }

    override fun getReviewException(): LiveData<Throwable?> = reviewError


    override fun areCreatorsLoaded(): LiveData<Boolean> = areCreatorsLoaded

    override fun getCreatorsException(): LiveData<Throwable?> = creatorsRequestError

    override fun getSubjects(): LiveData<List<Subject>> {
        return mSubjects ?: contentDao.getSubjects().also {
            mSubjects = it
            updateSubjects()
        }
    }

    override fun sendReview(review: Review): Disposable {
        return contentService
            .sendReview(review)
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    Log.d("MYTAG", "ContentRepositoryImpl_sendReview: Отправлено")
                },
                onError = { throwable ->
                    reviewError.postValue(throwable)
                })
    }

    override fun updateSubjects(): Disposable {
        return contentService.getCourses()
            .compose(RxUtils.asyncSingleBoth())
            .doOnSubscribe {
                areSubjectsLoaded.postValue(false)
            }
            .doAfterTerminate {
                areSubjectsLoaded.postValue(true)
            }
            .subscribeBy(onSuccess = { list ->
                saveToDao(list)
                subjectsRequestError.postValue(null)
            }, onError = { throwable ->
                subjectsRequestError.postValue(throwable)
            })
    }

    override fun areSubjectsLoaded(): LiveData<Boolean> = areSubjectsLoaded

    override fun getSubjectsException(): LiveData<Throwable?> = subjectsRequestError

    private fun saveToDao(list: List<CourseResponse>) {
        contentDao.insertSubjects(Converter.getSubjectsFromResponse(list))
        contentDao.insertLessons(Converter.getLessonsFromResponse(list))
        contentDao.insertLinks(Converter.getLinksFromResponse(list))
    }

}