package dev.carlodips.itunesmusicsearch.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.carlodips.itunesmusicsearch.R
import dev.carlodips.itunesmusicsearch.data.remote.responses.SearchResult

data class DetailsUIModel(
    val bean: SearchResult
)

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    uiModel: DetailsUIModel
) {
    val bean = uiModel.bean

    Surface(modifier = modifier.wrapContentSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            AsyncImage(
                modifier = modifier
                    .widthIn(min = 100.dp)
                    .heightIn(min = 100.dp)
                    .padding(start = 16.dp)
                    .align(Alignment.CenterHorizontally),
                model = bean.artworkThumbnail,
                placeholder = painterResource(id = R.drawable.ic_placeholder),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyLarge,
                    text = stringResource(R.string.artist_name)
                )
                Text(
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyLarge,
                    text = bean.artistName
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyLarge,
                    text = stringResource(R.string.track_name)
                )
                Text(
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyLarge,
                    text = bean.trackName
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyLarge,
                    text = stringResource(R.string.collection_name)
                )
                Text(
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyLarge,
                    text = bean.collectionName
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyLarge,
                    text = stringResource(R.string.track_time)
                )
                Text(
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyLarge,
                    text = bean.getDisplayTrackTime()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyLarge,
                    text = stringResource(R.string.track_price)
                )
                Text(
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyLarge,
                    text = bean.currency + " " + bean.trackPrice
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyLarge,
                    text = stringResource(R.string.release_date)
                )
                Text(
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyLarge,
                    text = bean.getDisplayReleaseDate()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyLarge,
                    text = stringResource(R.string.genre)
                )
                Text(
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyLarge,
                    text = bean.genre
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyLarge,
                    text = stringResource(R.string.track_explicitness)
                )
                Text(
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyLarge,
                    text = bean.trackExplicitness
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}