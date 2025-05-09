package com.anuvk.furryfriendapp.data.remote

import retrofit2.http.GET

interface FurryFriendApi {
    @GET("/api/breeds/list/all")
    suspend fun getAllBreeds(): BreedResponse

    companion object {
        const val BASE_URL = "https://dog.ceo"
    }
}