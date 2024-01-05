package dev.carlodips.itunesmusicsearch.source

import dev.carlodips.itunesmusicsearch.data.remote.responses.SearchResponseModel
import dev.carlodips.itunesmusicsearch.utils.NetworkResult

interface SearchRepository {
    suspend fun search(term: String): NetworkResult<SearchResponseModel>
}