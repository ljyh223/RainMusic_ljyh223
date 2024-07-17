package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class HomePageBlock(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("trp")
    val trp: Trp
)