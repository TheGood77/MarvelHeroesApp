package com.example.marvelheroesapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.marvelheroesapp.ui.theme.BackgroundColor
import com.example.marvelheroesapp.ui.theme.InterTextExtraBold28
import com.example.marvelheroesapp.ui.theme.TextColor

@Composable
fun FirstScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .background(color = BackgroundColor)
            .padding(vertical = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        AsyncImage(
            model = "https://iili.io/JMnuvbp.png",
            contentDescription = "Logo image",
        )
        Text(
            modifier = Modifier
                .padding(top = 54.dp, bottom = 82.dp),
            text = stringResource(id = R.string.choose_text),
            style = InterTextExtraBold28,
            color = TextColor,
        )
        CarouselCardsComponent(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    FirstScreen(rememberNavController())
}