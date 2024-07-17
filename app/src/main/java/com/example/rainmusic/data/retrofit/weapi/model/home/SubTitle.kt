package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class SubTitle(
    @SerializedName("canShowTitleLogo")
    val canShowTitleLogo: Boolean,
    @SerializedName("title")
    val title: String,
    @SerializedName("titleId")
    val titleId: String,
    @SerializedName("titleType")
    val titleType: String
)