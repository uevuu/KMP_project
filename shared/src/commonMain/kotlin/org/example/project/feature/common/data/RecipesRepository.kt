package org.example.project.feature.common.data

import org.example.project.feature.common.domain.RecipeModel

internal class RecipesRepository(
    private val remoteDataSource: RecipesRemoteDataSource,
    private val remoteDataSourceMapper: RecipesRemoteDataSourceMapper,
) {
    suspend fun getRandomRecipes(number: Int): List<RecipeModel> =
        remoteDataSource.getRandomRecipes(number).let(remoteDataSourceMapper::toDomainModel)
}
