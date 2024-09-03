package com.example.rainmusic.ui.screen.index

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LibraryMusic
import androidx.compose.material.icons.rounded.Recommend
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.rainmusic.ui.screen.index.page.IndexPage
import com.example.rainmusic.ui.screen.index.page.LibraryPage


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun IndexScreen() {
    var pagerState by remember {
        mutableStateOf(HomePages.FouYou)
    }

    val myTabs = remember {
        setOf(HomePages.FouYou, HomePages.Library)
    }


    Scaffold(
        content = { contentPadding ->
            AnimatedContent(
                label = "home-content",
                targetState = pagerState,
                modifier = Modifier
                    .padding(contentPadding)
                    .fillMaxSize(),
            ) {
                when (it) {
                    HomePages.FouYou -> {
                        IndexPage()
                    }

                    HomePages.Library -> {
                        LibraryPage()
                    }
                }
            }
        },
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
            ){
                myTabs.forEach { t ->
                    val isSelected = pagerState == t
                    BottomNavigationItem(
                        icon = { Icon(t.icon, t.label) },
                        label = {
                            Text(
                                t.label, style = MaterialTheme.typography.labelSmall,
                                textAlign = TextAlign.Center,
                                overflow = TextOverflow.Ellipsis,
                                softWrap = false,
                            )
                        },
                        selected = isSelected,
                        onClick = {
                            pagerState = t
                        }
                    )
                }
            }
        }
    )
}

enum class HomePages(
    val label: String,
    val icon: ImageVector,
) {
    FouYou(
        "For You", Icons.Rounded.Recommend
    ),
    Library(
        "Library", Icons.Rounded.LibraryMusic
    ),
}
