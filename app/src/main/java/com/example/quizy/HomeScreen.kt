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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

        Spacer(modifier = Modifier.height(48.dp))
        MiddleSection()

        Spacer(modifier = Modifier.height(32.dp))
        BottomSection()
    }
}

@Composable
fun BottomSection() {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Previous")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Next", modifier = Modifier.padding(horizontal = 20.dp))
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MiddleSection() {
    var question by remember { mutableStateOf("Question?") }
    var option1 by remember { mutableStateOf("Option1") }
    var option2 by remember { mutableStateOf("Option2") }
    var option3 by remember { mutableStateOf("Option3") }
    var option4 by remember { mutableStateOf("Option4") }
    Text(
        text = question,
        modifier = Modifier.padding(8.dp),
        style = TextStyle(
            fontSize = 20.sp, fontWeight = FontWeight.SemiBold
        ),

        )
    Spacer(modifier = Modifier.height(24.dp))
    Row {
        Card(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .weight(1f)
                .padding(start = 32.dp),
            colors = CardDefaults.cardColors(
                contentColor = Color.Gray
            ),
            elevation = CardDefaults.cardElevation(
                focusedElevation = 5.dp, pressedElevation = 10.dp
            )

        ) {
            Text(
                text = option1,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
        Spacer(modifier = Modifier.width(32.dp))

        Card(
            onClick = { /*TODO*/ }, modifier = Modifier
                .weight(1f)
                .padding(end = 32.dp)
        ) {
            Text(
                text = option2,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))
    Row {
        Card(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .weight(1f)
                .padding(start = 32.dp),

            ) {
            Text(
                text = option1,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
        Spacer(modifier = Modifier.width(32.dp))

        Card(
            onClick = { /*TODO*/ }, modifier = Modifier
                .weight(1f)
                .padding(end = 32.dp)
        ) {
            Text(
                text = option2,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
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
            color = Color.Blue,
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
                text = timeLeftInSec.toString(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}