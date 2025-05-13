package com.anuvk.furryfriendapp.data.repository

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
    @DisplayName("when getAllBreeds is success, then it should return a list of dog breeds")
    fun `when getAllBreeds is success, then it should return a list of dog breeds`() = runTest {
        val mockBreedResponse = BreedResponse(breeds = listOf("eskimo", "papillon", "boxer"), status = "success")

        coEvery { api.getAllBreeds() } returns mockBreedResponse

      val result =  repository.getAllBreeds().first()
      advanceUntilIdle()

      assertTrue(result is Result.Success)
      val responseData = (result as Result.Success).data
        responseData
      assertEquals(3, responseData.size)
      assertEquals("boxer", responseData.first().listOfBreeds.first())
      assertEquals("papillon        ", responseData.last().listOfBreeds.last())

//       repository.getAllBreeds().test {
//            val result = awaitItem()
//
//           assertTrue(result is Result.Success)
//           val responseData = (result as Result.Success).data
//           assertEquals(2, responseData.size)
//           assertEquals("eskimo", responseData.first())
//           assertEquals("boxer", responseData.last())
//
//           awaitComplete()
//
//       }
    }
}