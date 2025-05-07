package com.anuvk.mvvmhiltcompose.domain.usecase

import com.anuvk.mvvmhiltcompose.data.remote.BreedResponse
import com.anuvk.mvvmhiltcompose.domain.model.BreedsDomain
import com.anuvk.mvvmhiltcompose.domain.repository.BreedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllBreedsUseCase @Inject constructor(private val repository: BreedRepository
) {
    suspend operator fun invoke(): Flow<List<BreedsDomain>> {
        return repository.getAllBreeds()
    }
}