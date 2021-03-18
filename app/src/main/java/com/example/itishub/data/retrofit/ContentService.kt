package com.example.itishub.data.retrofit

import com.example.itishub.data.retrofit.entities.CourseResponse
import com.example.itishub.data.retrofit.entities.CreatorResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ContentService {
    @GET("creators")
    fun getCreators(): Single<List<CreatorResponse>>

    @GET("courses")
    fun getCourses(): Single<List<CourseResponse>>
}