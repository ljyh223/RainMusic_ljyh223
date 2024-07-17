package com.example.rainmusic.ui.local

import androidx.compose.runtime.compositionLocalOf
import com.example.rainmusic.data.model.UserData

val LocalUserData = compositionLocalOf<UserData> {
    UserData.VISITOR
}