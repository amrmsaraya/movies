package com.github.amrmsaraya.movies.feature_movies.data.mapper

import com.github.amrmsaraya.movies.database.dto.popular.PopularMovieDTO
import com.github.amrmsaraya.movies.database.dto.top_rated.TopRatedMovieDTO
import com.github.amrmsaraya.movies.feature_movies.domain.entity.movie.Movie
import com.github.amrmsaraya.movies.network.dto.movie.MovieNDTO


fun MovieNDTO.toMovie(): Movie {
    return Movie(
        posterPath = posterPath,
        adult = adult,
        overview = overview,
        releaseDate = releaseDate,
        genreIds = genreIds,
        id = id,
        originalTitle = originalTitle,
        originalLanguage = originalLanguage,
        title = title,
        backdropPath = backdropPath,
        popularity = popularity,
        voteCount = voteCount,
        video = video,
        voteAverage = voteAverage
    )
}

fun Movie.toPopularMovieDTO(): PopularMovieDTO {
    return PopularMovieDTO(
        posterPath = posterPath,
        adult = adult,
        overview = overview,
        releaseDate = releaseDate,
        genreIds = genreIds,
        id = id,
        originalTitle = originalTitle,
        originalLanguage = originalLanguage,
        title = title,
        backdropPath = backdropPath,
        popularity = popularity,
        voteCount = voteCount,
        video = video,
        voteAverage = voteAverage
    )
}

fun Movie.toTopRatedMovieDTO(): TopRatedMovieDTO {
    return TopRatedMovieDTO(
        posterPath = posterPath,
        adult = adult,
        overview = overview,
        releaseDate = releaseDate,
        genreIds = genreIds,
        id = id,
        originalTitle = originalTitle,
        originalLanguage = originalLanguage,
        title = title,
        backdropPath = backdropPath,
        popularity = popularity,
        voteCount = voteCount,
        video = video,
        voteAverage = voteAverage
    )
}

fun PopularMovieDTO.toMovie(): Movie {
    return Movie(
        posterPath = posterPath,
        adult = adult,
        overview = overview,
        releaseDate = releaseDate,
        genreIds = genreIds,
        id = id,
        originalTitle = originalTitle,
        originalLanguage = originalLanguage,
        title = title,
        backdropPath = backdropPath,
        popularity = popularity,
        voteCount = voteCount,
        video = video,
        voteAverage = voteAverage
    )
}

fun TopRatedMovieDTO.toMovie(): Movie {
    return Movie(
        posterPath = posterPath,
        adult = adult,
        overview = overview,
        releaseDate = releaseDate,
        genreIds = genreIds,
        id = id,
        originalTitle = originalTitle,
        originalLanguage = originalLanguage,
        title = title,
        backdropPath = backdropPath,
        popularity = popularity,
        voteCount = voteCount,
        video = video,
        voteAverage = voteAverage
    )
}

