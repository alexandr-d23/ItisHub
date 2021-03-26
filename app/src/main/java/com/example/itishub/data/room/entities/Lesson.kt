package com.example.itishub.data.room.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Subject::class,
        parentColumns = ["id"],
        childColumns = ["subjectId"]
    )]
)
data class Lesson(
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0,
    var description: String = "",
    var pdfFile: String = "",
    var title: String = "",
    var videoUrl: String = "",
    var subjectId: Int = 0,
    @Ignore
    var links: List<UsefulLink> = listOf()
) {
    fun getYoutubeId(): String = videoUrl.split('/').last()
}