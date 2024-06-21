package com.example.quizy

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuizViewModel(private val applicationContext: Application): ViewModel() {
    private val _timeLeftInSec = mutableStateOf(0)
    private val _timeLeftInMin = mutableStateOf(10)
    val timeLeftInMin: State<Int> = _timeLeftInMin
    val timeLeftInSec: State<Int> = _timeLeftInSec

    private val _questionList = mutableStateOf(listOf<Question>())
    val questionList: State<List<Question>> = _questionList

    val showAnswer = mutableStateOf(false)
    val score = mutableStateOf(0)
    val currentQuestion = mutableIntStateOf(0)

    init {
        viewModelScope.launch {
            val jsonString = readJsonFromAssets(applicationContext,"Question.json")
            jsonString?.let {
                val Quiz = parseQuizJson(it)
                _questionList.value = Quiz?.quiz!!
            }
        }
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (_timeLeftInMin.value > 0 || _timeLeftInSec.value > 0) {
                if(timeLeftInSec.value < 0){
                    _timeLeftInMin.value -= 1
                    _timeLeftInSec.value = 59
                }
                delay(1000L)
                _timeLeftInSec.value -= 1
            }
        }
    }

    fun startQuiz() {
        _timeLeftInMin.value = 10
        _timeLeftInSec.value = 0
        startTimer()
    }
}