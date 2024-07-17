package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class TagImg(
    @SerializedName("height")
    val height: Any,
    @SerializedName("tagImgUrl")
    val tagImgUrl: Any,
    @SerializedName("width")
    val width: Any
)