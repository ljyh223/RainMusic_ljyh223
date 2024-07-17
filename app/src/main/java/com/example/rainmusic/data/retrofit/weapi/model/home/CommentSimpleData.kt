package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class CommentSimpleData(
    @SerializedName("commentId")
    val commentId: Long,
    @SerializedName("content")
    val content: String,
    @SerializedName("threadId")
    val threadId: String,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("userName")
    val userName: String
)