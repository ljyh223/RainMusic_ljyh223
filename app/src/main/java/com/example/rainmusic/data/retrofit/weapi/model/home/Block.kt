package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class Block(
    @SerializedName("action")
    val action: String,
    @SerializedName("actionType")
    val actionType: String,
    @SerializedName("blockCode")
    val blockCode: String,
    @SerializedName("blockDemote")
    val blockDemote: Boolean,
    @SerializedName("blockStyle")
    val blockStyle: Int,
    @SerializedName("canClose")
    val canClose: Boolean,
    @SerializedName("canFeedback")
    val canFeedback: Boolean,
    @SerializedName("creatives")
    val creatives: List<Creative>,
    @SerializedName("crossPlatformConfig")
    val crossPlatformConfig: CrossPlatformConfig,
    @SerializedName("dislikeShowType")
    val dislikeShowType: Int,
    @SerializedName("dslData")
    val dslData: DslData,
    @SerializedName("extInfo")
    val extInfo: ExtInfo,
    @SerializedName("resourceIdList")
    val resourceIdList: List<String>,
    @SerializedName("showType")
    val showType: String,
    @SerializedName("sort")
    val sort: Int,
    @SerializedName("uiElement")
    val uiElement: UiElementXX
)