package com.example.quizy

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuizViewModel: ViewModel() {
    private val _timeLeftInSec = mutableStateOf(0)
    private val _timeLeftInMin = mutableStateOf(10)
    val timeLeftInMin: State<Int> = _timeLeftInMin
    val timeLeftInSec: State<Int> = _timeLeftInSec

    init {
        startTimer()
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
}