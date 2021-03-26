package com.example.itishub.data.retrofit

import com.example.itishub.data.retrofit.entities.CourseResponse
import com.example.itishub.data.retrofit.entities.CreatorResponse
import com.example.itishub.data.retrofit.entities.Review
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ContentService {
    @GET("creators")
    fun getCreators(): Single<List<CreatorResponse>>

    @GET("courses")
    fun getCourses(): Single<List<CourseResponse>>

    @Headers("Content-Type: application/json")
    @POST("review")
    fun sendReview(@Body review: Review): Single<ResponseBody>

}