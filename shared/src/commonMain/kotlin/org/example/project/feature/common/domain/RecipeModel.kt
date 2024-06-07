package org.example.project.feature.common.domain

data class RecipeModel(
    val id: Int,
    val pricePerServing: Double,
    val servings: Int,
    val readyInMinutes: Int,
    val image: String?,
    val title: String,
    val summary: String,
    val steps: List<StepModel>
)

data class StepModel(
    val number: Int,
    val step: String
)
