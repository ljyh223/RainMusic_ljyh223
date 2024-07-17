package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class Song(
    @SerializedName("a")
    val a: Any,
    @SerializedName("al")
    val al: Al,
    @SerializedName("alia")
    val alia: List<String>,
    @SerializedName("ar")
    val ar: List<Ar>,
    @SerializedName("cd")
    val cd: String,
    @SerializedName("cf")
    val cf: String,
    @SerializedName("copyright")
    val copyright: Int,
    @SerializedName("cp")
    val cp: Int,
    @SerializedName("crbt")
    val crbt: Any,
    @SerializedName("djId")
    val djId: Int,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("entertainmentTags")
    val entertainmentTags: Any,
    @SerializedName("fee")
    val fee: Int,
    @SerializedName("ftype")
    val ftype: Int,
    @SerializedName("h")
    val h: H,
    @SerializedName("hr")
    val hr: Hr,
    @SerializedName("id")
    val id: Long,
    @SerializedName("l")
    val l: L,
    @SerializedName("m")
    val m: M,
    @SerializedName("mark")
    val mark: Long,
    @SerializedName("mst")
    val mst: Int,
    @SerializedName("mv")
    val mv: Int,
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
    @SerializedName("pop")
    val pop: Int,
    @SerializedName("pst")
    val pst: Int,
    @SerializedName("publishTime")
    val publishTime: Long,
    @SerializedName("resourceState")
    val resourceState: Boolean,
    @SerializedName("rt")
    val rt: String,
    @SerializedName("rtUrl")
    val rtUrl: Any,
    @SerializedName("rtUrls")
    val rtUrls: List<Any>,
    @SerializedName("rtype")
    val rtype: Int,
    @SerializedName("rurl")
    val rurl: Any,
    @SerializedName("s_id")
    val sId: Long,
    @SerializedName("single")
    val single: Int,
    @SerializedName("songJumpInfo")
    val songJumpInfo: Any,
    @SerializedName("sq")
    val sq: Sq,
    @SerializedName("st")
    val st: Int,
    @SerializedName("t")
    val t: Int,
    @SerializedName("tagPicList")
    val tagPicList: Any,
    @SerializedName("tns")
    val tns: List<String>,
    @SerializedName("v")
    val v: Int,
    @SerializedName("version")
    val version: Int,
    @SerializedName("videoInfo")
    val videoInfo: VideoInfo
)