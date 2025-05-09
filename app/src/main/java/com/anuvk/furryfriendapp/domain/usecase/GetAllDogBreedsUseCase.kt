package com.anuvk.furryfriendapp.domain.usecase

import com.anuvk.furryfriendapp.domain.entity.result.Result
import com.anuvk.furryfriendapp.domain.error.DataError
import com.anuvk.furryfriendapp.domain.model.BreedsDomain
import com.anuvk.furryfriendapp.domain.repository.DogBreedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias DogBreedsResult = Result<List<BreedsDomain>, DataError.Network>

class GetAllDogBreedsUseCase @Inject constructor(private val repository: DogBreedRepository
) {
    suspend operator fun invoke(): Flow<DogBreedsResult> {
        return repository.getAllBreeds()
    }
}