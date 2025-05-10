package com.anuvk.furryfriendapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
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

@Composable
fun DogBreedItem(breedsDomain: BreedsDomain) {
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
                model = breedsDomain.imageUrl,
                contentDescription = breedsDomain.breedName,
                modifier = Modifier.size(60.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = breedsDomain.breedName,
                    style = MaterialTheme.typography.bodySmall
                )

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