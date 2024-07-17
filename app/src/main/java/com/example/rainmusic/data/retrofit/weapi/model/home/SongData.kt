package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class SongData(
    @SerializedName("album")
    val album: AlbumX,
    @SerializedName("alias")
    val alias: List<String>,
    @SerializedName("artists")
    val artists: List<Artist>,
    @SerializedName("audition")
    val audition: Any,
    @SerializedName("bMusic")
    val bMusic: BMusic,
    @SerializedName("commentThreadId")
    val commentThreadId: String,
    @SerializedName("copyFrom")
    val copyFrom: String,
    @SerializedName("copyright")
    val copyright: Int,
    @SerializedName("copyrightId")
    val copyrightId: Int,
    @SerializedName("crbt")
    val crbt: Any,
    @SerializedName("dayPlays")
    val dayPlays: Int,
    @SerializedName("disc")
    val disc: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("fee")
    val fee: Int,
    @SerializedName("ftype")
    val ftype: Int,
    @SerializedName("hMusic")
    val hMusic: HMusic,
    @SerializedName("hearTime")
    val hearTime: Int,
    @SerializedName("hrMusic")
    val hrMusic: HrMusic,
    @SerializedName("id")
    val id: Long,
    @SerializedName("lMusic")
    val lMusic: LMusic,
    @SerializedName("mMusic")
    val mMusic: MMusic,
    @SerializedName("mark")
    val mark: Int,
    @SerializedName("mp3Url")
    val mp3Url: Any,
    @SerializedName("mvid")
    val mvid: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("no")
    val no: Int,
    @SerializedName("noCopyrightRcmd")
    val noCopyrightRcmd: Any,
    @SerializedName("originCoverType")
    val originCoverType: Int,
    @SerializedName("originSongSimpleData")
    val originSongSimpleData: Any,
    @SerializedName("playedNum")
    val playedNum: Int,
    @SerializedName("popularity")
    val popularity: Int,
    @SerializedName("position")
    val position: Int,
    @SerializedName("ringtone")
    val ringtone: String,
    @SerializedName("rtUrl")
    val rtUrl: Any,
    @SerializedName("rtUrls")
    val rtUrls: List<Any>,
    @SerializedName("rtype")
    val rtype: Int,
    @SerializedName("rurl")
    val rurl: Any,
    @SerializedName("score")
    val score: Int,
    @SerializedName("sign")
    val sign: Any,
    @SerializedName("single")
    val single: Int,
    @SerializedName("sqMusic")
    val sqMusic: SqMusic,
    @SerializedName("starred")
    val starred: Boolean,
    @SerializedName("starredNum")
    val starredNum: Int,
    @SerializedName("status")
    val status: Int,
    @SerializedName("transName")
    val transName: String,
    @SerializedName("transNames")
    val transNames: List<String>
)