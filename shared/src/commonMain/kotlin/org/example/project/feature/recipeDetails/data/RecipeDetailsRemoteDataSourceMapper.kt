package org.example.project.feature.recipeDetails.data

import org.example.project.feature.common.domain.RecipeModel
import org.example.project.feature.common.domain.StepModel
import org.example.project.feature.recipeDetails.data.entities.RecipeDetailsResponse

internal class RecipeDetailsRemoteDataSourceMapper {
    fun toDomainModel(networkModel: RecipeDetailsResponse) = networkModel.let { recipe ->
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
