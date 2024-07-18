package com.example.rainmusic.ui.screen.index

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.QueueMusic
import androidx.compose.material.icons.rounded.Headphones
import androidx.compose.material.icons.rounded.Recommend
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.R
import com.example.rainmusic.RouteActivity
import com.example.rainmusic.ui.component.AppBarStyle
import com.example.rainmusic.ui.component.NetworkIssueBanner
import com.example.rainmusic.ui.component.RainBottomNavigation
import com.example.rainmusic.ui.component.RainTopBar
import com.example.rainmusic.ui.local.LocalNavController
import com.example.rainmusic.ui.local.LocalUserData
import com.example.rainmusic.ui.screen.Screen
import com.example.rainmusic.ui.screen.index.page.IndexPage
import com.example.rainmusic.ui.screen.index.page.LibraryPage
import com.example.rainmusic.util.DataState
import kotlinx.coroutines.launch


@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun IndexScreen(
    indexViewModel: IndexViewModel = hiltViewModel<IndexViewModel>()
) {
    val navController = LocalNavController.current
    val pagerState = rememberPagerState(pageCount = { 2 })

    Scaffold(
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
                        LibraryPage(indexViewModel)
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

