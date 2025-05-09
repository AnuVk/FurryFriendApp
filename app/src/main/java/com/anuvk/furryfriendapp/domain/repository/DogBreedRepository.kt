package com.anuvk.mvvmhiltcompose.domain.repository

import com.anuvk.mvvmhiltcompose.domain.model.BreedsDomain
import kotlinx.coroutines.flow.Flow

interface DogBreedRepository {
    suspend fun getAllBreeds(): Flow<List<BreedsDomain>>
}