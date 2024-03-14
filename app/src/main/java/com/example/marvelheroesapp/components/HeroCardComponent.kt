package com.example.marvelheroesapp.components

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.marvelheroesapp.models.HeroModel
import com.example.marvelheroesapp.ui.theme.InterTextExtraBold32
import com.example.marvelheroesapp.ui.theme.TextColor

@Composable
fun HeroCardComponent(navController: NavHostController, heroModel: HeroModel) {
    Card(
        modifier = Modifier
            .size(width = 300.dp, height = 550.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable {
                navController.navigate("second_screen_route/${heroModel.heroName}")
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
                    .padding(bottom = 32.dp, start = 32.dp),
                text = stringResource(id = heroModel.heroName),
                style = InterTextExtraBold32,
                color = TextColor,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeroCardComponentPreview() {
    HeroCardComponent(rememberNavController(), HeroModel.mockHeroList.first())
}
