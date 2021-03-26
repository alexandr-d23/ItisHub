package com.example.itishub.data.room.entities

import androidx.room.Entity
import androidx.room.ForeignKey


@Entity(
    primaryKeys = ["title", "url"],
    foreignKeys = [ForeignKey(
        entity = Lesson::class,
        parentColumns = ["id"],
        childColumns = ["lessonId"]
    )]
)
data class UsefulLink(
    var title: String,
    var url: String,
    var lessonId: Int
)