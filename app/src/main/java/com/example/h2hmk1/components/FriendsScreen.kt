package com.example.h2hmk1.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.h2hmk1.viewmodels.h2hViewmodel
import androidx.navigation.NavController

@Composable
fun FriendsScreen(viewModel: h2hViewmodel, navController: NavController) {
    var newFriendName by remember { mutableStateOf("") }
    val room = viewModel.selectedRoom

    Column(
        Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text("Room: ${room?.name ?: "No room"}", fontSize = 24.sp)
        Spacer(Modifier.height(16.dp))

        LazyColumn(Modifier.weight(1f)) {
            items(room?.friends ?: emptyList()) { friend ->
                Text(friend, fontSize = 20.sp, modifier = Modifier.padding(4.dp))
            }
        }

        OutlinedTextField(
            value = newFriendName,
            onValueChange = { newFriendName = it },
            label = { Text("Add Friend") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {
                viewModel.addFriendToRoom(newFriendName)
                newFriendName = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Friend")
        }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Rooms")
        }
    }
}

