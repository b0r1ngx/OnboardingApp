package com.smechotech.onboarding.ui.screens

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.smechotech.onboarding.R
import com.smechotech.onboarding.UserViewModel
import com.smechotech.onboarding.ui.IconText
import com.smechotech.onboarding.ui.Navigation
import com.smechotech.onboarding.ui.Title
import kotlin.random.Random

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: UserViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box() {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                /**
                 * Go to Shop Screen
                 */
                IconButton(onClick = { navController.navigate(Navigation.ShopScreen.name) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.shopping_cart),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                }
                /**
                 * Go to Settings Screen
                 */
                IconButton(onClick = { navController.navigate(Navigation.SettingsScreen.name) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                MainProfile(viewModel = viewModel)
                LeaderBoard(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun MainProfile(
    viewModel: UserViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        with(viewModel) {
            Image(
                painter = painterResource(id = userAvatarId),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .padding(vertical = 5.dp)
            ) // TODO: clickable for change avatar
            Title(
                titleText = userLogin,
                modifier = Modifier.padding(vertical = 5.dp)
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(vertical = 5.dp)
            ) {
                /**
                 * User experience count
                 */
                IconText(
                    iconId = R.drawable.star_white,
                    text = userExperience.toString(),
                    modifier = Modifier.padding(horizontal = 3.dp)
                )
                /**
                 * User diamonds count
                 */
                IconText(
                    iconId = R.drawable.gem,
                    text = userDiamonds.toString(),
                    modifier = Modifier.padding(horizontal = 3.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LeaderBoard(viewModel: UserViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(titleText = stringResource(id = R.string.leader_board_title))
        LazyColumn {
            itemsIndexed(viewModel.leadersName) { index, leader ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .animateItemPlacement(
                            tween(durationMillis = 250)
                        )
                ) {
                    Text(text = (index + 1).toString(), modifier = Modifier.padding(start = 50.dp))
                    Text(text = leader, modifier = Modifier.padding(start = 75.dp, end = 75.dp))
                    Text(
                        text = Random.nextInt(100, 2000).toString(),
                        modifier = Modifier.padding(end = 50.dp)
                    )
                }
            }
        }
    }
}

@Preview(
    device = PIXEL_4
)
@Composable
fun ProfilePreview() {
    ProfileScreen(navController = rememberNavController(), viewModel = viewModel())
}