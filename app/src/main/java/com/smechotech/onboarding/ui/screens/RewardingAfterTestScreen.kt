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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.smechotech.onboarding.R
import com.smechotech.onboarding.UserViewModel
import com.smechotech.onboarding.ui.IconText
import com.smechotech.onboarding.ui.Navigation
import com.smechotech.onboarding.ui.TextButtonDesign
import com.smechotech.onboarding.ui.Title

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

        Title(
            titleText = stringResource(id = R.string.rewards_test_title),
            modifier = Modifier.padding(top = 40.dp),
        )

        Column(modifier = Modifier) {
            IconText(iconId = R.drawable.start_white, text = ": " + viewModel.getCurrentExp())
            IconText(iconId = R.drawable.gem, text =  ": " + viewModel.getCurrentDiamonds())
        }

        TextButtonDesign(
            onClick = { navController.navigate(Navigation.MainScreen.name) }
        ) {
            Text(text = stringResource(id = R.string.next))
        }
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