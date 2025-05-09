package com.anuvk.mvvmhiltcompose.domain.usecase

import com.anuvk.mvvmhiltcompose.domain.model.BreedsDomain
import com.anuvk.mvvmhiltcompose.domain.repository.DogBreedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllDogBreedsUseCase @Inject constructor(private val repository: DogBreedRepository
) {
    suspend operator fun invoke(): Flow<List<BreedsDomain>> {
        return repository.getAllBreeds()
    }
}