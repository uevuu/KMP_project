package org.example.project.feature.common.data

import org.example.project.feature.common.domain.RecipeModel

internal class FavouritesRepository(
    private val localDataSource: FavouritesLocalDataSource,
    private val localDataSourceMapper: FavouritesLocalDataSourceMapper,
) {
    suspend fun getRecipeById(id: Int, userId: Long): RecipeModel? =
        localDataSource.getFavouriteRecipeById(id.toLong(), userId)
            ?.let(localDataSourceMapper::toDomainModel)

    suspend fun getAllRecipes(userId: Long): List<RecipeModel> = localDataSource.getFavouriteRecipes(userId.toLong())
        .map { localDataSourceMapper.toDomainModel(it) }

    suspend fun addRecipe(recipe: RecipeModel, userId: Long) =
        localDataSource.setFavouriteRecipe(recipe, userId)

    suspend fun removeRecipe(id: Int, userId: Long) = localDataSource.removeFavouriteRecipe(id.toLong(), userId)
}
