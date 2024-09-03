package com.example.rainmusic.service

import android.app.PendingIntent
import android.content.ComponentName
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Binder
import android.util.Log
import androidx.annotation.OptIn
import androidx.core.content.getSystemService
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSpec
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.ResolvingDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.analytics.AnalyticsListener
import androidx.media3.exoplayer.analytics.PlaybackStats
import androidx.media3.exoplayer.analytics.PlaybackStatsListener
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.extractor.DefaultExtractorsFactory
import androidx.media3.session.MediaController
import androidx.media3.session.MediaLibraryService
import androidx.media3.session.MediaSessionService
import androidx.media3.session.MediaSession
import androidx.media3.session.SessionToken
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import com.example.rainmusic.RouteActivity
import com.example.rainmusic.repo.MusicRepo
import com.example.rainmusic.util.CoilBitmapLoader
import com.example.rainmusic.util.DataState
import com.example.rainmusic.util.RainMusicProtocol
import com.example.rainmusic.util.okhttp.https
import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import kotlinx.coroutines.flow.combine
import java.io.IOException
import javax.inject.Inject

private const val TAG = "MusicService"

@AndroidEntryPoint
class MusicService : MediaLibraryService(),
    Player.Listener,
    PlaybackStatsListener.Callback {
    @Inject
    lateinit var musicRepo: MusicRepo
    private val lifecycleScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private lateinit var player: ExoPlayer
    private lateinit var mediaSession: MediaLibrarySession

    private lateinit var connectivityManager: ConnectivityManager


    @OptIn(UnstableApi::class)
    override fun onCreate() {
        super.onCreate()

        player = ExoPlayer.Builder(this).apply {
            //使用默认音频设备，以及设置焦点可获取
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
                    .setUsage(C.USAGE_MEDIA)
                    .build(), true
            )

            //遇到一些情况自动暂停
            setHandleAudioBecomingNoisy(true)
            //自定义媒体源工厂
            setMediaSourceFactory(
                DefaultMediaSourceFactory(
                    // 自定义datasource
                    ResolvingDataSource.Factory(
                        DefaultDataSource.Factory(this@MusicService),
                        NeteaseMusicResolver()
                    ),
                    DefaultExtractorsFactory()
                ),
            )
            //设置当应用进入后台时，ExoPlayer 的唤醒模式。C.WAKE_MODE_LOCAL 表示仅当设备屏幕开启时保持播放器唤醒
            setWakeMode(C.WAKE_MODE_LOCAL)
        }.build()
        //重复播放
        player.repeatMode = Player.REPEAT_MODE_ALL

        mediaSession = MediaLibrarySession.Builder(this, player,MediaSessionCallback())
            .setSessionActivity(
                PendingIntent.getActivity(
                    this,
                    0,
                    Intent(this, RouteActivity::class.java),
                    PendingIntent.FLAG_IMMUTABLE
                )
            )
            .setBitmapLoader(CoilBitmapLoader(this, lifecycleScope))
            .build()

        val sessionToken = SessionToken(this, ComponentName(this, MusicService::class.java))
        val controllerFuture = MediaController.Builder(this, sessionToken).buildAsync()
        controllerFuture.addListener({ controllerFuture.get() }, MoreExecutors.directExecutor())


        connectivityManager = getSystemService()!!



    }

    override fun onDestroy() {
        lifecycleScope.cancel()
        player.release()
        mediaSession.release()

        super.onDestroy()
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo)= mediaSession


    private inner class MediaSessionCallback :  MediaLibrarySession.Callback {
        override fun onAddMediaItems(
            mediaSession: MediaSession,
            controller: MediaSession.ControllerInfo,
            mediaItems: MutableList<MediaItem>
        ): ListenableFuture<MutableList<MediaItem>> {
            val updatedMediaItems = mediaItems.map {

                Log.d("MediaSessionCallback",it.mediaMetadata.artworkUri.toString())
                it.buildUpon().setUri(it.mediaId).build()
//                newMediaItem
            }.toMutableList()

            return Futures.immediateFuture(updatedMediaItems)
        }
    }

    @UnstableApi
    inner class NeteaseMusicResolver : ResolvingDataSource.Resolver {
        override fun resolveDataSpec(dataSpec: DataSpec): DataSpec {
            // 动态解析歌曲地址
            if (dataSpec.uri.scheme == RainMusicProtocol && dataSpec.uri.host == "music") {
                val musicId =
                    dataSpec.uri.getQueryParameter("id")?.toLong() ?: error("can't find music id")
                Log.i(TAG, "resolveDataSpec: 开始解析歌曲($musicId)的播放地址")
                val url = runBlocking {
                    var musicUrl = ""
                    musicRepo.getMusicUrl(musicId).collect {
                        if (it is DataState.Success && musicUrl.isBlank()) {
                            musicUrl = it.readSafely()?.data?.get(0)?.url ?: ""
                        }
                    }
                    musicUrl
                }
                Log.i(TAG, "resolveDataSpec: 解析完成: $url")
//                Log.d("resolveDataSpec", player.currentMediaItem?.mediaMetadata?.artworkUri.toString())
                if (url.isBlank()) {

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
                        if (url.isNotBlank()) {
                            setUri(Uri.parse(url.https))

                        }

                    }
                    .build()
            }
            return dataSpec
        }
    }

    inner class MusicBinder : Binder() {
        val service: MusicService
            get() = this@MusicService
    }

    override fun onPlaybackStatsReady(
        eventTime: AnalyticsListener.EventTime,
        playbackStats: PlaybackStats
    ) {
        TODO("Not yet implemented")
    }
}