package com.example.h2hmk1.components

import android.R.attr.name
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.h2hmk1.components.FakeCall
import com.example.h2hmk1.components.PremiumHome
import com.example.h2hmk1.components.TakeABreathHome

@Composable
fun Navigator() {
    val navController = rememberNavController()

    TopNav(navController)

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


@Composable
fun TopNav(
    navController: NavHostController
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    }
                }
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBackIosNew,
                contentDescription = "Back",
                tint = Color(0xFFd086b3),
                modifier = Modifier
                    .padding(start = 15.dp)
                    .size(35.dp, 35.dp)
            )
            Text(
                text = "Back",
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFd086b3)
            )
        }

    }
    HorizontalDivider()
}