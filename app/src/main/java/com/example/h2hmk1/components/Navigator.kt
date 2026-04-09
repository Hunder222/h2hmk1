package com.example.h2hmk1.components

import android.R.attr.name
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.h2hmk1.components.FakeCall
import com.example.h2hmk1.components.PremiumHome
import com.example.h2hmk1.components.TakeABreathHome

@Composable
fun Navigator(name : String) {
    val navController = rememberNavController()

    Text(
        text = "Hej ${name}!",
        fontSize = 36.sp,
        modifier = Modifier
            .padding(top = 50.dp, start = 30.dp)
    )

    NavHost(navController = navController, startDestination = "premium-home") {
        composable("premium-home") {
            PremiumHome (
                onBtn1Click = {navController.navigate("fake-call")},
                onBtn2Click = {navController.navigate("tab-home")},
                onBtn3Click = {navController.navigate("circles-home")}
            )
        }
        composable("tab-home"){
            TakeABreathHome()
        }
        composable("fake-call"){
            FakeCall()
        }
        composable("circles-home"){
            CirclesHome(
                createCircleBtn = {print("ellooo")},
                createJamBtn = {navController.navigate("jam-create")},
                joinJamBtn = {navController.navigate("jam-join")}
            )
        }
        composable("jam-create"){
            JamCreate()
        }

        composable("jam-join"){
            JamJoin()
        }
    }
}