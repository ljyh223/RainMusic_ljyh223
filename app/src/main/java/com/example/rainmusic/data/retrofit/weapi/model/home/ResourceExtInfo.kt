package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class ResourceExtInfo(
    @SerializedName("artists")
    val artists: List<Artist>,
    @SerializedName("commentSimpleData")
    val commentSimpleData: CommentSimpleData,
    @SerializedName("djProgram")
    val djProgram: DjProgram,
    @SerializedName("hasListened")
    val hasListened: Boolean,
    @SerializedName("highQuality")
    val highQuality: Boolean,
    @SerializedName("playCount")
    val playCount: Int,
    @SerializedName("song")
    val song: Song,
    @SerializedName("songData")
    val songData: SongData,
    @SerializedName("songPrivilege")
    val songPrivilege: SongPrivilege,
    @SerializedName("specialCover")
    val specialCover: Int,
    @SerializedName("specialType")
    val specialType: Int,
    @SerializedName("users")
    val users: List<User>
)