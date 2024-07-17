package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class ContentDescTags(
    @SerializedName("id")
    val id: Int,
    @SerializedName("tagGroupName")
    val tagGroupName: Any,
    @SerializedName("tagList")
    val tagList: List<Tag>
)