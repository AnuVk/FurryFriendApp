package com.anuvk.furryfriendapp.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anuvk.furryfriendapp.domain.model.BreedsDomain
import com.anuvk.furryfriendapp.presentation.state.DogBreedState

@Composable
fun DogBreedsScreenContent(
    state: DogBreedState
) {
    Column(modifier = Modifier.padding(16.dp)) {
        when {
            state.isLoading -> {
                CircularProgressIndicator() // Show loading indicator
            }
            state.error != null -> {
                Text("Sorry Your furry friend is napping") // Show error message
            }
            state.breedsDomainList.isNotEmpty() -> {
                LazyColumn {
                    items(state.breedsDomainList) { breed ->
                        DogBreedItem(breedsDomain = breed)
                    }
                }
            }
            // Optional: Handle empty state
            else -> {
                Text("No breeds available.")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DogBreedsScreenContentPreview_Loading() {
    DogBreedsScreenContent(
        state = DogBreedState(isLoading = true))
}

@Preview(showBackground = true)
@Composable
fun DogBreedsScreenContentPreview_Success() {
    val sampleBreeds = listOf(
        BreedsDomain("buhund", "https://images.dog.ceo/breeds/affenpinscher/n02110627_7013.jpg"),
        BreedsDomain("bullterrier", "https://images.dog.ceo/breeds/affenpinscher/n02110627_7013.jpg"),
        BreedsDomain("collie", "https://images.dog.ceo/breeds/affenpinscher/n02110627_7013.jpg")
    )
    DogBreedsScreenContent(
        state = DogBreedState(isLoading = false,
        breedsDomainList = sampleBreeds))
}

@Preview(showBackground = true)
@Composable
fun DogBreedsScreenContentPreview_Error() {
    DogBreedsScreenContent(
        state = DogBreedState(isLoading = false,
            error = "Server Error")
    )
}