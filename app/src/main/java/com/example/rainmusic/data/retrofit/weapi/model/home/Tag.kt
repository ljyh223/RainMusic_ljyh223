package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("id")
    val id: Int,
    @SerializedName("jumpUrl")
    val jumpUrl: Any,
    @SerializedName("tagGuiding")
    val tagGuiding: Any,
    @SerializedName("tagImg")
    val tagImg: TagImg,
    @SerializedName("tagName")
    val tagName: String
)