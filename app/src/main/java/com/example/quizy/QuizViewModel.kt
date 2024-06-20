package com.example.quizy

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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

    init {
        startTimer()
        viewModelScope.launch {
            val jsonString = readJsonFromAssets(applicationContext,"Question.json")
            jsonString?.let {
                val Quiz = parseQuizJson(it)
                _questionList.value = Quiz?.quiz!!
            }
        }
    }

    /*private*/ fun startTimer() {
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
}