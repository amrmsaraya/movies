package com.github.amrmsaraya.movies.common.util

suspend fun <T> handleResponse(
    onFailure: (suspend () -> T)? = null,
    result: suspend () -> T
): Result<T> {
    return runCatching {
        Result.Success(result())
    }.getOrElse { throwable ->
        return onFailure?.let { invokeOnFailure ->
            runCatching {
                Result.Error(throwable, invokeOnFailure())
            }.getOrElse { throwable ->
                throwable.printStackTrace()
                Result.Error(throwable)
            }
        } ?: run {
            throwable.printStackTrace()
            Result.Error(throwable)
        }
    }
}