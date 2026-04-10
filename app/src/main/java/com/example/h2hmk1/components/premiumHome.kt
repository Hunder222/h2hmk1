package com.example.h2hmk1.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun PremiumHome(
    onBtn1Click:  () -> Unit,
    onBtn2Click:  () -> Unit,
    onBtn3Click:  () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .padding(20.dp)
    ) {
        Text(
            text = "Premium Features",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),

            ) {
            Text(
                "Fake call",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Your safety, your terms. Trigger a realistic call from a chosen fake contact to create a quick, natural exit from situations that don’t feel right.",
                fontSize = 18.sp
            )
            Button(onClick = onBtn1Click,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFE77B7),
                    contentColor = Color(0xFFFFFFFF)
                ),
                modifier = Modifier
                    .padding(10.dp)
                    .shadow(
                    elevation = 3.dp,
                    shape = CircleShape,
                    ambientColor = Color.Black.copy(alpha = 0.3f),
                    spotColor = Color.Black
                )) {
                Text("Fake call customization")
            }

            Text("Take a breath")
            Text("Guided breathing helps calm your body and mind by slowing your breath and regaining control of your nervous system. Even just a few minutes can make a noticable shift in how you feel.")
            Button(onClick = onBtn2Click,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFE77B7),
                    contentColor = Color(0xFFFFFFFF)
                ),
                modifier = Modifier
                    .padding(10.dp)
                    .shadow(
                        elevation = 3.dp,
                        shape = CircleShape,
                        ambientColor = Color.Black.copy(alpha = 0.3f),
                        spotColor = Color.Black
                    )) {
                Text("Take a breath")
            }

            Text("Circles")
            Text("You can create circles for your friends and family to easily toggle your contact group that can send messages when you’re in need.")
            Button(onClick = onBtn3Click,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFE77B7),
                    contentColor = Color(0xFFFFFFFF)
                ),
                modifier = Modifier
                    .padding(10.dp)
                    .shadow(
                        elevation = 3.dp,
                        shape = CircleShape,
                        ambientColor = Color.Black.copy(alpha = 0.3f),
                        spotColor = Color.Black
                    )
            ) {
                Text("Go to circles")
            }
        }
    }
}