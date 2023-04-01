package com.smechotech.onboarding

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smechotech.onboarding.MainActivity.Companion.sharedPreferences
import com.smechotech.onboarding.ui.Navigation.*
import com.smechotech.onboarding.ui.screens.*
import com.smechotech.onboarding.ui.theme.OnboardingAppTheme

class MainActivity : ComponentActivity() {
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getPreferences(MODE_PRIVATE)
        setContent {
            OnboardingAppTheme {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.background),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                    App(viewModel = viewModel)
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
    viewModel: UserViewModel
) {
    val navController = rememberNavController()
    OnBoardingAppNavHost(navController = navController, viewModel = viewModel)
}

@Composable
fun OnBoardingAppNavHost(
    navController: NavHostController,
    viewModel: UserViewModel
) = NavHost(
    navController = navController, startDestination = MainScreen.name
) {
    composable(OnBoardingScreen.name) {
        OnBoardingScreen(navController = navController)
    }

    composable(LoginScreen.name) {
        LoginScreen(navController = navController)
    }

    composable(MainScreen.name) {
        MainScreen(
            navController = navController,
            viewModel = viewModel
        )

        if (firstTimeLaunch()) {
            navController.navigate(OnBoardingScreen.name)
        } else if (!isUserAuthorized()) {
            navController.navigate(LoginScreen.name)
        }
    }

    composable(TestScreen.name) {
        TestScreen(
            navController = navController,
            test = viewModel.test
        )
    }

    composable(TestQuestionScreen.name) {
        TestQuestionScreen(
            navController = navController,
            viewModel = viewModel
        )
    }

    composable(RewardingAfterTestScreen.name) {
        RewardingAfterTestScreen(navController = navController)
    }

    composable(ShopScreen.name) {
        ShopScreen(
            navController = navController,
            viewModel = viewModel
        )
    }

    composable(SettingsScreen.name) {
        SettingsScreen(navController = navController)
    }
}

const val FIRST_TIME_LAUNCH = "FIRST_TIME_LAUNCH"
const val USER_AUTHORIZED = "USER_AUTHORIZED"

fun firstTimeLaunch(): Boolean {
    val firsTimeLaunch = sharedPreferences.getBoolean(FIRST_TIME_LAUNCH, true)
    with(sharedPreferences.edit()) {
        putBoolean(FIRST_TIME_LAUNCH, false)
        apply()
    }
    return firsTimeLaunch
}

fun isUserAuthorized() = sharedPreferences.getBoolean(USER_AUTHORIZED, false)
fun userAuthorized() = with(sharedPreferences.edit()) {
    putBoolean(USER_AUTHORIZED, true)
    apply()
}

