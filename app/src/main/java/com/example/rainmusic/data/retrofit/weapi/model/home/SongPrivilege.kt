package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class SongPrivilege(
    @SerializedName("chargeInfoList")
    val chargeInfoList: List<ChargeInfo>,
    @SerializedName("cp")
    val cp: Int,
    @SerializedName("cs")
    val cs: Boolean,
    @SerializedName("dl")
    val dl: Int,
    @SerializedName("dlLevel")
    val dlLevel: String,
    @SerializedName("downloadMaxBrLevel")
    val downloadMaxBrLevel: String,
    @SerializedName("downloadMaxbr")
    val downloadMaxbr: Int,
    @SerializedName("fee")
    val fee: Int,
    @SerializedName("fl")
    val fl: Int,
    @SerializedName("flLevel")
    val flLevel: String,
    @SerializedName("flag")
    val flag: Int,
    @SerializedName("freeTrialPrivilege")
    val freeTrialPrivilege: FreeTrialPrivilege,
    @SerializedName("id")
    val id: Long,
    @SerializedName("maxBrLevel")
    val maxBrLevel: String,
    @SerializedName("maxbr")
    val maxbr: Int,
    @SerializedName("paidBigBang")
    val paidBigBang: Boolean,
    @SerializedName("payed")
    val payed: Int,
    @SerializedName("pc")
    val pc: Any,
    @SerializedName("pl")
    val pl: Int,
    @SerializedName("plLevel")
    val plLevel: String,
    @SerializedName("playMaxBrLevel")
    val playMaxBrLevel: String,
    @SerializedName("playMaxbr")
    val playMaxbr: Int,
    @SerializedName("preSell")
    val preSell: Boolean,
    @SerializedName("realPayed")
    val realPayed: Int,
    @SerializedName("rightSource")
    val rightSource: Int,
    @SerializedName("rscl")
    val rscl: Any,
    @SerializedName("sp")
    val sp: Int,
    @SerializedName("st")
    val st: Int,
    @SerializedName("subp")
    val subp: Int,
    @SerializedName("toast")
    val toast: Boolean
)