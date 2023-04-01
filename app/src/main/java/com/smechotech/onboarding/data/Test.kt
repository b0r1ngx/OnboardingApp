package com.smechotech.onboarding.data

data class Test(
    val title: String,
    val image: Int? = null,
    val theory: String,
    val questions: List<Question>
)

