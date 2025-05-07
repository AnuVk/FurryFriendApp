package com.anuvk.mvvmhiltcompose.presentation.di

import com.anuvk.mvvmhiltcompose.data.remote.FurryFriendApi
import com.anuvk.mvvmhiltcompose.data.repository.BreedRepositoryImpl
import com.anuvk.mvvmhiltcompose.domain.repository.BreedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providePlaceholderApi(okHttpClient: OkHttpClient): FurryFriendApi {
        return Retrofit.Builder()
            .baseUrl(FurryFriendApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(FurryFriendApi::class.java)
    }

    @Provides
    @Singleton
    fun providePostRepository(api: FurryFriendApi): BreedRepository {
        return BreedRepositoryImpl(api)
    }
}