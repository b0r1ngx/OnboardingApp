package com.smechotech.onboarding.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.R
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.smechotech.onboarding.ui.ImageButton
import com.smechotech.onboarding.ui.ImageDesign
import com.smechotech.onboarding.ui.Navigation
import com.smechotech.onboarding.userAuthorized

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
    var login by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        ImageButton(
            imageResource = R.id., // TODO: temp avatar's id
            onClick = { /* TODO: select avatar */ }
        )

        TextField(
            value = login,
            onValueChange = { newText: TextFieldValue -> login = newText },
            modifyer = Modifier.padding(vertical = 5.dp),
            textStyle = TextStyle(fontSize = 25.sp)
        )

        TextField(
            value = password,
            onValueChange = { newText -> password = newText },
            modifyer = Modifier.padding(vertical = 5.dp),
            textStyle = TextStyle(fontSize = 25.sp)
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