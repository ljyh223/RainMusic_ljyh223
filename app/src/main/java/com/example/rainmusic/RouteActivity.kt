package com.example.rainmusic

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.ImageLoader
import coil.compose.LocalImageLoader
import com.example.rainmusic.data.model.UserData
import com.example.rainmusic.data.model.toSongs
import com.example.rainmusic.di.LocalPlaylistRepository
import com.example.rainmusic.repo.UserRepo
import com.example.rainmusic.service.MusicController
import com.example.rainmusic.service.MusicEvent
import com.example.rainmusic.ui.local.LocalNavController
import com.example.rainmusic.ui.local.LocalUserData
import com.example.rainmusic.ui.screen.Screen
import com.example.rainmusic.ui.screen.dailysong.DailySongScreen
import com.example.rainmusic.ui.screen.index.IndexScreen
import com.example.rainmusic.ui.screen.index.IndexViewModel
import com.example.rainmusic.ui.screen.index.page.IndexPage
import com.example.rainmusic.ui.screen.index.page.LibraryPage
import com.example.rainmusic.ui.screen.player.PlayerScreen
import com.example.rainmusic.ui.screen.playlist.PlaylistScreen
import com.example.rainmusic.ui.screen.search.SearchScreen
import com.example.rainmusic.ui.screen.setting.SettingScreen
import com.example.rainmusic.ui.theme.RainMusicTheme
import com.example.rainmusic.ui.viewmodel.ShareViewModel
import com.example.rainmusic.util.DataState
import com.example.rainmusic.util.defaultEnterTransition
import com.example.rainmusic.util.defaultPopExitTransition
import com.example.rainmusic.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import okhttp3.OkHttpClient
import javax.inject.Inject

@AndroidEntryPoint
class RouteActivity : ComponentActivity() {
    @Inject
    lateinit var userRepo: UserRepo

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Inject
    lateinit var localPlaylistRepository:LocalPlaylistRepository

    @Inject
    lateinit var musicController: MusicController

//    @Inject
//    lateinit var shareViewModel: ShareViewModel
//
//    @Inject
//    lateinit var IndexViewModel:IndexViewModel


    private var preparingData = true
    private var userData by mutableStateOf(UserData.VISITOR)

    @OptIn(
        ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Edge to Edge
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // 启动闪屏
        installSplashScreen().apply {
            // 准备完数据才结束splash screen
            setKeepOnScreenCondition { preparingData }
        }
        // splash screen时加载用户数据
        init()


        setContent {
            RainMusicTheme {
                Surface(color = MaterialTheme.colorScheme.background){
                    val navController = rememberNavController()

                    CompositionLocalProvider(
                        // 全局提供NavController
                        LocalNavController provides navController,
                        // 全局提供用户账号信息
                        LocalUserData provides userData,
                        // Coil
                        LocalImageLoader provides ImageLoader.Builder(this)
                            .okHttpClient(okHttpClient)
                            .build()
                    ) {
                        NavHost(
                            modifier = Modifier.fillMaxSize(),
                            navController = navController,
                            startDestination = "index",
                            enterTransition = defaultEnterTransition,
                            exitTransition = {
                                if (targetState.destination.route == Screen.Player.route) {
                                    fadeOut()
                                } else {
                                    slideOutHorizontally(
                                        targetOffsetX = {
                                            -it
                                        },
                                        animationSpec = tween()
                                    ) + fadeOut(
                                        animationSpec = tween()
                                    )
                                }
                            },
                            popEnterTransition = {
                                if (initialState.destination.route == Screen.Player.route) {
                                    fadeIn()
                                } else {
                                    slideInHorizontally(
                                        initialOffsetX = {
                                            -it
                                        },
                                        animationSpec = tween()
                                    )
                                }
                            },
                            popExitTransition = defaultPopExitTransition
                        ) {


                            composable(Screen.Index.route) {
                                IndexScreen()
                            }

                            composable(Screen.Search.route) {
                                SearchScreen()
                            }

                            composable(Screen.Setting.route) {
                                SettingScreen()
                            }
                            composable(Screen.ForYou.route) {
                                IndexPage()
                            }

                            composable(Screen.Library.route) {
                                LibraryPage()
                            }

                            composable(
                                route = "${Screen.Playlist.route}/{id}",
                                arguments = listOf(
                                    navArgument("id") {
                                        type = NavType.LongType
                                    }
                                )
                            ) {
                                PlaylistScreen(id = it.arguments!!.getLong("id"))
                            }

                            composable(
                                route = Screen.Player.route,
                                enterTransition = {
                                    slideInVertically(
                                        initialOffsetY = {
                                            it
                                        },
                                        animationSpec = tween()
                                    ) + fadeIn()
                                },
                                popExitTransition = {
                                    slideOutVertically(
                                        targetOffsetY = {
                                            it
                                        },
                                        animationSpec = tween()
                                    ) + fadeOut()
                                }
                            ) {
                                PlayerScreen()
                            }

                            composable(Screen.DailySong.route) {
                                DailySongScreen()
                            }

                        }
                    }
                }

            }
        }

        // 禁止强制深色模式
        (window.decorView.findViewById<ViewGroup>(android.R.id.content)
            .getChildAt(0) as? ComposeView)?.let {
            it.isForceDarkAllowed = false
        }
    }

    private fun init() {


        // 自动签到
//        userRepo.dailySign().onEach {
//            if (it is DataState.Success) {
//                it.readSafely()?.code?.takeIf { code -> code == 200 }?.let {
//                    toast("自动签到成功！")
//                }
//            }
//        }.launchIn(lifecycleScope)

        // 检查身份信息

//        localPlaylistRepository.getPlaylist().onEach {
//            if (it.isNotEmpty()) {
//                Log.d("RouteActivity", "add playlist")
//                musicController.addMediaItems(it.toSongs())
//                musicController.play(0)
//            }else{
//                Log.d("RouteActivity", "no playlist")
//            }
//        }.launchIn(lifecycleScope)

        combine(
            userRepo.refreshLogin(),
            userRepo.getAccountDetail(),
        ) { a, b->
            a to b
        }.onEach {
            if (it.first is DataState.Error) {
                Log.d("RouteActivity", "未登录")
                toast("未登录!")
            }
            Log.d("你 1","ok")

            if (it.second is DataState.Success) {
                val data = it.second.read()
                userData = UserData(
                    id = data.account!!.id,
                    nickname = data.profile!!.nickname,
                    avatarUrl = data.profile.avatarUrl
                )
                Log.d("你 2","ok")
            }
        }.onCompletion {
            preparingData = false
        }.launchIn(lifecycleScope)
    }

    fun retryInit() {
        init()
    }
}

//val LocalPlayerConnection = staticCompositionLocalOf<ShareViewModel?> {
//    error("No ShareViewModel provided")
//}