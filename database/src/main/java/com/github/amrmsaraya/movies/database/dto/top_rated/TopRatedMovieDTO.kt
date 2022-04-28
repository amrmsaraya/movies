package com.github.amrmsaraya.movies.database.dto.top_rated


import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "top_rated_movie")
data class TopRatedMovieDTO(
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
    @PrimaryKey
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