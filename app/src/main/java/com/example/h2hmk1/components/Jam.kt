
package com.example.h2hmk1.components

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.h2hmk1.R

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Offset

@Composable
fun JamJoin() {
    Column {
        Text(
            "Get your jam host or friend already in the jam to show you the QR to join or put in the code that is on their screen",
            color = Color(0XFFA3A3A3),
            fontSize = 20.sp,
            modifier = Modifier
                .padding(30.dp)
        )

        CameraScreen()

        Spacer(modifier = Modifier.height(54.dp))

        JamJoinInput()
        
    }
}


@Composable
fun CameraScreen() {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var permissionGranted by remember { mutableStateOf(false) }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            permissionGranted = granted
        }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(Manifest.permission.CAMERA)
    }


    if (permissionGranted) {

        Box(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .padding(vertical = 70.dp)
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {

            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { ctx ->

                    val previewView = PreviewView(ctx)

                    val cameraProviderFuture =
                        ProcessCameraProvider.getInstance(ctx)

                    cameraProviderFuture.addListener({

                        val cameraProvider =
                            cameraProviderFuture.get()

                        val preview =
                            Preview.Builder().build()

                        preview.setSurfaceProvider(
                            previewView.surfaceProvider
                        )

                        val cameraSelector =
                            CameraSelector.DEFAULT_BACK_CAMERA

                        cameraProvider.unbindAll()

                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            cameraSelector,
                            preview
                        )

                    }, ContextCompat.getMainExecutor(ctx))

                    previewView
                }
            )

            // 🔹 RAMMEN OVEN PÅ KAMERAET
            CameraFrameOverlay()
        }
    }
}



    @Composable
    fun JamJoinInput() {
        var jamCodeInput by remember { mutableStateOf("") }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), verticalAlignment = Alignment.CenterVertically
        ) {

            TextField(
                value = jamCodeInput,
                onValueChange = { jamCodeInput = it },
                label = { Text("Jam code") },
                modifier = Modifier
                    .width(220.dp)
                    .shadow(
                        elevation = 3.dp,
                        shape = CircleShape
                    ),
                shape = CircleShape,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,

                    focusedIndicatorColor = h2hPink,
                    unfocusedIndicatorColor = h2hPink.copy(alpha = 0.5f),

                    cursorColor = h2hPink,

                    focusedLabelColor = h2hPink,
                    unfocusedLabelColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.width(20.dp))

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = h2hPink
                ),
                modifier = Modifier
                    .width(100.dp)
                    .shadow(
                        elevation = 3.dp,
                        shape = CircleShape,
                        ambientColor = Color.Black.copy(alpha = 0.3f),
                        spotColor = Color.Black
                    ), onClick = {
                    if (jamCodeInput.isNotBlank()) {

                        jamCodeInput = ""

                    }
                }) {
                Text("Join")
            }
        }
    }


    @Composable
    fun JamCreate() {
        Column {
            Text(
                "Get your friends to scan this to join your jam or have them type in the code in their app.",
                color = Color(0XFFA3A3A3),
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .padding(vertical = 7.dp)
            )

            Image(
                painter = painterResource(R.drawable.pinkqr),
                contentDescription = "QR-code",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp)
                    .padding(vertical = 7.dp)
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )


            Text(
                "2KPT4N55",
                color = h2hPink,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .padding(vertical = 7.dp)
                    .align(Alignment.CenterHorizontally)

            )

            Spacer(modifier = Modifier.height(30.dp))

            class JamContact(val nickname: String, val profileID: Int) {
            }

            val me: JamContact = JamContact("Rosalina (You)", 1)

            var jamMemberList: MutableList<JamContact> = mutableListOf(me)




            Text(
                "Current jammers:",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 60.dp)
                    .padding(vertical = 7.dp)
            )



            Column {
                Column {
                    jamMemberList.forEach { member ->

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 30.dp, vertical = 6.dp)
                                .shadow(
                                    elevation = 3.dp,
                                    shape = CircleShape,
                                    ambientColor = Color.Black.copy(alpha = 0.3f),
                                    spotColor = Color.Black
                                )
                                .background(
                                    color = Color.White,
                                    shape = CircleShape
                                )
                                .padding(vertical = 12.dp, horizontal = 20.dp)
                        ) {
                            Text(
                                text = member.nickname,
                                color = h2hPink,
                                fontSize = 18.sp
                            )
                        }

                    }
                }
            }
        }

    }


    @Composable
    fun CameraFrameOverlay() {

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp)
        ) {

            val strokeWidth = 8f
            val cornerLength = 80f

            val w = size.width
            val h = size.height

            // top-left
            drawLine(
                color = h2hPink,
                start = Offset(0f, 0f),
                end = Offset(cornerLength, 0f),
                strokeWidth = strokeWidth
            )
            drawLine(
                color = h2hPink,
                start = Offset(0f, 0f),
                end = Offset(0f, cornerLength),
                strokeWidth = strokeWidth
            )

            // top-right
            drawLine(
                color = h2hPink,
                start = Offset(w, 0f),
                end = Offset(w - cornerLength, 0f),
                strokeWidth = strokeWidth
            )
            drawLine(
                color = h2hPink,
                start = Offset(w, 0f),
                end = Offset(w, cornerLength),
                strokeWidth = strokeWidth
            )

            // bottom-left
            drawLine(
                color = h2hPink,
                start = Offset(0f, h),
                end = Offset(cornerLength, h),
                strokeWidth = strokeWidth
            )
            drawLine(
                color = h2hPink,
                start = Offset(0f, h),
                end = Offset(0f, h - cornerLength),
                strokeWidth = strokeWidth
            )

            // bottom-right
            drawLine(
                color = h2hPink,
                start = Offset(w, h),
                end = Offset(w - cornerLength, h),
                strokeWidth = strokeWidth
            )
            drawLine(
                color = h2hPink,
                start = Offset(w, h),
                end = Offset(w, h - cornerLength),
                strokeWidth = strokeWidth
            )
        }
    }


