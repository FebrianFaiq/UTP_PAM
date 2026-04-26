package com.example.utppam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.example.utppam.ui.theme.UTPPAMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            UTPPAMTheme {
                val navController = rememberNavController()
                val todoList = remember { mutableStateListOf<TodoItem>() }
                AppNavHost(
                    navController = navController,
                    todoList = todoList
                )
            }
        }
    }
}