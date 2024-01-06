package dev.carlodips.itunesmusicsearch.ui.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.carlodips.itunesmusicsearch.R
import dev.carlodips.itunesmusicsearch.data.remote.responses.SearchResult
import dev.carlodips.itunesmusicsearch.ui.composable.BaseCard
import dev.carlodips.itunesmusicsearch.ui.composable.CustomOutlinedTextField

data class SearchUIModel(
    val input: MutableState<String>,
    val results: SnapshotStateList<SearchResult>
)

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    uiModel: SearchUIModel,
    onSearchClick: () -> Unit,
    navigateToDetails: (result: SearchResult) -> Unit
) {
    Surface(modifier = modifier.wrapContentSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
            //.verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            CustomOutlinedTextField(
                modifier = Modifier.padding(horizontal = 16.dp),
                textValue = uiModel.input,
                label = ""
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = onSearchClick
            ) {
                Text(text = stringResource(R.string.search))
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(uiModel.results) { index, item ->
                    Spacer(modifier = Modifier.height(16.dp))

                    ResultItem(
                        bean = item,
                        onItemClick = {
                            navigateToDetails.invoke(item)
                        }
                    )

                    if (index == uiModel.results.lastIndex) {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
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
