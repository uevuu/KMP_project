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
    data object OpenRandomRecipes : MainAction()
    data class OpenRecommendedWines(val wineType: WineTypeModel) : MainAction()
    data object OpenAuth : MainAction()
    data object OpenSearch : MainAction()
}

sealed class MainEvent {
    data object OnInit : MainEvent()
    data object OnExitClicked : MainEvent()
    data object OnSearchClicked : MainEvent()
    data class OnRecipeClicked(val recipeId: Int) : MainEvent()
    data class OnWineTypeClicked(val wineType: WineTypeModel) : MainEvent()
    data object OnFavouriteRecipesClicked : MainEvent()
    data object OnMoreRandomRecipesClicked : MainEvent()
}
