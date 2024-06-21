package com.example.quizy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Welcome(viewModel: QuizViewModel,navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.screenBackground)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = "Welcome",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column (modifier = Modifier.fillMaxWidth()){

            Row {
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.padding(horizontal = 4.dp))
                Text(
                    text = "This Quiz contains 10 questions.",
                    modifier = Modifier.padding(end = 16.dp, bottom = 8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Row {
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.padding(horizontal = 4.dp))
                Text(
                    text = "For each question you will get 1 point.",
                    modifier = Modifier.padding( end = 16.dp, bottom = 8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Row {
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.padding(horizontal = 4.dp))
                Text(
                    text = "For wrong answer no points will be deducted.",
                    modifier = Modifier.padding(end = 16.dp, bottom = 8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Row {
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.padding(horizontal = 4.dp))
                Text(
                    text = "On clicking any option you will be directed to next question.",
                    modifier = Modifier.padding(end = 16.dp, bottom = 8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Row {
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.padding(horizontal = 4.dp))
                Text(
                    text = "You have total 10 minutes to complete the quiz.",
                    modifier = Modifier.padding(end = 16.dp, bottom = 8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = {
                navController.navigate(Screen.HomeScreen.route)
                viewModel.startQuiz()
                      },
            colors = ButtonDefaults.buttonColors(
                     containerColor = Color.DarkGray,
                     contentColor = Color.White
             )
        ) {
            Text(text = "Start")
        }
    }
}