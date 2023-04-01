package com.smechotech.onboarding.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.smechotech.onboarding.R
import com.smechotech.onboarding.data.Test
import com.smechotech.onboarding.data.testTest
import com.smechotech.onboarding.ui.Description
import com.smechotech.onboarding.ui.Navigation
import com.smechotech.onboarding.ui.Title

@Composable
fun TestScreen(navController: NavHostController, test: Test) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(test.title)
        Image(test.image)
        Description(test.theory)
        ButtonToTest(navController)
    }
}

@Composable
fun ButtonToTest(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { navController.navigate(Navigation.TestQuestionScreen.name) },
        modifier = modifier
    ) {
        Text(text = stringResource(id = R.string.Start))
    }
}

@Composable
fun Image(imageResource: Int?) {
    if (imageResource != null)
        Image(
            painter = painterResource(imageResource),
            contentDescription = null
        )
}

@Preview(
    showBackground = true,
    device = PIXEL_4
)
@Composable
fun TestPreview() {
    TestScreen(test = testTest, navController = rememberNavController())
}