package org.example.project.core.network

import io.ktor.client.plugins.api.SetupRequest
import io.ktor.client.plugins.api.createClientPlugin

private const val API_KEY_NAME = "apiKey"
private const val API_KEY = "5e39ad6046cd4125b0d1b095900a7901"

fun createApiKeyPlugin() = createClientPlugin(name = "ApiKeyPlugin") {
    on(SetupRequest) { builder ->
        builder.url.parameters.apply {
            append(API_KEY_NAME, API_KEY)
        }
    }
}
