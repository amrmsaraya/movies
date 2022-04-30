package com.github.amrmsaraya.movies.di

import android.util.Log
import com.github.amrmsaraya.movies.BuildConfig

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ClientProvider {

    private val client = HttpClient(Android) {

        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }

        install(HttpTimeout) {
            connectTimeoutMillis = 5000
            requestTimeoutMillis = 5000
            socketTimeoutMillis = 5000
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.v("Logger Ktor", message)
                }
            }
            level = LogLevel.BODY
        }

        install(ResponseObserver) {
            onResponse { response ->
                Log.d("HTTP status", "${response.status.value}")
            }
        }

        install(DefaultRequest) {
            url {
                host = BuildConfig.BASE_URL
                protocol = URLProtocol.HTTPS
            }

            url.parameters.append("api_key",BuildConfig.API_KEY)

            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }

    @Singleton
    @Provides
    fun provideKtorClient(): HttpClient {
        return client
    }

}
