package com.example.rainmusic.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rainmusic.data.model.Playlists
import com.example.rainmusic.data.retrofit.weapi.model.TopPlaylists
import com.example.rainmusic.repo.MusicRepo
import com.example.rainmusic.util.DataState

private const val TAG = "TopPlaylistPagingSource"

class TopPlaylistPagingSource(
    private val category: String,
    private val musicRepo: MusicRepo
) : PagingSource<Int, Playlists>() {
    override fun getRefreshKey(state: PagingState<Int, Playlists>): Int {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Playlists> {
        val page = params.key ?: 0
        var state: DataState<TopPlaylists> = DataState.Loading

        Log.i(TAG, "load: loading playlist[$page] of $category")
        
        musicRepo.getTopPlaylist(
            category = category,
            offset = page * params.loadSize,
            limit = params.loadSize
        ).collect {
            state = it
        }

        return when(state){
            is DataState.Success -> LoadResult.Page(
                data = state.read().playlists ?: emptyList(),
                prevKey = if(page == 0) null else page - 1,
                nextKey = if(state.read().more) page + 1 else null
            )
            is DataState.Error -> LoadResult.Error((state as DataState.Error).exception)
            else -> LoadResult.Invalid()
        }
    }
}