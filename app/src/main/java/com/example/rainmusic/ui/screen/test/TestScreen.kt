package com.example.rainmusic.ui.screen.test

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.rainmusic.data.retrofit.api.NeteaseMusicApi
import com.example.rainmusic.data.retrofit.eapi.NeteaseMusicEApi
import com.example.rainmusic.data.retrofit.weapi.NeteaseMusicWeApi
import com.example.rainmusic.repo.MusicRepo
import com.example.rainmusic.ui.component.PopBackIcon
import com.example.rainmusic.ui.component.RainTopBar
import com.example.rainmusic.ui.local.LocalUserData
import com.example.rainmusic.util.setPaste
import korlibs.crypto.AES
import korlibs.crypto.Padding
import korlibs.crypto.encoding.unhex
import javax.inject.Inject

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun TestScreen(testViewModel: TestViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            RainTopBar(
                title = {
                    Text(text = "测试页面")
                },
                navigationIcon = {
                    PopBackIcon()
                }
            )
        }
    ) {
        val user = LocalUserData.current
        val context = LocalContext.current
        Column(Modifier.fillMaxSize()) {
            var content by remember {
                mutableStateOf("")
            }

            Button(onClick = {
                testViewModel.test {
                    content = it
                }
            }) {
                Text(text = "Send")
            }

            Button(onClick = {

                context.setPaste(content)
            }) {
                Text(text = "Copy")
            }

            TextField(value = content, onValueChange = {content = it})
            Button(onClick = {
                val decrypt = AES.decryptAesEcb(
                    data = content.unhex,
                    key = "e82ckenh8dichen8".toByteArray(),
                    padding = Padding.PKCS7Padding
                )
                println("解密结果: ${String(decrypt)}")
            }) {
                Text(text = "解密EAPI")
            }
        }
    }
}

@HiltViewModel
class TestViewModel @Inject constructor(
    private val weApi: NeteaseMusicWeApi,
    private val api: NeteaseMusicApi,
    private val eApi: NeteaseMusicEApi,
    private val musicRepo: MusicRepo
) : ViewModel() {
    fun test(callback: (String) -> Unit){
        viewModelScope.launch {

        }
    }
}