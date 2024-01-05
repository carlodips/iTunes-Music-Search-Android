package dev.carlodips.itunesmusicsearch.data.remote.responses

data class SearchResponseModel(
    val resultCount: Int,
    val results: List<SearchResult>
)