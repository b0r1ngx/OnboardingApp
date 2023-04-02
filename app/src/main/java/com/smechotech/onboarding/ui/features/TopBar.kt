package com.smechotech.onboarding.ui.features

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.smechotech.onboarding.UserViewModel

@Composable
fun TopBar(
    viewModel: UserViewModel
) {
    Row {
//        Image(painter = , contentDescription = )
        Text(text = viewModel.userExperience.toString())
        Text(text = viewModel.userDiamonds.toString())
    }
}