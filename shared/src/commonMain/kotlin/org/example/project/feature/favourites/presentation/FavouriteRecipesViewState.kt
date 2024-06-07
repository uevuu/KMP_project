package org.example.project.feature.favourites.presentation

import org.example.project.feature.common.domain.RecipeModel

data class FavouriteRecipesState(
    val isLoading: Boolean,
    val result: List<RecipeModel>,
)

sealed class FavouriteRecipesAction {
    data object FavouriteRecipesFailure : FavouriteRecipesAction()
}

sealed class FavouriteRecipesEvent {
    data object OnInit : FavouriteRecipesEvent()
}
