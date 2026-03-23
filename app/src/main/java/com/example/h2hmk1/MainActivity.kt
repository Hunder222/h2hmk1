package com.example.h2hmk1

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.h2hmk1.ui.theme.H2hMk1Theme
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Navigator()
        }
    }
}

@Composable
fun Navigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "premium-home") {
        composable("premium-home") {
            PremiumHome(
                onBtn1Click = {navController.navigate("fake-call")},
                onBtn2Click = {navController.navigate("tab-home")}
            )
        }
        composable("tab-home"){
            TakeABreathHome()
        }
        composable("fake-call"){
            FakeCall()
        }
    }
}



@Composable
fun PremiumHome(
    onBtn1Click:  () -> Unit,
    onBtn2Click:  () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .padding(20.dp)
    ) {
        Text("Premium Features")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),

        ) {
            Text("Fake call")
            Text("Your safety, your terms. Trigger a realistic call from a chosen fake contact to create a quick, natural exit from situations that don’t feel right.")
            Button(onClick = onBtn1Click,
                modifier = Modifier
                    .padding(10.dp)) {
                Text("Fake call customization")
            }

            Text("Take a breath")
            Text("Guided breathing helps calm your body and mind by slowing your breath and regaining control of your nervous system. Even just a few minutes can make a noticable shift in how you feel.")
            Button(onClick = onBtn2Click,
                modifier = Modifier
                    .padding(10.dp)) {
                Text("Take a breath")
            }
        }
    }
}

// Hellooo
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

