package com.github.amrmsaraya.movies.feature_movies.data.mapper

import com.github.amrmsaraya.movies.database.dto.movie_details.GenreDTO
import com.github.amrmsaraya.movies.database.dto.movie_details.MovieDetailsDTO
import com.github.amrmsaraya.movies.feature_movies.domain.entity.movie_details.Genre
import com.github.amrmsaraya.movies.feature_movies.domain.entity.movie_details.MovieDetails
import com.github.amrmsaraya.movies.network.dto.movie_details.GenreNDTO
import com.github.amrmsaraya.movies.network.dto.movie_details.MovieDetailsNDTO

fun MovieDetailsNDTO.toMovieDetails(): MovieDetails {
    return MovieDetails(
        adult = adult,
        backdropPath = backdropPath,
        budget = budget,
        genres = genres.map { it.toGenre() },
        homepage = homepage,
        id = id,
        imdbId = imdbId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun GenreNDTO.toGenre(): Genre {
    return Genre(id, name)
}

fun MovieDetailsDTO.toMovieDetails(): MovieDetails {
    return MovieDetails(
        adult = adult,
        backdropPath = backdropPath,
        budget = budget,
        genres = genres.map { it.toGenre() },
        homepage = homepage,
        id = id,
        imdbId = imdbId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun GenreDTO.toGenre(): Genre {
    return Genre(id, name)
}

fun MovieDetails.toMovieDetailsDTO(): MovieDetailsDTO {
    return MovieDetailsDTO(
        adult = adult,
        backdropPath = backdropPath,
        budget = budget,
        genres = genres.map { it.toGenreDTO() },
        homepage = homepage,
        id = id,
        imdbId = imdbId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun Genre.toGenreDTO(): GenreDTO {
    return GenreDTO(id, name)
}
