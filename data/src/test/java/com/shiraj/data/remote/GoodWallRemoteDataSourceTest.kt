package com.shiraj.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shiraj.data.getDummyQuestions
import com.shiraj.data.services.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.exceptions.base.MockitoException
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GoodWallRemoteDataSourceTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var retrofit: Retrofit

    @Mock
    lateinit var apiService: ApiService

    private lateinit var goodWallRemoteDataSource: GoodWallRemoteDataSource

    @Before
    fun setup() {
        goodWallRemoteDataSource = GoodWallRemoteDataSource(apiService, retrofit)
    }

    @Test
    fun `Given Questions When fetchQuestions returns Success`() = runBlocking {
        //GIVEN
        val givenQuestions = getDummyQuestions()
        Mockito.`when`(apiService.getQuestions()).thenReturn(Response.success(givenQuestions))
        //WHEN
        val fetchQuestions = goodWallRemoteDataSource.fetchQuestions()
        //THEN
        assert(fetchQuestions.data?.size == givenQuestions.size)
    }

    @Test
    fun `Given Questions When fetchQuestions returns Error`() = runBlocking {
        //GIVEN
        val mockitoException = MockitoException("Unknown Error")
        Mockito.`when`(apiService.getQuestions()).thenThrow(mockitoException)
        //WHEN
        val fetchedQuestions = goodWallRemoteDataSource.fetchQuestions()
        //THEN
        assert(fetchedQuestions.message == "Unknown Error")
    }

    @Test
    fun `Given Questions When fetchQuestions return Server Error`() = runBlocking {
        //GIVEN
        Mockito.`when`(apiService.getQuestions())
            .thenReturn(Response.error(500, "".toResponseBody()))
        //WHEN
        val fetchedQuestions = goodWallRemoteDataSource.fetchQuestions()
        //THEN
        assert(fetchedQuestions.message == "Unknown Error")
    }
}