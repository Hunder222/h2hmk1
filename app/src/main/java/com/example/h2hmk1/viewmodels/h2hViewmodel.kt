package com.example.h2hmk1.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class h2hViewmodel: ViewModel() {
    var name: String by mutableStateOf("Mathias")

    // List of circles
    val circles = mutableStateListOf<Circle>()

    // Currently selected circle
    var selectedCircle: Circle? = null

    // Create a new room
    fun createCircle(circleName: String) {
        if (circleName.isNotBlank()) {
            circles.add(Circle(circleName))
        }
    }
}


// Data model for a Circle
class Circle(
    name: String,
    val isListExtended: MutableState<Boolean> = mutableStateOf(false),
    val friends: MutableList<String> = mutableStateListOf()
) {
    var name: String by mutableStateOf(name)
}