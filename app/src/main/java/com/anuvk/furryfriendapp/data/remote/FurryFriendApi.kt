package com.anuvk.furryfriendapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface FurryFriendApi {

    companion object {
        const val BASE_URL = "https://dog.ceo"
    }

    @GET("/api/breeds/list")
    suspend fun getAllBreeds(): BreedResponse

    @GET("/api/breed/{breedName}/images/random/{imageCount}")
    suspend fun getBreedImages(
        @Path("breedName") breedName: String,
        @Path("imageCount") imageCount: Int): BreedImageResponse
}