package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class User(
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
    val avatarImgId: Int,
    @SerializedName("avatarImgIdStr")
    val avatarImgIdStr: String,
    @SerializedName("avatarUrl")
    val avatarUrl: String,
    @SerializedName("backgroundImgId")
    val backgroundImgId: Int,
    @SerializedName("backgroundImgIdStr")
    val backgroundImgIdStr: String,
    @SerializedName("backgroundUrl")
    val backgroundUrl: Any,
    @SerializedName("birthday")
    val birthday: Int,
    @SerializedName("city")
    val city: Int,
    @SerializedName("defaultAvatar")
    val defaultAvatar: Boolean,
    @SerializedName("description")
    val description: Any,
    @SerializedName("detailDescription")
    val detailDescription: Any,
    @SerializedName("djStatus")
    val djStatus: Int,
    @SerializedName("expertTags")
    val expertTags: Any,
    @SerializedName("experts")
    val experts: Any,
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
    val signature: Any,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("userType")
    val userType: Int,
    @SerializedName("vipType")
    val vipType: Int
)