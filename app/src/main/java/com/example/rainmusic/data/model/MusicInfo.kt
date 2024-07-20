package com.example.rainmusic.data.model

import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import com.example.rainmusic.util.RainMusicProtocol
import com.example.rainmusic.util.media.buildMediaItem
import com.example.rainmusic.util.media.metadata

data class MusicInfo(
    val id: Long,
    val name: String,
    val artist: String,
    val musicUrl: String,
    val artworkUrl: String
) {
    fun toMediaItem(): MediaItem {
        return MediaItem.Builder()
            .setMediaId(id.toString())
            .setUri(musicUrl)
            .setMediaMetadata(
                MediaMetadata.Builder()
                    .setTitle(name)
                    .setSubtitle(artist)
                    .setArtist(artist)
                    .setArtworkUri(Uri.parse(artworkUrl))
                    .build()
            )
            .build()
    }

    companion object {
        val PersonalFM = MusicInfo(
            id = 0L,
            name = "私人FM",
            artist = "私人FM",
            musicUrl = "",
            artworkUrl = ""
        )
    }
}