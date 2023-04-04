package com.smechotech.onboarding.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.smechotech.onboarding.R
import com.smechotech.onboarding.ui.Navigation
import com.smechotech.onboarding.ui.features.userPassOnBoarding
import com.smechotech.onboarding.ui.theme.screensHorizontalPadding
import okhttp3.internal.immutableListOf

val OnBoardingData = immutableListOf(
    listOf(R.string.welcome, R.string.welcome_content),
    listOf(R.string.welcome_to_team, R.string.welcome_to_team_content),
    listOf(R.string.study, R.string.study_content)
)

@Composable
fun OnBoardingScreen(navController: NavHostController) {
    var page by remember { mutableStateOf(0) }
    val navigateNext = {
        if (OnBoardingData.size - 1 > page) page++
        else {
            userPassOnBoarding()
            navController.navigate(Navigation.LoginScreen.name)
        }
    }

    val catPadding by animateDpAsState(
        when (page) {
            0 -> 2400.dp
            1 -> 2250.dp
            2 -> 2000.dp
            else -> 1600.dp
        }
    )

    BaseOnBoardingScreen(navigateNext = navigateNext) {
        IconContentScreen(
            title = OnBoardingData[page][0],
            body = OnBoardingData[page][1],
            imagePadding = catPadding
        )
    }
}

@Composable
private fun IconContentScreen(
    @StringRes title: Int,
    @StringRes body: Int,
    imagePadding: Dp,
    @DrawableRes image: Int = R.drawable.nyan_cat_white_full
) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 40.dp)
                .wrapContentSize(unbounded = true)
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 800,
                        easing = LinearOutSlowInEasing
                    )
                )
                .padding(end = imagePadding),
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
    navigateNext: () -> Any,
    @DrawableRes buttonIcon: Int = R.drawable.ic_launcher_foreground,
    @StringRes buttonText: Int = R.string.next,
    @StringRes descriptionText: Int = R.string.next_description,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = screensHorizontalPadding),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.matchParentSize()) {
            content.invoke()
        }

        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
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
                style = TextStyle(textAlign = TextAlign.Center),
                color = Color.Gray,
                text = stringResource(id = descriptionText)
            )
        }
    }
}

@Preview(device = PIXEL_4)
@Composable
fun OnBoardingScreenPreview() =
    OnBoardingScreen(navController = rememberNavController())