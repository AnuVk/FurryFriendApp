package com.anuvk.mvvmhiltcompose.data.repository

import com.anuvk.furryfriendapp.domain.entity.result.Result
import com.anuvk.furryfriendapp.domain.error.DataError
import com.anuvk.mvvmhiltcompose.data.remote.FurryFriendApi
import com.anuvk.mvvmhiltcompose.data.remote.toDomain
import com.anuvk.mvvmhiltcompose.domain.model.BreedsDomain
import com.anuvk.mvvmhiltcompose.domain.repository.DogBreedRepository
import com.anuvk.mvvmhiltcompose.domain.usecase.DogBreedsResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DogBreedRepositoryImpl @Inject constructor(
    private val api: FurryFriendApi) : DogBreedRepository {

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


