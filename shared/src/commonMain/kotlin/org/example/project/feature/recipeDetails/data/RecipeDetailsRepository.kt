package org.example.project.feature.recipeDetails.data

import org.example.project.feature.common.domain.RecipeModel

internal class RecipeDetailsRepository(
    private val remoteDataSource: RecipeDetailsRemoteDataSource,
    private val remoteDataSourceMapper: RecipeDetailsRemoteDataSourceMapper,
) {
    suspend fun getRecipeDetails(id: Int): RecipeModel =
        remoteDataSource.getRecipeDetails(id).let(remoteDataSourceMapper::toDomainModel)
}
