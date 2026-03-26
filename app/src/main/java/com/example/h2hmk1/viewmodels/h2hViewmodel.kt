package com.example.h2hmk1.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class h2hViewmodel: ViewModel() {
    var name: String by mutableStateOf("Mathias")


}