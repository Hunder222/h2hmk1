package com.example.h2hmk1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.h2hmk1.ui.theme.H2hMk1Theme
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

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
        composable("fake-call") {

        }
        composable("tab-home") {

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

// Hewooo