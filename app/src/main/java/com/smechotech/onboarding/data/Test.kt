package com.smechotech.onboarding.data

import android.media.Image

data class Test(
    val title: String,
    val image: Int? = null,
    val theory: String,
    val questions: List<Question>
)

