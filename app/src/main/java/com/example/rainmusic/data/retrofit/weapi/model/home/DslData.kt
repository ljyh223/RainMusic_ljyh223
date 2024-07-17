package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class DslData(
    @SerializedName("Card_list_0")
    val cardList0: CardList0,
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: DataX,
    @SerializedName("HeaderMusHomePage3")
    val headerMusHomePage3: HeaderMusHomePage3X,
    @SerializedName("MusicHomeMusIncmHeader")
    val musicHomeMusIncmHeader: MusicHomeMusIncmHeader,
    @SerializedName("template_35001_carousel_0")
    val template35001Carousel0: Template35001Carousel0X,
    @SerializedName("track")
    val track: TrackXXXX
)