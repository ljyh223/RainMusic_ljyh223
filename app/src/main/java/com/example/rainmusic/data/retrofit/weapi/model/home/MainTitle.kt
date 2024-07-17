package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class MainTitle(
    @SerializedName("canShowTitleLogo")
    val canShowTitleLogo: Boolean,
    @SerializedName("title")
    val title: String
)