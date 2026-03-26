package com.example.h2hmk1.components

import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.h2hmk1.R


@Composable
fun FakeCall() {
    Column(modifier = Modifier.fillMaxSize()) {
        Title()
        Info()
        GifImage()

    }
}

@Composable
fun Title() {
    Text(
        text = "Fake call"
    )
}

@Composable
fun Info() {
    Text(
        text = "Even when your app isn’t open, simply press the heart down for 3 seconds twice to have a fake number calling you."
    )
}



@Composable
fun GifImage() {

    val context = LocalContext.current

    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(R.drawable.giphy)
            .build(),
        imageLoader = imageLoader,
        contentDescription = "gif"
    )
}