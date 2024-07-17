package com.example.rainmusic.data.retrofit.api.model


//data class PlaylistDetail(
//    @SerializedName("code")
//    val code: Int,
//    @SerializedName("playlist")
//    val playlist: Playlist,
//    @SerializedName("privileges")
//    val privileges: List<Privilege>,
//    @SerializedName("relatedVideos")
//    val relatedVideos: Any,
//    @SerializedName("resEntrance")
//    val resEntrance: Any,
//    @SerializedName("sharedPrivilege")
//    val sharedPrivilege: Any,
//    @SerializedName("urls")
//    val urls: Any
//) {
//    data class Playlist(
//        @SerializedName("adType")
//        val adType: Int,
//        @SerializedName("backgroundCoverId")
//        val backgroundCoverId: Long,
//        @SerializedName("backgroundCoverUrl")
//        val backgroundCoverUrl: String,
//        @SerializedName("cloudTrackCount")
//        val cloudTrackCount: Int,
//        @SerializedName("commentCount")
//        val commentCount: Int,
//        @SerializedName("commentThreadId")
//        val commentThreadId: String,
//        @SerializedName("coverImgId")
//        val coverImgId: Long,
//        @SerializedName("coverImgId_str")
//        val coverImgIdStr: String,
//        @SerializedName("coverImgUrl")
//        val coverImgUrl: String,
//        @SerializedName("createTime")
//        val createTime: Long,
//        @SerializedName("creator")
//        val creator: Creator,
//        @SerializedName("description")
//        val description: String,
//        @SerializedName("englishTitle")
//        val englishTitle: String,
//        @SerializedName("highQuality")
//        val highQuality: Boolean,
//        @SerializedName("historySharedUsers")
//        val historySharedUsers: Any,
//        @SerializedName("id")
//        val id: Long,
//        @SerializedName("name")
//        val name: String,
//        @SerializedName("newImported")
//        val newImported: Boolean,
//        @SerializedName("officialPlaylistType")
//        val officialPlaylistType: String,
//        @SerializedName("opRecommend")
//        val opRecommend: Boolean,
//        @SerializedName("ordered")
//        val ordered: Boolean,
//        @SerializedName("playCount")
//        val playCount: Long,
//        @SerializedName("privacy")
//        val privacy: Int,
//        @SerializedName("remixVideo")
//        val remixVideo: Any,
//        @SerializedName("shareCount")
//        val shareCount: Int,
//        @SerializedName("sharedUsers")
//        val sharedUsers: Any,
//        @SerializedName("specialType")
//        val specialType: Int,
//        @SerializedName("status")
//        val status: Int,
//        @SerializedName("subscribed")
//        val subscribed: Boolean,
//        @SerializedName("subscribedCount")
//        val subscribedCount: Int,
//        @SerializedName("subscribers")
//        val subscribers: List<Any>,
//        @SerializedName("tags")
//        val tags: List<String>,
//        @SerializedName("titleImage")
//        val titleImage: Long,
//        @SerializedName("titleImageUrl")
//        val titleImageUrl: String,
//        @SerializedName("trackCount")
//        val trackCount: Int,
//        @SerializedName("trackIds")
//        val trackIds: List<TrackId>,
//        @SerializedName("trackNumberUpdateTime")
//        val trackNumberUpdateTime: Long,
//        @SerializedName("trackUpdateTime")
//        val trackUpdateTime: Long,
//        @SerializedName("tracks")
//        val tracks: List<Track>,
//        @SerializedName("updateFrequency")
//        val updateFrequency: String,
//        @SerializedName("updateTime")
//        val updateTime: Long,
//        @SerializedName("userId")
//        val userId: Long,
//        @SerializedName("videoIds")
//        val videoIds: Any,
//        @SerializedName("videos")
//        val videos: Any
//    ) {
//        data class Creator(
//            @SerializedName("accountStatus")
//            val accountStatus: Int,
//            @SerializedName("anchor")
//            val anchor: Boolean,
//            @SerializedName("authStatus")
//            val authStatus: Int,
//            @SerializedName("authenticationTypes")
//            val authenticationTypes: Int,
//            @SerializedName("authority")
//            val authority: Int,
//            @SerializedName("avatarDetail")
//            val avatarDetail: AvatarDetail,
//            @SerializedName("avatarImgId")
//            val avatarImgId: Long,
//            @SerializedName("avatarImgIdStr")
//            val avatarImgIdStr: String,
//            @SerializedName("avatarUrl")
//            val avatarUrl: String,
//            @SerializedName("backgroundImgId")
//            val backgroundImgId: Long,
//            @SerializedName("backgroundImgIdStr")
//            val backgroundImgIdStr: String,
//            @SerializedName("backgroundUrl")
//            val backgroundUrl: String,
//            @SerializedName("birthday")
//            val birthday: Int,
//            @SerializedName("city")
//            val city: Int,
//            @SerializedName("defaultAvatar")
//            val defaultAvatar: Boolean,
//            @SerializedName("description")
//            val description: String,
//            @SerializedName("detailDescription")
//            val detailDescription: String,
//            @SerializedName("djStatus")
//            val djStatus: Int,
//            @SerializedName("expertTags")
//            val expertTags: Any,
//            @SerializedName("experts")
//            val experts: Any,
//            @SerializedName("followed")
//            val followed: Boolean,
//            @SerializedName("gender")
//            val gender: Int,
//            @SerializedName("mutual")
//            val mutual: Boolean,
//            @SerializedName("nickname")
//            val nickname: String,
//            @SerializedName("province")
//            val province: Int,
//            @SerializedName("remarkName")
//            val remarkName: Any,
//            @SerializedName("signature")
//            val signature: String,
//            @SerializedName("userId")
//            val userId: Long,
//            @SerializedName("userType")
//            val userType: Int,
//            @SerializedName("vipType")
//            val vipType: Int
//        ) {
//            data class AvatarDetail(
//                @SerializedName("identityIconUrl")
//                val identityIconUrl: String,
//                @SerializedName("identityLevel")
//                val identityLevel: Int,
//                @SerializedName("userType")
//                val userType: Int
//            )
//        }
//
//        data class TrackId(
//            @SerializedName("alg")
//            val alg: String,
//            @SerializedName("at")
//            val at: Long,
//            @SerializedName("id")
//            val id: Long,
//            @SerializedName("rcmdReason")
//            val rcmdReason: String,
//            @SerializedName("t")
//            val t: Int,
//            @SerializedName("uid")
//            val uid: Long,
//            @SerializedName("v")
//            val v: Int
//        )
//
//        data class Track(
//            @SerializedName("a")
//            val a: Any,
//            @SerializedName("al")
//            val al: Al,
//            @SerializedName("alg")
//            val alg: String,
//            @SerializedName("alia")
//            val alia: List<String>,
//            @SerializedName("ar")
//            val ar: List<Ar>,
//            @SerializedName("cd")
//            val cd: String,
//            @SerializedName("cf")
//            val cf: String,
//            @SerializedName("copyright")
//            val copyright: Int,
//            @SerializedName("cp")
//            val cp: Int,
//            @SerializedName("crbt")
//            val crbt: Any,
//            @SerializedName("djId")
//            val djId: Int,
//            @SerializedName("dt")
//            val dt: Int,
//            @SerializedName("fee")
//            val fee: Int,
//            @SerializedName("ftype")
//            val ftype: Int,
//            @SerializedName("h")
//            val h: H,
//            @SerializedName("id")
//            val id: Long,
//            @SerializedName("l")
//            val l: L,
//            @SerializedName("m")
//            val m: M,
//            @SerializedName("mark")
//            val mark: Long,
//            @SerializedName("mst")
//            val mst: Int,
//            @SerializedName("mv")
//            val mv: Int,
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
//            @SerializedName("pop")
//            val pop: Double,
//            @SerializedName("pst")
//            val pst: Int,
//            @SerializedName("publishTime")
//            val publishTime: Long,
//            @SerializedName("rt")
//            val rt: String,
//            @SerializedName("rtUrl")
//            val rtUrl: Any,
//            @SerializedName("rtUrls")
//            val rtUrls: List<Any>,
//            @SerializedName("rtype")
//            val rtype: Int,
//            @SerializedName("rurl")
//            val rurl: Any,
//            @SerializedName("s_id")
//            val sId: Int,
//            @SerializedName("single")
//            val single: Int,
//            @SerializedName("st")
//            val st: Int,
//            @SerializedName("t")
//            val t: Int,
//            @SerializedName("tns")
//            val tns: List<String>,
//            @SerializedName("v")
//            val v: Int
//        ) {
//            data class Al(
//                @SerializedName("id")
//                val id: Int,
//                @SerializedName("name")
//                val name: String,
//                @SerializedName("pic")
//                val pic: Long,
//                @SerializedName("pic_str")
//                val picStr: String,
//                @SerializedName("picUrl")
//                val picUrl: String,
//                @SerializedName("tns")
//                val tns: List<Any>
//            )
//
//            data class Ar(
//                @SerializedName("alias")
//                val alias: List<Any>,
//                @SerializedName("id")
//                val id: Int,
//                @SerializedName("name")
//                val name: String,
//                @SerializedName("tns")
//                val tns: List<Any>
//            )
//
//            data class H(
//                @SerializedName("br")
//                val br: Int,
//                @SerializedName("fid")
//                val fid: Int,
//                @SerializedName("size")
//                val size: Int,
//                @SerializedName("vd")
//                val vd: Double
//            )
//
//            data class L(
//                @SerializedName("br")
//                val br: Int,
//                @SerializedName("fid")
//                val fid: Int,
//                @SerializedName("size")
//                val size: Int,
//                @SerializedName("vd")
//                val vd: Double
//            )
//
//            data class M(
//                @SerializedName("br")
//                val br: Int,
//                @SerializedName("fid")
//                val fid: Int,
//                @SerializedName("size")
//                val size: Int,
//                @SerializedName("vd")
//                val vd: Double
//            )
//        }
//    }
//
//    data class Privilege(
//        @SerializedName("chargeInfoList")
//        val chargeInfoList: List<ChargeInfo>,
//        @SerializedName("cp")
//        val cp: Int,
//        @SerializedName("cs")
//        val cs: Boolean,
//        @SerializedName("dl")
//        val dl: Int,
//        @SerializedName("downloadMaxbr")
//        val downloadMaxbr: Int,
//        @SerializedName("fee")
//        val fee: Int,
//        @SerializedName("fl")
//        val fl: Int,
//        @SerializedName("flag")
//        val flag: Int,
//        @SerializedName("freeTrialPrivilege")
//        val freeTrialPrivilege: FreeTrialPrivilege,
//        @SerializedName("id")
//        val id: Int,
//        @SerializedName("maxbr")
//        val maxbr: Int,
//        @SerializedName("paidBigBang")
//        val paidBigBang: Boolean,
//        @SerializedName("payed")
//        val payed: Int,
//        @SerializedName("pc")
//        val pc: Any,
//        @SerializedName("pl")
//        val pl: Int,
//        @SerializedName("playMaxbr")
//        val playMaxbr: Int,
//        @SerializedName("preSell")
//        val preSell: Boolean,
//        @SerializedName("realPayed")
//        val realPayed: Int,
//        @SerializedName("rscl")
//        val rscl: Any,
//        @SerializedName("sp")
//        val sp: Int,
//        @SerializedName("st")
//        val st: Int,
//        @SerializedName("subp")
//        val subp: Int,
//        @SerializedName("toast")
//        val toast: Boolean
//    ) {
//        data class ChargeInfo(
//            @SerializedName("chargeMessage")
//            val chargeMessage: Any,
//            @SerializedName("chargeType")
//            val chargeType: Int,
//            @SerializedName("chargeUrl")
//            val chargeUrl: Any,
//            @SerializedName("rate")
//            val rate: Int
//        )
//
//        data class FreeTrialPrivilege(
//            @SerializedName("resConsumable")
//            val resConsumable: Boolean,
//            @SerializedName("userConsumable")
//            val userConsumable: Boolean
//        )
//    }
//}


data class PlaylistDetail(
    val code: Int,
    val fromUserCount: Int,
    val fromUsers: Any,
    val playlist: Playlist,
    val privileges: List<Privilege>,
    val relatedVideos: RelatedVideos,
    val resEntrance: Any,
    val sharedPrivilege: Any,
    val songFromUsers: Any,
    val urls: Any
)

data class Playlist(
    val adType: Int,
    val algTags: Any,
    val backgroundCoverId: Long,
    val backgroundCoverUrl: Any,
    val bannedTrackIds: Any,
    val cloudTrackCount: Int,
    val commentCount: Int,
    val commentThreadId: String,
    val copied: Boolean,
    val coverImgId: Long,
    val coverImgId_str: String,
    val coverImgUrl: String,
    val coverStatus: Int,
    val createTime: Long,
    val creator: Creator,
    val description: Any,
    val detailPageTitle: Any,
    val displayTags: Any,
    val distributeTags: List<Any>,
    val englishTitle: Any,
    val gradeStatus: String,
    val highQuality: Boolean,
    val historySharedUsers: Any,
    val id: Long,
    val mvResourceInfos: Any,
    val name: String,
    val newDetailPageRemixVideo: Any,
    val newImported: Boolean,
    val officialPlaylistType: Any,
    val opRecommend: Boolean,
    val ordered: Boolean,
    val playCount: Long,
    val playlistType: String,
    val privacy: Int,
    val relateResType: Any,
    val remixVideo: Any,
    val score: Any,
    val shareCount: Int,
    val sharedUsers: Any,
    val specialType: Int,
    val status: Int,
    val subscribed: Boolean,
    val subscribedCount: Int,
    val subscribers: List<Any>,
    val tags: List<Any>,
    val titleImage: Long,
    val titleImageUrl: Any,
    val trackCount: Int,
    val trackIds: List<TrackId>,
    val trackNumberUpdateTime: Long,
    val trackUpdateTime: Long,
    val tracks: List<Track>,
    val trialMode: Int,
    val updateFrequency: Any,
    val updateTime: Long,
    val userId: Long,
    val videoIds: Any,
    val videos: Any
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
    val paidBigBang: Boolean,
    val payed: Int,
    val pc: Any,
    val pl: Int,
    val plLevel: String,
    val playMaxBrLevel: String,
    val playMaxbr: Int,
    val preSell: Boolean,
    val realPayed: Int,
    val rightSource: Int,
    val rscl: Any,
    val sp: Int,
    val st: Int,
    val subp: Int,
    val toast: Boolean
)

class RelatedVideos

data class Creator(
    val accountStatus: Int,
    val anchor: Boolean,
    val authStatus: Int,
    val authenticationTypes: Int,
    val authority: Int,
    val avatarDetail: Any,
    val avatarImgId: Long,
    val avatarImgIdStr: String,
    val avatarImgId_str: String,
    val avatarUrl: String,
    val backgroundImgId: Long,
    val backgroundImgIdStr: String,
    val backgroundUrl: String,
    val birthday: Int,
    val city: Int,
    val defaultAvatar: Boolean,
    val description: String,
    val detailDescription: String,
    val djStatus: Int,
    val expertTags: Any,
    val experts: Any,
    val followed: Boolean,
    val gender: Int,
    val mutual: Boolean,
    val nickname: String,
    val province: Int,
    val remarkName: Any,
    val signature: String,
    val userId: Long,
    val userType: Int,
    val vipType: Int
)

data class TrackId(
    val alg: Any,
    val at: Long,
    val dpr: Any,
    val f: Any,
    val id: Long,
    val rcmdReason: String,
    val sc: Any,
    val sr: Any,
    val t: Int,
    val uid: Long,
    val v: Int
)

data class Track(
    val a: Any,
    val al: Al,
    val alg: Any,
    val alia: List<String>,
    val ar: List<Ar>,
    val awardTags: Any,
    val cd: String,
    val cf: String,
    val copyright: Int,
    val cp: Int,
    val crbt: Any,
    val displayReason: Any,
    val djId: Int,
    val dt: Int,
    val entertainmentTags: Any,
    val fee: Int,
    val ftype: Int,
    val h: H,
    val hr: Hr,
    val id: Long,
    val l: L,
    val m: M,
    val mark: Long,
    val mst: Int,
    val mv: Int,
    val name: String,
    val no: Int,
    val noCopyrightRcmd: NoCopyrightRcmd,
    val originCoverType: Int,
    val originSongSimpleData: OriginSongSimpleData,
    val pop: Int,
    val pst: Int,
    val publishTime: Long,
    val resourceState: Boolean,
    val rt: String,
    val rtUrl: Any,
    val rtUrls: List<Any>,
    val rtype: Int,
    val rurl: Any,
    val s_id: Long,
    val single: Int,
    val songJumpInfo: Any,
    val sq: Sq,
    val st: Int,
    val t: Int,
    val tagPicList: Any,
    val tns: List<String>,
    val v: Int,
    val version: Int,
    val videoInfo: VideoInfo
)

data class Al(
    val id: Int,
    val name: String,
    val pic: Long,
    val picUrl: String,
    val pic_str: String,
    val tns: List<String>
)

data class Ar(
    val alias: List<Any>,
    val id: Int,
    val name: String,
    val tns: List<Any>
)

data class H(
    val br: Int,
    val fid: Int,
    val size: Int,
    val sr: Int,
    val vd: Float
)

data class Hr(
    val br: Int,
    val fid: Int,
    val size: Int,
    val sr: Int,
    val vd: Int
)

data class L(
    val br: Int,
    val fid: Int,
    val size: Int,
    val sr: Int,
    val vd: Float
)

data class M(
    val br: Int,
    val fid: Int,
    val size: Int,
    val sr: Int,
    val vd: Float
)

data class NoCopyrightRcmd(
    val expInfo: Any,
    val songId: Any,
    val thirdPartySong: Any,
    val type: Int,
    val typeDesc: String
)

data class OriginSongSimpleData(
    val albumMeta: AlbumMeta,
    val artists: List<Artist>,
    val name: String,
    val songId: Int
)

data class Sq(
    val br: Int,
    val fid: Int,
    val size: Int,
    val sr: Int,
    val vd: Int
)

data class VideoInfo(
    val moreThanOne: Boolean,
    val video: Video
)

data class AlbumMeta(
    val id: Int,
    val name: String
)

data class Artist(
    val id: Int,
    val name: String
)

data class Video(
    val alias: Any,
    val artists: Any,
    val coverUrl: String,
    val playTime: Int,
    val publishTime: Long,
    val title: String,
    val type: Int,
    val vid: String
)

data class ChargeInfo(
    val chargeMessage: Any,
    val chargeType: Int,
    val chargeUrl: Any,
    val rate: Int
)

data class FreeTrialPrivilege(
    val cannotListenReason: Int,
    val listenType: Any,
    val playReason: Any,
    val resConsumable: Boolean,
    val userConsumable: Boolean
)