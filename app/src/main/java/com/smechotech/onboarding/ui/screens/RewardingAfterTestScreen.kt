package com.smechotech.onboarding.ui.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.smechotech.onboarding.ui.Navigation

@Composable
fun RewardingAfterTestScreen(
    navController: NavHostController
) {
    Button(onClick = { navController.navigate(Navigation.MainScreen.name) }) {
        Text(text = "OK")
    }
}