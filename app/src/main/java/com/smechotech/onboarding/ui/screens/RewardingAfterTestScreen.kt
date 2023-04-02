package com.smechotech.onboarding.ui.screens

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.smechotech.onboarding.R
import com.smechotech.onboarding.UserViewModel
import com.smechotech.onboarding.ui.IconText
import com.smechotech.onboarding.ui.Navigation
import com.smechotech.onboarding.ui.TextButtonDesign
import com.smechotech.onboarding.ui.Title
import com.smechotech.onboarding.ui.theme.screensHorizontalPadding

@Composable
fun RewardingAfterTestScreen(
    navController: NavHostController, viewModel: UserViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Title(
            titleText = stringResource(id = R.string.rewards_test_title),
            modifier = Modifier.padding(top = 40.dp).weight(.5f),
        )

        Column(modifier = Modifier) {
            IconText(iconId = R.drawable.start_white, text = ": " + viewModel.getCurrentExp())
            IconText(iconId = R.drawable.gem, text =  ": " + viewModel.getCurrentDiamonds())
        }

        GifInsteadOfImage(modifier = Modifier.padding(vertical = 50.dp).weight(1.3f))

        TextButtonDesign(
            onClick = { navController.navigate(Navigation.MainScreen.name) },
            modifier = Modifier.padding(
                top = 200.dp,
                bottom = screensHorizontalPadding
            )
        ) {
            Text(text = stringResource(id = R.string.next))
        }
    }

}

@Composable
fun GifInsteadOfImage(modifier: Modifier = Modifier, ) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = R.raw.confetti).apply(block = {
                size(Size.ORIGINAL)
            }).build(), imageLoader = imageLoader
        ),
        contentDescription = null,
        modifier = modifier.fillMaxWidth(),
    )
}

@Preview(
    showBackground = true,
    device = PIXEL_4
)
@Composable
fun RewardReview() {
    RewardingAfterTestScreen(rememberNavController(), UserViewModel())
}