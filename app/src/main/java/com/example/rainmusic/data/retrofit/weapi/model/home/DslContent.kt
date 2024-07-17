package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class DslContent(
    @SerializedName("lunaforcmLoadType")
    val lunaforcmLoadType: String,
    @SerializedName("lunaforcmSence")
    val lunaforcmSence: String,
    @SerializedName("lunaforcmTempletContent")
    val lunaforcmTempletContent: LunaforcmTempletContent,
    @SerializedName("lunaforcmTempletId")
    val lunaforcmTempletId: String
)