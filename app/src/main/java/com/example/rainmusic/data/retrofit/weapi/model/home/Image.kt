package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("action")
    val action: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("imageUrl2")
    val imageUrl2: String,
    @SerializedName("purePicture")
    val purePicture: Boolean,
    @SerializedName("title")
    val title: String
)