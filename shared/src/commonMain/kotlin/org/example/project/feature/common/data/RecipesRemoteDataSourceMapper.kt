package org.example.project.feature.common.data

import org.example.project.feature.common.data.entities.RecipesResponse
import org.example.project.feature.common.domain.RecipeModel
import org.example.project.feature.common.domain.StepModel

internal class RecipesRemoteDataSourceMapper {
    fun toDomainModel(networkModel: RecipesResponse) = networkModel.recipes.map { recipe ->
        RecipeModel(
            id = recipe.id,
            pricePerServing = recipe.pricePerServing,
            servings = recipe.servings,
            readyInMinutes = recipe.readyInMinutes,
            image = recipe.image,
            title = recipe.title,
            summary = recipe.summary,
            steps = recipe.analyzedInstructions.firstOrNull()?.steps?.map {
                StepModel(number = it.number, step = it.step)
            } ?: emptyList()
        )
    }
}
