package com.example.rainmusic.ui.screen.search


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rainmusic.ui.component.PopBackIcon
import com.example.rainmusic.ui.component.RainTopBar

@ExperimentalMaterial3Api
@Composable
fun SearchScreen() {
    Scaffold(
        topBar = {
            RainTopBar(
                navigationIcon = {
                    PopBackIcon()
                },
                title = {
                    Text(text = "搜索")
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            var query by remember {
                mutableStateOf("")
            }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                value = query,
                onValueChange = {
                    query = it
                },
                placeholder = {
                    Text(text = "尝试搜索一下吧 (●'◡'●)")
                },
                trailingIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Rounded.Search, null)
                    }
                }
            )
        }
    }
}

@Composable
private fun Body() {
    Column {
        var query by remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            value = query,
            onValueChange = {
                query = it
            },
            placeholder = {
                 Text(text = "尝试搜索一下吧 (●'◡'●)")
            },
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Rounded.Search, null)
                }
            }
        )
    }
}