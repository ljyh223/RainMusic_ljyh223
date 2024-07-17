package com.example.rainmusic.data.retrofit.weapi.model



data class PersonalFM(
    val code: Int,
    val `data`: List<Data>,
    val popAdjust: Boolean,
    val tag: Any
)

data class Data(
    val album: Album,
    val alg: String,
    val alias: List<String>,
    val artists: List<ArtistXX>,
    val audition: Any,
    val bMusic: BMusic,
    val commentThreadId: String,
    val copyFrom: String,
    val copyright: Int,
    val copyrightId: Int,
    val crbt: Any,
    val dayPlays: Int,
    val disc: String,
    val duration: Int,
    val fee: Int,
    val ftype: Int,
    val hMusic: HMusic,
    val hearTime: Int,
    val id: Long,
    val lMusic: LMusic,
    val mMusic: MMusic,
    val mp3Url: Any,
    val mvid: Int,
    val name: String,
    val no: Int,
    val playedNum: Int,
    val popularity: Int,
    val position: Int,
    val privilege: Privilege,
    val reason: String,
    val reasonId: String,
    val ringtone: String,
    val rtUrl: Any,
    val rtUrls: List<Any>,
    val rtype: Int,
    val rurl: Any,
    val s_ctrp: String,
    val score: Int,
    val sign: Any,
    val starred: Boolean,
    val starredNum: Int,
    val status: Int,
    val transName: String,
    val transNames: List<String>
)

