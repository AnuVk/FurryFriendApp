package com.anuvk.furryfriendapp.presentation.viewmodel

import app.cash.turbine.test
import com.anuvk.furryfriendapp.TestCoroutineRule
import com.anuvk.furryfriendapp.domain.entity.result.Result
import com.anuvk.furryfriendapp.domain.error.DataError
import com.anuvk.furryfriendapp.domain.model.BreedsDomain
import com.anuvk.furryfriendapp.domain.model.DogBreedImagesDomain
import com.anuvk.furryfriendapp.domain.usecase.DogBreedRandomImagesUseCase
import com.anuvk.furryfriendapp.domain.usecase.GetAllDogBreedsUseCase
import com.anuvk.furryfriendapp.presentation.state.DogBreedState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName


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
 @DisplayName("onLoading initial state should be triggered")
 fun `given view is initialised then initial state should be set`() = runTest {

  viewModel.state.test {
   assertEquals(
    DogBreedState(
     isLoading = false,
     breedsDomainList = emptyList(),
     error = null,
     randomDogBreedImages = emptyList()
    ), awaitItem()
   )

  }
 }


 @Test
 @DisplayName("When loadDogBreeds is success then return list of dog breeds")
 fun `given loadDogBreeds is success then success state is triggered with correct data`() =
  runTest {
   val mockListOfBreeds = listOf(
    BreedsDomain(groupName = "A", listOfBreeds = listOf("Andn", "Avjdh")),
    BreedsDomain(groupName = "B", listOfBreeds = listOf("Bssds", "Bzaa")),
    BreedsDomain(groupName = "C", listOfBreeds = listOf("Chisk", "Cijjs"))
   )

   coEvery { getAllDogBreedsUseCase.invoke() } returns flowOf(Result.Success(mockListOfBreeds))

   //initial load
   viewModel.state.test {

    awaitItem()

    //When
    viewModel.loadAllDogBreeds()

    assertEquals(
     DogBreedState(
      isLoading = true,
      breedsDomainList = emptyList(),
      error = null,
      randomDogBreedImages = emptyList()
     ), awaitItem()
    )

    assertEquals(
     DogBreedState(
      isLoading = false,
      breedsDomainList = mockListOfBreeds,
      error = null,
      randomDogBreedImages = emptyList()
     ), awaitItem()
    )
    expectNoEvents()

   }

  }

 @Test
 @DisplayName("when loadAll DogBreeds fails then return error")
 fun `given loadAllDogBreeds fails then error state is triggered`() = runTest {
  val expectedErrorMessage = "Please try again later"

  coEvery { getAllDogBreedsUseCase() } returns flowOf(Result.Error(DataError.Network.ServerError("Some server error")))

  viewModel.state.test {
   awaitItem()

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
     error = expectedErrorMessage,
     randomDogBreedImages = emptyList()
    ), awaitItem()
   )

   expectNoEvents()
  }
 }


 @Test
 @DisplayName("when getRandomImageByBreed is success then return list of images")
 fun `given getRandomImageByBreed is success then success state is triggered with correct data`() =
  runTest {

   val breedName = "poodle"
   val numberOfImages = 5
   val mockImages = listOf("img1.jpg", "img2.jpg")

   val mockDogBreedImagesDomain = DogBreedImagesDomain(listOfDogBreedImages = mockImages)

   coEvery {
    getDogBreedRandomImagesUseCase(
     breedName,
     numberOfImages
    )
   } returns flowOf(Result.Success(mockDogBreedImagesDomain))

   viewModel.state.test {
    assertEquals(
     DogBreedState(
      isLoading = false,
      breedsDomainList = emptyList(),
      error = null,
      randomDogBreedImages = emptyList()
     ), awaitItem()
    )

    viewModel.getRandomImageByBreed(breedName, numberOfImages)

    assertEquals(
     DogBreedState(
      isLoading = true,
      breedsDomainList = emptyList(),
      error = null,
      randomDogBreedImages = emptyList()
     ), awaitItem()
    )

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

 @Test
 @DisplayName("when getRandomImageByBreed fails then return error is thrown")
 fun `given getRandomImageByBreed fails then error state is triggered`() = runTest {
  // Arrange
  val breedName = "poodle"
  val numberOfImages = 5
  val expectedError = "Something went wrong"

  coEvery {
   getDogBreedRandomImagesUseCase(
    breedName,
    numberOfImages
   )
  } returns flowOf(Result.Error(DataError.Network.EmptyResponse("Something else went wrong")))

  viewModel.state.test {

   awaitItem()
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
     error = expectedError,
     randomDogBreedImages = emptyList()
    ), awaitItem()
   )

   expectNoEvents()
  }
 }
}