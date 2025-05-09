package com.anuvk.furryfriendapp.data.repository

import com.anuvk.furryfriendapp.data.remote.FurryFriendApi
import com.anuvk.furryfriendapp.data.remote.toDomain
import com.anuvk.furryfriendapp.domain.entity.result.Result
import com.anuvk.furryfriendapp.domain.error.DataError
import com.anuvk.furryfriendapp.domain.repository.DogBreedRepository
import com.anuvk.furryfriendapp.domain.usecase.DogBreedsResult
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DogBreedRepositoryImpl @Inject constructor(
    private val api: FurryFriendApi
) : DogBreedRepository {

    override suspend fun getAllBreeds() = flow<DogBreedsResult> {

            val response = api.getAllBreeds()
            response.status?.let { responseStatus ->
                if (responseStatus == "success") {
                    emit(Result.Success(response.toDomain()))
                } else {
                    emit(Result.Error(DataError.Network.ServerError("Server error")))
                }
            }
        }
}


