package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class ExtInfo(
    @SerializedName("banners")
    val banners: List<Banner>
)