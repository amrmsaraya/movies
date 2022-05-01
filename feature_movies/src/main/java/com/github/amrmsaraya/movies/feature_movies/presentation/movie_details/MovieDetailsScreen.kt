package com.github.amrmsaraya.movies.feature_movies.presentation.movie_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.github.amrmsaraya.movies.common.presentation.composable.NoInternetConnection
import com.github.amrmsaraya.movies.common.presentation.theme.surfaceAtElevation
import com.github.amrmsaraya.movies.common.util.Constants
import com.github.amrmsaraya.movies.feature_movies.R
import com.github.amrmsaraya.movies.feature_movies.domain.entity.movie_details.MovieDetails
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.material.placeholder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(
    id: Long,
    onBackClick: () -> Unit,
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState

    LaunchedEffect(true) {
        viewModel.sendIntent(MovieDetailsIntent.GetMovieDetails(id))
    }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                modifier = Modifier.statusBarsPadding(),
                title = {},
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent),
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        when {
            uiState.isLoading -> MovieDetailsLoading()
            uiState.throwable != null && uiState.movie == null -> {
                NoInternetConnection {
                    viewModel.sendIntent(MovieDetailsIntent.GetMovieDetails(id))
                }
            }
            uiState.movie != null -> MovieDetailsContent(uiState.movie)
        }
    }
}

@Composable
fun MovieDetailsContent(movie: MovieDetails) {
    val scrollState = rememberScrollState()

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data("${Constants.IMAGE_BASEURL}/${movie.backdropPath}")
            .crossfade(true)
            .build(),
        contentScale = ContentScale.FillBounds
    )
    var isImageLoading by remember { mutableStateOf(false) }

    LaunchedEffect(painter.state) {
        isImageLoading = painter.state is AsyncImagePainter.State.Loading
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .placeholder(
                        isImageLoading,
                        highlight = PlaceholderHighlight.fade(
                            highlightColor = MaterialTheme.colorScheme.surfaceAtElevation(12.dp)
                        ),
                        color = MaterialTheme.colorScheme.surfaceAtElevation(2.dp)
                    ),
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .align(Alignment.BottomStart)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.surface
                            )
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )
                movie.runtime
                Text(
                    text = "${movie.releaseDate.substringBefore('-')} - ${movie.voteAverage}/10",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    textAlign = TextAlign.Center
                )
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (movie.adult) {
                        item { GenreText("+18") }
                    }
                    items(movie.genres) {
                        GenreText(it.name)
                    }
                }
            }
        }
        movie.overview?.let {
            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                Text(
                    text = stringResource(R.string.overview),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }

        }
    }
}

@Composable
private fun GenreText(name: String) {
    Surface(
        shape = MaterialTheme.shapes.small,
        tonalElevation = 4.dp
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = name,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun MovieDetailsLoading() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .placeholder(
                    true,
                    highlight = PlaceholderHighlight.fade(
                        highlightColor = MaterialTheme.colorScheme.surfaceAtElevation(12.dp)
                    ),
                    color = MaterialTheme.colorScheme.surfaceAtElevation(2.dp)
                )
        )

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(5) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(if (it == 0) 0.3f else 1f)
                        .height(24.dp)
                        .placeholder(
                            true,
                            highlight = PlaceholderHighlight.fade(
                                highlightColor = MaterialTheme.colorScheme.surfaceAtElevation(12.dp)
                            ),
                            shape = MaterialTheme.shapes.large,
                            color = MaterialTheme.colorScheme.surfaceAtElevation(2.dp)
                        )
                )
            }

        }
    }
}