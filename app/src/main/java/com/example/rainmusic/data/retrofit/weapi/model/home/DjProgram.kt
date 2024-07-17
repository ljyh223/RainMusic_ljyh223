package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class DjProgram(
    @SerializedName("adjustedPlayCount")
    val adjustedPlayCount: Int,
    @SerializedName("algReason")
    val algReason: Any,
    @SerializedName("auditStatus")
    val auditStatus: Int,
    @SerializedName("bdAuditStatus")
    val bdAuditStatus: Int,
    @SerializedName("blurCoverUrl")
    val blurCoverUrl: Any,
    @SerializedName("buyed")
    val buyed: Boolean,
    @SerializedName("canReward")
    val canReward: Boolean,
    @SerializedName("category")
    val category: String,
    @SerializedName("categoryId")
    val categoryId: Int,
    @SerializedName("channels")
    val channels: List<String>,
    @SerializedName("classicRelationSong")
    val classicRelationSong: Any,
    @SerializedName("commentThreadId")
    val commentThreadId: String,
    @SerializedName("coverId")
    val coverId: Long,
    @SerializedName("coverUrl")
    val coverUrl: String,
    @SerializedName("createEventId")
    val createEventId: Int,
    @SerializedName("createTime")
    val createTime: Long,
    @SerializedName("description")
    val description: String,
    @SerializedName("disPlayStatus")
    val disPlayStatus: String,
    @SerializedName("dj")
    val dj: Dj,
    @SerializedName("djPlayRecordVo")
    val djPlayRecordVo: Any,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("extProperties")
    val extProperties: Any,
    @SerializedName("h5Links")
    val h5Links: List<Any>,
    @SerializedName("icon")
    val icon: Any,
    @SerializedName("id")
    val id: Long,
    @SerializedName("latestFreeTryEndPoint")
    val latestFreeTryEndPoint: Any,
    @SerializedName("latestFreeTryStartPoint")
    val latestFreeTryStartPoint: Any,
    @SerializedName("listenerCount")
    val listenerCount: Int,
    @SerializedName("mainSong")
    val mainSong: MainSong,
    @SerializedName("mainTrackId")
    val mainTrackId: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("participateAnchors")
    val participateAnchors: Any,
    @SerializedName("playFlag")
    val playFlag: Any,
    @SerializedName("price")
    val price: Any,
    @SerializedName("privacy")
    val privacy: Boolean,
    @SerializedName("programDesc")
    val programDesc: Any,
    @SerializedName("programFeeType")
    val programFeeType: Int,
    @SerializedName("pubStatus")
    val pubStatus: Int,
    @SerializedName("publish")
    val publish: Boolean,
    @SerializedName("radio")
    val radio: Radio,
    @SerializedName("replaceResource")
    val replaceResource: Any,
    @SerializedName("reward")
    val reward: Boolean,
    @SerializedName("scheduledPublishTime")
    val scheduledPublishTime: Long,
    @SerializedName("secondCategory")
    val secondCategory: String,
    @SerializedName("secondCategoryId")
    val secondCategoryId: Int,
    @SerializedName("serialNum")
    val serialNum: Int,
    @SerializedName("shortName")
    val shortName: Any,
    @SerializedName("showAlgReason")
    val showAlgReason: Any,
    @SerializedName("songTimeStamps")
    val songTimeStamps: Any,
    @SerializedName("songs")
    val songs: Any,
    @SerializedName("specialTags")
    val specialTags: Any,
    @SerializedName("specialType")
    val specialType: Int,
    @SerializedName("subscribedCount")
    val subscribedCount: Int,
    @SerializedName("titbitImages")
    val titbitImages: Any,
    @SerializedName("titbits")
    val titbits: Any,
    @SerializedName("trackCount")
    val trackCount: Int,
    @SerializedName("updateTime")
    val updateTime: Long,
    @SerializedName("userId")
    val userId: Long,
    @SerializedName("xInfo")
    val xInfo: Any
)