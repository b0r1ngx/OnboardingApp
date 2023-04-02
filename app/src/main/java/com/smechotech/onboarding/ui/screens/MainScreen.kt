package com.smechotech.onboarding.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.smechotech.onboarding.R
import com.smechotech.onboarding.UserViewModel
import com.smechotech.onboarding.data.Test

@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: UserViewModel,
    tests: List<Test>
) {
    val lazyListState = rememberLazyListState()
    val scrollState = rememberScrollState()
    LazyColumn(
        state = lazyListState
    ) {
        items(tests) {
            Test(
                it,
                centerImage = R.drawable.star_filled,
                viewModel = viewModel,
                navController = navController
            )
            Test(
                it,
                centerImage = R.drawable.star_black,
                viewModel = viewModel,
                navController = navController
            )
            Test(
                it,
                centerImage = R.drawable.crown,
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}

@Composable
fun Test(
    test: Test,
    @DrawableRes centerImage: Int,
    viewModel: UserViewModel,
    navController: NavHostController
) = Card(
    modifier = Modifier
        .drawBehind {
            drawOval(
                color = Color.White,
                size = Size(width = 67.dp.toPx(), height = 54.dp.toPx())
            )
            drawOval(
                color = Color(0x26FFFFFF),
                size = Size(width = 67.dp.toPx(), height = 54.dp.toPx()),
                topLeft = Offset(x = 0.dp.toPx(), y = 5.dp.toPx())
            )
        }
        .clickable {
            viewModel.test = test
            navController.navigate(com.smechotech.onboarding.ui.Navigation.TestScreen.name)
        },
    colors = CardDefaults.cardColors(
        containerColor = Color.White
    ),
) {
    Image(
        painter = painterResource(id = centerImage),
        contentDescription = "Тест",
        modifier = Modifier.size(24.dp)
    )
}

@Preview(
    device = PIXEL_4,
    showBackground = true
)
@Composable
fun MainScreenPreview() {
    MainScreen(
        navController = rememberNavController(),
        viewModel = viewModel(),
        tests = listOf()
    )
}