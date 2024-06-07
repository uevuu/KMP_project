package org.example.project.feature.main.presentation

import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.example.project.core.binding.FirebaseAnalyticsBinding
import org.example.project.core.viewmodel.BaseViewModel
import org.example.project.di.PlatformSDK
import org.example.project.feature.common.data.AuthRepository
import org.example.project.feature.common.data.RecipesRepository
import org.example.project.feature.main.data.MainRepository
import org.example.project.feature.utils.runSuspendCatching

private const val RANDOM_RECIPES_NUMBER = 20

class MainViewModel : BaseViewModel<MainState, MainEvent, MainAction>(
    initState = MainState(
        isLoading = false,
        randomRecipes = emptyList(),
        wineTypes = emptyList(),
    )
) {

    private val mainRepository: MainRepository by PlatformSDK.lazyInstance()
    private val recipesRepository: RecipesRepository by PlatformSDK.lazyInstance()
    private val authRepository: AuthRepository by PlatformSDK.lazyInstance()
    private val firebaseAnalyticsBinding: FirebaseAnalyticsBinding by PlatformSDK.lazyInstance()

    init {
        obtainEvent(MainEvent.OnInit)
    }

    override fun obtainEvent(event: MainEvent) {
        when (event) {
            MainEvent.OnInit -> scope.launch {
                state = state.copy(isLoading = true)
                val randomRecipesDeferred = async {
                    runSuspendCatching { recipesRepository.getRandomRecipes(RANDOM_RECIPES_NUMBER) }
                }
                val wineTypesDeferred = async { runSuspendCatching { mainRepository.getWineTypes() } }
                val randomRecipes = randomRecipesDeferred.await().fold(
                    onSuccess = { it },
                    onFailure = {
                        action = MainAction.MainFailure
                        return@launch
                    }
                )
                val wineTypes = wineTypesDeferred.await().fold(
                    onSuccess = { it },
                    onFailure = {
                        action = MainAction.MainFailure
                        return@launch
                    }
                )
                firebaseAnalyticsBinding.logScreenLoaded("main")
                state = state.copy(
                    isLoading = false,
                    randomRecipes = randomRecipes,
                    wineTypes = wineTypes
                )
            }

            is MainEvent.OnRecipeClicked -> action = MainAction.OpenRecipe(event.recipeId)
            MainEvent.OnFavouriteRecipesClicked -> action = MainAction.OpenFavourites
            MainEvent.OnMoreRandomRecipesClicked -> action = MainAction.OpenRandomRecipes
            is MainEvent.OnWineTypeClicked -> action = MainAction.OpenRecommendedWines(event.wineType)
            MainEvent.OnExitClicked -> scope.launch {
                runSuspendCatching { authRepository.signOutCurrentUser() }
                    .onSuccess { action = MainAction.OpenAuth }
            }

            MainEvent.OnSearchClicked -> action = MainAction.OpenSearch
        }
    }
}
