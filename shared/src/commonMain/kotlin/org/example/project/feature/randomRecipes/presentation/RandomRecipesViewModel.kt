package org.example.project.feature.randomRecipes.presentation

import kotlinx.coroutines.launch
import org.example.project.core.viewmodel.BaseViewModel
import org.example.project.di.PlatformSDK
import org.example.project.feature.common.data.RecipesRepository
import org.example.project.feature.utils.runSuspendCatching

private const val RANDOM_RECIPES_NUMBER = 100

class RandomRecipesViewModel : BaseViewModel<RandomRecipesState, RandomRecipesEvent, RandomRecipesAction>(
    initState = RandomRecipesState(
        isLoading = false,
        randomRecipes = emptyList(),
    )
) {
    init {
        obtainEvent(RandomRecipesEvent.OnInit)
    }

    private val recipesRepository: RecipesRepository by PlatformSDK.lazyInstance()

    override fun obtainEvent(event: RandomRecipesEvent) {
        when (event) {
            RandomRecipesEvent.OnInit -> scope.launch {
                state = state.copy(isLoading = true)
                val randomRecipes = runSuspendCatching {
                    recipesRepository.getRandomRecipes(RANDOM_RECIPES_NUMBER)
                }.fold(
                    onSuccess = { it },
                    onFailure = { return@launch }
                )
                state = state.copy(
                    isLoading = false,
                    randomRecipes = randomRecipes,
                )
            }

            is RandomRecipesEvent.OnRecipeClicked -> action = RandomRecipesAction.OpenRecipe(event.recipeId)
        }
    }
}
