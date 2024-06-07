package org.example.project.feature.randomRecipes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import org.example.project.feature.navigation.createRecipeDetailsRoute
import org.example.project.feature.randomRecipes.presentation.RandomRecipesAction
import org.example.project.feature.randomRecipes.presentation.RandomRecipesEvent
import org.example.project.feature.randomRecipes.presentation.RandomRecipesState
import org.example.project.feature.randomRecipes.presentation.RandomRecipesViewModel
import org.example.project.utils.rememberClick

@Composable
fun RandomRecipesScreen(
    viewModel: RandomRecipesViewModel = viewModel(),
    navController: NavController
) {
    val state by viewModel.states.collectAsStateWithLifecycle()
    val action by viewModel.actions.collectAsStateWithLifecycle(initialValue = null)
    val consumer = rememberClick<RandomRecipesEvent> { viewModel.obtainEvent(it) }

    Column {
        Text(
            text = "Рандомные рецепты",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(16.dp)
        )
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            RandomRecipesList(state, consumer)
        }
    }

    RandomRecipesAction(action, navController)
}

@Composable
private fun RandomRecipesAction(action: RandomRecipesAction?, navController: NavController) {
    LaunchedEffect(action) {
        when (action) {
            RandomRecipesAction.RandomRecipesFailure -> Unit
            is RandomRecipesAction.OpenRecipe -> navController.navigate(createRecipeDetailsRoute(action.recipeId))
            null -> Unit
        }
    }
}

@Composable
private fun RandomRecipesList(state: RandomRecipesState, eventConsumer: (RandomRecipesEvent) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        items(state.randomRecipes, key = { it.id }) { recipe ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        eventConsumer(RandomRecipesEvent.OnRecipeClicked(recipe.id))
                    },
                shape = MaterialTheme.shapes.medium,
            ) {
                Row {
                    AsyncImage(
                        model = recipe.image,
                        contentDescription = null
                    )
                    Text(
                        text = recipe.title,
                        modifier = Modifier
                            .padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
