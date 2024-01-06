package dev.carlodips.itunesmusicsearch.ui.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.carlodips.itunesmusicsearch.data.remote.responses.SearchResult
import dev.carlodips.itunesmusicsearch.source.SearchRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {

    private var resultsList = ArrayList<SearchResult>().toMutableStateList()

    val uiModel = SearchUIModel(
        input = mutableStateOf(""),
        results = resultsList
    )


    fun search() {
        if (uiModel.input.value.isBlank()) {
            return
        }

        viewModelScope.launch {
            val response = repository.search(uiModel.input.value)

            response.data?.results?.let {
                resultsList.clear()
                resultsList.addAll(it)
            }
        }
    }
}