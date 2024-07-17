package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class RnContent(
    @SerializedName("component")
    val component: String,
    @SerializedName("engineId")
    val engineId: String,
    @SerializedName("estimatedHeight")
    val estimatedHeight: Int,
    @SerializedName("estimatedRatio")
    val estimatedRatio: String,
    @SerializedName("moduleName")
    val moduleName: String,
    @SerializedName("params")
    val params: Params
)