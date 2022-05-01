package com.github.amrmsaraya.movies.feature_movies.domain.usecase

import androidx.paging.ExperimentalPagingApi
import com.github.amrmsaraya.movies.common.util.Result
import com.github.amrmsaraya.movies.common.util.handleResponse
import com.github.amrmsaraya.movies.feature_movies.domain.entity.movie_details.MovieDetails
import com.github.amrmsaraya.movies.feature_movies.domain.repository.movie_details.MovieDetailsRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class GetMovieDetailsUseCase @Inject constructor(
    private val movieDetailsRepo: MovieDetailsRepo
) {
    suspend operator fun invoke(id: Long): Result<MovieDetails> {
        return handleResponse(
            onFailure = {
                movieDetailsRepo.getLocalMovieDetails(id)
            },
            result = {
                val movieDetails = movieDetailsRepo.getRemoteMovieDetails(id)
                movieDetailsRepo.insert(movieDetails)
                movieDetailsRepo.getLocalMovieDetails(id)
            }
        )
    }
}