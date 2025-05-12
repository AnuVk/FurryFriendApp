package com.anuvk.furryfriendapp.data.repository

import com.anuvk.furryfriendapp.data.remote.FurryFriendApi
import com.anuvk.furryfriendapp.data.remote.toDomain
import com.anuvk.furryfriendapp.domain.entity.result.Result
import com.anuvk.furryfriendapp.domain.error.DataError
import com.anuvk.furryfriendapp.domain.model.BreedsDomain
import com.anuvk.furryfriendapp.domain.repository.DogBreedRepository
import com.anuvk.furryfriendapp.domain.usecase.DogBreedsImageResult
import com.anuvk.furryfriendapp.domain.usecase.DogBreedsResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DogBreedRepositoryImpl @Inject constructor(
    private val api: FurryFriendApi
) : DogBreedRepository {

    override suspend fun getAllBreeds() = flow<DogBreedsResult> {
        val response = api.getAllBreeds()
        response.status?.let { responseStatus ->
            if (responseStatus == "success" && !response.breeds.isNullOrEmpty()) {
                emit(Result.Success(response.toDomain()))
            } else {
                emit(Result.Error(DataError.Network.ServerError("Server error")))
            }
        }
    }

    override suspend fun getRandomNumberOfImagesByBreed(
        breedName: String,
        numberOfImages: Int
    ) = flow<DogBreedsImageResult> {
        val response = api.getBreedImages(breedName = breedName, imageCount = numberOfImages)
        response.status?.let { responseStatus ->
            if (responseStatus == "success" && !response.message.isNullOrEmpty()) {
            emit(Result.Success(response.toDomain()))
        } else {
            emit(Result.Error(DataError.Network.ServerError("Server error")))
        }
        }
    }

}


