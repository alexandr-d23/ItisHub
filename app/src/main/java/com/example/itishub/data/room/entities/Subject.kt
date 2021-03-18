package com.example.itishub.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subject(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var title: String,
    var url: String,
    var lessonsCount: Int
)