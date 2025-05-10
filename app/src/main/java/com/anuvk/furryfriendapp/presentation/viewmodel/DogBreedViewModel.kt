package com.anuvk.furryfriendapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anuvk.furryfriendapp.domain.entity.result.Result
import com.anuvk.furryfriendapp.domain.error.DataError
import com.anuvk.furryfriendapp.domain.model.BreedsDomain
import com.anuvk.furryfriendapp.domain.usecase.GetAllDogBreedsUseCase
import com.anuvk.furryfriendapp.presentation.state.DogBreedState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DogBreedViewModel @Inject constructor(
    private val getAllDogBreedsUseCase: GetAllDogBreedsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DogBreedState())
    val state: StateFlow<DogBreedState> = _state


    fun loadAllDogBreeds() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            getAllDogBreedsUseCase().collect { result ->
                when (result) {
                    is Result.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                breedsDomainList = result.data) }
                    }

                    is Result.Error -> {
                        val errorMessage = when(result.error) {
                            is DataError.Network.ServerError -> "Please try again later"
                            is DataError.Network.EmptyResponse -> "Something went wrong"
                        }
                        _state.update {
                            it.copy(isLoading = false, error = errorMessage)
                        }
                    }
                }
            }
        }
    }
}