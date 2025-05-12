package com.anuvk.furryfriendapp.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.anuvk.furryfriendapp.domain.model.BreedsDomain
import com.anuvk.furryfriendapp.domain.model.DogBreedImagesDomain
import com.anuvk.furryfriendapp.presentation.viewmodel.DogBreedViewModel

@Composable
fun  SubDogBreedsScreen(
    breedName: String,
    viewModel: DogBreedViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.getRandomImageByBreed(breedName, 10)
        println(">>> breedname $breedName")
    }

    DogBreedsScreenContent(
        state = state
    )

}

@Composable
fun DogBreedItem(
    dogBreedImagesDomain: DogBreedImagesDomain,
    onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = dogBreedImagesDomain.listOfDogBreedImages.first(),
                contentDescription = "breedsDomain.breedNam",
                modifier = Modifier.size(60.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

        }
    }
}
