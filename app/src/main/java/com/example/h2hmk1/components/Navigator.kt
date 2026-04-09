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

    TopNav(navController)

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