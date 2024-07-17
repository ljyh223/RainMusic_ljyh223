package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class TrackXXXX(
    @SerializedName("is_syscache")
    val isSyscache: String,
    @SerializedName("oid")
    val oid: String,
    @SerializedName("s_calg")
    val sCalg: String,
    @SerializedName("s_cid")
    val sCid: String,
    @SerializedName("s_ctrp")
    val sCtrp: String,
    @SerializedName("title")
    val title: String
)