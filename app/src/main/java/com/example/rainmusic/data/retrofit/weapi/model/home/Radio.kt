package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class Radio(
    @SerializedName("buyed")
    val buyed: Boolean,
    @SerializedName("category")
    val category: String,
    @SerializedName("categoryId")
    val categoryId: Int,
    @SerializedName("composeVideo")
    val composeVideo: Boolean,
    @SerializedName("createTime")
    val createTime: Long,
    @SerializedName("danmakuCount")
    val danmakuCount: Int,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("descPicList")
    val descPicList: List<DescPic>,
    @SerializedName("discountPrice")
    val discountPrice: Any,
    @SerializedName("dj")
    val dj: Any,
    @SerializedName("dynamic")
    val `dynamic`: Boolean,
    @SerializedName("extProperties")
    val extProperties: Any,
    @SerializedName("feeScope")
    val feeScope: Int,
    @SerializedName("finished")
    val finished: Boolean,
    @SerializedName("hightQuality")
    val hightQuality: Boolean,
    @SerializedName("icon")
    val icon: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("immersionAnimation")
    val immersionAnimation: Any,
    @SerializedName("immersionCover")
    val immersionCover: Any,
    @SerializedName("intervenePicId")
    val intervenePicId: Long,
    @SerializedName("intervenePicUrl")
    val intervenePicUrl: String,
    @SerializedName("lastProgramCreateTime")
    val lastProgramCreateTime: Long,
    @SerializedName("lastProgramId")
    val lastProgramId: Long,
    @SerializedName("lastProgramName")
    val lastProgramName: Any,
    @SerializedName("liveInfo")
    val liveInfo: Any,
    @SerializedName("manualTagsDTO")
    val manualTagsDTO: ManualTagsDTO,
    @SerializedName("name")
    val name: String,
    @SerializedName("original")
    val original: String,
    @SerializedName("originalPrice")
    val originalPrice: Int,
    @SerializedName("participateUidList")
    val participateUidList: List<Any>,
    @SerializedName("picId")
    val picId: Long,
    @SerializedName("picUrl")
    val picUrl: String,
    @SerializedName("playCount")
    val playCount: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("privacy")
    val privacy: Boolean,
    @SerializedName("programCount")
    val programCount: Int,
    @SerializedName("purchaseCount")
    val purchaseCount: Int,
    @SerializedName("radioFeeType")
    val radioFeeType: Int,
    @SerializedName("rcmdText")
    val rcmdText: Any,
    @SerializedName("replaceResource")
    val replaceResource: Any,
    @SerializedName("scoreInfoDTO")
    val scoreInfoDTO: Any,
    @SerializedName("secondCategory")
    val secondCategory: String,
    @SerializedName("secondCategoryId")
    val secondCategoryId: Int,
    @SerializedName("shortName")
    val shortName: Any,
    @SerializedName("specialType")
    val specialType: Int,
    @SerializedName("subCount")
    val subCount: Int,
    @SerializedName("subed")
    val subed: Boolean,
    @SerializedName("taskId")
    val taskId: Int,
    @SerializedName("underShelf")
    val underShelf: Boolean,
    @SerializedName("videos")
    val videos: Any,
    @SerializedName("whiteList")
    val whiteList: Boolean,
    @SerializedName("xInfo")
    val xInfo: Any
)