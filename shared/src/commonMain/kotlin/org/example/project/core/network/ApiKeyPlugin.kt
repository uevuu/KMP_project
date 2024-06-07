package org.example.project.core.network

import io.ktor.client.plugins.api.SetupRequest
import io.ktor.client.plugins.api.createClientPlugin

private const val API_KEY_NAME = "apiKey"
private const val API_KEY = "f8eec80b94144a5e8052d7e1dc6fdad0"

fun createApiKeyPlugin() = createClientPlugin(name = "ApiKeyPlugin") {
    on(SetupRequest) { builder ->
        builder.url.parameters.apply {
            append(API_KEY_NAME, API_KEY)
        }
    }
}
