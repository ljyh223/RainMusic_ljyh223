package com.example.rainmusic.ui.screen.index.page


import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.myapplication.R
import com.example.rainmusic.data.model.DailyImage
import com.example.rainmusic.data.model.MusicInfo
import com.example.rainmusic.data.model.Song
import com.example.rainmusic.data.model.toSongs
import com.example.rainmusic.data.retrofit.weapi.model.PersonalizedPlaylist
import com.example.rainmusic.service.MusicEvent
import com.example.rainmusic.ui.component.AppBarStyle
import com.example.rainmusic.ui.component.PlayerBottomBar
import com.example.rainmusic.ui.component.RainTopBar
import com.example.rainmusic.ui.component.shimmerPlaceholder
import com.example.rainmusic.ui.local.LocalNavController
import com.example.rainmusic.ui.local.LocalUserData
import com.example.rainmusic.ui.screen.Screen
import com.example.rainmusic.ui.screen.dailysong.playMusics
import com.example.rainmusic.ui.screen.index.IndexViewModel
import com.example.rainmusic.ui.viewmodel.ShareViewModel
import com.example.rainmusic.util.DataState
import com.example.rainmusic.util.RainMusicProtocol
import com.example.rainmusic.util.color.getImageDominantColor
import com.example.rainmusic.util.getGreeting
import com.example.rainmusic.util.largeImage
import com.example.rainmusic.util.middleImage
import com.example.rainmusic.util.sdf_
import com.example.rainmusic.util.smallImage
import kotlinx.coroutines.launch


@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalAnimationApi::class
)
@Composable
fun IndexPage(
    indexViewModel: IndexViewModel=hiltViewModel(),
    shareViewModel: ShareViewModel=hiltViewModel(),
) {
    val recommendStatus by indexViewModel.personalizedPlaylist.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val userData = LocalUserData.current
    val musicControllerUiState = shareViewModel.musicControllerUiState
//    val localPlaylist by shareViewModel.localPlaylist.collectAsState()
    LaunchedEffect(userData) {
        Log.d("IndexPage", recommendStatus.notLoaded().toString())
        indexViewModel.getAccountDetails()
        shareViewModel.getPlaylist()
        if (!userData.isVisitor) {
            if (recommendStatus.notLoaded()) {
                indexViewModel.refreshIndexPage()
//                shareViewModel.getPlaylist()
            }
        }
    }
//    localPlaylist.takeIf { it.isNotEmpty() }?.let {
//        shareViewModel.onEvent(MusicEvent.AddSongs(it.toSongs()))
//        shareViewModel.onEvent(MusicEvent.PlaySong(1))
//    }


    Scaffold(
        topBar = {
            HomeTopBar(
                scrollBehavior = scrollBehavior
            )
        },

        bottomBar = {
            PlayerBottomBar(
                onEvent = shareViewModel::onEvent,
                playerState= musicControllerUiState,
                song = musicControllerUiState.currentSong,
                onBarClick = {

                }
            )
        }
    ) {


        if (!userData.isVisitor) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                item {
                    LargeButton(indexViewModel)
                }

                item {
                    RecommendSong(indexViewModel,shareViewModel::onEvent)
                }

                item {
                    RecommendPlayLists(indexViewModel)
                }
            }
        } else {
            Text("请先设置Cookie")
        }
    }


}


@Composable
private fun RecommendPlayLists(
    indexViewModel: IndexViewModel
) {
    val personalizedPlaylist by indexViewModel.personalizedPlaylist.collectAsState()
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "推荐歌单", style = MaterialTheme.typography.headlineSmall)
        Surface(
            shape = RoundedCornerShape(12.dp),
            tonalElevation = 8.dp
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                when (personalizedPlaylist) {
                    is DataState.Success -> {
                        items(personalizedPlaylist.read().result,key = { it.id }) { it ->
                            PlaylistCard(it)
                        }
                    }

                    is DataState.Loading -> {
                        items(5) {
                            Box(
                                modifier = Modifier
                                    .shimmerPlaceholder(true)
                                    .size(100.dp)
                            )
                        }
                    }

                    is DataState.Error -> {
                        items(5) {
                            Box(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.surface)
                                    .size(100.dp)
                            )
                        }
                    }

                    DataState.Empty -> {}
                }
            }
        }
    }
}

@Composable
private fun PlaylistCard(
    playlist: PersonalizedPlaylist.Result
) {
    val navController = LocalNavController.current
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .clickable {
                Screen.Playlist.navigate(navController) {
                    addPath(playlist.id.toString())
                }
            }
            .padding(8.dp)
            .width(IntrinsicSize.Min),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(playlist.picUrl.largeImage())
                .size(Size.ORIGINAL) // 根据需要调整大小
                .build(),
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .size(100.dp),

            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Text(
            text = playlist.name,
            style = MaterialTheme.typography.labelMedium,
            maxLines = 2
        )
    }
}


@Composable
fun LargeButton(indexViewModel: IndexViewModel) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val userData = LocalUserData.current

        Text(text = getGreeting(), style = MaterialTheme.typography.headlineSmall)
//         日推

//         私人FM
//        val fmSongs by indexViewModel.fmSongs.collectAsState()


        val homePageBlocks by indexViewModel.homePageBlocks.collectAsState()

        var dailyCover = ""
        val sdf = sdf_
        val dailyImage by indexViewModel.getDailyImage(sdf).collectAsState(initial = null)
        if (dailyImage == null) {
//            Log.d("dailyImage", "null")
            val dailSongs by indexViewModel.dailySongs.collectAsState()
            when (dailSongs) {
                is DataState.Success -> {
                    dailyCover = dailSongs.read().data.dailySongs[0].al.picUrl
                    indexViewModel.insertDailyImage(DailyImage(sdf, dailyCover))
                }

                DataState.Empty -> {}
                is DataState.Error -> {}
                DataState.Loading -> {}
            }


        } else {
//            Log.d("dailyImage", "")
            dailyCover = dailyImage!!.imageUrl
        }
        val navController = LocalNavController.current
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (userData.isVisitor) {
                item {

                    RecommendCard(
                        picUrl = dailyCover.largeImage(),
                        title = "每日推荐",
                    ) {
                        Screen.DailySong.navigate(navController)
                    }
                }
            }


//            when (fmSongs) {
//                is DataState.Success -> {
//                    item {
//                        RecommendCard(
//                            picUrl = fmSongs.read().data[0].album.picUrl,
//                            title = "私人漫游",
//                        ) {
//
//                        }
//                    }
//                }
//
//                DataState.Empty -> {
//                    Log.d("LargeButton", "Empty")
//                }
//
//                is DataState.Error -> {
//                }
//
//                DataState.Loading -> {}
//            }


            when (homePageBlocks) {
                is DataState.Success -> {
                    val homePage = homePageBlocks.read()
                    val oneBlocks = homePage.data.blocks[0].creatives
                    val twoBlocks = homePage.data.blocks[1].creatives
                    items(twoBlocks) {
                        RecommendCard(
                            it.resources[0].uiElement.image.imageUrl.largeImage(),
                            it.resources[0].uiElement.mainTitle.title,
                        ) {
                            Screen.Playlist.navigate(navController) {
                                addPath(it.resources[0].resourceId)
                            }
                        }
                    }
                    items(oneBlocks) {
                        Log.d("RecommendCard", it.toString())
                        RecommendCard(
                            it.resources[0].uiElement.image.imageUrl.largeImage(),
                            it.resources[0].uiElement.mainTitle.title,
                        ) {
                            Screen.Playlist.navigate(navController) {
                                addPath(it.resources[0].resourceId)
                            }
                        }
                    }

                }

                DataState.Empty -> {
                    Log.d("LargeButton", "Empty")
                }

                is DataState.Error -> {
                    Log.d("LargeButton", "Error")

                }

                DataState.Loading -> {
                    Log.d("LargeButton", "Loading")

                }
            }
        }

    }

}


@Composable
private fun RecommendCard(
    picUrl: String,
    title: String,
    onClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var color by remember { mutableStateOf(Color.Black) }

    LaunchedEffect(key1 = title) {
        scope.launch {
            val c = getImageDominantColor(picUrl, context)
            Log.d("RecommendCard", c.toString())
            color = Color(c)
        }
    }
    Column(
        modifier = Modifier.fillMaxWidth(),

        ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp)
                .clip(RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp)),
            onClick = onClick,
            color = Color.Black.copy(alpha = 0.5f)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(picUrl)
                    .size(Size.ORIGINAL) // 根据需要调整大小
                    .build(),
                modifier = Modifier
                    .size(120.dp),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
        Surface(
            modifier = Modifier
                .width(120.dp)
                .clip(RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)),
            color = color
        ) {
            Text(
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                text = title,
                modifier = Modifier.padding(8.dp),
                maxLines = 1,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }

}


@Composable
private fun RecommendSong(indexViewModel: IndexViewModel,onEvent:(MusicEvent)->Unit) {


    val accountData = LocalUserData.current
    val context = LocalContext.current
    if (!accountData.isVisitor) {
        val dailSongs by indexViewModel.dailySongs.collectAsState()

        when (dailSongs) {
            is DataState.Success -> {
                fun play(index: Int) {
                    val songs = dailSongs.read().data.dailySongs.map {

//                        MusicInfo(
//                            id = it.id,
//                            name = it.name,
//                            artist = it.ar.joinToString { it.name },
//                            musicUrl = "$RainMusicProtocol://music?id=${it.id}",
//                            artworkUrl = it.al.picUrl
//                        )
//
                        Song(
                            mediaId = it.id.toString(),
                            title=it.name,
                            subtitle = it.ar.joinToString { it.name },
                            songUrl = "$RainMusicProtocol://music?id=${it.id}",
                            imageUrl=it.al.picUrl
                        )
                    }
                    onEvent(MusicEvent.AddSongs(songs))
                    onEvent(MusicEvent.PlaySong(index))
//                    playMusics(context, songs, songs[index])
                }

                key(true){
                    Column {
                        Row {
                            Surface(
                                modifier = Modifier
                                    .weight(2f)
                                    .aspectRatio(1f)
                                    .padding(start = 0.dp, top = 4.dp, end = 4.dp, bottom = 4.dp)
                                    .clickable {

                                        val songs =
                                            dailSongs.read().data.dailySongs
                                                .subList(5, 13)
                                                .map { track ->
//                                                MusicInfo(
//                                                    id = track.id,
//                                                    name = track.name,
//                                                    artist = track.ar.joinToString { it.name },
//                                                    musicUrl = "$RainMusicProtocol://music?id=${track.id}",
//                                                    artworkUrl = track.al.picUrl
//                                                )

                                                    Song(
                                                        mediaId = track.id.toString(),
                                                        title = track.name,
                                                        subtitle = track.ar.joinToString { it.name },
                                                        songUrl = "$RainMusicProtocol://music?id=${track.id}",
                                                        imageUrl = track.al.picUrl
                                                    )
                                                }

                                        onEvent(MusicEvent.AddSongs(songs))
                                        onEvent(MusicEvent.PlaySong(0))
//                                    playMusics(context, songs)
                                    },
                                tonalElevation = 12.dp,
                                shape = RoundedCornerShape(16.dp)

                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(8.dp)
                                ) {
                                    Text(
                                        text = "推荐",
                                        fontSize = 48.sp,
                                        color = colorResource(R.color.light_blue_A700)
                                    )
                                    Text(text = "歌曲", fontSize = 48.sp)
                                }
                            }

                            Column(
                                modifier = Modifier
                                    .weight(1f)
                            ) {
                                AsyncImage(
                                    model = ImageRequest.Builder(context)
                                        .data(dailSongs.read().data.dailySongs[0].al.picUrl.middleImage())
                                        .size(Size.ORIGINAL) // 根据需要调整大小
                                        .build(),
                                    modifier = Modifier
                                        .aspectRatio(1f)
                                        .padding(6.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable {
                                            play(0)
                                        },
                                    contentDescription = null
                                )
                                AsyncImage(
                                    model = ImageRequest.Builder(context)
                                        .data(dailSongs.read().data.dailySongs[1].al.picUrl.middleImage())
                                        .size(Size.ORIGINAL) // 根据需要调整大小
                                        .build(),
                                    modifier = Modifier
                                        .aspectRatio(1f)
                                        .padding(6.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable {
                                            play(1)
                                        },
                                    contentDescription = null
                                )
                            }

                            AsyncImage(
                                model = ImageRequest.Builder(context)
                                    .data(dailSongs.read().data.dailySongs[2].al.picUrl.largeImage())
                                    .size(Size.ORIGINAL) // 根据需要调整大小
                                    .build(),
                                modifier = Modifier
                                    .weight(2f)
                                    .aspectRatio(1f)
                                    .padding(6.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .clickable {
                                        play(2)
                                    },
                                contentDescription = null
                            )

                        }

                        Row {

                            for (i in 3..7) {
                                AsyncImage(
                                    model = ImageRequest.Builder(context)
                                        .data(dailSongs.read().data.dailySongs[i].al.picUrl.middleImage())
                                        .size(Size.ORIGINAL) // 根据需要调整大小
                                        .build(),
                                    modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(1f)
                                        .padding(4.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable {
                                            play(i)
                                        },
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }

            }

            is DataState.Loading -> {
            }

            else -> {}
        }

    }


}


@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
private fun HomeTopBar(
    scrollBehavior: TopAppBarScrollBehavior
) {
    val navController = LocalNavController.current
    RainTopBar(

        title = {
            Text(text = stringResource(R.string.app_name))

        },
        actions = {
            Icon(
                modifier = Modifier
                    .combinedClickable(
                        onClick = {
                            navController.navigate(Screen.Search.route)
                        }
                    ),
                imageVector = Icons.Rounded.Search,
                contentDescription = "Search"
            )
        },
        appBarStyle = AppBarStyle.Small,
        scrollBehavior = scrollBehavior
    )
}