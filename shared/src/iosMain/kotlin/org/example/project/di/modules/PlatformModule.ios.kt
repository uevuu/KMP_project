package org.example.project.di.modules

import org.example.project.feature.auth.presentation.AuthViewModel
import org.example.project.feature.favourites.presentation.FavouriteRecipesViewModel
import org.example.project.feature.main.presentation.MainViewModel
import org.example.project.feature.randomRecipes.presentation.RandomRecipesViewModel
import org.example.project.feature.recipeDetails.presentation.RecipeDetailsViewModel
import org.example.project.feature.recommendedWines.presentation.RecommendedWinesViewModel
import org.example.project.feature.search.presentation.SearchRecipesViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {

    single<MainViewModel> { MainViewModel() }
    single<AuthViewModel> { AuthViewModel() }
    single<FavouriteRecipesViewModel> { FavouriteRecipesViewModel() }
    single<RandomRecipesViewModel> { RandomRecipesViewModel() }
    single<RecipeDetailsViewModel> { RecipeDetailsViewModel() }
    single<RecommendedWinesViewModel> { RecommendedWinesViewModel() }
    single<SearchRecipesViewModel> { SearchRecipesViewModel() }
}

object ViewModels : KoinComponent {
    fun getMainViewModel() = get<MainViewModel>()
    fun getAuthViewModel() = get<AuthViewModel>()
    fun getFavouriteRecipesViewModel() = get<FavouriteRecipesViewModel>()
    fun getRandomRecipesViewModel() = get<RandomRecipesViewModel>()
    fun getRecipeDetailsViewModel() = get<RecipeDetailsViewModel>()
    fun getRecommendedWinesViewModel() = get<RecommendedWinesViewModel>()
    fun getSearchRecipesViewModel() = get<SearchRecipesViewModel>()
}
