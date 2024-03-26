package com.example.marvelheroesapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.marvelheroesapp.ui.theme.BackgroundFirstCardColor
import com.example.marvelheroesapp.ui.theme.InterTextBold22
import com.example.marvelheroesapp.ui.theme.TextColor
import com.example.marvelheroesapp.ui.theme.small16

@Composable
fun ErrorComponent(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(small16)
            .background(BackgroundFirstCardColor)
            .then(modifier),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Unable to load data",
            color = TextColor,
            style = InterTextBold22,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ErrorComponent()
}
