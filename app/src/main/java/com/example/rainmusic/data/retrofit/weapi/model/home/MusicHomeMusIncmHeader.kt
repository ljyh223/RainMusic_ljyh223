package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class MusicHomeMusIncmHeader(
    @SerializedName("moreBtnValue")
    val moreBtnValue: String,
    @SerializedName("showMore")
    val showMore: Boolean,
    @SerializedName("text")
    val text: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("titleT")
    val titleT: String,
    @SerializedName("url")
    val url: String
)