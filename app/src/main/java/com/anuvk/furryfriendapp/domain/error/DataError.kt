package com.anuvk.furryfriendapp.domain.error

sealed interface DataError : Error {
    sealed class Network: DataError {
        abstract val message: String?
        data class ServerError(override val message: String?): Network()

        data class EmptyResponse(override val message: String?): Network()
    }


}