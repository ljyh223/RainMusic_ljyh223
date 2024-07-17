package com.example.rainmusic.ui.screen.index


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.rainmusic.data.model.DailyImage
import com.example.rainmusic.data.model.ImageColor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import com.example.rainmusic.data.model.Playlists
import com.example.rainmusic.data.retrofit.api.model.DailyRecommendSongs
import com.example.rainmusic.data.retrofit.api.model.HighQualityPlaylist
import com.example.rainmusic.data.retrofit.api.model.Toplists
import com.example.rainmusic.data.retrofit.api.model.UserPlaylists
import com.example.rainmusic.data.retrofit.weapi.model.NewSongs
import com.example.rainmusic.data.retrofit.weapi.model.PersonalFM
import com.example.rainmusic.data.retrofit.weapi.model.PersonalizedPlaylist
import com.example.rainmusic.data.retrofit.weapi.model.PlaylistCategory
import com.example.rainmusic.data.retrofit.weapi.model.home.HomePageBlock
import com.example.rainmusic.di.DailyImageRepository
import com.example.rainmusic.di.ImageColorRepository
import com.example.rainmusic.repo.MusicRepo
import com.example.rainmusic.repo.UserRepo
import com.example.rainmusic.repo.YiYan
import com.example.rainmusic.repo.YiYanRepo
import com.example.rainmusic.util.DataState
import com.example.rainmusic.util.sharedPreferencesOf
import javax.inject.Inject

@HiltViewModel
class IndexViewModel @Inject constructor(
    private val userRepo: UserRepo,
    val musicRepo: MusicRepo,
    private val yiYanRepo: YiYanRepo,
    private val imageColorRepository: ImageColorRepository,
    private val dailyImageRepository: DailyImageRepository
) : ViewModel() {
    // recommend page
    val personalizedPlaylist: MutableStateFlow<DataState<PersonalizedPlaylist>> =
        MutableStateFlow(DataState.Empty)
    val personalizedSongs: MutableStateFlow<DataState<NewSongs>> = MutableStateFlow(DataState.Empty)
    val toplist: MutableStateFlow<DataState<Toplists>> = MutableStateFlow(DataState.Empty)
    val yiyan: MutableStateFlow<DataState<YiYan>> = MutableStateFlow(DataState.Empty)
    val dailySongs: MutableStateFlow<DataState<DailyRecommendSongs>> =
        MutableStateFlow(DataState.Empty)
    val fmSongs: MutableStateFlow<DataState<PersonalFM>> = MutableStateFlow(DataState.Empty)
    val homePageBlocks: MutableStateFlow<DataState<HomePageBlock>> =
        MutableStateFlow(DataState.Empty)

    // playlist discover
    val categoryAll: MutableStateFlow<DataState<PlaylistCategory>> =
        MutableStateFlow(DataState.Empty)
    val categorySelected: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    val highQualityPlaylist: MutableStateFlow<DataState<HighQualityPlaylist>> =
        MutableStateFlow(DataState.Empty)
    val playlistCatPager: MutableMap<String, Flow<PagingData<Playlists>>> = mutableMapOf()

    // library page
    val userPlaylist: MutableStateFlow<DataState<UserPlaylists>> = MutableStateFlow(DataState.Empty)


    fun getImageColor(url: String): Flow<ImageColor?> {
        return imageColorRepository.getImageColor(url)

    }

    fun insertImageColor(imageColor: ImageColor) {
        viewModelScope.launch {
            imageColorRepository.insertImageColor(imageColor)
        }
    }

    fun getDailyImage(date:String):Flow<DailyImage?>{
        return dailyImageRepository.getDailyImage(date)
    }

    fun insertDailyImage(dailyImage: DailyImage) {
        viewModelScope.launch {
            dailyImageRepository.insertDailyImage(dailyImage)
        }
    }


    fun refreshIndexPage() {
        Log.d("refreshIndexPage", "-----")
        musicRepo.getPersonalizedPlaylist(10)
            .onEach {
                personalizedPlaylist.value = it
            }
            .launchIn(viewModelScope)
        musicRepo.getNewSongs()
            .onEach {
                personalizedSongs.value = it
            }
            .launchIn(viewModelScope)
        musicRepo.getTopList()
            .onEach {
                toplist.value = it
            }.launchIn(viewModelScope)

        musicRepo.getDailyRecommendSongs()
            .onEach {
                dailySongs.value = it
            }.launchIn(viewModelScope)

        musicRepo.getPersonalFM().onEach {
            fmSongs.value = it
        }.launchIn(viewModelScope)

        musicRepo.getHomePageBlock().onEach {
            homePageBlocks.value = it
        }.launchIn(viewModelScope)


    }


    fun refreshExplorePage() {
        musicRepo.getPlaylistCategory().onEach {
            categoryAll.value = it
        }.launchIn(viewModelScope)

        musicRepo.getHighQualityPlaylist(
            cat = "全部",
            limit = 50
        ).onEach {
            highQualityPlaylist.value = it
        }.launchIn(viewModelScope)

        refreshSelectedCategory()
    }

    fun refreshSelectedCategory() {
        viewModelScope.launch {
            val sharedPreferences = sharedPreferencesOf("playlist_category")
            categorySelected.value = if (sharedPreferences.contains("data")) {
                // 载入用户自定义歌单category
                (sharedPreferences.getString("data", "")?.split(",") ?: emptyList())
            } else {
                // 载入热门category
                musicRepo.getHotPlaylistTags()?.tags?.map { it.name } ?: emptyList()
            }
        }
    }

    fun refreshHotComment() {
        yiYanRepo.loadYiYan()
            .onEach {
                yiyan.value = it
            }.launchIn(viewModelScope)
    }

    fun refreshLibraryPage(id: Long) {
        userRepo.getUserPlaylists(
            uid = id,
            limit = 1000
        ).onEach {
            userPlaylist.value = it
        }.launchIn(viewModelScope)
    }
}