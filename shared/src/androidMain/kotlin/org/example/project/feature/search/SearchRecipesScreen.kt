package org.example.project.feature.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import org.example.project.feature.navigation.createRecipeDetailsRoute
import org.example.project.feature.search.presentation.SearchRecipesAction
import org.example.project.feature.search.presentation.SearchRecipesEvent
import org.example.project.feature.search.presentation.SearchRecipesState
import org.example.project.feature.search.presentation.SearchRecipesViewModel
import org.example.project.utils.rememberClick
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchRecipesScreen(
    viewModel: SearchRecipesViewModel = koinViewModel(),
    navController: NavController
) {
    val state by viewModel.states.collectAsStateWithLifecycle()
    val action by viewModel.actions.collectAsStateWithLifecycle(initialValue = null)
    val consumer = rememberClick<SearchRecipesEvent> { viewModel.obtainEvent(it) }

    Column {
        Text(
            text = "Поиск рецептов",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(16.dp)
        )
        SearchRecipesList(state, consumer)
    }

    SearchAction(action, navController)
}

@Composable
private fun SearchAction(action: SearchRecipesAction?, navController: NavController) {
    LaunchedEffect(action) {
        when (action) {
            SearchRecipesAction.SearchRecipesFailure -> Unit
            is SearchRecipesAction.OpenRecipe -> navController.navigate(createRecipeDetailsRoute(action.recipeId))
            null -> Unit
        }
    }
}

@Composable
private fun SearchRecipesList(state: SearchRecipesState, eventConsumer: (SearchRecipesEvent) -> Unit) {
    Column {
        SearchField(state, eventConsumer)
        Spacer(modifier = Modifier.height(16.dp))
        if (state.result.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(modifier = Modifier.align(Alignment.Center), text = "Ничего не найдено :(")
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
                                eventConsumer(SearchRecipesEvent.OnRecipeClicked(recipe.id))
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
}

@Composable
fun SearchField(state: SearchRecipesState, eventConsumer: (SearchRecipesEvent) -> Unit) {
    TextField(
        value = state.query,
        onValueChange = { eventConsumer(SearchRecipesEvent.OnQueryChanged(it)) },
        placeholder = { Text(text = "Найти...") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(MaterialTheme.shapes.large),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}
