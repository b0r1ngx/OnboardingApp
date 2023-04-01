package com.smechotech.onboarding.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Title(titleText: String) = Text(
    text = titleText,
    style = TextStyle(fontSize = 25.sp)
)

@Composable
fun Description(descriptionText: String) = Text(
    text = descriptionText,
    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
)