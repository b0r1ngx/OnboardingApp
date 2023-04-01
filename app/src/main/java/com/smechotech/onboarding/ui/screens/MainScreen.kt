package com.smechotech.onboarding.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.smechotech.onboarding.R
import com.smechotech.onboarding.UserViewModel

@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: UserViewModel
) {
    Column {
        Test(centerImage = R.drawable.star_filled)
        Test(centerImage = R.drawable.star_black)
        Test(centerImage = R.drawable.star_black)
        Test(centerImage = R.drawable.star_black)
        Test(centerImage = R.drawable.crown)
    }
}


class OvalCornerShape(
    size: Dp
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val rect = size.toRect()
        val path = Path().apply {
            addOval(rect)
        }
        return Outline.Generic(path)
    }
}

@Composable
fun Test(
    @DrawableRes centerImage: Int
) = Card(
    modifier = Modifier.size(100.dp),
    shape = OvalCornerShape(30.dp),
    colors = CardDefaults.cardColors(
        containerColor = Color.White
    ),
//    elevation = 10.dp
) {
    Image(
        painter = painterResource(id = centerImage),
        contentDescription = "Тест",
        modifier = Modifier.size(24.dp)
    )
}