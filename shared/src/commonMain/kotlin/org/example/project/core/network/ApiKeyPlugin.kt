package org.example.project.core.network

import io.ktor.client.plugins.api.SetupRequest
import io.ktor.client.plugins.api.createClientPlugin

private const val API_KEY_NAME = "apiKey"
private const val API_KEY = "d1d4ddb9df1f4616a2b8608816a5f3fa"

fun createApiKeyPlugin() = createClientPlugin(name = "ApiKeyPlugin") {
    on(SetupRequest) { builder ->
        builder.url.parameters.apply {
            append(API_KEY_NAME, API_KEY)
        }
    }
}
