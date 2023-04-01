package com.smechotech.onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smechotech.onboarding.ui.Navigation.*
import com.smechotech.onboarding.ui.screens.*
import com.smechotech.onboarding.ui.theme.OnboardingAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnboardingAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App(viewModel = viewModel)
                }
            }
        }
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
    navController = navController, startDestination = TestScreen.name
) {
    composable(OnBoardingScreen.name) {
        OnBoardingScreen(navController)
    }

    composable(LoginScreen.name) {
        LoginScreen(navController)
    }

    composable(MainScreen.name) {
        MainScreen(navController, viewModel)
    }

    composable(TestScreen.name) {
        TestScreen(navController, viewModel.test)
    }

    composable(TestQuestionScreen.name) {
        TestQuestionScreen(
            navController,
            viewModel,
            viewModel.test.title,
            viewModel.test.questions[viewModel.currentQuestionIndex]
        )
    }

//    composable("${DeviceScreen.name}/{deviceMac}") {
//        DeviceScreen(navController, viewModel, it.arguments?.getString("deviceMac"))
//    }

    composable(SettingsScreen.name) {
        SettingsScreen(navController)
    }
}

