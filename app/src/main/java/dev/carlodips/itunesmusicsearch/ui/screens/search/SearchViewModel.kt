package dev.carlodips.itunesmusicsearch.ui.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.carlodips.itunesmusicsearch.data.remote.responses.SearchResult
import dev.carlodips.itunesmusicsearch.source.SearchRepository
import dev.carlodips.itunesmusicsearch.utils.Event
import dev.carlodips.itunesmusicsearch.utils.toEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {
    private var job: Job? = null

    private var resultsList = ArrayList<SearchResult>().toMutableStateList()

    val ldNavigateToDetails = MutableLiveData<Event<SearchResult>>()

    val input = mutableStateOf("")

    private val _uiState =
        MutableStateFlow<SearchResultsUIState>(SearchResultsUIState.Empty)
    val uiState: StateFlow<SearchResultsUIState>
        get() = _uiState.asStateFlow()


    fun search() {
        if (input.value.isBlank()) return

        if (job?.isActive == true) return

        job = viewModelScope.launch {
            withContext(Dispatchers.Main) {
                _uiState.update {
                    SearchResultsUIState.Loading
                }
            }


            withContext(Dispatchers.IO) {
                val response = repository.search(input.value)

                response.data?.results?.let {
                    resultsList.clear()
                    resultsList.addAll(it)

                    _uiState.update {
                        SearchResultsUIState.ShowResults(
                            results = resultsList,
                            navigateToDetails = {
                                ldNavigateToDetails.value = it.toEvent()
                            }
                        )
                    }
                }
            }

        }
    }
}