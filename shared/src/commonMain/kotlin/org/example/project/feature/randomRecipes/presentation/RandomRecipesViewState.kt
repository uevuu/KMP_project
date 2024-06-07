package org.example.project.feature.randomRecipes.presentation

import org.example.project.feature.common.domain.RecipeModel

data class RandomRecipesState(
    val isLoading: Boolean,
    val randomRecipes: List<RecipeModel>,
)

sealed class RandomRecipesAction {
    data object RandomRecipesFailure : RandomRecipesAction()
}

sealed class RandomRecipesEvent {
    data object OnInit : RandomRecipesEvent()
}
