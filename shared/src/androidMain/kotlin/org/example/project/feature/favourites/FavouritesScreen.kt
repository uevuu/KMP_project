package org.example.project.feature.favourites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import org.example.project.feature.favourites.presentation.FavouriteRecipesAction
import org.example.project.feature.favourites.presentation.FavouriteRecipesEvent
import org.example.project.feature.favourites.presentation.FavouriteRecipesState
import org.example.project.feature.favourites.presentation.FavouriteRecipesViewModel
import org.example.project.feature.navigation.createRecipeDetailsRoute
import org.example.project.utils.rememberClick

@Composable
fun FavouritesScreen(
    viewModel: FavouriteRecipesViewModel = viewModel(),
    navController: NavController
) {
    val state by viewModel.states.collectAsStateWithLifecycle()
    val action by viewModel.actions.collectAsStateWithLifecycle(initialValue = null)
    val consumer = rememberClick<FavouriteRecipesEvent> { viewModel.obtainEvent(it) }

    LaunchedEffect(key1 = Unit) {
        viewModel.obtainEvent(FavouriteRecipesEvent.OnInit)
    }

    Column {
        Text(
            text = "Избранные рецепты",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(16.dp)
        )
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            FavouriteRecipesList(state, consumer)
        }
    }

    FavouritesAction(action, navController)
}

@Composable
private fun FavouritesAction(action: FavouriteRecipesAction?, navController: NavController) {
    LaunchedEffect(action) {
        when (action) {
            FavouriteRecipesAction.FavouriteRecipesFailure -> Unit
            is FavouriteRecipesAction.OpenRecipe -> navController.navigate(createRecipeDetailsRoute(action.recipeId))
            null -> Unit
        }
    }
}

@Composable
private fun FavouriteRecipesList(state: FavouriteRecipesState, eventConsumer: (FavouriteRecipesEvent) -> Unit) {
    if (state.result.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(modifier = Modifier.align(Alignment.Center), text = "У вас нет избранных")
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            items(state.result, key = { it.id }) { recipe ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .clickable {
                            eventConsumer(FavouriteRecipesEvent.OnRecipeClicked(recipe.id))
                        },
                    shape = MaterialTheme.shapes.medium,
                ) {
                    Row {
                        AsyncImage(
                            model = recipe.image,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(150.dp)
                                .width(200.dp)
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
}
