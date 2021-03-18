package com.example.itishub.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Creator(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var about: String,
    var avatar: String,
    var github: String,
    var name: String,
    var surname: String,
    var telegram: String,
    var vk: String
)