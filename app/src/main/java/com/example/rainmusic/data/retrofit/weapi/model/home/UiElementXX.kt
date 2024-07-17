package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class UiElementXX(
    @SerializedName("button")
    val button: Button,
    @SerializedName("rcmdShowType")
    val rcmdShowType: String,
    @SerializedName("subTitle")
    val subTitle: SubTitleXX
)