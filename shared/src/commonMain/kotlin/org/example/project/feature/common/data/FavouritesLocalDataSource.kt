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
    
    suspend fun getFavouriteRecipes(): List<FavouritesDB> = withContext(Dispatchers.IO) {
        database.favouritesQueries.selectAll().executeAsList()
    }

    suspend fun getFavouriteRecipeById(id: Int): FavouritesDB? = withContext(Dispatchers.IO) {
        database.favouritesQueries.selectById(id.toLong()).executeAsOneOrNull()
    }

    suspend fun setFavouriteRecipe(recipe: RecipeModel) {
        withContext(Dispatchers.IO) {
            recipe.let {
                database.favouritesQueries.insert(
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

    suspend fun removeFavouriteRecipe(id: Long) = withContext(Dispatchers.IO) {
        database.favouritesQueries.removeById(id)
    }
}
