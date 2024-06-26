package org.example.project.feature.search.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.example.project.core.viewmodel.BaseViewModel
import org.example.project.di.PlatformSDK
import org.example.project.feature.search.data.SearchRecipesRepository
import org.example.project.feature.utils.runSuspendCatching

private const val DEFAULT_SEARCH_DEBOUNCE = 400L

class SearchRecipesViewModel : BaseViewModel<SearchRecipesState, SearchRecipesEvent, SearchRecipesAction>(
    initState = SearchRecipesState(
        isLoading = false,
        result = emptyList(),
        query = "",
    )
) {

    private val queryFlow = MutableStateFlow("")
    private val searchRecipesRepository: SearchRecipesRepository by PlatformSDK.lazyInstance()

    init {
        queryFlow
            .onEach { state = state.copy(isLoading = true) }
            .debounce(DEFAULT_SEARCH_DEBOUNCE)
            .distinctUntilChanged()
            .filter { it.isNotBlank() }
            .onEach { searchRecipes(it) }
            .launchIn(scope)
    }

    override fun obtainEvent(event: SearchRecipesEvent) {
        when (event) {
            is SearchRecipesEvent.OnQueryChanged -> scope.launch {
                queryFlow.emit(event.query)
                state = state.copy(query = event.query)
            }

            is SearchRecipesEvent.OnRecipeClicked -> action = SearchRecipesAction.OpenRecipe(event.recipeId)
        }
    }

    private suspend fun searchRecipes(query: String) {
        val searchResults = runSuspendCatching { searchRecipesRepository.searchRecipes(query) }.fold(
            onSuccess = { it },
            onFailure = {
                println(it.stackTraceToString())
                action = SearchRecipesAction.SearchRecipesFailure
                return
            }
        )
        state = state.copy(
            isLoading = false,
            result = searchResults,
        )
    }
}
