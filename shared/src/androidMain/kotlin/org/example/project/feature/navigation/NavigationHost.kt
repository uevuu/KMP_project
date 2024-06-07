package org.example.project.feature.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.example.project.feature.main.MainScreen
import org.example.project.feature.recipeDetails.RecipeDetailsScreen

@Composable
fun NavigationHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = mainRoute) {
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
        composable(favourites) {}
    }
}
