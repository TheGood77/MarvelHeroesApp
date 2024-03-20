package com.example.marvelheroesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.marvelheroesapp.navigation.AppNavigation
import com.example.marvelheroesapp.ui.theme.MarvelHeroesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelHeroesAppTheme {
                AppNavigation()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MarvelHeroesAppTheme {
        AppNavigation()
    }
}
