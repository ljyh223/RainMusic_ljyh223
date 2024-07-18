package com.example.rainmusic.ui.screen.player

import android.annotation.SuppressLint
import android.provider.ContactsContract.CommonDataKinds.Identity.IDENTITY
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Repeat
import androidx.compose.material.icons.rounded.SkipNext
import androidx.compose.material.icons.rounded.SkipPrevious
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.rainmusic.data.retrofit.api.model.parse
import com.example.rainmusic.service.MusicService
import com.example.rainmusic.ui.component.PopBackIcon
import com.example.rainmusic.ui.component.RainTopBar
import com.example.rainmusic.ui.local.LocalUserData
import com.example.rainmusic.ui.states.rememberCurrentMediaItem
import com.example.rainmusic.ui.states.rememberMediaSessionPlayer
import com.example.rainmusic.ui.states.rememberPlayProgress
import com.example.rainmusic.ui.states.rememberPlayState
import com.example.rainmusic.util.BlurTransformation
import com.example.rainmusic.util.calculateScaleToFit
import com.example.rainmusic.util.color.getImageDominantColor
import com.example.rainmusic.util.dp2px
import com.example.rainmusic.util.formatAsPlayerTime
import com.example.rainmusic.util.sharedPreferencesOf
import com.example.rainmusic.util.smallImage
import com.smarttoolfactory.slider.ColorfulSlider
import com.smarttoolfactory.slider.MaterialSliderDefaults
import kotlinx.coroutines.launch
import kotlin.math.roundToLong


@ExperimentalMaterial3Api
@Composable
fun PlayerScreen(
    playerScreenViewModel: PlayerScreenViewModel = hiltViewModel()
) {
    val player by rememberMediaSessionPlayer(MusicService::class.java)
    val userData = LocalUserData.current
    LaunchedEffect(userData) {
        playerScreenViewModel.loadLikeList(userData.id)
    }
    when (player) {
        null -> {
            NotConnectScreen()
        }

        else -> {
            PlayerUI(player!!, playerScreenViewModel)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
private fun NotConnectScreen() {
    Scaffold(
        topBar = {
            RainTopBar(
                title = {
                    Text(text = "播放器")
                },
                navigationIcon = {
                    PopBackIcon(Icons.Rounded.Close)
                }
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "很抱歉，无法连接到播放器服务", modifier = Modifier)
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope", "UnrememberedMutableState")
@ExperimentalMaterial3Api
@Composable
private fun PlayerUI(
    player: Player,
    playerScreenViewModel: PlayerScreenViewModel
) {
    val context = LocalContext.current
    val pagerState = rememberPagerState(pageCount = { 2 })
    val currentMediaItem = rememberCurrentMediaItem(player)
    val progress = rememberPlayProgress(player)
    val isPlaying = rememberPlayState(player)
    var containerColor by remember { mutableStateOf(Color.Unspecified) }

    val angle = remember { Animatable(0f) }


    val musicDetail by playerScreenViewModel.musicDetail.collectAsState()
    val scope = rememberCoroutineScope()
    // 加载音乐信息
    LaunchedEffect(currentMediaItem) {
        Log.d("PlayerUI", "加载音乐信息")
        playerScreenViewModel.loadMusicDetail(currentMediaItem?.mediaId?.toLong() ?: 0L)

        musicDetail.readSafely()?.let {
            scope.launch {
                val c = getImageDominantColor(it.songs[0].al.picUrl, context)
                Log.d("PlayerUI", c.toString())
                containerColor = Color(c)
            }
        }
    }


    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                PopBackIcon(Icons.Rounded.Close)
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = currentMediaItem?.mediaMetadata?.title?.toString() ?: "暂未播放",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 1
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = currentMediaItem?.mediaMetadata?.artist?.toString() ?: "暂未播放",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1
                    )
                }
                IconButton(onClick = {

                }) {
                    Icon(Icons.Rounded.Menu, null)
                }
            }
        },

        bottomBar = {
            BottomBar(playerScreenViewModel, progress, player, isPlaying, currentMediaItem)
        },

        ) {
        val backdropImage = rememberAsyncImagePainter(
            model = ImageRequest.Builder(context)
                .data(musicDetail.readSafely()?.songs?.get(0)?.al?.picUrl?.smallImage())
                .transformations(BlurTransformation(context, radius = 25f))
                .build()
        )


        val backdropColorFilter = remember {
            val cm=ColorMatrix(
                floatArrayOf(
                    2f, 0f, 0f, 0f, 0f, // 红色通道的亮度增加
                    0f, 2f, 0f, 0f, 0f, // 绿色通道的亮度增加
                    0f, 0f, 2f, 0f, 0f, // 蓝色通道的亮度增加
                    0f, 0f, 0f, 1f, 0f    // 透明度保持不变
                )
            )
            cm.setToSaturation(2.5f)
            ColorFilter.colorMatrix(cm)
        }

        Image(
            painter = backdropImage,
            modifier = Modifier
                .fillMaxSize()
                .scale(scale = calculateScaleToFit())
                .graphicsLayer { rotationZ = angle.value + 90f },
            colorFilter = backdropColorFilter,
            contentDescription = null,
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.5f) // 调整透明度
                .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
        )


        LaunchedEffect(isPlaying) {
            if (isPlaying == true) {
                // 创建一个无限循环的旋转动画
                angle.animateTo(
                    targetValue = angle.value + 360f,
                    animationSpec = InfiniteRepeatableSpec(
                        animation = tween(24000, easing = LinearEasing),
                        repeatMode = RepeatMode.Restart
                    )
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) { page ->
            when (page) {
                // 封面

                0 -> {
                    val cover = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(context)
                            .data(musicDetail.readSafely()?.songs?.get(0)?.al?.picUrl)
                            .build()
                    )
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        musicDetail.readSafely()?.songs?.get(0)?.al?.picUrl?.let { it1 ->
                            Log.d(
                                "Cover",
                                it1
                            )
                        }
                        Image(
                            painter =cover,
                            modifier = Modifier
                                .graphicsLayer { rotationZ = angle.value }
                                .clip(CircleShape)
                                .fillMaxWidth(0.75f)
                                .aspectRatio(1f),
                            contentDescription = null
                        )
                    }
                }

                // 歌词
                1 -> {

                    val lyric by playerScreenViewModel.lyric.collectAsState()
                    val lyricLines = lyric.readSafely()?.parse() ?: emptyList()
                    val lyricSharedPreferences = remember {
                        sharedPreferencesOf("lyric")
                    }


                    var currentLyricIndex by remember {
                        mutableIntStateOf(0)
                    }

                    val state = rememberLazyListState()
                    // 当没歌词的时候
                    if (lyricLines.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = "暂无歌词",
                                modifier = Modifier.align(Alignment.Center),
                                fontSize = lyricSharedPreferences.getInt("fontSize", 26).textDp
                            )
                        }
                    } else {
                        val currentTextElementHeightPx = remember { mutableIntStateOf(0) }
                        BoxWithConstraints(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            // 前后空白
                            val blackItem: (LazyListScope.() -> Unit) = {
                                item {
                                    Box(
                                        modifier = Modifier
                                            .height(maxHeight / 2)
                                    ) {

                                    }
                                }
                            }
                            // 歌词主体
                            val lyricsEntryListItems: (LazyListScope.() -> Unit) = {
                                itemsIndexed(lyricLines) { index, lyric ->
                                    key(lyric.time) {
                                        LyricsItem(
                                            lyricsEntry = lyric,
                                            current = currentLyricIndex == index,
                                            currentTextElementHeightPxState = currentTextElementHeightPx,
                                            textSize = lyricSharedPreferences.getInt(
                                                "fontSize",
                                                26
                                            ),
                                            textBold = lyricSharedPreferences.getBoolean(
                                                "textBold",
                                                true
                                            ),
                                            centerAlign = lyricSharedPreferences.getBoolean(
                                                "lyricCenter",
                                                true
                                            ),
                                            showSubText = true,
                                            onClick = {
                                                lyricLines.indexOfFirst { l ->
                                                    l == lyric
                                                }.takeIf { i -> i > 0 }?.let { index ->
                                                    Log.d(
                                                        "LyricsItem",
                                                        "也是找到了，好吧 --> $index"
                                                    )
                                                    currentLyricIndex = index
                                                    player.seekTo(
                                                        lyricLines[index].time
                                                    )
                                                }
                                            }
                                        )
                                    }
                                }
                            }
                            LazyColumn(
                                Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .padding(32.dp, 0.dp)
                                    .graphicsLayer { alpha = 0.99F }
                                    .drawWithContent {
                                        val colors = listOf(
                                            Color.Transparent,
                                            Color.Black,
                                            Color.Black,
                                            Color.Black,
                                            Color.Black,
                                            Color.Black,
                                            Color.Black,
                                            Color.Black,
                                            Color.Transparent
                                        )
                                        drawContent()
                                        drawRect(
                                            brush = Brush.verticalGradient(colors),
                                            blendMode = BlendMode.DstIn
                                        )
                                    },
                                state = state
                            ) {
                                blackItem()
                                lyricsEntryListItems()
                                blackItem()
                            }
                            // 定位中间
                            LaunchedEffect(
                                key1 = progress,
                                key2 = currentTextElementHeightPx.intValue,
                                block = {
                                    kotlinx.coroutines.delay(200)
                                    val height =
                                        (dp2px(maxHeight.value) - currentTextElementHeightPx.intValue) / 2
                                    lyricLines.indexOfLast { lyric ->
                                        lyric.time <= (progress?.first ?: 0)
                                    }.takeIf { i -> i > 0 }?.let { index ->
                                        state.animateScrollToItem(
                                            (index + 1).coerceAtLeast(0),
                                            -height.toInt()

                                        )
                                        currentLyricIndex = index
                                    }
                                }
                            )
                        }
                    }

                }
            }
        }
    }
}


@Composable
private fun BottomBar(
    playerScreenViewModel: PlayerScreenViewModel,
    progress: Pair<Long, Long>?,
    player: Player,
    isPlaying: Boolean?,
    currentMediaItem: MediaItem?
) {
    val userData = LocalUserData.current
    Column(
        modifier = Modifier
            .padding(16.dp)
            .navigationBarsPadding()
    ) {
        // 歌曲操作栏
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                16.dp,
                Alignment.CenterHorizontally
            )
        ) {
            var showDialog by remember {
                mutableStateOf(false)
            }
            ManipulatePlaylistDialog(
                show = showDialog,
                musicId = currentMediaItem?.mediaId?.toLong() ?: 0L
            )
            // 将歌曲添加到歌单
            IconButton(onClick = {
                showDialog = true
            }) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = null
                )
            }
            // 红心
            val likeList by playerScreenViewModel.likeList.collectAsState()
            IconButton(onClick = {
                playerScreenViewModel.like(userData.id)
            }) {
                Icon(
                    imageVector = if (likeList.readSafely()?.ids?.contains(
                            currentMediaItem?.mediaId?.toLong() ?: 0
                        ) == true
                    ) {
                        Icons.Rounded.Favorite
                    } else {
                        Icons.Rounded.FavoriteBorder
                    },
                    contentDescription = null
                )
            }
            // 循环控制
            var show by remember {
                mutableStateOf(false)
            }
            IconButton(onClick = {
                show = true
            }) {
                Icon(Icons.Rounded.Repeat, null)
                DropdownMenu(
                    expanded = show,
                    onDismissRequest = { show = false }
                ) {
                    DropdownMenuItem(onClick = {
                        show = false
                        player.repeatMode = Player.REPEAT_MODE_ONE
                    }) {
                        Text(text = "单曲循环")
                    }
                    DropdownMenuItem(onClick = {
                        show = false
                        player.repeatMode = Player.REPEAT_MODE_ALL
                    }) {
                        Text(text = "列表循环")
                    }
                }
            }
        }
        // 进度条
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val percent: Float = progress?.let { (it.first * 100f / it.second) / 100f } ?: 0f

            Text(text = progress?.first?.formatAsPlayerTime() ?: "00:00")
            var valueChanger by remember(percent) {
                mutableFloatStateOf(percent)
            }

            ColorfulSlider(
                modifier = Modifier.weight(1f),
                value = valueChanger,
                thumbRadius = 5.dp,
                trackHeight = 12.dp,
                onValueChange = { value ->
                    valueChanger = value
                },
                onValueChangeFinished = {
                    player.seekTo(
                        (valueChanger * (progress?.second ?: 0L))
                            .roundToLong()
                            .coerceAtLeast(0L)
                    )
                },
                colors = MaterialSliderDefaults.materialColors()
            )
            Text(text = progress?.second?.formatAsPlayerTime() ?: "00:00")
        }
        // 播放控制栏
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                32.dp,
                Alignment.CenterHorizontally
            )
        ) {
            IconButton(onClick = {
                player.seekToPreviousMediaItem()
            }) {
                Icon(
                    modifier = Modifier.size(72.dp),
                    imageVector = Icons.Rounded.SkipPrevious,
                    contentDescription = null
                )
            }

            IconButton(
                onClick = {
                    if (player.isPlaying) {
                        player.pause()
                    } else {
                        player.play()
                    }
                }
            ) {
                Icon(
                    modifier = Modifier.size(96.dp),
                    imageVector = if (isPlaying == true) Icons.Rounded.Pause else Icons.Rounded.PlayArrow,
                    contentDescription = null
                )
            }

            IconButton(onClick = {
                player.seekToNextMediaItem()

            }) {
                Icon(
                    modifier = Modifier.size(72.dp),
                    imageVector = Icons.Rounded.SkipNext,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun ManipulatePlaylistDialog(show: Boolean, musicId: Long) {
    if (show) {
        AlertDialog(
            onDismissRequest = {},
            title = {
                Text(text = "添加到歌单")
            },
            text = {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    // TODO: 加载歌单列表
                }
            },
            confirmButton = {
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "完成")
                }
            }
        )
    }
}


val Int.textDp: TextUnit
    @Composable get() = this.textDp(density = LocalDensity.current)

private fun Int.textDp(density: Density): TextUnit = with(density) {
    this@textDp.dp.toSp()
}
