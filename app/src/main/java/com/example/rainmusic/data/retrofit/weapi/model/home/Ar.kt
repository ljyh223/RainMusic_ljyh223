package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class Ar(
    @SerializedName("alias")
    val alias: List<Any>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("tns")
    val tns: List<Any>
)