package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class Button(
    @SerializedName("action")
    val action: String,
    @SerializedName("actionType")
    val actionType: String,
    @SerializedName("biData")
    val biData: Any,
    @SerializedName("iconUrl")
    val iconUrl: Any,
    @SerializedName("text")
    val text: String
)