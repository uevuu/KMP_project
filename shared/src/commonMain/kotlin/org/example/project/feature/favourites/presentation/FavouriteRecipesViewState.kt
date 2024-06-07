package org.example.project.feature.favourites.presentation

import org.example.project.feature.common.domain.RecipeModel

data class FavouriteRecipesState(
    val isLoading: Boolean,
    val result: List<RecipeModel>,
)

sealed class FavouriteRecipesAction {
    data object FavouriteRecipesFailure : FavouriteRecipesAction()
    data class OpenRecipe(val recipeId: Int) : FavouriteRecipesAction()
}

sealed class FavouriteRecipesEvent {
    data object OnInit : FavouriteRecipesEvent()
    data class OnRecipeClicked(val recipeId: Int) : FavouriteRecipesEvent()
}
