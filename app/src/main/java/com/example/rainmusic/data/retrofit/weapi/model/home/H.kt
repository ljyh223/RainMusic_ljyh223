package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class H(
    @SerializedName("br")
    val br: Int,
    @SerializedName("fid")
    val fid: Int,
    @SerializedName("size")
    val size: Int,
    @SerializedName("sr")
    val sr: Int,
    @SerializedName("vd")
    val vd: Int
)