package com.anuvk.mvvmhiltcompose.data.repository

import com.anuvk.mvvmhiltcompose.data.remote.FurryFriendApi
import com.anuvk.mvvmhiltcompose.data.remote.toDomain
import com.anuvk.mvvmhiltcompose.domain.model.BreedsDomain
import com.anuvk.mvvmhiltcompose.domain.repository.BreedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BreedRepositoryImpl @Inject constructor(
    private val api: FurryFriendApi) : BreedRepository {

    override suspend fun getAllBreeds(): Flow<List<BreedsDomain>> =
        flow {
            val response = api.getAllBreeds()
            emit(response)
        }.map { it.toDomain() }
}

