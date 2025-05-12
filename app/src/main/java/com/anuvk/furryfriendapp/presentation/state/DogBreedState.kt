package com.anuvk.furryfriendapp.presentation.state

import com.anuvk.furryfriendapp.domain.model.BreedsDomain

data class DogBreedState(
    val breedsDomainList: List<BreedsDomain> = emptyList(),
    val randomDogBreedImages: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
