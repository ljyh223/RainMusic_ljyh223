package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class Creative(
    @SerializedName("action")
    val action: String,
    @SerializedName("actionType")
    val actionType: String,
    @SerializedName("alg")
    val alg: String,
    @SerializedName("creativeId")
    val creativeId: String,
    @SerializedName("creativeType")
    val creativeType: String,
    @SerializedName("logInfo")
    val logInfo: String,
    @SerializedName("position")
    val position: Int,
    @SerializedName("resources")
    val resources: List<Resource>,
    @SerializedName("uiElement")
    val uiElement: UiElementX
)