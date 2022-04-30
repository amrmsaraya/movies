package com.github.amrmsaraya.movies.network.dto.movie


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieNDTO(
    @SerialName("poster_path")
    val posterPath: String? = "",
    @SerialName("adult")
    val adult: Boolean = false,
    @SerialName("overview")
    val overview: String = "",
    @SerialName("release_date")
    val releaseDate: String = "",
    @SerialName("genre_ids")
    val genreIds: List<Int> = listOf(),
    @SerialName("id")
    val id: Long = 0,
    @SerialName("original_title")
    val originalTitle: String = "",
    @SerialName("original_language")
    val originalLanguage: String = "",
    @SerialName("title")
    val title: String = "",
    @SerialName("backdrop_path")
    val backdropPath: String? = "",
    @SerialName("popularity")
    val popularity: Double = 0.0,
    @SerialName("vote_count")
    val voteCount: Int = 0,
    @SerialName("video")
    val video: Boolean = false,
    @SerialName("vote_average")
    val voteAverage: Double = 0.0
)