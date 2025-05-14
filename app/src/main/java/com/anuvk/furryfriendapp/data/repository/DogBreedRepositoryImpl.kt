package com.anuvk.furryfriendapp.data.repository

import com.anuvk.furryfriendapp.data.remote.FurryFriendApi
import com.anuvk.furryfriendapp.data.remote.toDomain
import com.anuvk.furryfriendapp.domain.entity.result.Result
import com.anuvk.furryfriendapp.domain.error.DataError
import com.anuvk.furryfriendapp.domain.repository.DogBreedRepository
import com.anuvk.furryfriendapp.domain.usecase.DogBreedsImageResult
import com.anuvk.furryfriendapp.domain.usecase.DogBreedsResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DogBreedRepositoryImpl @Inject constructor(
    private val api: FurryFriendApi,
    private val dispatcher: CoroutineDispatcher
) : DogBreedRepository {

    override suspend fun getAllBreeds() = flow<DogBreedsResult> {
        runCatching {
            api.getAllBreeds()
        }.onFailure { exception ->
            emit(Result.Error(DataError.Network.ServerError("Server error")))
        }.onSuccess { response ->
            response.status?.let { responseStatus ->
                if (responseStatus == "success" && !response.breeds.isNullOrEmpty()) {
                    emit(Result.Success(response.toDomain()))
                } else {
                    emit(Result.Error(DataError.Network.ServerError("Server error")))
                }
            }
        }

    }.flowOn(dispatcher)

    override suspend fun getRandomNumberOfImagesByBreed(
        breedName: String,
        numberOfImages: Int
    ) = flow<DogBreedsImageResult> {
        runCatching {
            api.getBreedImages(breedName = breedName, imageCount = numberOfImages)
        }.onFailure { exception ->
            println(">>> exception : $exception")
            emit(Result.Error(DataError.Network.ServerError("Server error")))
        }.onSuccess { response ->
            response.status?.let { responseStatus ->
                if (responseStatus == "success" && !response.message.isNullOrEmpty()) {
                    emit(Result.Success(response.toDomain()))
                } else {
                    emit(Result.Error(DataError.Network.EmptyResponse("Server error")))
                }
        }
        }
    }.flowOn(dispatcher)

}


