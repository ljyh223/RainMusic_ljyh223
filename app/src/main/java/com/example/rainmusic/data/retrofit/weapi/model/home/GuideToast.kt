package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class GuideToast(
    @SerializedName("hasGuideToast")
    val hasGuideToast: Boolean,
    @SerializedName("toastList")
    val toastList: List<Any>
)