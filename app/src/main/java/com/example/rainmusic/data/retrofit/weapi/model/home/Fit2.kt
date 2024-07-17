package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class Fit2(
    @SerializedName("css")
    val css: String,
    @SerializedName("databinding")
    val databinding: String,
    @SerializedName("json")
    val json: String,
    @SerializedName("relationMaterials")
    val relationMaterials: Any
)