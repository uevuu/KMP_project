package org.example.project.feature.common.data

import org.example.project.FavouritesDB
import org.example.project.feature.common.domain.RecipeModel
import org.example.project.feature.common.domain.StepModel

internal class FavouritesLocalDataSourceMapper {
    fun toDomainModel(localModel: FavouritesDB): RecipeModel = localModel.let {
        RecipeModel(
            id = it.id.toInt(),
            pricePerServing = it.pricePerServing,
            servings = it.servings.toInt(),
            readyInMinutes = it.readyInMinutes.toInt(),
            image = it.image,
            title = it.title,
            summary = it.summary,
            steps = it.steps.mapIndexed { index, step ->
                StepModel(number = index + 1, step = step)
            }
        )
    }
}
