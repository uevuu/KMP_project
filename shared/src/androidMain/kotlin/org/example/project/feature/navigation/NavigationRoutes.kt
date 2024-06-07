package org.example.project.feature.navigation

internal const val authRoute = "authRoute"
internal const val mainRoute = "mainRoute"
internal const val recipeDetailsArg = "recipeId"
internal const val recipeDetailsRoute = "recipeDetailsRoute/{$recipeDetailsArg}"
internal const val favouritesRoute = "favouritesRoute"
internal const val randomRecipesRoute = "randomRecipesRoute"
internal const val recommendedWinesArg = "wineType"
internal const val recommendedWinesNameArg = "wineTypeName"
internal const val recommendedWinesRoute = "recommendedWinesRoute/{$recommendedWinesArg}/{$recommendedWinesNameArg}"
internal const val searchRecipesRoute = "searchRecipesRoute"

internal fun createRecipeDetailsRoute(recipeId: Int) = "recipeDetailsRoute/$recipeId"
internal fun createRecommendedWinesRoute(wineType: String, wineTypeName: String) =
    "recommendedWinesRoute/$wineType/$wineTypeName"
