package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class ThemeDescTags(
    @SerializedName("id")
    val id: Int,
    @SerializedName("tagGroupName")
    val tagGroupName: Any,
    @SerializedName("tagList")
    val tagList: List<TagX>
)