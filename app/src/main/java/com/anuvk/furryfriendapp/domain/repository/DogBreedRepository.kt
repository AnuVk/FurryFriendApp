package com.anuvk.mvvmhiltcompose.domain.repository

import com.anuvk.furryfriendapp.domain.entity.result.Result
import com.anuvk.furryfriendapp.domain.error.DataError
import com.anuvk.mvvmhiltcompose.domain.model.BreedsDomain
import com.anuvk.mvvmhiltcompose.domain.usecase.DogBreedsResult
import kotlinx.coroutines.flow.Flow

interface DogBreedRepository {
    suspend fun getAllBreeds(): Flow<DogBreedsResult>
}