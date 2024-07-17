package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class Dj(
    @SerializedName("accountStatus")
    val accountStatus: Int,
    @SerializedName("anchor")
    val anchor: Boolean,
    @SerializedName("authStatus")
    val authStatus: Int,
    @SerializedName("authenticationTypes")
    val authenticationTypes: Int,
    @SerializedName("authority")
    val authority: Int,
    @SerializedName("avatarDetail")
    val avatarDetail: Any,
    @SerializedName("avatarImgId")
    val avatarImgId: Long,
    @SerializedName("avatarImgIdStr")
    val avatarImgIdStr: String,
    @SerializedName("avatarUrl")
    val avatarUrl: String,
    @SerializedName("backgroundImgId")
    val backgroundImgId: Long,
    @SerializedName("backgroundImgIdStr")
    val backgroundImgIdStr: String,
    @SerializedName("backgroundUrl")
    val backgroundUrl: String,
    @SerializedName("birthday")
    val birthday: Long,
    @SerializedName("city")
    val city: Int,
    @SerializedName("defaultAvatar")
    val defaultAvatar: Boolean,
    @SerializedName("description")
    val description: String,
    @SerializedName("detailDescription")
    val detailDescription: String,
    @SerializedName("djStatus")
    val djStatus: Int,
    @SerializedName("expertTags")
    val expertTags: Any,
    @SerializedName("experts")
    val experts: Experts,
    @SerializedName("followed")
    val followed: Boolean,
    @SerializedName("gender")
    val gender: Int,
    @SerializedName("mutual")
    val mutual: Boolean,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("province")
    val province: Int,
    @SerializedName("remarkName")
    val remarkName: Any,
    @SerializedName("signature")
    val signature: String,
    @SerializedName("userId")
    val userId: Long,
    @SerializedName("userType")
    val userType: Int,
    @SerializedName("vipType")
    val vipType: Int
)