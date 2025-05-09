package com.anuvk.furryfriendapp.domain.repository

import com.anuvk.furryfriendapp.domain.usecase.DogBreedsResult
import kotlinx.coroutines.flow.Flow

interface DogBreedRepository {
    suspend fun getAllBreeds(): Flow<DogBreedsResult>
}