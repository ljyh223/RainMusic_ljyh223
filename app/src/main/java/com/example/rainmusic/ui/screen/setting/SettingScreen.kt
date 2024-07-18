package com.example.rainmusic.ui.screen.setting

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cookie
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.hilt.navigation.compose.hiltViewModel
import com.alorma.compose.settings.storage.preferences.rememberPreferenceBooleanSettingState
import com.alorma.compose.settings.storage.preferences.rememberPreferenceIntSettingState
import com.alorma.compose.settings.ui.SettingsGroup
import com.alorma.compose.settings.ui.SettingsMenuLink
import com.alorma.compose.settings.ui.SettingsSlider
import com.alorma.compose.settings.ui.SettingsSwitch
import com.example.rainmusic.ui.component.PopBackIcon
import com.example.rainmusic.ui.component.RainTopBar
import com.example.rainmusic.ui.screen.index.IndexViewModel
import com.example.rainmusic.util.DataState
import com.example.rainmusic.util.sharedPreferencesOf
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    indexViewModel: IndexViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            RainTopBar(
                navigationIcon = {
                    PopBackIcon()
                },
                title = {
                    Text(text = "设置")
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            item {
                Body(indexViewModel, context)
            }
        }
    }

}

@Composable
fun Body(
    indexViewModel: IndexViewModel,
    context: Context
) {

    val scope = rememberCoroutineScope()
    val showDialog = remember { mutableStateOf(false) }
    val inputText = remember { mutableStateOf(TextFieldValue()) }
    val cookieSharedPreferences = remember {
        sharedPreferencesOf("cookie")
    }
    val lyricSharedPreferences=remember {
        sharedPreferencesOf("lyric")
    }
//    val fontBold=rememberp
//    rememberBooleanSettingState()
    val fontBold = rememberPreferenceBooleanSettingState(key = "fontBold", defaultValue = true, preferences = lyricSharedPreferences)
    val lyricCenter = rememberPreferenceBooleanSettingState(key = "lyricCenter", defaultValue = true,preferences=lyricSharedPreferences)
    val fontSize = rememberPreferenceIntSettingState(key = "fontSize", defaultValue = 26, preferences = lyricSharedPreferences)
    if (showDialog.value) {

        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text(text = "网易云的Cookie的MUSIC_U字段") },
            text = {
                Column {
                    OutlinedTextField(
                        value = inputText.value,
                        onValueChange = { inputText.value = it },
                        label = { Text(text = "MUSIC_U") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    showDialog.value = false

                    cookieSharedPreferences.edit {
                        putString("MUSIC_U", inputText.value.text)
                    }
                }) {
                    Text(text = "OK")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog.value = false }) {
                    Text(text = "Cancel")
                }
            }
        )
    }
    SettingsGroup(
        modifier = Modifier,
        title = { Text(text = "User") },
    ) {
        SettingsMenuLink(
            title = { Text(text = "Cookie") },
            subtitle = { Text(text = "网易云cookie") },
            enabled = true,
            onClick = {
                showDialog.value = true
                Toast.makeText(context, "测试", Toast.LENGTH_SHORT).show()
            },
        )

        SettingsMenuLink(
            title = { Text(text = "测试Cookie") },
            enabled = true,
            onClick = {
                scope.launch {
                    indexViewModel.getAccountDetails()
                    indexViewModel.accountDetails.collect{
                        when(it){
                            DataState.Empty -> {}
                            is DataState.Error -> Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                            DataState.Loading -> {}
                            is DataState.Success -> Toast.makeText(context, it.read().profile?.nickname ?: "", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            },
        )
    }

    SettingsGroup(
        title = { Text(text = "Lyric") },
    ) {
        SettingsSwitch(
            state = fontBold,
            title = { Text(text = "字体加粗") },
            enabled = true,
            onCheckedChange = { fontBold.value=it},
        )
        SettingsSwitch(
            state = lyricCenter,
            title = { Text(text = "歌词居中") },
            enabled = true,
            onCheckedChange = {lyricCenter.value=it },
        )
        SettingsSlider(
            state = fontSize,
            valueRange = 12f..48f,
            title = { Text(text = "字体大小 12 -- 48") },
            enabled = true,
            onValueChange = { fontSize.value=it },
        )
    }
}