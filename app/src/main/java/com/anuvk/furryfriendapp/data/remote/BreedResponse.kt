package com.anuvk.furryfriendapp.data.remote

import com.google.gson.annotations.SerializedName

data class BreedResponse(
    @SerializedName("message") val breeds: List<String>?,
    @SerializedName("status") val status: String?
)
