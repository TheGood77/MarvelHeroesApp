package com.example.marvelheroesapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.marvelheroesapp.models.HeroModel
import com.example.marvelheroesapp.ui.theme.InterTextExtraBold32
import com.example.marvelheroesapp.ui.theme.TextColor
import com.example.marvelheroesapp.ui.theme.cardSizeHeight
import com.example.marvelheroesapp.ui.theme.cardSizeWidth
import com.example.marvelheroesapp.ui.theme.less10
import com.example.marvelheroesapp.ui.theme.medium32

@Composable
fun HeroCardComponent(onClick: (Int) -> Unit, heroModel: HeroModel) {
    Card(
        modifier = Modifier
            .size(width = cardSizeWidth, height = cardSizeHeight)
            .clip(shape = RoundedCornerShape(less10))
            .clickable {
                onClick(heroModel.heroName)
            }
    ) {
        Box {
            AsyncImage(
                model = heroModel.heroImageUrl,
                contentDescription = "Card image",
                contentScale = ContentScale.Crop,
            )
            Text(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = medium32, start = medium32),
                text = stringResource(id = heroModel.heroName),
                style = InterTextExtraBold32,
                color = TextColor,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    HeroCardComponent(onClick = {}, heroModel = HeroModel.mockHeroList.first())
}
