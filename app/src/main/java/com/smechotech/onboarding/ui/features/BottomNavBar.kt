package com.smechotech.onboarding.ui.features

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.smechotech.onboarding.UserViewModel
import com.smechotech.onboarding.ui.Navigation
import com.smechotech.onboarding.R

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
    BottomNavBar.Home -> R.drawable.home to Navigation.MainScreen.name
    BottomNavBar.Profile -> R.drawable.profile to Navigation.ProfileScreen.name
    BottomNavBar.Calendar -> R.drawable.calendar to Navigation.CalendarScreen.name
    BottomNavBar.Questions -> R.drawable.questions to Navigation.QuestionsScreen.name
}
