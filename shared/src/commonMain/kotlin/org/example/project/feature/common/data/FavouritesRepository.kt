package org.example.project.feature.common.data

import org.example.project.feature.common.domain.RecipeModel

internal class FavouritesRepository(
    private val localDataSource: FavouritesLocalDataSource,
    private val localDataSourceMapper: FavouritesLocalDataSourceMapper,
) {
    suspend fun getRecipeById(id: Int): RecipeModel? = localDataSource.getFavouriteRecipeById(id)
        ?.let(localDataSourceMapper::toDomainModel)

    suspend fun getAllRecipes(): List<RecipeModel> = localDataSource.getFavouriteRecipes()
        .map { localDataSourceMapper.toDomainModel(it) }

    suspend fun addRecipe(recipe: RecipeModel) = localDataSource.setFavouriteRecipe(recipe)

    suspend fun removeRecipe(id: Int) = localDataSource.removeFavouriteRecipe(id.toLong())
}
