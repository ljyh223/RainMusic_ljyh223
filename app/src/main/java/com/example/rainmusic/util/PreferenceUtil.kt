package com.example.rainmusic.util

import android.content.Context
import android.content.SharedPreferences
import com.example.rainmusic.AppContext

fun sharedPreferencesOf(
    name: String
): SharedPreferences = AppContext.instance.getSharedPreferences(name, Context.MODE_PRIVATE)