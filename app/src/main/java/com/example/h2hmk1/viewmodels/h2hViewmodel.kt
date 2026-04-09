package com.example.h2hmk1.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf

enum class Screen {
    ROOMS,
    FRIENDS
}

// Data model for a Room
class Room(
    val name: String,
    val friends: MutableList<String> = mutableStateListOf()
)

class h2hViewmodel : ViewModel() {

    var currentScreen by mutableStateOf(Screen.ROOMS)

    fun goToFriends(room: Room) {
        selectedRoom = room
        currentScreen = Screen.FRIENDS
    }

    fun goBackToRooms() {
        currentScreen = Screen.ROOMS
    }

    // List of rooms
    val rooms = mutableStateListOf<Room>()

    // Currently selected room
    var selectedRoom: Room? = null

    // Create a new room
    fun addRoom(roomName: String) {
        if (roomName.isNotBlank()) {
            rooms.add(Room(roomName))
        }
    }

    // Select a room
    fun selectRoom(room: Room) {
        selectedRoom = room
    }

    // Add friend to selected room
    fun addFriendToRoom(friendName: String) {
        if (friendName.isNotBlank()) {
            selectedRoom?.friends?.add(friendName)
        }
    }
}
