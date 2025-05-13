package com.anuvk.furryfriendapp.presentation.viewmodel

import app.cash.turbine.test
import com.anuvk.furryfriendapp.TestCoroutineRule
import com.anuvk.furryfriendapp.domain.model.BreedsDomain
import com.anuvk.furryfriendapp.domain.model.DogBreedImagesDomain
import com.anuvk.furryfriendapp.domain.usecase.DogBreedRandomImagesUseCase
import com.anuvk.furryfriendapp.domain.usecase.GetAllDogBreedsUseCase
import com.anuvk.furryfriendapp.presentation.state.DogBreedState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import com.anuvk.furryfriendapp.domain.entity.result.Result


@ObsoleteCoroutinesApi
class DogBreedViewModelTest {

 @get:Rule
 val mainCoroutineRule = TestCoroutineRule()

 private val getAllDogBreedsUseCase: GetAllDogBreedsUseCase = mockk()

 private val getDogBreedRandomImagesUseCase: DogBreedRandomImagesUseCase = mockk()

 private lateinit var viewModel: DogBreedViewModel

 @Before
 fun setUp() {
  viewModel = DogBreedViewModel(getAllDogBreedsUseCase, getDogBreedRandomImagesUseCase)
 }

 @Test
 @DisplayName("When loadDogBreeds is success then return list of dog breeds")
 fun `given loadDogBreeds is success then return list of dog breeds`() = runTest {
  val mockListOfBreeds = listOf(
   BreedsDomain(groupName = "A", listOfBreeds = listOf("Andn", "Avjdh")),
   BreedsDomain(groupName = "B", listOfBreeds = listOf("Bssds", "Bzaa")),
   BreedsDomain(groupName = "C", listOfBreeds = listOf("Chisk", "Cijjs"))
  )

  coEvery { getAllDogBreedsUseCase.invoke() } returns flowOf(Result.Success(mockListOfBreeds))

  viewModel.state.test {
   assertEquals(
    DogBreedState(
     isLoading = false,
     breedsDomainList = emptyList(),
     error = null,
     randomDogBreedImages = emptyList()
    ), awaitItem()
   )

   viewModel.loadAllDogBreeds()

   assertEquals(
    DogBreedState(
     isLoading = true,
     breedsDomainList = emptyList(),
     error = null,
     randomDogBreedImages = emptyList()
    ), awaitItem())
   assertEquals(
    DogBreedState(
     isLoading = false,
     breedsDomainList = mockListOfBreeds,
     error = null,
     randomDogBreedImages = emptyList()
    ), awaitItem()
   )

  }

 }

 @Test
 @DisplayName("loadAllDogBreeds - error")
 fun `loadAllDogBreeds should update state with error message on error`() = runTest {
  // Arrange
  val errorMessage = "Please try again later"
  // Mock the Use Case to return an error Flow
  coEvery { getAllDogBreedsUseCase() } returns flowOf(Result.Error(androidx.credentials.exceptions.domerrors.DataError.Network.ServerError))

  // Use Turbine to test the state flow
  viewModel.state.test {
   // Initial state assertion
   assertEquals(
    DogBreedState(
     isLoading = false,
     breedsDomainList = emptyList(),
     error = null,
     randomDogBreedImages = emptyList()
    ), awaitItem()
   )

   // Act
   viewModel.loadAllDogBreeds()

   // Assert loading state
   assertEquals(
    DogBreedState(
     isLoading = true,
     breedsDomainList = emptyList(),
     error = null,
     randomDogBreedImages = emptyList()
    ), awaitItem()
   )

   // Assert error state
   assertEquals(
    DogBreedState(
     isLoading = false,
     breedsDomainList = emptyList(),
     error = errorMessage,
     randomDogBreedImages = emptyList()
    ), awaitItem()
   )

   expectNoEvents()
  }
 }

 @Test
 @DisplayName("getRandomImageByBreed - success with data")
 fun `getRandomImageByBreed should update state with images on success`() = runTest {
  // Arrange
  val breedName = "poodle"
  val numberOfImages = 5
  val mockImages = listOf("img1.jpg","img2.jpg")

  val mockDogBreedImagesDomain = DogBreedImagesDomain(listOfDogBreedImages = mockImages)

  // Mock the Use Case to return a successful Flow
  coEvery {
   getDogBreedRandomImagesUseCase(
    breedName,
    numberOfImages
   )
  } returns flowOf(Result.Success(mockDogBreedImagesDomain))

  // Use Turbine to test the state flow
  viewModel.state.test {
   // Initial state assertion
   assertEquals(
    DogBreedState(
     isLoading = false,
     breedsDomainList = emptyList(),
     error = null,
     randomDogBreedImages = emptyList()
    ), awaitItem()
   )

   // Act
   viewModel.getRandomImageByBreed(breedName, numberOfImages)

   // Assert loading state
   assertEquals(
    DogBreedState(
     isLoading = true,
     breedsDomainList = emptyList(),
     error = null,
     randomDogBreedImages = emptyList()
    ), awaitItem()
   )

   // Assert success state with images
   assertEquals(
    DogBreedState(
     isLoading = false,
     breedsDomainList = emptyList(),
     error = null,
     randomDogBreedImages = mockImages
    ), awaitItem()
   )

   expectNoEvents()
  }
 }
}