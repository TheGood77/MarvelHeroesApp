package com.example.marvelheroesapp.presentation.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.marvelheroesapp.R
import com.example.marvelheroesapp.models.HeroData
import com.example.marvelheroesapp.presentation.components.CarouselCardsComponent
import com.example.marvelheroesapp.presentation.components.ErrorComponent
import com.example.marvelheroesapp.ui.theme.BackgroundColor
import com.example.marvelheroesapp.ui.theme.InterTextExtraBold28
import com.example.marvelheroesapp.ui.theme.TextColor
import com.example.marvelheroesapp.ui.theme.huge64
import com.example.marvelheroesapp.ui.theme.large48
import com.example.marvelheroesapp.ui.theme.medium32

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = viewModel(),
    navigateToSecondScreen: (Int) -> Unit,
) {
    val heroes = mainViewModel.heroes.collectAsState()

    val errorState = mainViewModel.errorState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        HeroScreen(
            heroData = heroes.value,
            navigateToSecondScreen = navigateToSecondScreen,
        )

        AnimatedVisibility(errorState.value != null) {
            ErrorComponent(
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

@Composable
fun HeroScreen(
    heroData: HeroData? = null,
    navigateToSecondScreen: (Int) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .background(color = BackgroundColor)
            .padding(top = medium32)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = "https://iili.io/JMnuvbp.png",
            contentDescription = "Logo image",
        )
        Text(
            modifier = Modifier
                .padding(top = large48, bottom = huge64),
            text = stringResource(id = R.string.choose_text),
            style = InterTextExtraBold28,
            color = TextColor,
        )
        CarouselCardsComponent(
            heroData,
            navigateToSecondScreen,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    HeroScreen(navigateToSecondScreen = {})
}
