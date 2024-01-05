package dev.carlodips.itunesmusicsearch.data.remote.responses

import com.google.gson.annotations.SerializedName

data class SearchResult(
    val trackName: String,
    val artistName: String,
    @SerializedName("artworkUrl100")
    val artworkThumbnail: String,
    val trackPrice: Int,
    val currency: String
)