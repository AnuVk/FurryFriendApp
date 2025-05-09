package com.anuvk.furryfriendapp.domain.entity.result

import com.anuvk.furryfriendapp.domain.error.Error

typealias AppError = Error

sealed interface Result<out D, out E: AppError> {
    data class Success<out D>(val data: D): Result<D, Nothing>
    data class Error<out E: AppError>(val error: E): Result<Nothing, E>
}
