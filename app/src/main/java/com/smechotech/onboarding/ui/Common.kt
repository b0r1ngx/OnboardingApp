package com.smechotech.onboarding.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Tittle function
 *
 * It's a big text on start of the screen
 *
 * Parameters:
 * titleText: String,
 * modifier: Modifier = Modifier
 *
 */
@Composable
fun Title(
    titleText: String,
    modifier: Modifier = Modifier
) = Text(
    text = titleText,
    modifier = modifier,
    style = TextStyle(fontSize = 25.sp),
    color = Color.White,
    textAlign = TextAlign.Center
)

@Composable
fun Description(
    descriptionText: String,
    modifier: Modifier = Modifier
) = Text(
    text = "\t\t\t${descriptionText}",
    modifier = modifier
        .padding(all = 20.dp),
    color = Color.White,
    textAlign = TextAlign.Justify
)

@Composable
fun TextButtonDesign(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        contentColor = Color.Black,
        containerColor = Color(0xD6FFFFFF)
    ),
    enabled: Boolean = true,
    text: @Composable () -> Unit
) =
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        enabled = enabled,
        colors = colors
    ) {
        text()
    }

@Composable
fun ImageDesign(
    imageResource: Int?,
    modifier: Modifier = Modifier
) {
    if (imageResource != null)
        Image(
            painter = painterResource(imageResource),
            contentDescription = null,
            modifier = modifier
        )
}

@Composable
fun ImageButton(
    imageResource: Int?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    if (imageResource != null)
        Image(
            painter = painterResource(imageResource),
            contentDescription = contentDescription,
            modifier = modifier.clickable { onClick() }
        )
}

@Composable
fun IconText(
    iconId: Int,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        ImageDesign(
            imageResource = iconId,
            modifier = Modifier.size(30.dp)
        )
        Text(
            text = text,
            style = TextStyle(fontSize = 30.sp),
            color = Color.White
        )
    }
}