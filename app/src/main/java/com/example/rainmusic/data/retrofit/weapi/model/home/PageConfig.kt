package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class PageConfig(
    @SerializedName("abtest")
    val abtest: List<String>,
    @SerializedName("fullscreen")
    val fullscreen: Boolean,
    @SerializedName("homepageMode")
    val homepageMode: String,
    @SerializedName("nodataToast")
    val nodataToast: String,
    @SerializedName("orderInfo")
    val orderInfo: String,
    @SerializedName("refreshInterval")
    val refreshInterval: Int,
    @SerializedName("refreshToast")
    val refreshToast: String,
    @SerializedName("showModeEntry")
    val showModeEntry: Boolean,
    @SerializedName("songLabelMarkLimit")
    val songLabelMarkLimit: Int,
    @SerializedName("songLabelMarkPriority")
    val songLabelMarkPriority: List<String>,
    @SerializedName("title")
    val title: Any
)