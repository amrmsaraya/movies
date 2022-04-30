package com.github.amrmsaraya.movies.network.dto.movie


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponseNDTO(
    @SerialName("page")
    val page: Int = 0,
    @SerialName("results")
    val movies: List<MovieNDTO> = listOf(),
    @SerialName("total_results")
    val totalResults: Int = 0,
    @SerialName("total_pages")
    val totalPages: Int = 0
)