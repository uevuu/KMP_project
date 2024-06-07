package org.example.project.feature.favourites.presentation

import kotlinx.coroutines.launch
import org.example.project.core.viewmodel.BaseViewModel
import org.example.project.di.PlatformSDK
import org.example.project.feature.common.data.AuthRepository
import org.example.project.feature.common.data.FavouritesRepository
import org.example.project.feature.utils.runSuspendCatching

class FavouriteRecipesViewModel : BaseViewModel<FavouriteRecipesState, FavouriteRecipesEvent, FavouriteRecipesAction>(
    initState = FavouriteRecipesState(
        isLoading = false,
        result = emptyList(),
    )
) {
    private val favouritesRepository: FavouritesRepository by PlatformSDK.lazyInstance()
    private val authRepository: AuthRepository by PlatformSDK.lazyInstance()

    override fun obtainEvent(event: FavouriteRecipesEvent) {
        when (event) {
            FavouriteRecipesEvent.OnInit -> scope.launch {
                state = state.copy(isLoading = true)
                val userId = runSuspendCatching { requireNotNull(authRepository.getCurrentUserId()) }.fold(
                    onSuccess = { it },
                    onFailure = {
                        action = FavouriteRecipesAction.FavouriteRecipesFailure
                        return@launch
                    }
                )
                val favouriteRecipes = runSuspendCatching { favouritesRepository.getAllRecipes(userId) }.fold(
                    onSuccess = { it },
                    onFailure = {
                        action = FavouriteRecipesAction.FavouriteRecipesFailure
                        return@launch
                    }
                )
                state = state.copy(
                    isLoading = false,
                    result = favouriteRecipes,
                )
            }

            is FavouriteRecipesEvent.OnRecipeClicked -> action = FavouriteRecipesAction.OpenRecipe(event.recipeId)
        }
    }
}
