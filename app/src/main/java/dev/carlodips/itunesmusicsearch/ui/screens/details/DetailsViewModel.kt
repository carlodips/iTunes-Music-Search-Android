package dev.carlodips.itunesmusicsearch.ui.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.carlodips.itunesmusicsearch.data.remote.responses.SearchResult
import dev.carlodips.itunesmusicsearch.source.SearchRepository
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: SearchRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val bean: SearchResult? =
        savedStateHandle.get<SearchResult>("result")

    val uiModel = DetailsUIModel(
        bean = bean!!
    )
}