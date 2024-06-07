package org.example.project.feature.common.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.project.feature.common.data.entities.RecipesResponse

private const val NUMBER_KEY = "number"

internal class RecipesRemoteDataSource(
    private val httpClient: HttpClient,
) {
    suspend fun getRandomRecipes(number: Int): RecipesResponse {
        return httpClient.get("recipes/random") {
            parameter(NUMBER_KEY, number)
        }.body()
    }
}
