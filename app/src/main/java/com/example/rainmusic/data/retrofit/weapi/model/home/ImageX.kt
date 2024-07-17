package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class ImageX(
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("purePicture")
    val purePicture: Boolean
)