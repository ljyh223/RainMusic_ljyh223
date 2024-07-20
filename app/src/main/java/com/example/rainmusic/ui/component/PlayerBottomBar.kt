package com.example.rainmusic.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.media3.common.Player
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.rainmusic.service.MusicService
import com.example.rainmusic.ui.states.rememberMediaSessionPlayer
import org.jetbrains.annotations.Async

@Composable
fun PlayerBottomBar() {

    val player by rememberMediaSessionPlayer(MusicService::class.java)
    val image= remember {
        player?.mediaMetadata?.artworkUri
    }
    val title= remember {
        player?.mediaMetadata?.title.toString()
    }
    val artist= remember {
        player?.mediaMetadata?.artist.toString()
    }
    val context= LocalContext.current
    Box {
        Row {
            AsyncImage(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),

                model =ImageRequest.Builder(context)
                .data(image)
                .build()
                , contentDescription = null)
            Column {
                Text(text = title)
                Text(text = artist)
            }
        }
    }
}