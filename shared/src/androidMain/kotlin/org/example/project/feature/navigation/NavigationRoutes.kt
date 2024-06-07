package org.example.project.feature.navigation

internal const val mainRoute = "mainRoute"
internal const val recipeDetailsArg = "recipeId"
internal const val recipeDetailsRoute = "recipeDetailsRoute/{$recipeDetailsArg}"
internal const val favourites = "favourites"
internal fun createRecipeDetailsRoute(recipeId: Int) = "recipeDetailsRoute/$recipeId"
