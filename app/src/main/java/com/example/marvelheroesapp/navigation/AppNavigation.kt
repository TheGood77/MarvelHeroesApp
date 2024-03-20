package com.example.marvelheroesapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.marvelheroesapp.models.HeroModel.Companion.mockHeroList
import com.example.marvelheroesapp.navigation.Destinations.MAIN_SCREEN
import com.example.marvelheroesapp.navigation.Destinations.SECOND_SCREEN
import com.example.marvelheroesapp.presentation.FirstScreen
import com.example.marvelheroesapp.presentation.SecondScreen

object Destinations {
    const val MAIN_SCREEN = "main_screen_route"
    const val SECOND_SCREEN = "second_screen_route/{heroName}"
}

class Actions(navController: NavHostController) {
    val navigateToSecondScreen: (Int) -> Unit = { heroName ->
        val routeWithHeroName = SECOND_SCREEN.replace("{heroName}", heroName.toString())
        navController.navigate(routeWithHeroName)
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val actions = remember(navController) { Actions(navController) }

    NavHost(navController = navController, startDestination = MAIN_SCREEN) {
        composable(route = MAIN_SCREEN) {
            FirstScreen(actions.navigateToSecondScreen)
        }
        composable(
            route = SECOND_SCREEN,
            arguments = listOf(navArgument("heroName") { type = NavType.IntType })
        ) { backStackEntry ->
            val heroName = backStackEntry.arguments?.getInt("heroName")
            heroName?.let { heroId ->
                val heroModel = mockHeroList.find { it.heroName == heroId }
                heroModel?.let {
                    SecondScreen(it, actions.upPress)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppNavigation()
}
