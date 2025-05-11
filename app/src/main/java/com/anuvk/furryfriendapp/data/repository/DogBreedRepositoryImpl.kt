package com.anuvk.furryfriendapp.data.repository

import com.anuvk.furryfriendapp.data.remote.FurryFriendApi
import com.anuvk.furryfriendapp.domain.entity.result.Result
import com.anuvk.furryfriendapp.domain.error.DataError
import com.anuvk.furryfriendapp.domain.model.BreedsDomain
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
                val breeds = response.breeds
                println(">>> size : ${breeds?.size}")
                val listOfBreeds = mutableListOf<BreedsDomain>()
                breeds?.forEach { breed ->
                    val imageUrl = getBreedImages(breed)
                    listOfBreeds.add(BreedsDomain(breedName = breed, imageUrl = imageUrl))
                }
                emit(Result.Success(listOfBreeds))
            } else {
                emit(Result.Error(DataError.Network.ServerError("Server error")))
            }
        }
    }

    private suspend fun getBreedImages(breedName: String): String? {
        return try {
            val imageResp = api.getBreedImages(breedName)
            imageResp.status?.let { status ->
                if (status == "success") {
                    return imageResp.message?.first()
                } else {
                    null
                }
            }
        } catch (e: Exception) {
            null
        }
    }
}


