package com.anuvk.furryfriendapp.domain.usecase

import com.anuvk.furryfriendapp.domain.entity.result.Result
import com.anuvk.furryfriendapp.domain.error.DataError
import com.anuvk.furryfriendapp.domain.model.DogBreedImagesDomain
import com.anuvk.furryfriendapp.domain.repository.DogBreedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias DogBreedsImageResult = Result<DogBreedImagesDomain, DataError.Network>

class DogBreedRandomImagesUseCase @Inject constructor(private val repository: DogBreedRepository
) {
    suspend operator fun invoke(breedName: String, numberOfImages: Int): Flow<DogBreedsImageResult> {
        return repository.getRandomNumberOfImagesByBreed(breedName, numberOfImages)
    }
}