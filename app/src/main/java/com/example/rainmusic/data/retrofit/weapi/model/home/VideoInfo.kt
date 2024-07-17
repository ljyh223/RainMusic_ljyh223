package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class VideoInfo(
    @SerializedName("moreThanOne")
    val moreThanOne: Boolean,
    @SerializedName("video")
    val video: Video
)