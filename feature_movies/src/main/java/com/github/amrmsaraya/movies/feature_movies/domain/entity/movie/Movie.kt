package com.github.amrmsaraya.movies.feature_movies.domain.entity.movie


data class Movie(
    val posterPath: String? = "",
    val adult: Boolean = false,
    val overview: String = "",
    val releaseDate: String = "",
    val genreIds: List<Int> = listOf(),
    val id: Long = 0,
    val originalTitle: String = "",
    val originalLanguage: String = "",
    val title: String = "",
    val backdropPath: String? = "",
    val popularity: Double = 0.0,
    val voteCount: Int = 0,
    val video: Boolean = false,
    val voteAverage: Double = 0.0
)