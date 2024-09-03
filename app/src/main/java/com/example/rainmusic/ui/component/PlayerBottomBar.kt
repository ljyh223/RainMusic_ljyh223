package com.example.rainmusic.ui.component

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.rainmusic.data.model.MusicControllerUiState
import com.example.rainmusic.data.model.Song
import com.example.rainmusic.service.MusicEvent
import com.example.rainmusic.service.PlayerState
import com.example.rainmusic.ui.local.LocalNavController
import com.example.rainmusic.ui.screen.Screen

@Composable
fun PlayerBottomBar(
    onEvent: (MusicEvent) -> Unit,
    playerState: MusicControllerUiState,
    song: Song?,
    onBarClick: () -> Unit
) {

    val context = LocalContext.current
    val navController= LocalNavController.current
    AnimatedVisibility(
        visible = playerState.playerState != PlayerState.STOPPED,

    ) {
        if (song != null) {
            val radio= if (playerState.currentPosition == 0L) 0f else playerState.currentPosition.toFloat() / playerState.totalDuration.toFloat()

            Column(
                Modifier.clickable {
                    Screen.Player.navigate(navController)
                }
            ) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surfaceTint.copy(alpha = 0.25f))
                        .height(2.dp)
                        .fillMaxWidth()
                ) {

                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.CenterStart)
                            .background(MaterialTheme.colorScheme.surfaceTint)
                            .fillMaxHeight()
                            .fillMaxWidth(radio)
                    )
                }

                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()

                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(0.dp, 8.dp)
                    ) {
                        Spacer(modifier = Modifier.width(12.dp))
                        AsyncImage(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(16.dp)),
                            model = ImageRequest.Builder(context)
                                .data(song.imageUrl)
                                .build(), contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column{
                            Text(text = song.title, maxLines = 1)
                            Text(text = song.subtitle, maxLines = 1)
                        }
                    }
                }


            }
        }
    }


}