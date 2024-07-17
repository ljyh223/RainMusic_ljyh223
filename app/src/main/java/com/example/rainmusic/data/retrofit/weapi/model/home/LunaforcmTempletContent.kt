package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class LunaforcmTempletContent(
    @SerializedName("blockId")
    val blockId: Int,
    @SerializedName("blockName")
    val blockName: String,
    @SerializedName("blockType")
    val blockType: String,
    @SerializedName("dataSources")
    val dataSources: Any,
    @SerializedName("dslMap")
    val dslMap: DslMap,
    @SerializedName("dslMapMd5")
    val dslMapMd5: Any,
    @SerializedName("dslRootId")
    val dslRootId: String,
    @SerializedName("headlessJson")
    val headlessJson: Any,
    @SerializedName("merge")
    val merge: Any,
    @SerializedName("minSupportVersion")
    val minSupportVersion: Any,
    @SerializedName("needScaleForPad")
    val needScaleForPad: Boolean,
    @SerializedName("publishTime")
    val publishTime: String,
    @SerializedName("sceneName")
    val sceneName: String,
    @SerializedName("templateConfig")
    val templateConfig: Any
)