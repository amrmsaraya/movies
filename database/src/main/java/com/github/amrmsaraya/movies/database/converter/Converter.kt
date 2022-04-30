package com.github.amrmsaraya.movies.database.converter

import androidx.room.TypeConverter
import com.github.amrmsaraya.movies.database.dto.movie_details.GenreDTO
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converter {

    @TypeConverter
    fun fromIntList(list: List<Int>): String {
        return Json.encodeToString(list)
    }

    @TypeConverter
    fun toIntList(string: String): List<Int> {
        return Json.decodeFromString(string)
    }

    @TypeConverter
    fun fromGenreDTO(list: List<GenreDTO>): String {
        return Json.encodeToString(list)
    }

    @TypeConverter
    fun toGenreDTOList(string: String): List<GenreDTO> {
        return Json.decodeFromString(string)
    }
}