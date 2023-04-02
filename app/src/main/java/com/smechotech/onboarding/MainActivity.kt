package com.smechotech.onboarding

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smechotech.onboarding.MainActivity.Companion.sharedPreferences
import com.smechotech.onboarding.data.tests
import com.smechotech.onboarding.ui.Navigation.*
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
                    topBar = { if (topBarState.value) TopInfoBar(viewModel) },
                    bottomBar = { if (bottomBarState.value) BottomNavBar(viewModel, navController) }
                ) { paddingValues ->
                    App(
                        modifier = Modifier.padding(paddingValues),
                        viewModel = viewModel,
                        navController = navController
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
    navController: NavHostController
) = Box(modifier = modifier) {
    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
    OnBoardingAppNavHost(
        navController = navController,
        viewModel = viewModel
    )
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
            viewModel = viewModel,
            tests = tests
        )

        if (firstTimeLaunch()) {
            navController.navigate(OnBoardingScreen.name)
        } else if (!isUserAuthorized()) {
            navController.navigate(LoginScreen.name)
        }
    }

    composable(ProfileScreen.name) {
        ProfileScreen(navController)
    }

    composable(CalendarScreen.name) {
        CalendarScreen(navController)
    }

    composable(QuestionsScreen.name) {
        QuestionsScreen(navController)
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

@Composable
fun TopInfoBar(
    viewModel: UserViewModel
) {
    Row {
//        Image(painter = , contentDescription = )
        Text(text = viewModel.userExperience.toString())
        Text(text = viewModel.userDiamonds.toString())
    }
}

enum class BottomNavBar(val descriptions: String) {
    Home("Home"), Profile("Profile"), Calendar("Calendar"),
    Questions("Questions / Rules / FAQ / About Us")
}

@Composable
fun BottomNavBar(viewModel: UserViewModel, navController: NavHostController) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = BottomNavBar.values()

    NavigationBar {
        items.forEachIndexed { index, item ->
            val (painterResource, navigateTo) = bottomNavBar(item)
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(navigateTo)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = painterResource),
                        contentDescription = item.descriptions,
                        modifier = Modifier.size(34.dp),
                        tint = if (selectedItem == index) Color.White else Color(0xFF8A8A8A)
                    )
                },
                enabled = selectedItem != index
            )
        }
    }
}

fun bottomNavBar(item: BottomNavBar) = when (item) {
    BottomNavBar.Home -> R.drawable.home to MainScreen.name
    BottomNavBar.Profile -> R.drawable.profile to ProfileScreen.name
    BottomNavBar.Calendar -> R.drawable.calendar to CalendarScreen.name
    BottomNavBar.Questions -> R.drawable.questions to QuestionsScreen.name
}

