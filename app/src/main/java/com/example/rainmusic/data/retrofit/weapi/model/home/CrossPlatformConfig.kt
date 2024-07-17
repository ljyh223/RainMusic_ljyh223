package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class CrossPlatformConfig(
    @SerializedName("alertConfig")
    val alertConfig: AlertConfig,
    @SerializedName("containerType")
    val containerType: String,
    @SerializedName("dslContent")
    val dslContent: DslContent,
    @SerializedName("rnContent")
    val rnContent: RnContent
)