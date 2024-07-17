package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class UiElementX(
    @SerializedName("button")
    val button: Button,
    @SerializedName("image")
    val image: ImageX,
    @SerializedName("labelTexts")
    val labelTexts: List<String>,
    @SerializedName("mainTitle")
    val mainTitle: MainTitle,
    @SerializedName("rcmdShowType")
    val rcmdShowType: String,
    @SerializedName("subTitle")
    val subTitle: SubTitleX
)