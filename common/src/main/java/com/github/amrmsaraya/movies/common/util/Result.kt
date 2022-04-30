package com.github.amrmsaraya.movies.common.util

sealed class Result<out T> {
    data class Success<out T>(val result: T) : Result<T>()
    data class Error<out T>(
        val throwable: Throwable,
        val result: T? = null
    ) : Result<T>()
}
