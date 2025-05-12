package com.anuvk.furryfriendapp.data.remote

import com.anuvk.furryfriendapp.domain.model.BreedsDomain
import com.anuvk.furryfriendapp.domain.model.DogBreedImagesDomain

fun BreedResponse.toDomain(): List<BreedsDomain> {
    return breeds!!.let { allbreeds ->
         allbreeds.groupBy { it.first().uppercase() }.toSortedMap().map {
             BreedsDomain(
                 groupName = it.key.toString(),
                 listOfBreeds = it.value.toList())
         }
    }
}

fun BreedImageResponse.toDomain(): DogBreedImagesDomain {
    return DogBreedImagesDomain(listOfDogBreedImages = this.message!!)
}