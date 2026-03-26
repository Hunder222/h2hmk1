
package com.example.h2hmk1.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun JamHome() {
    Text(
        "Dette er jam",
        fontSize = 40.sp,
        modifier = Modifier
            .padding(30.dp)
    )
}