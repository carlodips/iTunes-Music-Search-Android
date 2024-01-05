package dev.carlodips.itunesmusicsearch.data.remote.request

data class SearchRequestModel(
    val term: String,
    val country: String,
    val media: String,
    val entity: String,
    val attribute: String,
    val limit: String
)