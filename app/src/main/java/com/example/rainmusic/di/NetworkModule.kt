package com.example.rainmusic.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.rainmusic.data.retrofit.api.NeteaseMusicApi
import com.example.rainmusic.data.retrofit.eapi.NeteaseMusicEApi
import com.example.rainmusic.data.retrofit.weapi.NeteaseMusicWeApi
import com.example.rainmusic.util.okhttp.CookieHelper
import com.example.rainmusic.util.okhttp.EnsureHttpsInterceptor
import com.example.rainmusic.util.okhttp.UserAgentInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideHttpClient() = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .addInterceptor(EnsureHttpsInterceptor())
        .addInterceptor(UserAgentInterceptor()) // user-agent 拦截
        .cookieJar(CookieHelper) // cookie
        .build()

    @Singleton
    @Provides
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://music.163.com")
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideNeteaseWeApi(
        retrofit: Retrofit
    ): NeteaseMusicWeApi = retrofit.create(
        NeteaseMusicWeApi::class.java
    )

    @Singleton
    @Provides
    fun provideNeteaseApi(
        retrofit: Retrofit
    ): NeteaseMusicApi = retrofit.create(
        NeteaseMusicApi::class.java
    )

    @Singleton
    @Provides
    fun provideNeteaseEApi(okHttpClient: OkHttpClient): NeteaseMusicEApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://interface3.music.163.com")
        .client(okHttpClient)
        .build()
        .create(NeteaseMusicEApi::class.java)
}