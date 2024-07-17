package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class Al(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("pic")
    val pic: Long,
    @SerializedName("pic_str")
    val picStr: String,
    @SerializedName("picUrl")
    val picUrl: String,
    @SerializedName("tns")
    val tns: List<Any>
)