package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class HMusic(
    @SerializedName("bitrate")
    val bitrate: Int,
    @SerializedName("dfsId")
    val dfsId: Int,
    @SerializedName("extension")
    val extension: String,
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: Any,
    @SerializedName("playTime")
    val playTime: Int,
    @SerializedName("size")
    val size: Int,
    @SerializedName("sr")
    val sr: Int,
    @SerializedName("volumeDelta")
    val volumeDelta: Int
)