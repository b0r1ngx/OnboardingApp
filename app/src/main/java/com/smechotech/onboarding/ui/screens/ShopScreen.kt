package com.smechotech.onboarding.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.smechotech.onboarding.UserViewModel

@Composable
fun ShopScreen(
    navController: NavHostController,
    viewModel: UserViewModel
) {
    Text(text = "ShopScreen")
}