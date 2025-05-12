package com.anuvk.furryfriendapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.anuvk.furryfriendapp.presentation.AppDestinations
import com.anuvk.furryfriendapp.presentation.viewmodel.DogBreedViewModel

@Composable
fun  AllDogBreedsScreen(
    navController: NavController,
    viewModel: DogBreedViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.loadAllDogBreeds()
    }

    DogBreedsScreenContent(
        state = state,
        onBreedClick = { breedName ->
            navController.navigate(AppDestinations.createBreedDetailRoute(breedName))
        }
    )

}
