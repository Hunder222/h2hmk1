package com.example.h2hmk1.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TakeABreathHome(
){
    Column(
        modifier = Modifier
            .padding(top = 50.dp, start = 20.dp, end = 20.dp)
    ) {
        Text("Breath",
            fontSize = 50.sp
        )

        Text("Well-Known Breathing Exercises",
            fontSize = 40.sp
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                BreathingExercise("Box Breathing", "Breathe in, hold, breathe out, and hold again, each for about 4 seconds. A steady rhythm that helps calm your mind and body.")
            }
            item {
                BreathingExercise("4-7-8 breathing", "Breathe in for 4 seconds, hold for 7, then breathe out slowly for 8. A calming rhythm that helps your body relax and unwind.")
            }
            item {
                BreathingExercise("Belly breathing", "Breathe deeply using your diaphragm. Let your belly rise as you inhale, then fall as you exhale. A slow, steady rhythm that helps your body relax and reset.")
            }
            item {
                BreathingExercise("Pursed-Lip breathing", "Breathe in through your nose for 2 seconds\n" +
                        "Calmly without lifting your shoulders.\n" +
                        "\u2028Exhale slowly through gently pursed lips for 4 seconds\u2028Your lips should be shaped as if you’re softly blowing out a candle without extinguishing it\n" +
                        "\u2028Repeat for 1-2 minutes \n" +
                        "Keep the pace steady and relaxed")
            }
            item {
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}


@Composable
fun BreathingExercise(btnText: String, pText: String){
    Column(
        modifier = Modifier
            .padding(top = 40.dp)
    ) {
        Button(
            onClick = {}
        ) {
            Text(btnText, fontSize = 25.sp)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(pText,
            fontSize = 22.sp
        )
    }
}