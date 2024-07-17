package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class ItemXXX(
    @SerializedName("address")
    val address: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("algInfo")
    val algInfo: String,
    @SerializedName("artistName")
    val artistName: String,
    @SerializedName("artistNames")
    val artistNames: String,
    @SerializedName("avatarUrl")
    val avatarUrl: String,
    @SerializedName("constellation")
    val constellation: String,
    @SerializedName("contactText")
    val contactText: String,
    @SerializedName("gender")
    val gender: Int,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("profession")
    val profession: String,
    @SerializedName("recommendText")
    val recommendText: String,
    @SerializedName("similarity")
    val similarity: Int,
    @SerializedName("similarityText")
    val similarityText: String,
    @SerializedName("songCoverUrl")
    val songCoverUrl: String,
    @SerializedName("songName")
    val songName: String,
    @SerializedName("subTitle")
    val subTitle: String,
    @SerializedName("track")
    val track: TrackX,
    @SerializedName("url")
    val url: String,
    @SerializedName("userId")
    val userId: Long
)