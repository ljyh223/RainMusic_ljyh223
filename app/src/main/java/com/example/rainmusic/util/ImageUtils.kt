package com.example.rainmusic.util


import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun calculateScaleToFit(): Float {
    val boxSize = with(LocalDensity.current) { 1.dp.toPx() }
    val imageSize = 1f // This should be replaced with actual image size calculation
    return boxSize / imageSize
//
}

fun String.smallImage(): String {

    return "$this?param=100y100"
}

fun String.middleImage(): String {
    return "$this?param=300y300"
}

fun String.largeImage(): String {

    return "$this?param=500y500"
}

@Composable
fun imageWithDynamicFilter(): ColorFilter {
    val isDarkTheme = isSystemInDarkTheme()


    val cm = ColorMatrix(
        floatArrayOf(
            if (isDarkTheme) 0.5f else 1.5f, 0f, 0f, 0f, 0f, // 红色通道
            0f, if (isDarkTheme) 0.5f else 1.5f, 0f, 0f, 0f, // 绿色通道
            0f, 0f, if (isDarkTheme) 0.5f else 1.5f, 0f, 0f, // 蓝色通道
            0f, 0f, 0f, 1f, 0f // 透明度
        )
    )
    cm.setToSaturation(1.5f)
    val colorMatrix = ColorFilter.colorMatrix(cm)

    return colorMatrix
}