package com.github.amrmsaraya.movies.feature_movies.presentation.movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.github.amrmsaraya.movies.common.presentation.composable.NoInternetConnection
import com.github.amrmsaraya.movies.common.presentation.theme.surfaceAtElevation
import com.github.amrmsaraya.movies.common.util.Constants
import com.github.amrmsaraya.movies.feature_movies.R
import com.github.amrmsaraya.movies.feature_movies.domain.entity.movie.Movie
import com.github.amrmsaraya.movies.preference.domain.entity.MovieSort
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.material.placeholder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    onNavigateToMovieDetails: (Long) -> Unit,
    viewModel: MovieViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState
    val popularMovies = uiState.popularMovies.collectAsLazyPagingItems()
    val topRatedMovies = uiState.topRatedMovies.collectAsLazyPagingItems()

    var expanded by remember { mutableStateOf(false) }

    val movies by derivedStateOf {
        when (uiState.movieSort) {
            MovieSort.POPULAR -> popularMovies
            MovieSort.TOP_RATED -> topRatedMovies
        }
    }

    LaunchedEffect(uiState.movieSort) {
        when (uiState.movieSort) {
            MovieSort.POPULAR -> if (popularMovies.itemCount == 0) {
                viewModel.sendIntent(MovieIntent.GetPopularMovies)
            }
            MovieSort.TOP_RATED -> if (topRatedMovies.itemCount == 0) {
                viewModel.sendIntent(MovieIntent.GetTopRatedMovies)
            }
        }
    }

    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            SmallTopAppBar(
                title = { Text(text = stringResource(id = uiState.movieSort.stringRes)) },
                actions = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            Icons.Default.Sort,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            SortMenu(
                expanded = expanded,
                selected = uiState.movieSort,
                onSelect = { viewModel.sendIntent(MovieIntent.SaveMovieSort(it)) },
                onDismiss = { expanded = false }
            )
            if (movies.loadState.mediator?.refresh is LoadState.Error && movies.itemCount == 0) {
                NoInternetConnection {
                    movies.refresh()
                }
            } else {
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Adaptive(140.dp),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    if (movies.itemCount == 0) {
                        items(20) {
                            LoadingMovies()
                        }
                    } else {
                        items(movies.itemCount) { index ->
                            movies[index]?.let { movie ->
                                MovieItem(
                                    movie = movie,
                                    onClick = { onNavigateToMovieDetails(movie.id) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SortMenu(
    expanded: Boolean,
    onDismiss: () -> Unit,
    selected: MovieSort,
    onSelect: (MovieSort) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 16.dp)
            .wrapContentSize(Alignment.TopEnd)
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onDismiss() }
        ) {
            DropdownMenuItem(
                text = { Text(stringResource(id = R.string.popular)) },
                onClick = {
                    onSelect(MovieSort.POPULAR)
                    onDismiss()
                },
                leadingIcon = {
                    RadioButton(
                        selected = selected == MovieSort.POPULAR,
                        onClick = {
                            onSelect(MovieSort.POPULAR)
                            onDismiss()
                        }
                    )
                }
            )
            DropdownMenuItem(
                text = { Text(stringResource(id = R.string.top_rated)) },
                onClick = {
                    onSelect(MovieSort.TOP_RATED)
                    onDismiss()
                },
                leadingIcon = {
                    RadioButton(
                        selected = selected == MovieSort.TOP_RATED,
                        onClick = {
                            onSelect(MovieSort.TOP_RATED)
                            onDismiss()
                        }
                    )
                }

            )
        }
    }
}

@Composable
private fun MovieItem(
    movie: Movie,
    onClick: () -> Unit
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data("${Constants.POSTER_BASEURL}/${movie.posterPath}")
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
            .padding(8.dp)
            .clickable(
                indication = null,
                interactionSource = MutableInteractionSource(),
                onClick = onClick
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Surface(
            shape = MaterialTheme.shapes.large,
            tonalElevation = 2.dp
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .placeholder(
                        isImageLoading,
                        highlight = PlaceholderHighlight.fade(
                            highlightColor = MaterialTheme.colorScheme.surfaceAtElevation(12.dp)
                        ),
                        shape = MaterialTheme.shapes.large,
                        color = MaterialTheme.colorScheme.surfaceAtElevation(2.dp)
                    ),
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )

        }
        Text(text = movie.title, textAlign = TextAlign.Center)
    }
}

@Composable
private fun LoadingMovies() {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .placeholder(
                    true,
                    highlight = PlaceholderHighlight.fade(
                        highlightColor = MaterialTheme.colorScheme.surfaceAtElevation(12.dp)
                    ),
                    shape = MaterialTheme.shapes.large,
                    color = MaterialTheme.colorScheme.surfaceAtElevation(2.dp)
                ),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
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
