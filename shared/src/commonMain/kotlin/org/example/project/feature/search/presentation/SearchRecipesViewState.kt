package org.example.project.feature.search.presentation

import org.example.project.feature.search.entities.SearchRecipeModel

data class SearchRecipesState(
    val isLoading: Boolean,
    val result: List<SearchRecipeModel>,
)

sealed class SearchRecipesAction {
    data object SearchRecipesFailure : SearchRecipesAction()
}

sealed class SearchRecipesEvent {
    data class OnQueryChanged(val query: String) : SearchRecipesEvent()
}
