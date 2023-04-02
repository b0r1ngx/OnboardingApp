package com.smechotech.onboarding.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.smechotech.onboarding.R
import com.smechotech.onboarding.UserViewModel
import com.smechotech.onboarding.ui.Navigation

@Composable
fun RewardingAfterTestScreen(
    navController: NavHostController, viewModel: UserViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.padding(top = 40.dp), text = "За этот тест вы набрали:",
            style = TextStyle(fontSize = 24.sp), color = Color.White
        )


        Column(modifier = Modifier) {
            ResultField(R.drawable.start_white, ": " + viewModel.getCurrentExp())
            ResultField(R.drawable.gem, ": " + viewModel.getCurrentDiamonds())
        }


        Button(onClick = {
            navController.navigate(Navigation.MainScreen.name)
        }) {
            Text(text = "Продолжить", color = Color.Black)
        }

    }


}

@Composable
fun ResultField(image: Int, text: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Image(imageResource = image, modifier = Modifier.size(30.dp))

        Text(text = text, style = TextStyle(fontSize = 30.sp), color = Color.White)

    }
}

@Preview(
    showBackground = true,
    device = PIXEL_4
)
@Composable
fun RewardReview() {
    RewardingAfterTestScreen(rememberNavController(), UserViewModel())
}