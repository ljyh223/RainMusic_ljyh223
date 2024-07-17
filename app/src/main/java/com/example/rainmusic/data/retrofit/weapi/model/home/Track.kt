package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("s_calg")
    val sCalg: String,
    @SerializedName("s_ctrp")
    val sCtrp: String
)