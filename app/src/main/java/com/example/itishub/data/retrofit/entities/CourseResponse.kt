package com.example.itishub.data.retrofit.entities


import com.google.gson.annotations.SerializedName

data class CourseResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("lessons")
    var lessons: List<Lesson>,
    @SerializedName("title")
    var title: String,
    @SerializedName("url")
    var url: String
) {
    data class Lesson(
        @SerializedName("description")
        var description: String,
        @SerializedName("id")
        var id: Int,
        @SerializedName("pdf_file")
        var pdfFile: String,
        @SerializedName("title")
        var title: String,
        @SerializedName("useful_links")
        var usefulLinks: List<UsefulLink>,
        @SerializedName("video_url")
        var videoUrl: String
    ) {
        data class UsefulLink(
            @SerializedName("title")
            var title: String,
            @SerializedName("url")
            var url: String
        )
    }
}