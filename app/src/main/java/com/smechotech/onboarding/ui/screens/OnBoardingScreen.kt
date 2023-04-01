package com.smechotech.onboarding.ui.screens

import android.Manifest
import android.app.Activity
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import com.smechotech.onboarding.R

typealias NavigateNextPage = () -> Unit

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(navController: NavHostController) {
    val fPagerState = rememberPagerState()

    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current
    val skipNotificationScreen = if (context is Activity) {
        if (Build.VERSION.SDK_INT >= 33) {
            !shouldShowRequestPermissionRationale(context, Manifest.permission.POST_NOTIFICATIONS)
        } else {
            true
        }
    } else {
        true
    }

    val navigateNext: NavigateNextPage = {
        // Skip notifications screen
        if (fPagerState.currentPage == 3 && skipNotificationScreen) {
            coroutineScope.launch {
                navController.navigateUp()
            }
        } else if (fPagerState.pageCount - 1 > fPagerState.currentPage) {
            coroutineScope.launch {
                fPagerState.animateScrollToPage(fPagerState.currentPage + 1)
            }
        } else {
            navController.navigateUp()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier
                .padding(16.dp)
                .width(29.dp)
                .height(29.dp)
                .align(Alignment.End),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                contentColorFor(backgroundColor =  Color(0x1AFFFFFF))
            ),
            contentPadding = PaddingValues(0.dp),
            onClick = {
                navController.navigateUp()
            }) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_launcher_foreground),
                contentDescription = "",
                tint = Color(0xFF75727B)
            )
        }

        HorizontalPager(
            modifier = Modifier
                .fillMaxSize(),
            count = 5,
            userScrollEnabled = false,
            state = fPagerState
        ) { page ->
            when (page) {
                0 -> {
                    WifiScreen(navigateNext)
                }
                1 -> {
                    ScanNetworkScreen(navigateNext)
                }
                2 -> {
                    FindDevicesScreen(navigateNext)
                }
                3 -> {
                    LastUpdates(navigateNext)
                }
            }
        }
    }
}

@Composable
private fun IconContentScreen(
    @DrawableRes icon: Int,
    @StringRes title: Int,
    @StringRes body: Int,
) {
    Column(Modifier.fillMaxSize()) {
        Icon(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 50.dp)
                .width(130.dp)
                .height(130.dp),
            painter = painterResource(id = icon),
            contentDescription = ""
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(id = title),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            text = stringResource(id = body)
        )
    }
}

@Composable
private fun BaseContentScreen(@StringRes title: Int, @StringRes body: Int) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 50.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(id = title),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            text = stringResource(id = body)
        )
    }
}

@Composable
private fun BaseOnboardingScreen(
    navigateNext: NavigateNextPage,
    @DrawableRes icon: Int = R.drawable.ic_launcher_foreground,
    @StringRes btext: Int = R.string.start1,
    @StringRes bsubtext: Int = R.string.subtext1,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.matchParentSize()) {
            content.invoke()
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            Divider(
                modifier = Modifier.padding(bottom = 15.dp),
                thickness = 1.dp
            )

            Button(
                onClick = {
                    navigateNext.invoke()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(start = 20.dp, end = 20.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                colors = ButtonDefaults.buttonColors()
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(icon),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                        .padding(end = 4.dp)
                )
                Text(
                    stringResource(id = btext),
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 30.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                text = stringResource(id = bsubtext)
            )
        }
    }
}

@Composable
private fun WifiScreen(navigateNext: NavigateNextPage) {
    BaseOnboardingScreen(navigateNext = navigateNext) {
        IconContentScreen(R.drawable.ic_launcher_foreground, R.string.wifi_title, R.string.wifi_body)
    }
}

@Composable
private fun ScanNetworkScreen(navigateNext: NavigateNextPage) {
    BaseOnboardingScreen(navigateNext = navigateNext) {
        IconContentScreen(
            R.drawable.ic_launcher_foreground,
            R.string.scnetwork_title,
            R.string.scnetwork_body
        )
    }
}

@Composable
private fun FindDevicesScreen(navigateNext: NavigateNextPage) {
    BaseOnboardingScreen(navigateNext = navigateNext) {
        IconContentScreen(
            R.drawable.ic_launcher_foreground,
            R.string.fdevices_title,
            R.string.fdevices_body
        )
    }
}

@Composable
private fun LastUpdates(navigateNext: NavigateNextPage) {
    BaseOnboardingScreen(
        navigateNext = navigateNext,
        icon = R.drawable.ic_launcher_foreground,
        btext = R.string.ltupdates_btext,
        bsubtext = R.string.ltupdates_subtext
    ) {
        BaseContentScreen(R.string.ltupdates_title, R.string.ltupdates_body)
    }
}