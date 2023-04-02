package com.smechotech.onboarding.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.smechotech.onboarding.ui.theme.screensHorizontalPadding

@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: UserViewModel,
    tests: List<Test>
) {
    val lazyListState = rememberLazyListState()
    Box {
        FastSwiper(lazyListState)
        TestMap(
            tests = tests,
            lazyListState = lazyListState,
            viewModel = viewModel,
            navController = navController
        )
    }
}

@Composable
fun FastSwiper(lazyListState: LazyListState) {
    Divider(
        modifier = Modifier
            .padding(
                start = screensHorizontalPadding,
                top = 80.dp,
                bottom = 80.dp
                )
            .fillMaxHeight()
            .width(1.dp),
        thickness = 1.dp,
        color = Color.White
    )
}

@Composable
fun TestMap(
    tests: List<Test>,
    lazyListState: LazyListState,
    viewModel: UserViewModel,
    navController: NavHostController
) = LazyColumn(
    modifier = Modifier.fillMaxSize(),
    state = lazyListState,
    reverseLayout = true,
    verticalArrangement = Arrangement.SpaceEvenly,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    items(tests) {
        Test(
            it,
            centerImage = R.drawable.star_filled,
            viewModel = viewModel,
            navController = navController
        )
    }
}

//Test(
//it,
//centerImage = R.drawable.star_black,
//viewModel = viewModel,
//navController = navController
//)
//Test(
//it,
//centerImage = R.drawable.crown,
//viewModel = viewModel,
//navController = navController
//)

@Composable
fun Test(
    test: Test,
    @DrawableRes centerImage: Int,
    viewModel: UserViewModel,
    navController: NavHostController
) = Card(
    modifier = Modifier
        .clip(CircleShape)
        .size(70.dp)
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
        modifier = Modifier.size(24.dp).align(Alignment.CenterHorizontally)
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