package dev.carlodips.itunesmusicsearch.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.carlodips.itunesmusicsearch.data.remote.SearchAPI
import dev.carlodips.itunesmusicsearch.source.SearchRepository
import dev.carlodips.itunesmusicsearch.source.SearchRepositoryImpl
import dev.carlodips.itunesmusicsearch.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSearchAPI(): SearchAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(SearchAPI::class.java)

    }

    @Provides
    @Singleton
    fun provideSearchRepository(
        api: SearchAPI
    )= SearchRepositoryImpl(api) as SearchRepository
}