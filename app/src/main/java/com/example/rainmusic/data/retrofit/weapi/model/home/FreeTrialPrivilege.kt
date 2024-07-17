package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class FreeTrialPrivilege(
    @SerializedName("cannotListenReason")
    val cannotListenReason: Any,
    @SerializedName("listenType")
    val listenType: Any,
    @SerializedName("playReason")
    val playReason: Any,
    @SerializedName("resConsumable")
    val resConsumable: Boolean,
    @SerializedName("userConsumable")
    val userConsumable: Boolean
)