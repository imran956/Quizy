@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.quizy

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(viewModel: QuizViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.screenBackground)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Header()

        Spacer(modifier = Modifier.height(32.dp))
        CircularProgressWithTimer(viewModel = viewModel)

        Spacer(modifier = Modifier.height(32.dp))
        MiddleSection(viewModel = viewModel)

    }
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MiddleSection(viewModel: QuizViewModel) {

    val questions by remember {
        viewModel.questionList
    }

    var currentQuestion by remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier.wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .padding(start = 16.dp)
        ) {
            Text(
                text = questions[currentQuestion].question,
                modifier = Modifier.padding(8.dp),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W400
                )
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Card(
                onClick = { viewModel.showAnswer.value = true },
                modifier = Modifier.padding(start = 24.dp, end = 8.dp),

                ) {
                Text(
                    text = questions[currentQuestion].options.get(0),
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Card(
                onClick = { viewModel.showAnswer.value = true },
                modifier = Modifier
                    .padding(start = 24.dp, end = 8.dp)
            ) {
                Text(
                    text = questions[currentQuestion].options.get(1),
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Card(
                onClick = { viewModel.showAnswer.value = true },
                modifier = Modifier
                    .padding(start = 24.dp, end = 8.dp)

            ) {
                Text(
                    text = questions[currentQuestion].options.get(2),
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Card(
                onClick = { viewModel.showAnswer.value = true },
                modifier = Modifier
                    .padding(start = 24.dp, end = 8.dp)
            ) {
                Text(
                    text = questions[currentQuestion].options.get(3),
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {
                    if (currentQuestion > 0) currentQuestion -= 1
                    else currentQuestion = (questions.size - 1)
                    viewModel.showAnswer.value = false
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Previous")
            }
            Button(
                onClick = {
                    if (currentQuestion < questions.size - 1) currentQuestion += 1
                    else currentQuestion = 0
                    viewModel.showAnswer.value = false
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Next", modifier = Modifier.padding(horizontal = 20.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        if (viewModel.showAnswer.value) {
            UpdateAnswer(answer = questions[currentQuestion].answer)
        }
    }
}

@Composable
fun UpdateAnswer(answer:String) {
    Text(text = "Correct answer: $answer", fontWeight = FontWeight.W500, modifier = Modifier.padding(8.dp))
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(
                color = colorResource(id = R.color.background),
                shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp)
            ), contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Quiz App",
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(id = R.color.black),
            fontSize = 28.sp
        )
    }
}


@Composable
fun CircularProgressWithTimer(viewModel: QuizViewModel, totalTime: Int = 600) {
    val timeLeftInMin by viewModel.timeLeftInMin
    val timeLeftInSec by viewModel.timeLeftInSec
    val timeLeft = 60 * timeLeftInMin + timeLeftInSec
    val progress by animateFloatAsState(targetValue = timeLeft.toFloat() / totalTime)

    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(100.dp)) {
        CircularProgressIndicator(
            progress = progress,
            color = Color.DarkGray,
            strokeWidth = 10.dp,
            modifier = Modifier.size(100.dp)
        )
        Row {
            Text(
                text = timeLeftInMin.toString(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = ":",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 2.dp)
            )
            Text(
                text = if (timeLeftInSec < 10) "0$timeLeftInSec" else timeLeftInSec.toString(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}