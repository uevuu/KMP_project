package org.example.project.feature.main.presentation

import org.example.project.feature.common.domain.RecipeModel
import org.example.project.feature.main.domain.entities.WineTypeModel

data class MainState(
    val isLoading: Boolean,
    val randomRecipes: List<RecipeModel>,
    val wineTypes: List<WineTypeModel>,
)

sealed class MainAction {
    data object MainFailure : MainAction()
    data class OpenRecipe(val recipeId: Int) : MainAction()
    data object OpenFavourites : MainAction()
}

sealed class MainEvent {
    data object OnInit : MainEvent()
    data class OnRecipeClicked(val recipeId: Int) : MainEvent()
    data object OnFavouriteRecipesClicked : MainEvent()
}
