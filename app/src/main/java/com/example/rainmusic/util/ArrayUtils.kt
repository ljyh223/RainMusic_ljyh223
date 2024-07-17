package com.example.rainmusic.util

fun <T> selectRandomElements(array: List<T>, count: Int): List<T> {
    require(count <= array.size) { "Count cannot be greater than the size of the array" }

    return array.indices.shuffled().take(count).map { array[it] }
}
fun toArgb(red: Int, green: Int, blue: Int): Int {
    return (255 shl 24) or // Alpha channel set to 255 (fully opaque)
            (red shl 16) or // Red component
            (green shl 8) or // Green component
            blue // Blue component
}
