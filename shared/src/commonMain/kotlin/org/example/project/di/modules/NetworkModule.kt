package org.example.project.di.modules

import org.example.project.core.configuration.Configuration
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.project.core.network.HttpEngineFactory
import org.example.project.core.network.createApiKeyPlugin
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.kodein.di.singleton

val networkModule = DI.Module(name = "networkModule") {

    bind<HttpEngineFactory>() with singleton { HttpEngineFactory() }

    bindSingleton<Json> {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
    }

    bindSingleton {
        buildHttpClient(
            engine = instance<HttpEngineFactory>().createEngine(instance()),
            json = instance(),
            configuration = instance(),
        )
    }
}

private const val BASE_URL = "api.spoonacular.com"

private fun buildHttpClient(
    engine: HttpClientEngineFactory<HttpClientEngineConfig>,
    json: Json,
    configuration: Configuration,
) = HttpClient(engine) {

    if (configuration.isHttpLoggingEnabled) {
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.BODY
        }
    }

    install(ContentNegotiation) {
        json(json)
    }

    install(createApiKeyPlugin())

    install(HttpTimeout) {
        connectTimeoutMillis = 15000
        requestTimeoutMillis = 30000
        socketTimeoutMillis = 30000
    }

    defaultRequest {
        url {
            this.host = BASE_URL
            this.protocol = URLProtocol.HTTPS
        }
    }
}
