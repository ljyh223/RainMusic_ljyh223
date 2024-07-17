package com.example.rainmusic

import android.app.Application
import androidx.room.Room
import com.example.rainmusic.di.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppContext : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        @JvmStatic
        lateinit var instance: AppContext

    }


}

