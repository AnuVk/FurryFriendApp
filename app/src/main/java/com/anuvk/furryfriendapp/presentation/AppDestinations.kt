package com.anuvk.furryfriendapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.anuvk.furryfriendapp.ui.screens.AllDogBreedsScreen
import com.anuvk.furryfriendapp.ui.screens.DogBreedsImageScreen

object AppDestinations {
    const val BREED_LIST_ROUTE = "breedList"
    const val BREED_IMAGES_LIST_ROUTE = "breedImagesDetails/{breedName}" // Route with an argument

    fun createBreedDetailRoute(breedName: String): String {
        return "breedImagesDetails/$breedName"
    }
}

@Composable
fun AppNavigation(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = AppDestinations.BREED_LIST_ROUTE) {
        composable(AppDestinations.BREED_LIST_ROUTE) {
            AllDogBreedsScreen(navController = navController)
        }
        composable(
            AppDestinations.BREED_IMAGES_LIST_ROUTE,
            arguments = listOf(navArgument("breedName") { type = NavType.StringType })
        ) { backStackEntry ->
            val breedName = backStackEntry.arguments?.getString("breedName")
            DogBreedsImageScreen(breedName ?: "")
        }
    }
}
