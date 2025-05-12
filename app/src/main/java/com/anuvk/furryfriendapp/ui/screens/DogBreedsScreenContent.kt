package com.anuvk.furryfriendapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anuvk.furryfriendapp.domain.model.BreedsDomain
import com.anuvk.furryfriendapp.presentation.state.DogBreedState
import com.anuvk.furryfriendapp.utils.convertFirstCharToCapital

@Composable
fun DogBreedsScreenContent(
    state: DogBreedState,
    onBreedClick: (String) -> Unit = {}
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
                Text("Sorry Your furry friend is napping") // Show error message
            }
            state.breedsDomainList.isNotEmpty() -> {
                        DogBreedCategorizedLazyColumn(
                            listOfCategorizedBreeds = state.breedsDomainList,
                            onItemClick = onBreedClick
                        )
                }
            // Optional: Handle empty state
            else -> {
                Text("No breeds available.")
            }
        }
    }
}

@Composable
private fun DogCategoryHeader(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)
    )
}

@Composable
private fun DogCategoryItem(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Text(
        text = text.convertFirstCharToCapital(),
        fontSize = 14.sp,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    )
}

@Composable
private fun DogBreedCategorizedLazyColumn(
    listOfCategorizedBreeds: List<BreedsDomain>,
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
    ) {
        listOfCategorizedBreeds.forEach { category ->
            stickyHeader {
                DogCategoryHeader(category.groupName)
            }
            items(category.listOfBreeds) { text ->
                DogCategoryItem(text, onClick = { onItemClick(text) })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DogBreedsScreenContentPreview_Loading() {
    DogBreedsScreenContent(
        state = DogBreedState(isLoading = true),
        onBreedClick = {})
}

@Preview(showBackground = true)
@Composable
fun DogBreedsScreenContentPreview_Success() {
    val sampleBreeds = listOf(
        BreedsDomain("A", listOfBreeds = listOf("aaa", "ajhdjkdh")),
        BreedsDomain("B", listOfBreeds = listOf("bbb", "ajhdjkdh")))

    DogBreedsScreenContent(
        state = DogBreedState(isLoading = false,
        breedsDomainList = sampleBreeds),
        onBreedClick = {})
}

@Preview(showBackground = true)
@Composable
fun DogBreedsScreenContentPreview_Error() {
    DogBreedsScreenContent(
        state = DogBreedState(isLoading = false,
            error = "Server Error"),
        onBreedClick = {}
    )
}