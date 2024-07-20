package com.example.rainmusic.ui.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun AlertDialogOK(
    show: MutableState<Boolean>,
    title: @Composable (() -> Unit)? = null,
    text: @Composable (() -> Unit)? = null,
) {
    AlertDialog(
        onDismissRequest = {
            show.value = false
        },
        title = title,
        text = text,
        confirmButton = {
            TextButton(onClick = { show.value = false }) {
                Text(text = "确定")
            }
        }
    )
}
