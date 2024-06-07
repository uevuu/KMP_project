package org.example.project.feature.recommendedWines.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.project.feature.recommendedWines.data.entities.RecommendedWinesResponse

private const val NUMBER_KEY = "number"
private const val WINE_TYPE_KEY = "wine"

internal class WinesRemoteDataSource(
    private val httpClient: HttpClient,
) {
    suspend fun getRecommendedWines(type: String, number: Int): RecommendedWinesResponse {
        return httpClient.get("food/wine/recommendation") {
            parameter(NUMBER_KEY, number)
            parameter(WINE_TYPE_KEY, type)
        }.body()
    }
}
