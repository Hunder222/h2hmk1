package com.example.h2hmk1

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.h2hmk1.components.Navigator
import com.example.h2hmk1.viewmodels.h2hViewmodel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val viewModel: h2hViewmodel = viewModel()
            Column {

                Navigator(viewModel)

            }
        }
    }
}