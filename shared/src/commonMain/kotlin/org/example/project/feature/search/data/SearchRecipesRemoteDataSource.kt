package org.example.project.feature.search.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.project.feature.search.data.entities.SearchRecipesResponse

private const val QUERY_KEY = "query"
private const val NUMBER_KEY = "query"

internal class SearchRecipesRemoteDataSource(
    private val httpClient: HttpClient,
) {
    suspend fun searchRecipes(type: String, number: Int): SearchRecipesResponse {
        return httpClient.get("recipes/complexSearch") {
            parameter(QUERY_KEY, type)
            parameter(NUMBER_KEY, number)
        }.body()
    }
}
