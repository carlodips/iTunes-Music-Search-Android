package dev.carlodips.itunesmusicsearch.data.remote

import dev.carlodips.itunesmusicsearch.data.remote.responses.SearchResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SearchAPI {
    @GET("/search")
    suspend fun search(
        @QueryMap map: Map<String, String>
    ): Response<SearchResponseModel>
}