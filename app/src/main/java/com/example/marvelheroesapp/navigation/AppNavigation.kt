package com.example.marvelheroesapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.marvelheroesapp.navigation.Destinations.MAIN_SCREEN
import com.example.marvelheroesapp.navigation.Destinations.SECOND_SCREEN
import com.example.marvelheroesapp.presentation.description.DescriptionScreen
import com.example.marvelheroesapp.presentation.main.MainScreen
import com.example.marvelheroesapp.presentation.main.MainViewModel

object Destinations {
    const val MAIN_SCREEN = "main_screen_route"
    const val SECOND_SCREEN = "second_screen_route/{id}"
}

class Actions(navController: NavHostController) {
    val navigateToSecondScreen: (Int) -> Unit = { id ->
        val routeWithId = SECOND_SCREEN.replace("{id}", id.toString())
        navController.navigate(routeWithId)
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}

@Composable
fun AppNavigation(viewModel: MainViewModel = viewModel()) {

    val navController = rememberNavController()
    val actions = remember(navController) { Actions(navController) }

    NavHost(navController = navController, startDestination = MAIN_SCREEN) {
        composable(route = MAIN_SCREEN) {
            MainScreen(
                mainViewModel = viewModel,
                navigateToSecondScreen = actions.navigateToSecondScreen
            )
        }
        composable(
            route = SECOND_SCREEN,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val heroId = backStackEntry.arguments?.getInt("id") ?: -1
            DescriptionScreen(heroId = heroId, upPress = actions.upPress)
        }
    }
}
