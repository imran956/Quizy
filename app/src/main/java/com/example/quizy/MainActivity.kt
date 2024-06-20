package com.example.quizy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.quizy.ui.theme.QuizyTheme
import androidx.activity.viewModels
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        val factory = QuizViewModelFactory(application)
        val viewModel: QuizViewModel by viewModels { factory }
        setContent {
            QuizyTheme {

                HomeScreen(viewModel)
//                CircularProgressWithTimer(viewModel = viewModel)
            }
        }
    }
}