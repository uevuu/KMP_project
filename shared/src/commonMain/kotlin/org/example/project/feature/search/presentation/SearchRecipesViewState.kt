package org.example.project.feature.search.presentation

import org.example.project.feature.search.entities.SearchRecipeModel

data class SearchRecipesState(
    val isLoading: Boolean,
    val result: List<SearchRecipeModel>,
    val query: String,
)

sealed class SearchRecipesAction {
    data object SearchRecipesFailure : SearchRecipesAction()
    data class OpenRecipe(val recipeId: Int) : SearchRecipesAction()
}

sealed class SearchRecipesEvent {
    data class OnQueryChanged(val query: String) : SearchRecipesEvent()
    data class OnRecipeClicked(val recipeId: Int) : SearchRecipesEvent()
}
