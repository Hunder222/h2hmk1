package com.example.h2hmk1.components

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CirclesHome(
    createCircleBtn: () -> Unit,
    createJamBtn: () -> Unit,
    joinJamBtn: () -> Unit

) {
    Column(
        modifier = Modifier
            .padding(30.dp)
    ) {
        Text(
            "Circles",
            fontSize = 40.sp,
            modifier = Modifier
                .padding(bottom = 10.dp)
        )

        Text(
            "Your friends and loved ones, specify which people you need and when",
            color = Color(0XFFA3A3A3),
            fontSize = 20.sp
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        ) {
            Text(
                "My circles",
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(top = 20.dp)

            )

            Button(
                onClick = createCircleBtn,
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
                    )
            ) {
                Text(
                    "New"
                )


            }
        }

        Column(
            modifier = Modifier
                .padding(top = 100.dp)
        ) {
            Text(
                "Jams",
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(bottom = 10.dp)
            )

            Text(
                "Your temporary circle",
                color = Color(0XFFA3A3A3),
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(bottom = 20.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = createJamBtn,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFE77B7)
                    ),
                    modifier = Modifier
                        .width(150.dp)
                        .shadow(
                            elevation = 3.dp,
                            shape = CircleShape,
                            ambientColor = Color.Black.copy(alpha = 0.3f),
                            spotColor = Color.Black
                        )
                ) {
                    Text(
                        "Create a jam"
                    )
                }

                Button(
                    onClick = joinJamBtn,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFE77B7)
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
                        "Join a jam"
                    )
                }
            }
        }
    }
}