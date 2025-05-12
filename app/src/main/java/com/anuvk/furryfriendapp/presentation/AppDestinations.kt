package com.anuvk.furryfriendapp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anuvk.furryfriendapp.ui.screens.AllDogBreedsScreen
import com.anuvk.furryfriendapp.ui.screens.SubDogBreedsScreen

object AppDestinations {
    const val BREED_LIST_ROUTE = "breedList"
    const val BREED_DETAIL_ROUTE = "breedDetail/{breedName}" // Route with an argument

    // Helper function to create the detail route with an argument
    fun createBreedDetailRoute(breedName: String): String {
        return "breedDetail/$breedName"
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
        composable(AppDestinations.BREED_DETAIL_ROUTE) { backStackEntry ->
            val breedName = backStackEntry.arguments?.getString("breedName")
            SubDogBreedsScreen(breedName ?: "")
            // Navigate to your detail screen, passing the breedName
            // BreedDetailScreen(breedName = breedName) // Assuming you have a BreedDetailScreen
        }
    }
}
