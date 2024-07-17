package com.example.rainmusic.util.color

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.Coil.imageLoader
import coil.request.ImageRequest
import com.example.rainmusic.data.model.ImageColor
import com.example.rainmusic.di.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//@Composable
//fun getImageDominantColor (url: String,imageColorRepository:ImageColorRepository) : Color {
//    val context = LocalContext.current
//
//    return remember(url) {
//        withContext(Dispatchers.IO) {
//            var color: Int
//            imageColorRepository.getImageColor(url).collect{
//                if(it!=null){
//                    color= it.mainColor
//                }else{
//                    val bitmap = CoilImageLoader.loadBitmap(context, url)
//                    val palette = Palette.from(bitmap).generate()
//                    val dominantSwatch = palette.dominantSwatch
//
//                    color = dominantSwatch?.rgb ?: 0xff000000.toInt()
//                    imageColorRepository.insertImageColor(ImageColor(url, color))
//                }
//            }
//
//
//
//            Color(color)
//        }
//    }
//}

suspend fun getImageDominantColor(url: String, context: Context): Int {

    val imageColorDao1 = AppDatabase.getDatabase(context).ImageColorDao1()
    return withContext(Dispatchers.IO) {
        val cachedColor=imageColorDao1.getImageColor(url)?.mainColor
        if (cachedColor != null) {
            return@withContext cachedColor
        }

        val drawable = CoilImageLoader.loadDrawable(context, url)
        if(drawable is BitmapDrawable){
            var bitmap = drawable.bitmap
            if(bitmap.config == Bitmap.Config.HARDWARE){
                bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
            }
            val dominantSwatch=Palette.from(bitmap).generate().dominantSwatch
            val dominantColor = dominantSwatch?.rgb ?: 0xff000000.toInt()
            imageColorDao1.insert(ImageColor(url, dominantColor))
            dominantColor
        }else{
            Log.d("getImageDominantColor","drawable not is BitmapDrawable")
            0xff000000.toInt()
        }
    }
}
class CoilImageLoader {
    companion object {
        suspend fun loadDrawable(context: Context, url: String): Drawable? {
            val request = ImageRequest.Builder(context)
                .data(url)
                .build()
            return imageLoader(context).execute(request).drawable
        }
    }
}