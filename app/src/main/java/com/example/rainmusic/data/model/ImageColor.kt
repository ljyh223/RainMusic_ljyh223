package com.example.rainmusic.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_colors")
data class ImageColor(
    @PrimaryKey val url: String,
    @ColumnInfo(name = "main_color") val mainColor: Int
)

@Entity(tableName = "daily_image")
data class DailyImage(
    @PrimaryKey val date: String,
    @ColumnInfo(name = "image_url") val imageUrl: String
)