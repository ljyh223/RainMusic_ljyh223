package com.example.rainmusic.util


import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun calculateScaleToFit(): Float {
    val boxSize = with(LocalDensity.current) { 1.dp.toPx() }
    val imageSize = 1f // This should be replaced with actual image size calculation
    return boxSize / imageSize
//
}

fun String.smallImage():String{

    return "$this?param=100y100"
}
fun String.middleImage():String{
    return "$this?param=300y300"
}

fun String.largeImage():String{

    return "$this?param=500y500"
}