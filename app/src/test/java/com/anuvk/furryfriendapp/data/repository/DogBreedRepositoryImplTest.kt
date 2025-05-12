package com.anuvk.furryfriendapp.data.repository

import app.cash.turbine.test
import com.anuvk.furryfriendapp.data.remote.BreedResponse
import com.anuvk.furryfriendapp.data.remote.FurryFriendApi
import com.anuvk.furryfriendapp.domain.entity.result.Result
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class DogBreedRepositoryImplTest {

    @MockK
    lateinit var api: FurryFriendApi

    private lateinit var repository: DogBreedRepositoryImpl

    @BeforeEach
    fun setUp() {
        repository = DogBreedRepositoryImpl(api)
    }

    @Test
    @DisplayName("when getAllBreeds is success, then it should return a list of dog breeds")
    fun `when getAllBreeds is success, then it should return a list of dog breeds`() = runTest {
        val mockBreedResponse = BreedResponse(breeds = listOf("eskimo", "papillon", "boxer"), status = "success")

        coEvery { api.getAllBreeds() } returns mockBreedResponse

       repository.getAllBreeds().test {
            val result = awaitItem()

           assertTrue(result is Result.Success)
           val responseData = (result as Result.Success).data
           assertEquals(2, responseData.size)
           assertEquals("eskimo", responseData.first())
           assertEquals("boxer", responseData.last())

           awaitComplete()

       }
    }
}