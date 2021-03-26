package com.example.itishub.data.room.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Subject(
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0,
    var title: String = "",
    var url: String = "",
    var lessonsCount: Int = 0,
    var image: String? = null,
    @Ignore
    var lessons: List<Lesson>? = null
)