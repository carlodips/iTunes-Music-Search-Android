package dev.carlodips.itunesmusicsearch.ui.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.carlodips.itunesmusicsearch.R
import dev.carlodips.itunesmusicsearch.data.remote.responses.SearchResult
import dev.carlodips.itunesmusicsearch.ui.composable.BaseCard
import dev.carlodips.itunesmusicsearch.ui.composable.CustomOutlinedTextField
import dev.carlodips.itunesmusicsearch.utils.collectAsStateLifecycleAware
import kotlinx.coroutines.flow.StateFlow

sealed class SearchResultsUIState {
    data object Loading : SearchResultsUIState()
    data object Empty : SearchResultsUIState()
    class ShowResults(
        val results: SnapshotStateList<SearchResult>,
        val navigateToDetails: (result: SearchResult) -> Unit
    ) : SearchResultsUIState()
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    input: MutableState<String>,
    onSearchClick: () -> Unit,
    uiState: StateFlow<SearchResultsUIState>
) {
    val stateUiState = uiState.collectAsStateLifecycleAware(
        SearchResultsUIState.Empty
    )

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Surface(modifier = modifier.wrapContentSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(24.dp))

            CustomOutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .focusRequester(focusRequester),
                textValue = input,
                label = stringResource(R.string.search_for_a_song)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = {
                    focusManager.clearFocus()
                    onSearchClick.invoke()
                }
            ) {
                Text(text = stringResource(R.string.search))
            }
            Spacer(modifier = Modifier.height(16.dp))

            when (val currentUiState = stateUiState.value) {
                is SearchResultsUIState.Empty -> Unit

                is SearchResultsUIState.Loading -> {
                    Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Spacer(modifier = Modifier.height(16.dp))
                        CircularProgressIndicator(
                            modifier = Modifier.width(32.dp),
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        )
                    }
                }

                is SearchResultsUIState.ShowResults -> {
                    ResultsList(uiState = currentUiState)
                }
            }

        }
    }
}

@Composable
fun ResultsList(
    modifier: Modifier = Modifier,
    uiState: SearchResultsUIState.ShowResults
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        itemsIndexed(uiState.results) { index, item ->
            Spacer(modifier = Modifier.height(16.dp))

            ResultItem(
                bean = item,
                onItemClick = {
                    uiState.navigateToDetails.invoke(item)
                }
            )

            if (index == uiState.results.lastIndex) {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun ResultItem(
    modifier: Modifier = Modifier,
    bean: SearchResult,
    onItemClick: () -> Unit
) {
    BaseCard(
        modifier = modifier.padding(horizontal = 16.dp),
        onClick = onItemClick,
        isEnabled = true
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {

            AsyncImage(
                modifier = modifier
                    .wrapContentSize()
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically),
                model = bean.artworkThumbnail,
                placeholder = painterResource(id = R.drawable.ic_placeholder),
                contentDescription = null
            )

            Column(
                modifier = modifier
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .weight(1f)
            ) {
                Text(
                    style = MaterialTheme.typography.titleLarge,
                    text = bean.trackName
                )
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = bean.artistName
                )
            }

            Column(
                modifier = modifier
                    .wrapContentSize()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = bean.currency + " " + bean.trackPrice
                )
            }
        }
    }
}
