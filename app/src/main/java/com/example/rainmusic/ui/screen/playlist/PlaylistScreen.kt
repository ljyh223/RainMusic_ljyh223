package com.example.rainmusic.ui.screen.playlist

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.rainmusic.data.model.MusicInfo
import com.example.rainmusic.data.retrofit.api.model.Track
import com.example.rainmusic.ui.component.PopBackIcon
import com.example.rainmusic.ui.component.RainTopBar
import com.example.rainmusic.ui.screen.dailysong.playMusics
import com.example.rainmusic.util.RainMusicProtocol
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun PlaylistScreen(
    id: Long,
    playlistViewModel: PlaylistViewModel = hiltViewModel()
) {
    LaunchedEffect(id) {
        playlistViewModel.loadPlaylist(id)
    }

    val lazyListState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val scope = rememberCoroutineScope()
    val context= LocalContext.current
    Scaffold(
        topBar = {
            PlaylistTopBar(scrollBehavior)
        },
        floatingActionButton = {
            if (remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }.value > 10) {
                FloatingActionButton(
                    modifier = Modifier.navigationBarsPadding(),
                    onClick = {
                        scope.launch {
                            lazyListState.scrollToItem(0)
                        }
                    }
                ) {
                    Icon(Icons.Rounded.ArrowUpward, null)
                }
            }
        }
    ) { paddingValues ->
        val playlistDetail by playlistViewModel.playlistDetail.collectAsState()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            state = lazyListState
        ) {
            item {
                PlaylistInfo(playlistViewModel)
            }

            item {
                PlaylistAction(playlistViewModel)
            }

            Log.d("PlaylistScreen", "re")
            playlistDetail.readSafely()?.let {
                itemsIndexed(it.playlist.tracks) { index, item ->
                    key(item.id) {
                        PlaylistMusic(
                            index = index + 1,
                            track = item
                        ){
                            val songs = playlistDetail.read().playlist.tracks.map {i->
                                MusicInfo(
                                    id = i.id,
                                    name = i.name,
                                    artist = i.ar.joinToString { it.name } ,
                                    musicUrl = "$RainMusicProtocol://music?id=${i.id}",
                                    artworkUrl = i.al.picUrl
                                )
                            }
                            playMusics(context, songs,songs[index])

                        }
                    }

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlaylistTopBar(
    scrollBehavior: TopAppBarScrollBehavior
) {
    RainTopBar(
        navigationIcon = {
            PopBackIcon()
        },
        title = {
            Text(text = "歌单详情")
        },
        colors = TopAppBarDefaults.topAppBarColors(),
        scrollBehavior = scrollBehavior,
    )
}

@Composable
private fun PlaylistInfo(playlistViewModel: PlaylistViewModel) {
    val playlistDetail by playlistViewModel.playlistDetail.collectAsState()
    var showPlaylistDetailDialog by remember {
        mutableStateOf(false)
    }
    if (showPlaylistDetailDialog) {
        AlertDialog(
            onDismissRequest = {
                showPlaylistDetailDialog = false
            },
            title = {
                Text(text = playlistDetail.readSafely()?.playlist?.name ?: "歌单信息")
            },
            text = {
                SelectionContainer {
                    Text(text = playlistDetail.readSafely()?.playlist?.description.toString())
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    showPlaylistDetailDialog = false
                }) {
                    Text(text = "关闭")
                }
            }
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(8.dp)),
            model = ImageRequest.Builder(LocalContext.current)
                .data(playlistDetail.readSafely()?.playlist?.coverImgUrl)
                .size(Size.ORIGINAL)
                .build(),
            contentDescription = null
        )

        Column(
            modifier = Modifier.clickable {
                showPlaylistDetailDialog = true
            },
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = playlistDetail.readSafely()?.playlist?.name ?: "加载中",
                style = MaterialTheme.typography.titleLarge,
                maxLines = 2
            )
            Text(
                text = playlistDetail.readSafely()?.playlist?.creator?.nickname ?: "",
                maxLines = 1,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = playlistDetail.readSafely()?.playlist?.description.toString(),
                style = MaterialTheme.typography.labelMedium,
                maxLines = 2
            )
        }
    }
}

@Composable
private fun PlaylistAction(
    playlistViewModel: PlaylistViewModel
) {
    val playlistDetail by playlistViewModel.playlistDetail.collectAsState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(onClick = {
            val songs = ArrayList<MusicInfo>()

            playlistDetail.readSafely()?.playlist?.tracks?.map { track ->
                songs.add(
                    MusicInfo(
                        id = track.id,
                        name = track.name,
                        artist = track.ar.joinToString(", ") { ar -> ar.name },
                        musicUrl = "$RainMusicProtocol://music?id=${track.id}",
                        artworkUrl = track.al.picUrl
                    )
                )
            }

            playMusics(context, songs)


        }) {
            Text(text = "播放")
        }

        IconButton(onClick = {
            playlistViewModel.subscribe(scope)
        }) {
            val imageVector = if (playlistDetail.readSafely()?.playlist?.subscribed == true) {
                Icons.Rounded.Favorite
            } else {
                Icons.Rounded.FavoriteBorder
            }
            Icon(imageVector, null)
        }

        Text(
            modifier = Modifier.weight(1f),
            text = "共 ${playlistDetail.readSafely()?.playlist?.trackCount} 首歌",
            maxLines = 1,
            textAlign = TextAlign.End
        )
    }
}

@Composable
private fun PlaylistMusic(
    index: Int,
    track: Track,
    onclick:()->Unit
) {
    val name by remember {
        mutableStateOf(track.name)
    }
    val singer by remember {
        mutableStateOf(track.ar.joinToString(separator = "/") { it.name } + if (track.al.name.isNotBlank()) " - ${track.al.name}" else "")
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        tonalElevation = if (index % 2 == 0) 8.dp else 16.dp,
        shape = RoundedCornerShape(6.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = index.toString())
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(track.al.picUrl)
                    .build(),
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .size(36.dp),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = singer,
                    style = MaterialTheme.typography.labelMedium
                )
            }

            IconButton(onClick = onclick) {
                Icon(Icons.Rounded.PlayArrow, null)
            }
        }
    }
}
