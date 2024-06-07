package org.example.project.feature.recipeDetails.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.example.project.feature.recipeDetails.data.entities.RecipeDetailsResponse

internal class RecipeDetailsRemoteDataSource(
    private val httpClient: HttpClient,
) {
    suspend fun getRecipeDetails(id: Int): RecipeDetailsResponse {
        return httpClient.get("recipes/$id/information").body()
    }
}
