package com.example.h2hmk1.components

import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.h2hmk1.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.h2hmk1.viewmodels.h2hViewmodel
import androidx.navigation.NavController



@Composable
fun FakeCall(viewModel: h2hViewmodel, navController: NavController) {
    circlesList(viewModel, navController)
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

@Composable
fun circlesList(viewModel: h2hViewmodel, navController: NavController) {
    var newRoomName by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(16.dp)) {

        Text("Rooms", fontSize = 24.sp)

        Spacer(Modifier.height(16.dp))

        LazyColumn(Modifier.weight(1f)) {
            items(viewModel.rooms) { room ->
                Button(
                    onClick = {
                        viewModel.selectRoom(room)
                        navController.navigate("friends")
                    },
                    modifier = Modifier.fillMaxWidth().padding(4.dp)
                ) {
                    Text(room.name)
                }
            }
        }

        OutlinedTextField(
            value = newRoomName,
            onValueChange = { newRoomName = it },
            label = { Text("New Room") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {
                viewModel.addRoom(newRoomName)
                newRoomName = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Room")
        }
    }
}