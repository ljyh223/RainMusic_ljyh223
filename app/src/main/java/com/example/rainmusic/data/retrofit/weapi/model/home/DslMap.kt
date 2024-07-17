package com.example.rainmusic.data.retrofit.weapi.model.home


import com.google.gson.annotations.SerializedName

data class DslMap(
    @SerializedName("fit-2")
    val fit2: Fit2,
    @SerializedName("HeaderMusHomePage3")
    val headerMusHomePage3: HeaderMusHomePage3,
    @SerializedName("MusicHomeMusIncmItem3")
    val musicHomeMusIncmItem3: MusicHomeMusIncmItem3,
    @SerializedName("template_35001")
    val template35001: Template35001,
    @SerializedName("template_35001-carousel-0")
    val template35001Carousel0: Template35001Carousel0,
    @SerializedName("template_35001-list-1")
    val template35001List1: Template35001List1
)