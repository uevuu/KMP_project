package org.example.project.di.modules

import org.example.project.feature.auth.presentation.AuthViewModel
import org.example.project.feature.favourites.presentation.FavouriteRecipesViewModel
import org.example.project.feature.main.presentation.MainViewModel
import org.example.project.feature.randomRecipes.presentation.RandomRecipesViewModel
import org.example.project.feature.recipeDetails.presentation.RecipeDetailsViewModel
import org.example.project.feature.recommendedWines.presentation.RecommendedWinesViewModel
import org.example.project.feature.search.presentation.SearchRecipesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module  = module {
    viewModel<MainViewModel> { MainViewModel() }
    viewModel<AuthViewModel> { AuthViewModel() }
    viewModel<FavouriteRecipesViewModel> { FavouriteRecipesViewModel() }
    viewModel<RandomRecipesViewModel> { RandomRecipesViewModel() }
    viewModel<RecipeDetailsViewModel> { RecipeDetailsViewModel() }
    viewModel<RecommendedWinesViewModel> { RecommendedWinesViewModel() }
    viewModel<SearchRecipesViewModel> { SearchRecipesViewModel() }
}
