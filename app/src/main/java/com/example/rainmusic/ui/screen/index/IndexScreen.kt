package com.example.rainmusic.ui.screen.index

import android.net.Uri
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material.icons.automirrored.rounded.FeaturedPlayList
import androidx.compose.material.icons.automirrored.rounded.QueueMusic
import androidx.media3.common.MediaItem
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch
import com.example.rainmusic.RouteActivity
import com.example.rainmusic.ui.component.*
import com.example.rainmusic.ui.local.LocalNavController
import com.example.rainmusic.ui.local.LocalUserData
import com.example.rainmusic.ui.screen.Screen
import com.example.rainmusic.ui.screen.index.page.DiscoverPage
import com.example.rainmusic.ui.screen.index.page.IndexPage
import com.example.rainmusic.ui.screen.index.page.LibraryPage
import com.example.myapplication.R
import com.example.rainmusic.data.model.MusicInfo
import com.example.rainmusic.service.MusicService
import com.example.rainmusic.ui.states.asyncGetSessionPlayer
import com.example.rainmusic.util.DataState
import com.example.rainmusic.util.RainMusicProtocol
import com.example.rainmusic.util.media.buildMediaItem
import com.example.rainmusic.util.media.metadata
import com.example.rainmusic.util.sharedPreferencesOf

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun IndexScreen(
    indexViewModel: IndexViewModel = hiltViewModel<IndexViewModel>()
) {
    val navController = LocalNavController.current
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val pagerState = rememberPagerState(pageCount = { 3 })
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    LaunchedEffect(key1 = 1) {

    }
    Scaffold(
        topBar = {
            IndexTopBar(
                indexViewModel = indexViewModel,
                scrollBehavior = scrollBehavior,
                drawerState = drawerState
            )
        },
        bottomBar = {
            BottomNavigationBar(
                pagerState = pagerState
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                Screen.Player.navigate(navController)
            }) {
                Icon(Icons.AutoMirrored.Rounded.QueueMusic, null)
            }
        }
    ) {
        Column {
            NetworkBanner(indexViewModel)

            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                state = pagerState,
            ) { page ->
                when (page) {
                    0 -> {
                        IndexPage(indexViewModel)
                    }

                    1 -> {
                        DiscoverPage(indexViewModel)
                    }

                    2 -> {
                        RequireLoginVisible {
                            LibraryPage(indexViewModel)
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun NetworkBanner(
    indexViewModel: IndexViewModel
) {
    val context = LocalContext.current
    val data by indexViewModel.personalizedSongs.collectAsState()
    AnimatedVisibility(
        visible = data is DataState.Error
    ) {
        NetworkIssueBanner {
            (context as RouteActivity).retryInit()
            indexViewModel.refreshIndexPage()
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
private fun IndexTopBar(
    indexViewModel: IndexViewModel,
    scrollBehavior: TopAppBarScrollBehavior,
    drawerState: DrawerState
) {
    val navController = LocalNavController.current
    RainTopBar(

        title = {
            Text(text = stringResource(R.string.app_name))

        },
        actions = {
            var showDebugButtons by remember {
                mutableStateOf(false)
            }
            Icon(
                modifier = Modifier
                    .combinedClickable(
                        onClick = {
                            navController.navigate(Screen.Search.route)
                        },
                        onLongClick = {
                            showDebugButtons = !showDebugButtons
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


@Composable
private fun BottomNavigationBar(
    pagerState: androidx.compose.foundation.pager.PagerState
) {
    val scope = rememberCoroutineScope()
    RainBottomNavigation {
        NavigationBarItem(
            selected = pagerState.currentPage == 0,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(
                        page = 0
                    )
                }
            },
            icon = {
                Icon(Icons.Rounded.Recommend, null)
            },
            label = {
                Text(text = "推荐")
            }
        )

        NavigationBarItem(
            selected = pagerState.currentPage == 1,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(
                        page = 1
                    )
                }
            },
            icon = {
                Icon(Icons.AutoMirrored.Rounded.FeaturedPlayList, null)
            },
            label = {
                Text(text = "发现")
            }
        )

        NavigationBarItem(
            selected = pagerState.currentPage == 2,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(
                        page = 2
                    )
                }
            },
            icon = {
                Icon(Icons.Rounded.Headphones, null)
            },
            label = {
                Text(text = "音乐库")
            }
        )
    }
}

@Composable
private fun DrawerContent(indexViewModel: IndexViewModel) {
    val userData = LocalUserData.current
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // TODO: 完善drawer
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = userData.avatarUrl),
                contentDescription = null,
                modifier = Modifier.clip(CircleShape)
            )
        }
    }
}

