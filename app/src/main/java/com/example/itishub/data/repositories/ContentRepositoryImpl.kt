package com.example.itishub.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.itishub.data.retrofit.ContentService
import com.example.itishub.data.retrofit.entities.CourseResponse
import com.example.itishub.data.room.ContentDao
import com.example.itishub.data.room.entities.Creator
import com.example.itishub.data.room.entities.Subject
import com.example.itishub.domain.ContentRepository
import com.example.itishub.utils.Converter
import com.example.itishub.utils.RxUtils
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentService: ContentService,
    private val contentDao: ContentDao
) : ContentRepository {

    private val areCreatorsLoaded: MutableLiveData<Boolean> = MutableLiveData(true)
    private val areSubjectsLoaded: MutableLiveData<Boolean> = MutableLiveData(true)
    private var mCreators: LiveData<List<Creator>>? = null
    private var mSubjects: LiveData<List<Subject>>? = null

    override fun getCreators(): LiveData<List<Creator>> {
        return mCreators?: contentDao.getCreators().also {
            mCreators = it
            val d = contentService.getCreators().map { list ->
                Converter.getCreatorsFromResponse(list)
            }.compose(RxUtils.asyncSingleBoth())
                .doOnSubscribe {
                    areCreatorsLoaded.postValue(false)
                }
                .doAfterTerminate {
                    areCreatorsLoaded.postValue(true)
                }
                .subscribe() { list ->
                    contentDao.insertCreators(list)
                }
        }
    }

    override fun areCreatorsLoaded(): LiveData<Boolean> = areCreatorsLoaded

    override fun getSubjects(): LiveData<List<Subject>> {
        return mSubjects?: contentDao.getSubjects().also{
            mSubjects = it
            val d = contentService
                .getCourses()
                .compose(RxUtils.asyncSingleBoth()).doOnSubscribe {
                    areSubjectsLoaded.postValue(false)
                }
                .doAfterTerminate {
                    areSubjectsLoaded.postValue(true)
                }
                .subscribe() { list ->
                    saveToDao(list)
                }
        }
    }

    override fun areSubjectsLoaded(): LiveData<Boolean> = areSubjectsLoaded

    private fun saveToDao(list: List<CourseResponse>) {
        contentDao.insertSubjects(Converter.getSubjectsFromResponse(list))
        contentDao.insertLessons(Converter.getLessonsFromResponse(list))
        contentDao.insertLinks(Converter.getLinksFromResponse(list))
    }


}