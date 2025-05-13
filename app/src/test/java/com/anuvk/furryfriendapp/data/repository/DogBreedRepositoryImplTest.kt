package com.anuvk.furryfriendapp.data.repository

import com.anuvk.furryfriendapp.data.remote.BreedImageResponse
import com.anuvk.furryfriendapp.data.remote.BreedResponse
import com.anuvk.furryfriendapp.data.remote.FurryFriendApi
import com.anuvk.furryfriendapp.domain.entity.result.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName

@OptIn(ExperimentalCoroutinesApi::class)
class DogBreedRepositoryImplTest {

    private val api: FurryFriendApi = mockk()

    private lateinit var repository: DogBreedRepositoryImpl

    @Before
    fun setUp() {
        repository = DogBreedRepositoryImpl(api, UnconfinedTestDispatcher())
    }

    @Test
    @DisplayName("when getAllBreeds is success, then it should emit success and return a list of dog breeds")
    fun `when getAllBreeds is success, then it should return a list of dog breeds`() = runTest {
        val mockBreedResponse = BreedResponse(breeds = listOf("eskimo", "papillon", "boxer"), status = "success")

        coEvery { api.getAllBreeds() } returns mockBreedResponse

        val result =  repository.getAllBreeds().first()
        advanceUntilIdle()

        assertTrue(result is Result.Success)
        val responseData = (result as Result.Success).data
        assertEquals(3, responseData.size)
        assertEquals("boxer", responseData.first().listOfBreeds.first())
        assertEquals("papillon", responseData.last().listOfBreeds.last())
    }

    @Test
    @DisplayName("when getAllBreeds is success but empty list returns then it should emit error")
    fun `when getAllBreeds is success with empty list then emit error`() = runTest {
        val mockBreedResponse = BreedResponse(breeds = emptyList(), status = "success")

        coEvery { api.getAllBreeds() } returns mockBreedResponse

        val result =  repository.getAllBreeds().first()
        advanceUntilIdle()

        assertTrue(result is Result.Error)
        val errorMessage = (result as Result.Error).error.message
        assertEquals(errorMessage, "Server error")
    }

    @Test
    @DisplayName("when getAllBreeds fails, then it should emit error")
    fun `when getAllBreeds is fails then emit error`() = runTest {
        val mockBreedResponse = BreedResponse(breeds = emptyList(), status = "failure")

        coEvery { api.getAllBreeds() } returns mockBreedResponse

        val result =  repository.getAllBreeds().first()
        advanceUntilIdle()

        assertTrue(result is Result.Error)
        val errorMessage = (result as Result.Error).error.message
        assertEquals(errorMessage, "Server error")
    }



    @Test
    @DisplayName("when getImagesByBreed is success, then it should emit success and return a list of dog images")
    fun `when getImagesByBreed is called and is successful then emit success`() = runTest {
        val mockBreedImageResponse = BreedImageResponse(message = listOf("image1", "image2", "image3"), status = "success")

        coEvery { api.getBreedImages(any(), any()) } returns mockBreedImageResponse

        val result =  repository.getRandomNumberOfImagesByBreed("hound", 10).first()
        advanceUntilIdle()

        assertTrue(result is Result.Success)
        val responseData = (result as Result.Success).data
        assertEquals(3, responseData.listOfDogBreedImages.size)
        assertEquals("image1", responseData.listOfDogBreedImages.first())
        assertEquals("image2", responseData.listOfDogBreedImages[1])
        assertEquals("image3", responseData.listOfDogBreedImages.last())
    }


    @Test
    @DisplayName("when getImagesByBreed is success, then it should emit error")
    fun `when getImagesByBreed is success with empty list then emit error`() = runTest {
        val mockBreedImageResponse = BreedImageResponse(message = emptyList(), status = "success")

        coEvery { api.getBreedImages("husky", 10) } returns mockBreedImageResponse

        val result =  repository.getRandomNumberOfImagesByBreed("hound", 10).first()
        advanceUntilIdle()

        assertTrue(result is Result.Error)
        val errorMessage = (result as Result.Error).error.message
        assertEquals(errorMessage, "Server error")
    }

    @Test
    @DisplayName("when getImagesByBreed fails, then it should emit error")
    fun `when getImagesByBreed is fails then emit error`() = runTest {
        val mockBreedImageResponse = BreedImageResponse(message = emptyList(), status = "failure")

        coEvery { api.getBreedImages("husky", 10) } returns mockBreedImageResponse

        val result =  repository.getRandomNumberOfImagesByBreed("hound", 10).first()
        advanceUntilIdle()

        assertTrue(result is Result.Error)
        val errorMessage = (result as Result.Error).error.message
        assertEquals(errorMessage, "Server error")
    }


}