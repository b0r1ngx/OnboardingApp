package com.smechotech.onboarding

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smechotech.onboarding.data.tests
import com.smechotech.onboarding.ui.Navigation.*
import com.smechotech.onboarding.ui.features.BottomNavBar
import com.smechotech.onboarding.ui.features.TopBar
import com.smechotech.onboarding.ui.features.firstTimeLaunch
import com.smechotech.onboarding.ui.features.isUserNotAuthorized
import com.smechotech.onboarding.ui.screens.*
import com.smechotech.onboarding.ui.theme.OnboardingAppTheme

class MainActivity : ComponentActivity() {
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getPreferences(MODE_PRIVATE)
        setContent {
            val navController = rememberNavController()

            val topBarState = remember { (mutableStateOf(true)) }
            val bottomBarState = remember { (mutableStateOf(true)) }

            OnboardingAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { if (topBarState.value) TopBar(viewModel) },
                    bottomBar = { if (bottomBarState.value) BottomNavBar(viewModel, navController) }
                ) { paddingValues ->
                    App(
                        modifier = Modifier.padding(paddingValues),
                        viewModel = viewModel,
                        navController = navController,
                        topBarState,
                        bottomBarState
                    )
                }
            }
        }
    }

    companion object {
        lateinit var sharedPreferences: SharedPreferences
    }
}

@Composable
fun App(
    modifier: Modifier = Modifier,
    viewModel: UserViewModel,
    navController: NavHostController,
    topBarState: MutableState<Boolean>,
    bottomBarState: MutableState<Boolean>
) = Box(modifier = modifier) {
    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
    OnBoardingAppNavHost(
        navController = navController,
        viewModel = viewModel,
        topBarState,
        bottomBarState
    )
}


@Composable
fun OnBoardingAppNavHost(
    navController: NavHostController,
    viewModel: UserViewModel,
    topBarState: MutableState<Boolean>,
    bottomBarState: MutableState<Boolean>
) = NavHost(
    navController = navController, startDestination = ProfileScreen.name
) {
    composable(OnBoardingScreen.name) {
        topBarState.value = false
        bottomBarState.value = false

        OnBoardingScreen(navController = navController)
    }

    composable(LoginScreen.name) {
        topBarState.value = false
        bottomBarState.value = false

        LoginScreen(navController = navController)
    }

    composable(MainScreen.name) {
        topBarState.value = true
        bottomBarState.value = true

        MainScreen(
            navController = navController,
            viewModel = viewModel,
            tests = tests
        )

        if (firstTimeLaunch()) {
            navController.navigate(OnBoardingScreen.name)
        } else if (isUserNotAuthorized()) {
            navController.navigate(LoginScreen.name)
        }
    }

    composable(ProfileScreen.name) {
        topBarState.value = false
        bottomBarState.value = true

        ProfileScreen(navController, viewModel)
    }

    composable(CalendarScreen.name) {
        topBarState.value = true
        bottomBarState.value = true

        CalendarScreen(navController)
    }

    composable(QuestionsScreen.name) {
        topBarState.value = true
        bottomBarState.value = true

        QuestionsScreen(navController)
    }

    composable(TestScreen.name) {
        topBarState.value = false
        bottomBarState.value = false

        TestScreen(
            navController = navController,
            test = viewModel.test
        )
    }

    composable(TestQuestionScreen.name) {
        topBarState.value = false
        bottomBarState.value = false

        TestQuestionScreen(
            navController = navController,
            viewModel = viewModel
        )
    }

    composable(RewardingAfterTestScreen.name) {
        topBarState.value = false
        bottomBarState.value = false

        RewardingAfterTestScreen(navController = navController,
            viewModel = viewModel)
    }

    composable(ShopScreen.name) {
        topBarState.value = true
        bottomBarState.value = false

        ShopScreen(
            navController = navController,
            viewModel = viewModel
        )
    }

    composable(SettingsScreen.name) {
        topBarState.value = false
        bottomBarState.value = false

        SettingsScreen(navController = navController)
    }
}
