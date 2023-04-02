package com.smechotech.onboarding.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.smechotech.onboarding.R
import com.smechotech.onboarding.data.Test
import com.smechotech.onboarding.data.safetyPrecautionsTest
import com.smechotech.onboarding.ui.*
import com.smechotech.onboarding.ui.theme.screensHorizontalPadding

@Composable
fun TestScreen(navController: NavHostController, test: Test) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = screensHorizontalPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(titleText = test.title)
        ImageDesign(
            imageResource = test.image,
            modifier = Modifier.weight(1f)
        )
        Description(
            descriptionText = test.theory,
            modifier = Modifier.weight(1f)
        )
        ButtonToTest(navController)
    }
}

@Composable
fun ButtonToTest(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    TextButtonDesign(
        onClick = { navController.navigate(Navigation.TestQuestionScreen.name) },
        modifier = modifier
    ) {
        Text(text = stringResource(id = R.string.start_test))
    }
}

@Preview(
    showBackground = true,
    device = PIXEL_4
)
@Composable
fun TestPreview() {
    TestScreen(test = safetyPrecautionsTest, navController = rememberNavController())
}