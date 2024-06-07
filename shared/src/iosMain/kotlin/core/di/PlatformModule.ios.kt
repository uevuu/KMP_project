package org.example.project.core.di

import org.example.project.core.binding.FirebaseCrashlyticsBindings
import org.example.project.core.configuration.Configuration
import org.example.project.core.configuration.PlatformConfiguration
import org.example.project.di.PlatformSDK
import org.example.project.feature.auth.presentation.AuthViewModel
import org.example.project.feature.favourites.presentation.FavouriteRecipesViewModel
import org.example.project.feature.main.presentation.MainViewModel
import org.example.project.feature.randomRecipes.presentation.RandomRecipesViewModel
import org.example.project.feature.recipeDetails.presentation.RecipeDetailsViewModel
import org.example.project.feature.recommendedWines.presentation.RecommendedWinesViewModel
import org.kodein.di.instance

object DependenciesFactory {
    fun create(): Dependencies = DependenciesImpl()
}

interface Dependencies {
    fun provideAuthViewModel(): AuthViewModel
    fun provideMainViewModel(): MainViewModel
    fun provideRecipeDetailsViewModel(): RecipeDetailsViewModel
    fun provideRecommendedWinesViewModel(): RecommendedWinesViewModel
    fun provideRandomRecipesViewModel(): RandomRecipesViewModel
    fun provideFavouriteRecipesViewModel(): FavouriteRecipesViewModel
}

class DependenciesImpl : Dependencies {

    init {
        PlatformSDK.init(
            Configuration(
                platformConfiguration = PlatformConfiguration(
                    appVersionName = "",
                    appVersionNumber = "",
                    osVersion = ""
                ),
                isHttpLoggingEnabled = false,
                isDebug = false,
                firebaseCrashlyticsBindings = object : FirebaseCrashlyticsBindings {
                    override fun nonFatal(error: Throwable) {}
                    override fun setParams(key: String, value: String) {}
                }
            )
        )
    }

    override fun provideAuthViewModel(): AuthViewModel {
        return PlatformSDK.di.instance()
    }

    override fun provideMainViewModel(): MainViewModel {
        return PlatformSDK.di.instance()
    }

    override fun provideRecipeDetailsViewModel(): RecipeDetailsViewModel {
        return PlatformSDK.di.instance()
    }

    override fun provideRecommendedWinesViewModel(): RecommendedWinesViewModel {
        return PlatformSDK.di.instance()
    }

    override fun provideRandomRecipesViewModel(): RandomRecipesViewModel {
        return PlatformSDK.di.instance()
    }

    override fun provideFavouriteRecipesViewModel(): FavouriteRecipesViewModel {
        return PlatformSDK.di.instance()
    }
}
