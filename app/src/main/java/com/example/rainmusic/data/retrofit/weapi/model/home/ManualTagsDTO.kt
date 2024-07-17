package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class ManualTagsDTO(
    @SerializedName("brandColumnTags")
    val brandColumnTags: Any,
    @SerializedName("contentDescTags")
    val contentDescTags: ContentDescTags,
    @SerializedName("hotTags")
    val hotTags: Any,
    @SerializedName("themeDescTags")
    val themeDescTags: ThemeDescTags
)