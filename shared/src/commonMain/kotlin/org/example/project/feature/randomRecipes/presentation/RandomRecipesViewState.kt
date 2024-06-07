package org.example.project.feature.randomRecipes.presentation

import org.example.project.feature.common.domain.RecipeModel
import org.example.project.feature.favourites.presentation.FavouriteRecipesEvent

data class RandomRecipesState(
    val isLoading: Boolean,
    val randomRecipes: List<RecipeModel>,
)

sealed class RandomRecipesAction {
    data object RandomRecipesFailure : RandomRecipesAction()
    data class OpenRecipe(val recipeId: Int) : RandomRecipesAction()
}

sealed class RandomRecipesEvent {
    data object OnInit : RandomRecipesEvent()
    data class OnRecipeClicked(val recipeId: Int) : RandomRecipesEvent()
}
