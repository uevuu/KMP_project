package org.example.project.core.network

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin
import org.example.project.core.configuration.PlatformConfiguration

actual open class HttpEngineFactory actual constructor() {

    actual fun createEngine(
        platformConfiguration: PlatformConfiguration
    ): HttpClientEngineFactory<HttpClientEngineConfig> = Darwin
}