package org.example.project.feature.search.data

import org.example.project.feature.search.entities.SearchRecipeModel

private const val SEARCH_RECIPES_NUMBER = 100

internal class SearchRecipesRepository(
    private val remoteDataSource: SearchRecipesRemoteDataSource,
    private val remoteDataSourceMapper: SearchRecipesRemoteDataSourceMapper,
) {
    suspend fun searchRecipes(query: String, number: Int = SEARCH_RECIPES_NUMBER): List<SearchRecipeModel> =
        remoteDataSource.searchRecipes(query, number).let(remoteDataSourceMapper::toDomainModel)
}
