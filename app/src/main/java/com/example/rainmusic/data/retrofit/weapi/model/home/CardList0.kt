package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class CardList0(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("track")
    val track: TrackX
)