package com.example.marvelheroesapp.presentation

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.marvelheroesapp.models.HeroModel
import com.example.marvelheroesapp.ui.theme.BackgroundColor
import com.example.marvelheroesapp.ui.theme.InterTextBold22
import com.example.marvelheroesapp.ui.theme.InterTextExtraBold34
import com.example.marvelheroesapp.ui.theme.TextColor
import com.example.marvelheroesapp.ui.theme.medium32
import com.example.marvelheroesapp.ui.theme.small16

@Composable
fun SecondScreen(heroModel: HeroModel, upPress: () -> Unit) {
    Box(
        modifier = Modifier
            .background(color = BackgroundColor)
            .fillMaxSize(),
    )
    {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = heroModel.heroImageUrl,
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
                text = stringResource(id = heroModel.heroName),
                style = InterTextExtraBold34,
                color = TextColor,
            )
            Text(
                modifier = Modifier
                    .padding(top = small16),
                text = stringResource(id = heroModel.heroDesc),
                style = InterTextBold22,
                color = TextColor,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SecondScreen(heroModel = HeroModel.mockHeroList.first(), upPress = {})
}
