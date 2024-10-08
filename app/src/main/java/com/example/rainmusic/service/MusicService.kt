package com.example.rainmusic.service

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.annotation.OptIn
import androidx.core.content.edit
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSpec
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.ResolvingDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.extractor.DefaultExtractorsFactory
import androidx.media3.session.MediaLibraryService
import androidx.media3.session.MediaSession
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import com.example.rainmusic.RouteActivity
import com.example.rainmusic.repo.MusicRepo
import com.example.rainmusic.util.DataState
import com.example.rainmusic.util.RainMusicProtocol
import com.example.rainmusic.util.okhttp.https
import com.example.rainmusic.util.sharedPreferencesOf
import java.io.IOException
import javax.inject.Inject

private const val TAG = "MusicService"

@AndroidEntryPoint
class MusicService : MediaLibraryService() {
    @Inject
    lateinit var musicRepo: MusicRepo
    private val lifecycleScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private lateinit var player: Player
    private lateinit var mediaSession: MediaLibrarySession

    @OptIn(UnstableApi::class)
    override fun onCreate() {
        super.onCreate()

        player = ExoPlayer.Builder(this)
            //使用默认音频设备，以及设置焦点可获取
            .setAudioAttributes(AudioAttributes.DEFAULT, true)
            //遇到一些情况自动暂停
            .setHandleAudioBecomingNoisy(true)
            //自定义媒体源工厂
            .setMediaSourceFactory(
                DefaultMediaSourceFactory(
                    // 自定义datasource
                    ResolvingDataSource.Factory(DefaultDataSource.Factory(this), NeteaseMusicResolver()),
                    DefaultExtractorsFactory()
                ),
            )
            //设置当应用进入后台时，ExoPlayer 的唤醒模式。C.WAKE_MODE_LOCAL 表示仅当设备屏幕开启时保持播放器唤醒
            .setWakeMode(C.WAKE_MODE_LOCAL)
            .build()
        //重复播放
        player.repeatMode = Player.REPEAT_MODE_ALL

        mediaSession = MediaLibrarySession.Builder(this, player, LibrarySessionCallback())
            .setSessionActivity(
                PendingIntent.getActivity(
                    this,
                    0,
                    Intent(this, RouteActivity::class.java),
                    PendingIntent.FLAG_IMMUTABLE
                )
            )
            .build()
    }

    override fun onDestroy() {
        lifecycleScope.cancel()
        Log.d("onDestroy","onDestroy")
        val sharedPreferences = sharedPreferencesOf("last")

        player.currentMediaItem?.mediaMetadata.let {
            sharedPreferences.edit {
                putString("id", player.currentMediaItem?.mediaId)
                if (it != null) {
                    putString("title", it.title.toString())
                }

                if (it != null) {
                    putString("artist",it.artist.toString())
                }
                if (it != null) {
                    Log.d("onDestroy sharedPreferences",it.artworkUri.toString())
                    putString("image",it.artworkUri.toString())
                }
            }
        }

        player.release()
        mediaSession.release()

        super.onDestroy()
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaLibrarySession {
        return mediaSession
    }


    class LibrarySessionCallback : MediaLibrarySession.Callback

    @UnstableApi
    inner class NeteaseMusicResolver : ResolvingDataSource.Resolver {
        override fun resolveDataSpec(dataSpec: DataSpec): DataSpec {
            // 动态解析歌曲地址
            if(dataSpec.uri.scheme == RainMusicProtocol && dataSpec.uri.host == "music"){
                val musicId = dataSpec.uri.getQueryParameter("id")?.toLong() ?: error("can't find music id")
                Log.i(TAG, "resolveDataSpec: 开始解析歌曲($musicId)的播放地址")
                val url = runBlocking {
                    var musicUrl = ""
                    musicRepo.getMusicUrl(musicId).collect {
                        if(it is DataState.Success && musicUrl.isBlank()){
                            musicUrl = it.readSafely()?.data?.get(0)?.url ?: ""
                        }
                    }
                    musicUrl
                }
                Log.i(TAG, "resolveDataSpec: 解析完成: $url")
                if(url.isBlank()){
                    lifecycleScope.launch {
                        if (player.hasNextMediaItem()) {
                            player.seekToNextMediaItem()
                            player.prepare()
                            player.play()
                        } else {
                            throw IOException("无法解析Music URL")
                        }
                    }
                }
                return dataSpec.buildUpon()
                    .apply {
                        if(url.isNotBlank()){
                            setUri(Uri.parse(url.https))

                        }
                    }
                    .build()
            }
            return dataSpec
        }
    }
}