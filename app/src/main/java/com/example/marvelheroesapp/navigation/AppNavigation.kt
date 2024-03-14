package com.example.marvelheroesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.marvelheroesapp.FirstScreen
import com.example.marvelheroesapp.SecondScreen
import com.example.marvelheroesapp.models.HeroModel.Companion.mockHeroList

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main_screen_route") {
        composable(route = "main_screen_route") {
            FirstScreen(navController)
        }
        composable(
            route = "second_screen_route/{heroName}",
            arguments = listOf(navArgument("heroName") { type = NavType.IntType })
        ) { backStackEntry ->
            val heroName = backStackEntry.arguments?.getInt("heroName")
            if (heroName != null) {
                val heroModel = mockHeroList.find { it.heroName == heroName }
                if (heroModel != null) {
                    SecondScreen(navController, heroModel)
                }
            }
        }
    }
}