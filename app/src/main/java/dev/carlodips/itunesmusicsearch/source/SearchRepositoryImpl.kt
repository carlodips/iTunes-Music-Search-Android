package dev.carlodips.itunesmusicsearch.source

import dev.carlodips.itunesmusicsearch.data.remote.SearchAPI
import dev.carlodips.itunesmusicsearch.data.remote.responses.SearchResponseModel
import dev.carlodips.itunesmusicsearch.utils.NetworkResult
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchAPI: SearchAPI
): SearchRepository{
    override suspend fun search(term: String): NetworkResult<SearchResponseModel> {
        return try {
            val response = searchAPI.search(
                HashMap<String, String>().apply {
                    put("term", term)
                    put("country", "PH")
                    put("media", "music")
                    put("entity", "song")
                    put("attribute", "songTerm")
                    put("limit", "60")
                }
            )

            if(response.isSuccessful) {
                response.body()?.let {
                    return@let NetworkResult.success(it)
                } ?: NetworkResult.error("unexpected error", null)
            } else {
                NetworkResult.error("unexpected error", null)
            }
        } catch (e: Exception) {
            NetworkResult.error(e.message ?: "error", null)
        }

    }
}