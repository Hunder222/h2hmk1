
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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun JamJoin() {
    Column {
        Text(
            "Get your friends to scan this to join your jam or have them type in the code in their app",
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
                    modifier = Modifier
                        .fillMaxWidth(),
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


            }


        }
    }



@Composable
fun JamJoinInput() {
    var jamCodeInput by remember { mutableStateOf("") }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {

        TextField(
            value = jamCodeInput,
            label = { Text("Jam code") },
            modifier = Modifier,
            onValueChange = { jamCodeInput = it }
        )

        Spacer(modifier = Modifier.width(20.dp))

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFFFFF),
                contentColor = Color(0xFFFE77B7)
            ),
            modifier = Modifier
                .padding(top = 15.dp)
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