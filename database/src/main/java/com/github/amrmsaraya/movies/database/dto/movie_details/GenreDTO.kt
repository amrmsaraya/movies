package com.github.amrmsaraya.movies.database.dto.movie_details


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreDTO(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("name")
    val name: String = ""
)