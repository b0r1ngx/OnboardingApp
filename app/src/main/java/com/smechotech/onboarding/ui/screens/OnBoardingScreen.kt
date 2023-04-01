package com.smechotech.onboarding.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.smechotech.onboarding.R
import com.smechotech.onboarding.ui.Navigation
import kotlinx.coroutines.launch

typealias NavigateNextPage = () -> Unit

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(navController: NavHostController) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    val navigateNext: NavigateNextPage = {
        if (pagerState.pageCount - 1 > pagerState.currentPage) coroutineScope.launch {
            pagerState.scrollToPage(pagerState.currentPage + 1)
        }
        else navController.navigate(Navigation.LoginScreen.name)
    }

    Box {
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            count = 3,
            userScrollEnabled = false,
            state = pagerState
        ) { page ->
            val padding = when (page) {
                0 -> 2400.dp
                1 -> 2250.dp
                2 -> 2000.dp
                else -> 1600.dp
            }

            when (page) {
                0 -> Welcome(navigateNext, padding)
                1 -> WelcomeToTeam(navigateNext, padding)
                2 -> Study(navigateNext, padding)
            }
        }
    }
}

@Composable
private fun IconContentScreen(
    @StringRes title: Int,
    @StringRes body: Int,
    padding: Dp,
    @DrawableRes icon: Int = R.drawable.nyan_cat_white_full
) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 40.dp)
                .wrapContentSize(unbounded = true)
                .padding(end = padding), // 2400 start, 1600 out,
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = stringResource(id = title),
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        )
        Text(
            text = stringResource(id = body),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 110.dp),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 20.sp,
                color = Color.White
            ),
            color = Color.White,
        )
    }
}

@Composable
private fun BaseOnBoardingScreen(
    navigateNext: NavigateNextPage,
    @DrawableRes icon: Int = R.drawable.ic_launcher_foreground,
    @StringRes buttonText: Int = R.string.next,
    @StringRes descriptionText: Int = R.string.next_description,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.matchParentSize()) {
            content.invoke()
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    navigateNext.invoke()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(46.dp)
                    .padding(horizontal = 70.dp),
                shape = RoundedCornerShape(corner = CornerSize(23.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(
                    stringResource(id = buttonText),
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                )
            }

            Text(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 30.dp),
                style = TextStyle(
                    textAlign = TextAlign.Center
                ),
                color = Color.Gray,
                text = stringResource(id = descriptionText)
            )
        }
    }
}

@Composable
private fun Welcome(
    navigateNext: NavigateNextPage,
    padding: Dp
) {
    BaseOnBoardingScreen(navigateNext = navigateNext) {
        IconContentScreen(
            R.string.welcome,
            R.string.welcome_content,
            padding
        )
    }
}

@Composable
private fun WelcomeToTeam(
    navigateNext: NavigateNextPage,
    padding: Dp
) {
    BaseOnBoardingScreen(navigateNext = navigateNext) {
        IconContentScreen(
            R.string.welcome_to_team,
            R.string.welcome_to_team_content,
            padding
        )
    }
}

@Composable
private fun Study(
    navigateNext: NavigateNextPage,
    padding: Dp
) {
    BaseOnBoardingScreen(navigateNext = navigateNext) {
        IconContentScreen(
            R.string.study,
            R.string.study_content,
            padding
        )
    }
}

@Preview(device = PIXEL_4)
@Composable
fun OnBoardingScreenPreview() {
    OnBoardingScreen(navController = rememberNavController())
}