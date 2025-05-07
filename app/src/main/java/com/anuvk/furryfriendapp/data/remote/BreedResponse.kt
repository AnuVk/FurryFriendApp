package com.anuvk.mvvmhiltcompose.data.remote

import com.google.gson.annotations.SerializedName

data class BreedResponse(
    @SerializedName("message")
    val breeds: Map<String, List<String>>,
    @SerializedName("status")
    val status: String
)
