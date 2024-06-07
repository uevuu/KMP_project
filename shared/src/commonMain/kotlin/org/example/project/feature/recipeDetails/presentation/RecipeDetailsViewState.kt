package org.example.project.feature.recipeDetails.presentation

import org.example.project.feature.common.domain.RecipeModel

data class RecipeDetailsState(
    val isLoading: Boolean,
    val details: RecipeModel?,
    val isFavourite: Boolean,
)

sealed class RecipeDetailsAction {
    data object RecipeDetailsFailure : RecipeDetailsAction()
}

sealed class RecipeDetailsEvent {
    data class OnInitWithId(val recipeId: Int) : RecipeDetailsEvent()
    data class OnInitWithDetails(val recipeDetails: RecipeModel) : RecipeDetailsEvent()
    data object OnAddToFavouritesClicked : RecipeDetailsEvent()
}
