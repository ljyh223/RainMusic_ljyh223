package com.example.rainmusic.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.rainmusic.ui.local.LocalNavController

/**
 * 支持Edge to Edge的TopBar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RainTopBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = WindowInsets.statusBars.asPaddingValues(),
    navigationIcon: @Composable () -> Unit ={},
    actions: @Composable RowScope.() -> Unit = {},
    colors: TopAppBarColors = topAppBarColors(
        ),
    appBarStyle: AppBarStyle = AppBarStyle.Small,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {

    val appBarContainerColor = MaterialTheme.colorScheme.primary

    Surface(modifier = modifier, color = appBarContainerColor) {
        when (appBarStyle) {
            AppBarStyle.Small -> {
                TopAppBar(
                    title = title,
//                    modifier = Modifier.padding(contentPadding),
                    navigationIcon = navigationIcon,
                    actions = actions,
                    colors = colors,
                    scrollBehavior = scrollBehavior
                )
            }
            AppBarStyle.Medium -> {
                MediumTopAppBar(
                    title = title,
//                    modifier = Modifier.padding(contentPadding),
                    navigationIcon = navigationIcon,
                    actions = actions,
                    colors = colors,
                    scrollBehavior = scrollBehavior
                )
            }
            AppBarStyle.Large -> {
                LargeTopAppBar(
                    title = title,
//                    modifier = Modifier.padding(contentPadding),
                    navigationIcon = navigationIcon,
                    actions = actions,
                    colors = colors,
                    scrollBehavior = scrollBehavior
                )
            }
        }
    }
}

/**
 * 支持Edge to Edge的 NavigationBar
 */
@Composable
fun RainBottomNavigation(
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        tonalElevation = 3.dp
    ) {
        CompositionLocalProvider(
            LocalAbsoluteTonalElevation provides LocalAbsoluteTonalElevation.current - 3.dp
        ) {
            NavigationBar(
                modifier = Modifier.padding(
                    WindowInsets.navigationBars.asPaddingValues()
                )
            ) {
                content()
            }
        }
    }
}

/**
 * 点击返回上一级的按钮
 */
@Composable
fun PopBackIcon(
    icon: ImageVector = Icons.AutoMirrored.Rounded.ArrowBack
){
    val navController = LocalNavController.current
    IconButton(onClick = {
        navController.popBackStack()
    }) {
        Icon(icon, "Back")
    }
}

/**
 * 顶栏样式
 */
enum class AppBarStyle {
    Small,
    Medium,
    Large
}