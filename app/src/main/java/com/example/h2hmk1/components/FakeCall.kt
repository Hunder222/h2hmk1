package com.example.h2hmk1.components

import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.h2hmk1.R


@Composable
fun FakeCall(
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(30.dp)
    ) {
        Text(
            "Fake call",
            fontSize = 40.sp,
            modifier = Modifier
                .padding(bottom = 10.dp)
        )

        Text(
            "Even when your app isn’t open, simply press the heart down for 3 seconds twice to have a fake number calling you.",
            color = Color(0XFFA3A3A3),
            fontSize = 20.sp
        )

        GifImage()

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        ) {
            Text(
                "Who's calling?",
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(top = 20.dp)

            )

            Text(
                "There will not be an actual person calling you, but your phone will look as though someone is calling you to let you easily exit an unwanted conversation." +
                        "Right now in the start period it will say 'Dad', but eventually you will be able to change this into what you want to yourself.",
                color = Color.Black,
                fontSize = 18.sp
            )




            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(
                    onClick = onBackClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = h2hPink
                    ),
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .width(150.dp)
                        .shadow(
                            elevation = 3.dp,
                            shape = CircleShape,
                            ambientColor = Color.Black.copy(alpha = 0.3f),
                            spotColor = Color.Black
                        )
                ) {
                    Text(
                        "I got it"
                    )
                }
            }
        }
    }
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
            .data(R.drawable.h2hfakecall)
            .build(),
        imageLoader = imageLoader,
        contentDescription = "gif"
    )
}