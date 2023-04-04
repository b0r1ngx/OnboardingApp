package com.smechotech.onboarding.ui.features

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smechotech.onboarding.R
import com.smechotech.onboarding.UserViewModel
import com.smechotech.onboarding.ui.ImageDesign
import com.smechotech.onboarding.ui.theme.screensHorizontalPadding

@Composable
fun TopBar(
    viewModel: UserViewModel
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.top_bar_background),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillBounds
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Image(
                painter = painterResource(id = R.drawable.avatar_great),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = screensHorizontalPadding)
                    .size(76.dp)
                    .clip(CircleShape)
            )
            TextImage(
                text = viewModel.userExperience.toString(),
                imageResourceInt = R.drawable.star_white,
                textModifier = Modifier.padding(start = 60.dp, top = screensHorizontalPadding)
            )
            TextImage(
                text = viewModel.userDiamonds.toString(),
                imageResourceInt = R.drawable.gem,
                textModifier = Modifier.padding(start = 40.dp, top = screensHorizontalPadding)
            )
        }
    }

}

@Composable
fun TextImage(
    text: String,
    @DrawableRes imageResourceInt: Int,
    textModifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier.size(32.dp)
) = Row(
    modifier = textModifier,
    verticalAlignment = Alignment.CenterVertically
) {
    Text(text = text, fontSize = 20.sp)
    ImageDesign(
        imageResource = imageResourceInt,
        modifier = imageModifier
    )
}