package com.anuvk.furryfriendapp.domain.repository

import com.anuvk.furryfriendapp.domain.usecase.DogBreedsImageResult
import com.anuvk.furryfriendapp.domain.usecase.DogBreedsResult
import kotlinx.coroutines.flow.Flow

interface DogBreedRepository {
    suspend fun getAllBreeds(): Flow<DogBreedsResult>

    suspend fun getRandomNumberOfImagesByBreed(breedName: String,
                                               numberOfImages: Int): Flow<DogBreedsImageResult>
}