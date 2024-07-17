package com.example.rainmusic.util

import android.util.Log
import androidx.compose.runtime.Composable

sealed class DataState<out T> {
    object Empty : DataState<Nothing>()
    object Loading : DataState<Nothing>()

    data class Error(
        val exception: Exception
    ) : DataState<Nothing>()

    data class Success<T>(
        val data: T
    ) : DataState<T>()

    fun read(): T = (this as Success<T>).data

    fun readSafely(): T? = if (this is Success<T>) read() else null

//    val notLoaded: Boolean
//        get() = this is Empty || this is Error

    fun notLoaded(): Boolean {

        Log.d("notLoaded Error",(this is Error).toString())
        Log.d("notLoaded Empty",(this is Empty).toString())
        Log.d("notLoaded Success",(this is Success).toString())

        return when(this){
            is Empty -> true
            is Error -> true
            is Success -> false
            else -> false
        }
    }

    /**
     * 可视化DataState
     */
    @Composable
    inline fun Display(
        empty: @Composable () -> Unit = {},
        loading: @Composable () -> Unit = {},
        error: @Composable (Exception) -> Unit = {},
        success: @Composable (T) -> Unit
    ) {
        when(this){
            is Success -> success(read())
            is Error -> error(exception)
            is Loading -> loading()
            is Empty -> empty()
        }
    }
}