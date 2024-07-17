package com.example.rainmusic.data.retrofit.weapi.model




//data class NewSongs(
//    @SerializedName("category")
//    val category: Int,
//    @SerializedName("code")
//    val code: Int,
//    @SerializedName("result")
//    val result: List<Result>
//) {
//    data class Result(
//        @SerializedName("alg")
//        val alg: String,
//        @SerializedName("canDislike")
//        val canDislike: Boolean,
//        @SerializedName("copywriter")
//        val copywriter: Any,
//        @SerializedName("id")
//        val id: Long,
//        @SerializedName("name")
//        val name: String,
//        @SerializedName("picUrl")
//        val picUrl: String,
//        @SerializedName("song")
//        val song: Song,
//        @SerializedName("trackNumberUpdateTime")
//        val trackNumberUpdateTime: Any,
//        @SerializedName("type")
//        val type: Int
//    ) {
//        data class Song(
//            @SerializedName("album")
//            val album: Album,
//            @SerializedName("alias")
//            val alias: List<String>,
//            @SerializedName("artists")
//            val artists: List<Artist>,
//            @SerializedName("audition")
//            val audition: Any,
//            @SerializedName("bMusic")
//            val bMusic: BMusic,
//            @SerializedName("commentThreadId")
//            val commentThreadId: String,
//            @SerializedName("copyFrom")
//            val copyFrom: String,
//            @SerializedName("copyright")
//            val copyright: Int,
//            @SerializedName("copyrightId")
//            val copyrightId: Int,
//            @SerializedName("crbt")
//            val crbt: Any,
//            @SerializedName("dayPlays")
//            val dayPlays: Int,
//            @SerializedName("disc")
//            val disc: String,
//            @SerializedName("duration")
//            val duration: Int,
//            @SerializedName("exclusive")
//            val exclusive: Boolean,
//            @SerializedName("fee")
//            val fee: Int,
//            @SerializedName("ftype")
//            val ftype: Int,
//            @SerializedName("hMusic")
//            val hMusic: HMusic,
//            @SerializedName("hearTime")
//            val hearTime: Int,
//            @SerializedName("id")
//            val id: Int,
//            @SerializedName("lMusic")
//            val lMusic: LMusic,
//            @SerializedName("mMusic")
//            val mMusic: MMusic,
//            @SerializedName("mark")
//            val mark: Int,
//            @SerializedName("mp3Url")
//            val mp3Url: Any,
//            @SerializedName("mvid")
//            val mvid: Int,
//            @SerializedName("name")
//            val name: String,
//            @SerializedName("no")
//            val no: Int,
//            @SerializedName("noCopyrightRcmd")
//            val noCopyrightRcmd: Any,
//            @SerializedName("originCoverType")
//            val originCoverType: Int,
//            @SerializedName("originSongSimpleData")
//            val originSongSimpleData: Any,
//            @SerializedName("playedNum")
//            val playedNum: Int,
//            @SerializedName("popularity")
//            val popularity: Double,
//            @SerializedName("position")
//            val position: Int,
//            @SerializedName("privilege")
//            val privilege: Privilege,
//            @SerializedName("ringtone")
//            val ringtone: String,
//            @SerializedName("rtUrl")
//            val rtUrl: Any,
//            @SerializedName("rtUrls")
//            val rtUrls: List<Any>,
//            @SerializedName("rtype")
//            val rtype: Int,
//            @SerializedName("rurl")
//            val rurl: Any,
//            @SerializedName("score")
//            val score: Int,
//            @SerializedName("sign")
//            val sign: Any,
//            @SerializedName("single")
//            val single: Int,
//            @SerializedName("starred")
//            val starred: Boolean,
//            @SerializedName("starredNum")
//            val starredNum: Int,
//            @SerializedName("status")
//            val status: Int,
//            @SerializedName("transName")
//            val transName: Any
//        ) {
//            data class Album(
//                @SerializedName("alias")
//                val alias: List<Any>,
//                @SerializedName("artist")
//                val artist: Artist,
//                @SerializedName("artists")
//                val artists: List<Artist>,
//                @SerializedName("blurPicUrl")
//                val blurPicUrl: String,
//                @SerializedName("briefDesc")
//                val briefDesc: String,
//                @SerializedName("commentThreadId")
//                val commentThreadId: String,
//                @SerializedName("company")
//                val company: String,
//                @SerializedName("companyId")
//                val companyId: Int,
//                @SerializedName("copyrightId")
//                val copyrightId: Int,
//                @SerializedName("description")
//                val description: String,
//                @SerializedName("id")
//                val id: Int,
//                @SerializedName("mark")
//                val mark: Int,
//                @SerializedName("name")
//                val name: String,
//                @SerializedName("onSale")
//                val onSale: Boolean,
//                @SerializedName("pic")
//                val pic: Long,
//                @SerializedName("picId")
//                val picId: Long,
//                @SerializedName("picId_str")
//                val picIdStr: String,
//                @SerializedName("picUrl")
//                val picUrl: String,
//                @SerializedName("publishTime")
//                val publishTime: Long,
//                @SerializedName("size")
//                val size: Int,
//                @SerializedName("songs")
//                val songs: List<Any>,
//                @SerializedName("status")
//                val status: Int,
//                @SerializedName("subType")
//                val subType: String,
//                @SerializedName("tags")
//                val tags: String,
//                @SerializedName("transName")
//                val transName: Any,
//                @SerializedName("type")
//                val type: String
//            ) {
//                data class Artist(
//                    @SerializedName("albumSize")
//                    val albumSize: Int,
//                    @SerializedName("alias")
//                    val alias: List<Any>,
//                    @SerializedName("briefDesc")
//                    val briefDesc: String,
//                    @SerializedName("id")
//                    val id: Int,
//                    @SerializedName("img1v1Id")
//                    val img1v1Id: Int,
//                    @SerializedName("img1v1Url")
//                    val img1v1Url: String,
//                    @SerializedName("musicSize")
//                    val musicSize: Int,
//                    @SerializedName("name")
//                    val name: String,
//                    @SerializedName("picId")
//                    val picId: Int,
//                    @SerializedName("picUrl")
//                    val picUrl: String,
//                    @SerializedName("topicPerson")
//                    val topicPerson: Int,
//                    @SerializedName("trans")
//                    val trans: String
//                )
//            }
//
//            data class Artist(
//                @SerializedName("albumSize")
//                val albumSize: Int,
//                @SerializedName("alias")
//                val alias: List<Any>,
//                @SerializedName("briefDesc")
//                val briefDesc: String,
//                @SerializedName("id")
//                val id: Int,
//                @SerializedName("img1v1Id")
//                val img1v1Id: Int,
//                @SerializedName("img1v1Url")
//                val img1v1Url: String,
//                @SerializedName("musicSize")
//                val musicSize: Int,
//                @SerializedName("name")
//                val name: String,
//                @SerializedName("picId")
//                val picId: Int,
//                @SerializedName("picUrl")
//                val picUrl: String,
//                @SerializedName("topicPerson")
//                val topicPerson: Int,
//                @SerializedName("trans")
//                val trans: String
//            )
//
//            data class BMusic(
//                @SerializedName("bitrate")
//                val bitrate: Int,
//                @SerializedName("dfsId")
//                val dfsId: Int,
//                @SerializedName("extension")
//                val extension: String,
//                @SerializedName("id")
//                val id: Long,
//                @SerializedName("name")
//                val name: Any,
//                @SerializedName("playTime")
//                val playTime: Int,
//                @SerializedName("size")
//                val size: Int,
//                @SerializedName("sr")
//                val sr: Int,
//                @SerializedName("volumeDelta")
//                val volumeDelta: Double
//            )
//
//            data class HMusic(
//                @SerializedName("bitrate")
//                val bitrate: Int,
//                @SerializedName("dfsId")
//                val dfsId: Int,
//                @SerializedName("extension")
//                val extension: String,
//                @SerializedName("id")
//                val id: Long,
//                @SerializedName("name")
//                val name: Any,
//                @SerializedName("playTime")
//                val playTime: Int,
//                @SerializedName("size")
//                val size: Int,
//                @SerializedName("sr")
//                val sr: Int,
//                @SerializedName("volumeDelta")
//                val volumeDelta: Double
//            )
//
//            data class LMusic(
//                @SerializedName("bitrate")
//                val bitrate: Int,
//                @SerializedName("dfsId")
//                val dfsId: Int,
//                @SerializedName("extension")
//                val extension: String,
//                @SerializedName("id")
//                val id: Long,
//                @SerializedName("name")
//                val name: Any,
//                @SerializedName("playTime")
//                val playTime: Int,
//                @SerializedName("size")
//                val size: Int,
//                @SerializedName("sr")
//                val sr: Int,
//                @SerializedName("volumeDelta")
//                val volumeDelta: Double
//            )
//
//            data class MMusic(
//                @SerializedName("bitrate")
//                val bitrate: Int,
//                @SerializedName("dfsId")
//                val dfsId: Int,
//                @SerializedName("extension")
//                val extension: String,
//                @SerializedName("id")
//                val id: Long,
//                @SerializedName("name")
//                val name: Any,
//                @SerializedName("playTime")
//                val playTime: Int,
//                @SerializedName("size")
//                val size: Int,
//                @SerializedName("sr")
//                val sr: Int,
//                @SerializedName("volumeDelta")
//                val volumeDelta: Double
//            )
//
//            data class Privilege(
//                @SerializedName("chargeInfoList")
//                val chargeInfoList: List<ChargeInfo>,
//                @SerializedName("cp")
//                val cp: Int,
//                @SerializedName("cs")
//                val cs: Boolean,
//                @SerializedName("dl")
//                val dl: Int,
//                @SerializedName("downloadMaxbr")
//                val downloadMaxbr: Int,
//                @SerializedName("fee")
//                val fee: Int,
//                @SerializedName("fl")
//                val fl: Int,
//                @SerializedName("flag")
//                val flag: Int,
//                @SerializedName("freeTrialPrivilege")
//                val freeTrialPrivilege: FreeTrialPrivilege,
//                @SerializedName("id")
//                val id: Int,
//                @SerializedName("maxbr")
//                val maxbr: Int,
//                @SerializedName("payed")
//                val payed: Int,
//                @SerializedName("pl")
//                val pl: Int,
//                @SerializedName("playMaxbr")
//                val playMaxbr: Int,
//                @SerializedName("preSell")
//                val preSell: Boolean,
//                @SerializedName("rscl")
//                val rscl: Any,
//                @SerializedName("sp")
//                val sp: Int,
//                @SerializedName("st")
//                val st: Int,
//                @SerializedName("subp")
//                val subp: Int,
//                @SerializedName("toast")
//                val toast: Boolean
//            ) {
//                data class ChargeInfo(
//                    @SerializedName("chargeMessage")
//                    val chargeMessage: Any,
//                    @SerializedName("chargeType")
//                    val chargeType: Int,
//                    @SerializedName("chargeUrl")
//                    val chargeUrl: Any,
//                    @SerializedName("rate")
//                    val rate: Int
//                )
//
//                data class FreeTrialPrivilege(
//                    @SerializedName("resConsumable")
//                    val resConsumable: Boolean,
//                    @SerializedName("userConsumable")
//                    val userConsumable: Boolean
//                )
//            }
//        }
//    }
//}

data class NewSongs(
    val category: Int,
    val code: Int,
    val result: List<Result>
)

data class Result(
    val alg: String,
    val canDislike: Boolean,
    val copywriter: Any,
    val id: Long,
    val name: String,
    val picUrl: String,
    val song: Song,
    val trackNumberUpdateTime: Any,
    val type: Int
)

data class Song(
    val album: Album,
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
    val exclusive: Boolean,
    val fee: Int,
    val ftype: Int,
    val hMusic: HMusic,
    val hearTime: Int,
    val hrMusic: HrMusic,
    val id: Long,
    val lMusic: LMusic,
    val mMusic: MMusic,
    val mark: Int,
    val mp3Url: Any,
    val mvid: Int,
    val name: String,
    val no: Int,
    val noCopyrightRcmd: Any,
    val originCoverType: Int,
    val originSongSimpleData: OriginSongSimpleData,
    val playedNum: Int,
    val popularity: Int,
    val position: Int,
    val privilege: Privilege,
    val ringtone: String,
    val rtUrl: Any,
    val rtUrls: List<Any>,
    val rtype: Int,
    val rurl: Any,
    val score: Int,
    val sign: Any,
    val single: Int,
    val sqMusic: SqMusic,
    val starred: Boolean,
    val starredNum: Int,
    val status: Int,
    val transName: String,
    val transNames: List<String>
)

data class Album(
    val alias: List<String>,
    val artist: ArtistXX,
    val artists: List<ArtistXX>,
    val blurPicUrl: String,
    val briefDesc: String,
    val commentThreadId: String,
    val company: String,
    val companyId: Int,
    val copyrightId: Int,
    val description: String,
    val gapless: Int,
    val id: Int,
    val mark: Int,
    val name: String,
    val onSale: Boolean,
    val pic: Long,
    val picId: Long,
    val picId_str: String,
    val picUrl: String,
    val publishTime: Long,
    val size: Int,
    val songs: List<Any>,
    val status: Int,
    val subType: String,
    val tags: String,
    val transName: String,
    val transNames: List<String>,
    val type: String
)

data class ArtistXX(
    val albumSize: Int,
    val alias: List<Any>,
    val briefDesc: String,
    val id: Int,
    val img1v1Id: Int,
    val img1v1Url: String,
    val musicSize: Int,
    val name: String,
    val picId: Int,
    val picUrl: String,
    val topicPerson: Int,
    val trans: String
)

data class BMusic(
    val bitrate: Int,
    val dfsId: Int,
    val extension: String,
    val id: Long,
    val name: Any,
    val playTime: Int,
    val size: Int,
    val sr: Int,
    val volumeDelta: Int
)

data class HMusic(
    val bitrate: Int,
    val dfsId: Int,
    val extension: String,
    val id: Long,
    val name: Any,
    val playTime: Int,
    val size: Int,
    val sr: Int,
    val volumeDelta: Int
)

data class HrMusic(
    val bitrate: Int,
    val dfsId: Int,
    val extension: String,
    val id: Long,
    val name: Any,
    val playTime: Int,
    val size: Int,
    val sr: Int,
    val volumeDelta: Int
)

data class LMusic(
    val bitrate: Int,
    val dfsId: Int,
    val extension: String,
    val id: Long,
    val name: Any,
    val playTime: Int,
    val size: Int,
    val sr: Int,
    val volumeDelta: Int
)

data class MMusic(
    val bitrate: Int,
    val dfsId: Int,
    val extension: String,
    val id: Long,
    val name: Any,
    val playTime: Int,
    val size: Int,
    val sr: Int,
    val volumeDelta: Int
)

data class OriginSongSimpleData(
    val albumMeta: AlbumMeta,
    val artists: List<ArtistXXX>,
    val name: String,
    val songId: Long
)

data class Privilege(
    val chargeInfoList: List<ChargeInfo>,
    val cp: Int,
    val cs: Boolean,
    val dl: Int,
    val dlLevel: String,
    val downloadMaxBrLevel: String,
    val downloadMaxbr: Int,
    val fee: Int,
    val fl: Int,
    val flLevel: String,
    val flag: Int,
    val freeTrialPrivilege: FreeTrialPrivilege,
    val id: Long,
    val maxBrLevel: String,
    val maxbr: Int,
    val payed: Int,
    val pl: Int,
    val plLevel: String,
    val playMaxBrLevel: String,
    val playMaxbr: Int,
    val preSell: Boolean,
    val rightSource: Int,
    val rscl: Any,
    val sp: Int,
    val st: Int,
    val subp: Int,
    val toast: Boolean
)

data class SqMusic(
    val bitrate: Int,
    val dfsId: Int,
    val extension: String,
    val id: Long,
    val name: Any,
    val playTime: Int,
    val size: Int,
    val sr: Int,
    val volumeDelta: Int
)

data class AlbumMeta(
    val id: Int,
    val name: String
)

data class ArtistXXX(
    val id: Int,
    val name: String
)

data class ChargeInfo(
    val chargeMessage: Any,
    val chargeType: Int,
    val chargeUrl: Any,
    val rate: Int
)

data class FreeTrialPrivilege(
    val cannotListenReason: Any,
    val listenType: Any,
    val playReason: Any,
    val resConsumable: Boolean,
    val userConsumable: Boolean
)