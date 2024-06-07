package org.example.project.feature.recommendedWines.data

import org.example.project.feature.recommendedWines.domain.entities.WineModel
import org.example.project.feature.search.entities.SearchRecipeModel
import org.example.project.feature.search.data.SearchRecipesRemoteDataSource
import org.example.project.feature.search.data.SearchRecipesRemoteDataSourceMapper

private const val RECOMMENDED_WINES_NUMBER = 50

internal class WinesRepository(
    private val remoteDataSource: WinesRemoteDataSource,
    private val remoteDataSourceMapper: WinesRemoteDataSourceMapper,
) {
    suspend fun getRecommendedWines(type: String, number: Int = RECOMMENDED_WINES_NUMBER): List<WineModel> =
        remoteDataSource.getRecommendedWines(type, number).let(remoteDataSourceMapper::toDomainModel)
}
