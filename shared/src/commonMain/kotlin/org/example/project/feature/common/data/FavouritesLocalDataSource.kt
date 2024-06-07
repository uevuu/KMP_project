package org.example.project.feature.common.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.example.project.AppDatabase
import org.example.project.FavouritesDB
import org.example.project.feature.common.domain.RecipeModel

internal class FavouritesLocalDataSource(
    private val database: AppDatabase
) {

    suspend fun getFavouriteRecipes(userId: Long): List<FavouritesDB> = withContext(Dispatchers.IO) {
        database.favouritesQueries.selectAll(userId).executeAsList()
    }

    suspend fun getFavouriteRecipeById(id: Long, userId: Long): FavouritesDB? = withContext(Dispatchers.IO) {
        database.favouritesQueries.selectById(id, userId).executeAsOneOrNull()
    }

    suspend fun setFavouriteRecipe(recipe: RecipeModel, userId: Long) {
        withContext(Dispatchers.IO) {
            recipe.let {
                database.favouritesQueries.insert(
                    userId = userId,
                    id = it.id.toLong(),
                    pricePerServing = it.pricePerServing,
                    servings = it.servings.toLong(),
                    readyInMinutes = it.readyInMinutes.toLong(),
                    image = it.image.orEmpty(),
                    title = it.title,
                    summary = it.summary,
                    steps = it.steps.map { step -> step.step }
                )
            }
        }
    }

    suspend fun removeFavouriteRecipe(id: Long, userId: Long) = withContext(Dispatchers.IO) {
        database.favouritesQueries.removeById(id, userId)
    }
}
