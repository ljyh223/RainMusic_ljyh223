package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class DescPic(
    @SerializedName("content")
    val content: String,
    @SerializedName("height")
    val height: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageContentURLInvalid")
    val imageContentURLInvalid: Boolean,
    @SerializedName("type")
    val type: Int,
    @SerializedName("width")
    val width: Any
)