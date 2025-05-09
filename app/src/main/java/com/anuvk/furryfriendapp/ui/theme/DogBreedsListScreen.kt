package com.anuvk.furryfriendapp.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.anuvk.furryfriendapp.domain.model.BreedsDomain

@Composable
fun DogBreedsListScreen(listOfDogBreeds: List<BreedsDomain>) {

    LazyColumn {
        items(listOfDogBreeds) { item ->
            DogBreedItem(item)
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
                    style = MaterialTheme.typography.body1
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DogBreedsListScreenPreview() {
    FurryFriendAppTheme {
        DogBreedsListScreen(listOf(
            BreedsDomain("buhund", "https://images.dog.ceo/breeds/affenpinscher/n02110627_7013.jpg"),
            BreedsDomain("bullterrier", "https://images.dog.ceo/breeds/affenpinscher/n02110627_7013.jpg"),
            BreedsDomain("collie", "https://images.dog.ceo/breeds/affenpinscher/n02110627_7013.jpg"),
            )
        )
    }
}