package com.example.rainmusic.di

import com.example.rainmusic.data.model.DailyImage
import com.example.rainmusic.data.model.ImageColor
import com.example.rainmusic.data.model.LocalSong
import com.example.rainmusic.util.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageColorRepository @Inject constructor(private val dao: ImageColorDao) {

    fun getImageColor(url:String): Flow<ImageColor?> {
        return dao.getImageColor(url)
    }

    suspend fun insertImageColor(imageColor: ImageColor) {
        dao.insert(imageColor)
    }
}


class DailyImageRepository @Inject constructor(private val dao: DailyImageDao) {

    fun getDailyImage(date:String): Flow<DailyImage?> {
        return dao.getDailyImage(date)
    }

    suspend fun insertDailyImage(dailyImage: DailyImage) {
        dao.insert(dailyImage)
    }
}


class LocalPlaylistRepository @Inject constructor(private val playlistDao: LocalPlaylistDao) {

    fun getPlaylist(): Flow<List<LocalSong>> {
        return playlistDao.getPlaylist()
    }

    suspend fun insertPlaylist(playlist: List<LocalSong>) {
        playlistDao.insertPlaylist(playlist)
    }
}