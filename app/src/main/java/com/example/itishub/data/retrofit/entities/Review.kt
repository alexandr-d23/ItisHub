package com.example.itishub.data.retrofit.entities

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("email")
    var email: String,
    @SerializedName("text")
    var text: String
)