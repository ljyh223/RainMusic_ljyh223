package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("alias")
    val alias: Any,
    @SerializedName("artists")
    val artists: Any,
    @SerializedName("coverUrl")
    val coverUrl: String,
    @SerializedName("playTime")
    val playTime: Int,
    @SerializedName("publishTime")
    val publishTime: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: Int,
    @SerializedName("vid")
    val vid: String
)