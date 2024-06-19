package com.example.quizy

data class Question(
    val question: String,
    val options: List<String>,
    val correctAnswer: String
)

data class Quiz(
    val quiz: List<Question>
)
