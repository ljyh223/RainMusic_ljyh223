package com.example.rainmusic.ui.component

import android.annotation.SuppressLint
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImagePainter
import coil.compose.ImagePainter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.shimmerPlaceholder(
    visible: Boolean
) = composed {
    Modifier.placeholder(
        visible = visible,
        highlight = PlaceholderHighlight.shimmer()
    )
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
@OptIn(ExperimentalCoilApi::class)
fun Modifier.shimmerPlaceholder(
    imagePainter: AsyncImagePainter
) = composed {
    Modifier.placeholder(
        visible = imagePainter.state is AsyncImagePainter.State.Loading,
        highlight = PlaceholderHighlight.shimmer()
    )
}