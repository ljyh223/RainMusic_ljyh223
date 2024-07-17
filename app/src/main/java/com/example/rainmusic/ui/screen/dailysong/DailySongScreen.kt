package com.example.rainmusic.ui.screen.dailysong

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.ui.graphics.Color
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.PlaylistAdd
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Shuffle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.rainmusic.data.model.MusicInfo
import com.example.rainmusic.data.retrofit.api.model.DailyRecommendSongs
import com.example.rainmusic.service.MusicService
import com.example.rainmusic.ui.component.AppBarStyle
import com.example.rainmusic.ui.component.PopBackIcon
import com.example.rainmusic.ui.component.RainTopBar
import com.example.rainmusic.ui.component.shimmerPlaceholder
import com.example.rainmusic.ui.states.asyncGetSessionPlayer
import com.example.rainmusic.util.DataState
import com.example.rainmusic.util.RainMusicProtocol
import com.example.rainmusic.util.media.buildMediaItem
import com.example.rainmusic.util.media.metadata

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailySongScreen(
    dailySongViewModel: DailySongViewModel = hiltViewModel()
) {
    val dailySongs by dailySongViewModel.dailySongs.collectAsState()
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            RainTopBar(
                title = {
                    Text(text = "每日推荐")
                },
                navigationIcon = {
                    PopBackIcon()
                },
                actions = {
                    IconButton(onClick = {
                        val songs = ArrayList<MusicInfo>()
                        dailySongs.read().data.dailySongs.forEach {
                            songs.add(
                                MusicInfo(
                                    id = it.id,
                                    name = it.name,
                                    artist = it.ar.joinToString(separator = "/") { it.name } + if (it.al.name.isNotBlank()) " - ${it.al.name}" else "",
                                    musicUrl = "$RainMusicProtocol://music?id=${it.id}",
                                    artworkUrl = it.al.picUrl
                                )
                            )
                        }
                        playMusics(context, songs)
                    }) {
                        Icon(Icons.Rounded.PlayArrow, null)
                    }
                },
                scrollBehavior = scrollBehavior,
                appBarStyle = AppBarStyle.Small
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = WindowInsets.navigationBars.asPaddingValues()
        ) {

            when (dailySongs) {
                is DataState.Success -> {

                    dailySongs.readSafely()?.data?.dailySongs?.let {
                        item {
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
                                        .data(it[0].al.picUrl)
                                        .size(Size.ORIGINAL)
                                        .build(),
                                    contentDescription = null
                                )

                                Column(
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Text(
                                        text = "每日推荐",
                                        style = MaterialTheme.typography.titleLarge,
                                    )
                                    Text(
                                        text = "${it.size} 歌曲",
                                        maxLines = 1,
                                        style = MaterialTheme.typography.labelSmall
                                    )
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Surface(
                                            color = MaterialTheme.colorScheme.primary,
                                            shape = CircleShape, // 形状，你可能需要导入Shapes
                                            modifier = Modifier
                                                .weight(3f)
                                                .height(48.dp) // 设置按钮大小
                                        ) {
                                            Icon(
                                                modifier = Modifier.size(18.dp),
                                                imageVector = Icons.Rounded.PlayArrow,
                                                contentDescription = null
                                            )
                                        }


                                        Surface(
                                            color = Color.Unspecified,
                                            shape = CircleShape, // 形状，你可能需要导入Shapes
                                            modifier = Modifier
                                                .weight(1f)
                                                .height(48.dp) // 设置按钮大小
                                        ) {
                                            Icon(
                                                modifier = Modifier.size(18.dp),
                                                imageVector = Icons.Rounded.Shuffle,
                                                contentDescription = null
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        itemsIndexed(it) { index, item ->
                            key(item.id) {
                                PlaylistMusic(index + 1, item)
                            }

                        }
                    }
                }

                is DataState.Loading -> {
                    items(5) {
                        Box(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .height(80.dp)
                                .shimmerPlaceholder(true)
                        )
                    }
                }

                is DataState.Error -> {
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "加载失败")
                        }
                    }
                }

                else -> {}
            }
        }
    }
}

@Composable
private fun PlaylistMusic(
    index: Int,
    track: DailyRecommendSongs.Data.DailySong
) {
    val context = LocalContext.current
    val name by remember { mutableStateOf(track.name) }
    val singer by remember { mutableStateOf(track.ar.joinToString(separator = "/") { it.name } + if (track.al.name.isNotBlank()) " - ${track.al.name}" else "") }
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
                model = ImageRequest.Builder(context)
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

            IconButton(onClick = {
                playMusic(
                    context,
                    MusicInfo(
                        id = track.id,
                        name = name,
                        artist = singer,
                        musicUrl = "$RainMusicProtocol://music?id=${track.id}",
                        artworkUrl = track.al.picUrl
                    )
                )
            }) {
                Icon(Icons.Rounded.PlayArrow, null)
            }
        }
    }
}

fun playMusic(context: Context, song: MusicInfo) {
    context.asyncGetSessionPlayer(MusicService::class.java) {
        it.apply {
            stop()
            clearMediaItems()
            addMediaItem(song.toMediaItem())
            prepare()
            play()
        }
    }
}


fun playMusics(context: Context, songs: List<MusicInfo>) {
    context.asyncGetSessionPlayer(MusicService::class.java) {
        it.apply {
            // 停止当前播放
            stop()
            // 清除当前媒体列表
            clearMediaItems()
            val mediaList = songs.map { v ->
                v.toMediaItem()
            }
            // 添加媒体列表到播放器
            addMediaItems(mediaList)
            // 准备播放器
            prepare()
            // 开始播放
            play()
        }
    }
}