package com.example.quizy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun FinalScreen(viewModel: QuizViewModel, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.screenBackground)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(330.dp))
        Text(
            text = "Congratulations!",
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = " You have completed the quiz",
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "You scored:${viewModel.score.value}",
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = {
                viewModel.currentQuestion.value = 0
                viewModel.score.value = 0
                navController.navigate(Screen.HomeScreen.route)
                viewModel.startQuiz()
                      },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White
            )
        ) {
            Text(text = "Restart")
        }

    }
}