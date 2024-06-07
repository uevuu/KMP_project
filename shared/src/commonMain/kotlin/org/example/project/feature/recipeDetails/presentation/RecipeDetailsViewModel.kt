package org.example.project.feature.recipeDetails.presentation

import kotlinx.coroutines.launch
import org.example.project.core.viewmodel.BaseViewModel
import org.example.project.di.PlatformSDK
import org.example.project.feature.common.data.FavouritesRepository
import org.example.project.feature.recipeDetails.data.RecipeDetailsRepository
import org.example.project.feature.utils.runSuspendCatching

class RecipeDetailsViewModel : BaseViewModel<RecipeDetailsState, RecipeDetailsEvent, RecipeDetailsAction>(
    initState = RecipeDetailsState(
        isLoading = false,
        details = null,
        isFavourite = false,
    )
) {
    private val recipeDetailsRepository: RecipeDetailsRepository by PlatformSDK.lazyInstance()
    private val favouritesRepository: FavouritesRepository by PlatformSDK.lazyInstance()

    override fun obtainEvent(event: RecipeDetailsEvent) {
        when (event) {
            is RecipeDetailsEvent.OnInitWithDetails -> scope.launch {
                state = state.copy(details = event.recipeDetails)
            }

            is RecipeDetailsEvent.OnInitWithId -> scope.launch {
                state = state.copy(isLoading = true)
                val recipeDetails = runSuspendCatching {
                    recipeDetailsRepository.getRecipeDetails(event.recipeId)
                }.fold(
                    onSuccess = { it },
                    onFailure = {
                        action = RecipeDetailsAction.RecipeDetailsFailure
                        return@launch
                    }
                )
                val isFavourite = favouritesRepository.getRecipeById(recipeDetails.id) != null
                state = state.copy(
                    isLoading = false,
                    details = recipeDetails,
                    isFavourite = isFavourite
                )
            }

            RecipeDetailsEvent.OnAddToFavouritesClicked -> scope.launch {
                if (state.isFavourite) {
                    runSuspendCatching { state.details?.id?.let { favouritesRepository.removeRecipe(it) } }
                        .onSuccess { state = state.copy(isFavourite = false) }
                } else {
                    runSuspendCatching { state.details?.let { favouritesRepository.addRecipe(it) } }
                        .onSuccess { state = state.copy(isFavourite = true) }
                }
            }
        }
    }
}
