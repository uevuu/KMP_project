package org.example.project.feature.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.example.project.feature.auth.AuthScreen
import org.example.project.feature.favourites.FavouritesScreen
import org.example.project.feature.main.MainScreen
import org.example.project.feature.randomRecipes.RandomRecipesScreen
import org.example.project.feature.recipeDetails.RecipeDetailsScreen
import org.example.project.feature.recommendedWines.RecommendedWinesScreen
import org.example.project.feature.search.SearchRecipesScreen

@Composable
fun NavigationHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = authRoute) {
        composable(authRoute) {
            AuthScreen(navController = navController)
        }
        composable(mainRoute) {
            MainScreen(navController = navController)
        }
        composable(
            recipeDetailsRoute,
            listOf(navArgument(recipeDetailsArg) { type = NavType.IntType })
        ) {
            it.arguments?.getInt(recipeDetailsArg)?.let { recipeId ->
                RecipeDetailsScreen(recipeId = recipeId)
            }
        }
        composable(favouritesRoute) {
            FavouritesScreen(navController = navController)
        }
        composable(randomRecipesRoute) {
            RandomRecipesScreen(navController = navController)
        }
        composable(
            recommendedWinesRoute,
            listOf(
                navArgument(recommendedWinesArg) { type = NavType.StringType },
                navArgument(recommendedWinesNameArg) { type = NavType.StringType },
            )
        ) {
            val wineTypeName = it.arguments?.getString(recommendedWinesNameArg).orEmpty()
            it.arguments?.getString(recommendedWinesArg)?.let { wineType ->
                RecommendedWinesScreen(
                    navController = navController,
                    wineType = wineType,
                    wineTypeName = wineTypeName
                )
            }
        }
        composable(searchRecipesRoute) {
            SearchRecipesScreen(navController = navController)
        }
    }
}
