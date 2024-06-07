package org.example.project.core.network

import org.example.project.core.configuration.PlatformConfiguration
import io.ktor.client.engine.*

expect open class HttpEngineFactory() {

    fun createEngine(platformConfiguration: PlatformConfiguration): HttpClientEngineFactory<HttpClientEngineConfig>
}
