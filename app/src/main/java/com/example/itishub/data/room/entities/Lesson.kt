package com.example.itishub.data.room.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = Subject::class, parentColumns = ["id"], childColumns = ["subjectId"])])
data class Lesson(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var description: String,
    var pdfFile: String,
    var title: String,
    var videoUrl: String,
    var subjectId: Int
)