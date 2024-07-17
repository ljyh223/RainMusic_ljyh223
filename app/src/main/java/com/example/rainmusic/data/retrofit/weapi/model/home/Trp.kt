package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class Trp(
    @SerializedName("rules")
    val rules: List<String>
)