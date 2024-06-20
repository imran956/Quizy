package com.example.quizy

data class Question(
    val question: String,
    val options: List<String>,
    val answer: String
)

data class Quiz(
    val quiz: List<Question>
)
