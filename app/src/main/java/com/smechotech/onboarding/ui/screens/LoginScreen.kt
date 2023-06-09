package com.smechotech.onboarding.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.smechotech.onboarding.R
import com.smechotech.onboarding.ui.ImageButton
import com.smechotech.onboarding.ui.Navigation
import com.smechotech.onboarding.ui.features.userPassAuthorization

/**
 * Login Screen
 *
 * A screen that provides standard login password entry
 * (registration is implied on the main portal)
 * and the choice of an avatar.
 *
 */
@Composable
fun LoginScreen(navController: NavHostController) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        ImageButton(
            imageResource = R.drawable.avatar_great,
            onClick = { /* TODO: select avatar */ },
            modifier = Modifier.clip(CircleShape)
        )

        Text(text = "Имя")
        TextField(
            value = login,
            onValueChange = { newText -> login = newText },
            modifier = Modifier.padding(vertical = 5.dp),
            textStyle = TextStyle(fontSize = 25.sp)
        )

        Text(text = "Должность")
        TextField(
            value = password,
            onValueChange = { newText -> password = newText },
            modifier = Modifier.padding(vertical = 5.dp),
            textStyle = TextStyle(fontSize = 25.sp)
        )
        
        Button(onClick = {
            userPassAuthorization()
            navController.navigate(Navigation.TestScreen.name)
        }) {
            Text(text = "Log in")
        }
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_4
)
@Composable
fun LoginPreview() {
    LoginScreen(navController = rememberNavController())
}