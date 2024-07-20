package com.example.rainmusic.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.media3.common.Player
import com.example.rainmusic.data.retrofit.api.model.MusicDetails
import com.example.rainmusic.util.DataState

@Composable
fun Menu(showMenu: MutableState<Boolean> ,musicDetail: DataState<MusicDetails>){
    DropdownMenu(
        expanded = showMenu.value,
        onDismissRequest = { showMenu.value = false }
    ) {
        val showMore = remember { mutableStateOf(false) }
        DropdownMenuItem(
            onClick = { showMore.value = true },
            text = {
                    Text(
                        text = "详情"
                    )
                    if (showMore.value) {
                        AlertDialogOK(
                            showMore,
                            title = { Text("详情") },
                            text = {
                                Column {
                                    Text("id ${musicDetail.readSafely()?.songs?.get(0)?.id}")
                                    Text(
                                        "singer: ${
                                            musicDetail.readSafely()?.songs?.get(0)?.ar?.get(
                                                0
                                            )?.name
                                        }"
                                    )
                                    Text("album: ${musicDetail.readSafely()?.songs?.get(0)?.al?.name}")
                                }
                            }
                        )
                    }

            }
        )
    }
}