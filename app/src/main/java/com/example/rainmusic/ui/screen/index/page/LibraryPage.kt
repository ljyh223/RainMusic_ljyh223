package com.example.rainmusic.ui.screen.index.page

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.rainmusic.data.retrofit.api.model.UserPlaylists
import com.example.rainmusic.ui.component.shimmerPlaceholder
import com.example.rainmusic.ui.local.LocalNavController
import com.example.rainmusic.ui.local.LocalUserData
import com.example.rainmusic.ui.screen.Screen
import com.example.rainmusic.ui.screen.index.IndexViewModel
import com.example.rainmusic.util.DataState

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalFoundationApi
@Composable
fun LibraryPage(indexViewModel: IndexViewModel) {
    val userData = LocalUserData.current
    val playlists by indexViewModel.userPlaylist.collectAsState()

    LaunchedEffect(userData) {

        if(playlists !is DataState.Success) {
            indexViewModel.refreshLibraryPage(userData.id)
            Log.d("LibraryPage","LibraryPage")
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(
                state = rememberPullRefreshState(playlists is DataState.Loading, onRefresh ={
                    indexViewModel.refreshLibraryPage(userData.id)
                })
            )
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            playlists.readSafely()?.playlist?.let {
                it.groupBy { playlist ->
                    playlist.creator.userId == userData.id
                }.forEach { (self, items) ->
                    if (self) {
                        stickyHeader {
                            Surface(modifier = Modifier.fillMaxWidth()) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "创建的歌单",
                                        style = MaterialTheme.typography.headlineSmall,
                                        modifier = Modifier.weight(1f)
                                    )
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(Icons.Rounded.Add, null)
                                    }
                                }
                            }
                        }
                    } else {
                        stickyHeader {
                            Surface(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "收藏的歌单",
                                    style = MaterialTheme.typography.headlineSmall,
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                )
                            }
                        }
                    }

                    items(items) { item ->
                        PlayListItem(item)
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun PlayListItem(playlist: UserPlaylists.Playlist) {
    val navController = LocalNavController.current
    Surface(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clickable {
                Screen.Playlist.navigate(navController) {
                    addPath(playlist.id.toString())
                }
            },
        tonalElevation = 8.dp,
        shape = RoundedCornerShape(6.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val painter = rememberImagePainter(
                data = playlist.coverImgUrl
            )
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(10))
                    .aspectRatio(1f)
                    .heightIn(min = 100.dp)
                    .fillMaxHeight()
                    .shimmerPlaceholder(painter),
                contentScale = ContentScale.FillHeight
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = playlist.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1
                )
                Text(
                    text = "${playlist.trackCount} 首音乐 ${playlist.playCount} 次播放",
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 1
                )
            }

            IconButton(onClick = {
                // TODO: Playlist Actions
            }) {
                Icon(Icons.Rounded.Menu, null)
            }
        }
    }
}