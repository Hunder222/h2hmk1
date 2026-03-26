package com.example.h2hmk1.components

import android.R.attr.name
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
            .padding(top = 50.dp)
    )

    NavHost(navController = navController, startDestination = "premium-home") {
        composable("premium-home") {
            PremiumHome (
                onBtn1Click = {navController.navigate("fake-call")},
                onBtn2Click = {navController.navigate("tab-home")},
                onBtn3Click = {navController.navigate("circles")}
            )
        }
        composable("tab-home"){
            TakeABreathHome()
        }
        composable("fake-call"){
            FakeCall()
        }
        composable("circles"){
            circlesHome()
        }
    }
}