package com.example.itishub.data.retrofit.entities

import androidx.room.Entity
import com.google.gson.annotations.SerializedName


data class CreatorResponse(
    @SerializedName("about")
    var about: String,
    @SerializedName("avatar")
    var avatar: String,
    @SerializedName("github")
    var github: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("surname")
    var surname: String,
    @SerializedName("telegram")
    var telegram: String,
    @SerializedName("vk")
    var vk: String
)