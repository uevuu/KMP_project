package org.example.project.feature.common.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipesResponse(
    @SerialName("recipes")
    val recipes: List<RecipeResponse>
)
