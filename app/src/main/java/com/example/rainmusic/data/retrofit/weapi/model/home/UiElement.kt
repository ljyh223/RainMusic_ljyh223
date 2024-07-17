package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class UiElement(
    @SerializedName("image")
    val image: Image,
    @SerializedName("labelTexts")
    val labelTexts: List<String>,
    @SerializedName("labelType")
    val labelType: String,
    @SerializedName("mainTitle")
    val mainTitle: MainTitle,
    @SerializedName("rcmdShowType")
    val rcmdShowType: String,
    @SerializedName("subTitle")
    val subTitle: SubTitle
)