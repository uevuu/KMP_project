package org.example.project.core.network

import org.example.project.core.configuration.PlatformConfiguration
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.config
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

actual open class HttpEngineFactory {

    actual open fun createEngine(
        platformConfiguration: PlatformConfiguration,
    ): HttpClientEngineFactory<HttpClientEngineConfig> =
        OkHttp.config {
            config {
                retryOnConnectionFailure(true)
            }

            addInterceptor(
                HttpLoggingInterceptor { Timber.tag("Network").d(it) }.apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                },
            )
        }
}
