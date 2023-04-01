package com.smechotech.onboarding.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.smechotech.onboarding.ui.Navigation
import com.smechotech.onboarding.userAuthorized

@Composable
fun LoginScreen(navController: NavHostController) {
    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        TextField(
            value = login.value,
            textStyle = TextStyle(fontSize = 25.sp),
            onValueChange = { newText -> login.value = newText }
        )

        TextField(
            value = password.value,
            textStyle = TextStyle(fontSize = 25.sp),
            onValueChange = { newText -> password.value = newText }
        )
        
        Button(onClick = {
            userAuthorized()
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