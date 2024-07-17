package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class AlertConfig(
    @SerializedName("alertType")
    val alertType: Any,
    @SerializedName("hPadding")
    val hPadding: Int,
    @SerializedName("hpadding")
    val hpadding: Int,
    @SerializedName("needCloseBtn")
    val needCloseBtn: Boolean,
    @SerializedName("widthExpand")
    val widthExpand: Boolean
)