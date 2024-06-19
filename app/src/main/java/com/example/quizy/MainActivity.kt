package com.example.quizy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.quizy.ui.theme.QuizyTheme
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
     val viewModel: QuizViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            QuizyTheme {
                HomeScreen(viewModel)
//                CircularProgressWithTimer(viewModel = viewModel)
            }
        }
    }
}
