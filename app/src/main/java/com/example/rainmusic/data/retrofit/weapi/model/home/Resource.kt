package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class Resource(
    @SerializedName("action")
    val action: String,
    @SerializedName("actionType")
    val actionType: String,
    @SerializedName("alg")
    val alg: String,
    @SerializedName("ctrp")
    val ctrp: String,
    @SerializedName("likedCount")
    val likedCount: Any,
    @SerializedName("logInfo")
    val logInfo: String,
    @SerializedName("playParams")
    val playParams: PlayParams ? =null,
    @SerializedName("position")
    val position: Any,
    @SerializedName("replyCount")
    val replyCount: Any,
    @SerializedName("resourceContentList")
    val resourceContentList: Any,
    @SerializedName("resourceExtInfo")
    val resourceExtInfo: ResourceExtInfo,
    @SerializedName("resourceId")
    val resourceId: String,
    @SerializedName("resourceState")
    val resourceState: Any,
    @SerializedName("resourceType")
    val resourceType: String,
    @SerializedName("resourceUrl")
    val resourceUrl: Any,
    @SerializedName("uiElement")
    val uiElement: UiElement,
    @SerializedName("valid")
    val valid: Boolean
)

data class PlayParams (
    val playerType: String,
    val showUI: Boolean,
    val resourceIDS: List<String>
)