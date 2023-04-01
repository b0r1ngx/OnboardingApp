package com.smechotech.onboarding.data

data class Question(
    val quest: String,
    val isFewAnswers: Boolean = false,
    val answers: List<Answer>
)

data class Answer(
    val text: String,
    val isCorrect: Boolean
)