package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("items")
    val items: List<ItemX>
)