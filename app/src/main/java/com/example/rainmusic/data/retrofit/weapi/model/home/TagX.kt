package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class TagX(
    @SerializedName("id")
    val id: Int,
    @SerializedName("jumpUrl")
    val jumpUrl: String,
    @SerializedName("tagGuiding")
    val tagGuiding: Any,
    @SerializedName("tagImg")
    val tagImg: TagImg,
    @SerializedName("tagName")
    val tagName: String
)