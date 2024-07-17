package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("albumSize")
    val albumSize: Int,
    @SerializedName("alias")
    val alias: List<Any>,
    @SerializedName("briefDesc")
    val briefDesc: String,
    @SerializedName("id")
    val id: Long,
    @SerializedName("img1v1Id")
    val img1v1Id: Int,
    @SerializedName("img1v1Url")
    val img1v1Url: String,
    @SerializedName("musicSize")
    val musicSize: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("picId")
    val picId: Long,
    @SerializedName("picUrl")
    val picUrl: String,
    @SerializedName("topicPerson")
    val topicPerson: Int,
    @SerializedName("trans")
    val trans: String
)