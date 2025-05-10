package com.anuvk.furryfriendapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anuvk.furryfriendapp.presentation.viewmodel.DogBreedViewModel

@Composable
fun  DogBreedsScreen(
    viewModel: DogBreedViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.loadAllDogBreeds()
    }

    DogBreedsScreenContent(
        state = state
    )

}
