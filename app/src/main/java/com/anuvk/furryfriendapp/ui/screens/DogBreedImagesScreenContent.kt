package com.anuvk.furryfriendapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anuvk.furryfriendapp.presentation.state.DogBreedState

@Composable
fun DogBreedImagesScreenContent(
    state: DogBreedState,
) {
    Column(modifier = Modifier.padding(16.dp)) {
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            state.error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Sorry!!! Your furry friends are napping")
                }
            }
            state.randomDogBreedImages.isNotEmpty() -> {
                        DogBreedsImageLazyColumn(
                            listOfImages = state.randomDogBreedImages
                        )
                }
            else -> {
                Text("No breeds available.")
            }
        }
    }
}




@Composable
private fun DogBreedsImageLazyColumn(
    listOfImages: List<String>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
            items(items = listOfImages) { text ->
                DogImageItem(text)
            }

    }
}

@Composable
fun DogImageItem(
    image: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .crossfade(true)
                    .build(),
                contentDescription = "breedsDomain.breedName",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DogBreedImagesScreenContentPreview_Loading() {
    DogBreedImagesScreenContent(
        state = DogBreedState(isLoading = true))
}

@Preview(showBackground = true)
@Composable
fun DogBreedImagesScreenContentPreview_Success() {
    val listOfImages = listOf("https://images.dog.ceo/breeds/elkhound-norwegian/n02091467_1406.jpg",
        "https://images.dog.ceo/breeds/elkhound-norwegian/n02091467_141.jpg")
    DogBreedImagesScreenContent(
        state = DogBreedState(isLoading = false,
        randomDogBreedImages = listOfImages))
}

@Preview(showBackground = true)
@Composable
fun DogBreedImagesScreenContentPreview_Error() {
    DogBreedImagesScreenContent(
        state = DogBreedState(isLoading = false,
            error = "Server Error")
    )
}