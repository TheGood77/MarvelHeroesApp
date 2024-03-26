package com.example.marvelheroesapp.presentation.description

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.marvelheroesapp.R
import com.example.marvelheroesapp.models.HeroCard
import com.example.marvelheroesapp.models.HeroThumbnail
import com.example.marvelheroesapp.presentation.components.ErrorComponent
import com.example.marvelheroesapp.ui.theme.BackgroundColor
import com.example.marvelheroesapp.ui.theme.InterTextBold22
import com.example.marvelheroesapp.ui.theme.InterTextExtraBold34
import com.example.marvelheroesapp.ui.theme.TextColor
import com.example.marvelheroesapp.ui.theme.medium32
import com.example.marvelheroesapp.ui.theme.small16

@Composable
fun DescriptionScreen(
    descriptionViewModel: DescriptionViewModel = viewModel(),
    heroId: Int,
    upPress: () -> Unit,
) {
    val heroData = descriptionViewModel.hero.collectAsState()

    val errorState = descriptionViewModel.errorState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        LaunchedEffect(Unit) {
            descriptionViewModel.getHeroById(heroId)
        }

        Description(
            hero = heroData.value?.data?.results?.first(),
            upPress = upPress,
        )

        AnimatedVisibility(errorState.value != null) {
            ErrorComponent(
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

@Composable
private fun Description(
    hero: HeroCard?,
    upPress: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .background(color = BackgroundColor)
            .fillMaxSize(),
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = hero?.getImageUrl(),
            contentDescription = "Card image",
            contentScale = ContentScale.Crop,
        )
        Icon(
            modifier = Modifier
                .padding(all = small16)
                .size(size = medium32)
                .clickable { upPress() },
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = TextColor,
        )
        Column(
            modifier = Modifier
                .padding(start = small16, bottom = medium32)
                .align(Alignment.BottomStart),
        ) {
            Text(
                text = hero?.name ?: "",
                style = InterTextExtraBold34,
                color = TextColor,
            )
            Text(
                modifier = Modifier
                    .padding(top = small16),
                text = hero?.description ?: "",
                style = InterTextBold22,
                color = TextColor,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    val mockHero = HeroCard(
        id = 0,
        name = stringResource(id = R.string.first_hero_name),
        description = stringResource(id = R.string.first_hero_text),
        thumbnail = HeroThumbnail(
            path = "",
            extension = "",
        ),
    )
    Description(
        hero = mockHero,
    )
}
