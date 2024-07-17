package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("blockCodeOrderList")
    val blockCodeOrderList: Any,
    @SerializedName("blockUUIDs")
    val blockUUIDs: Any,
    @SerializedName("blocks")
    val blocks: List<Block>,
    @SerializedName("cursor")
    val cursor: String,
    @SerializedName("demote")
    val demote: Boolean,
    @SerializedName("exposedResource")
    val exposedResource: String,
    @SerializedName("guideToast")
    val guideToast: GuideToast,
    @SerializedName("hasMore")
    val hasMore: Boolean,
    @SerializedName("internalTest")
    val internalTest: Any,
    @SerializedName("pageConfig")
    val pageConfig: PageConfig,
    @SerializedName("titles")
    val titles: List<Any>
)