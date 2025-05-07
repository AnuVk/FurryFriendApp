package com.anuvk.mvvmhiltcompose.data.remote

import com.anuvk.mvvmhiltcompose.domain.model.BreedsDomain


fun BreedResponse.toDomain(): List<BreedsDomain> {
    return breeds.keys.map { breedName -> BreedsDomain(breedName = breedName) }

}