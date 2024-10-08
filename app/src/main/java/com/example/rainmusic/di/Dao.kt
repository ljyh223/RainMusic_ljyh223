package com.example.rainmusic.di

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Room
import com.example.rainmusic.data.model.DailyImage
import com.example.rainmusic.data.model.ImageColor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow

@Database(entities = [ImageColor::class,DailyImage::class], version = 2,exportSchema=false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ImageColorDao(): ImageColorDao
    abstract fun DailyImageDao(): DailyImageDao
    abstract fun ImageColorDao1(): ImageColorDao1




    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}


@Dao
interface ImageColorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(imageColor: ImageColor)

    @Query("SELECT * FROM image_colors WHERE url = :url")
    fun getImageColor(url: String): Flow<ImageColor?>
}
@Dao
interface ImageColorDao1 {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(imageColor: ImageColor)

    @Query("SELECT * FROM image_colors WHERE url = :url")
    fun getImageColor(url: String): ImageColor?
}


@Dao
interface DailyImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(imageColor: DailyImage)

    @Query("SELECT * FROM daily_image WHERE date = :date")
    fun getDailyImage(date: String): Flow<DailyImage?>
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(appContext, AppDatabase::class.java, "app_database").build()

    @Provides
    fun provideImageColorRepository(db: AppDatabase): ImageColorRepository =
        ImageColorRepository(db.ImageColorDao())

    @Provides
    fun provideDailyImageRepository(db: AppDatabase): DailyImageRepository =
        DailyImageRepository(db.DailyImageDao())
}
