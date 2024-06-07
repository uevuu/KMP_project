package org.example.project.feature.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import org.example.project.feature.common.domain.RecipeModel
import org.example.project.feature.main.presentation.MainAction
import org.example.project.feature.main.presentation.MainEvent
import org.example.project.feature.main.presentation.MainState
import org.example.project.feature.main.presentation.MainViewModel
import org.example.project.feature.navigation.authRoute
import org.example.project.feature.navigation.createRecipeDetailsRoute
import org.example.project.feature.navigation.createRecommendedWinesRoute
import org.example.project.feature.navigation.favouritesRoute
import org.example.project.feature.navigation.mainRoute
import org.example.project.feature.navigation.randomRecipesRoute
import org.example.project.utils.rememberClick

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel(),
    navController: NavController
) {
    val state by viewModel.states.collectAsStateWithLifecycle()
    val action by viewModel.actions.collectAsStateWithLifecycle(initialValue = null)
    val consumer = rememberClick<MainEvent> { viewModel.obtainEvent(it) }

    LaunchedEffect(Unit) {
        viewModel.obtainEvent(MainEvent.OnInit)
    }

    Column {
        Text(
            text = "Главная",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(16.dp)
        )
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            MainContent(state, consumer)
        }
    }

    MainAction(action, navController)
}

@Composable
private fun MainAction(action: MainAction?, navController: NavController) {
    LaunchedEffect(action) {
        when (action) {
            MainAction.MainFailure -> Unit
            is MainAction.OpenRecipe -> navController.navigate(createRecipeDetailsRoute(action.recipeId))
            MainAction.OpenFavourites -> navController.navigate(favouritesRoute)
            MainAction.OpenRandomRecipes -> navController.navigate(randomRecipesRoute)
            is MainAction.OpenRecommendedWines -> navController.navigate(
                createRecommendedWinesRoute(
                    wineType = action.wineType.value,
                    wineTypeName = action.wineType.title
                )
            )

            MainAction.OpenAuth -> navController.navigate(authRoute) {
                popUpTo(mainRoute) { inclusive = true }
            }

            null -> Unit
        }
    }
}

@Composable
private fun MainContent(state: MainState, eventConsumer: (MainEvent) -> Unit) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        ) {
            SearchField(eventConsumer)
            WineTypesList(state, eventConsumer)
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(32.dp),
            onClick = { eventConsumer(MainEvent.OnFavouriteRecipesClicked) },
        ) {
            Icon(Icons.Filled.Favorite, null)
        }
    }
}

@Composable
private fun SearchField(eventConsumer: (MainEvent) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(top = 8.dp, start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(modifier = Modifier.weight(1f)) {
            TextField(
                value = "",
                onValueChange = {},
                enabled = false,
                placeholder = { Text(text = "Найти...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp)
                    .clip(MaterialTheme.shapes.large),
                colors = TextFieldDefaults.colors(
                    disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }
        Box(modifier = Modifier.size(46.dp)) {
            Icon(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .clickable { eventConsumer(MainEvent.OnExitClicked) }
                    .padding(4.dp)
                    .fillMaxSize(),
                imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                tint = MaterialTheme.colorScheme.secondary,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun RandomRecipesList(recipes: List<RecipeModel>, eventConsumer: (MainEvent) -> Unit) {
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(recipes, key = { it.id }) { recipe ->
            Card(
                modifier = Modifier
                    .height(150.dp)
                    .width(200.dp)
                    .padding(start = 16.dp, end = if (recipes.last().id == recipe.id) 16.dp else 0.dp)
                    .clickable {
                        eventConsumer(MainEvent.OnRecipeClicked(recipe.id))
                    },
                shape = MaterialTheme.shapes.medium,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = recipe.image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(100.dp)
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.medium)
                            .padding(bottom = 8.dp),
                    )
                    Text(
                        text = recipe.title,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                }
            }
        }
    }
}

@Composable
private fun WineTypesList(state: MainState, eventConsumer: (MainEvent) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Посмотреть еще",
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .align(Alignment.CenterEnd)
                        .clip(MaterialTheme.shapes.medium)
                        .clickable { eventConsumer(MainEvent.OnMoreRandomRecipesClicked) }
                        .padding(horizontal = 4.dp)
                )
            }
            RandomRecipesList(state.randomRecipes, eventConsumer)
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Text(
                text = "Сорта вин",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
            )
        }
        items(state.wineTypes, key = { it.value }) { wineType ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .clickable {
                        eventConsumer(MainEvent.OnWineTypeClicked(wineType))
                    },
                shape = MaterialTheme.shapes.medium,
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = wineType.title,
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        text = wineType.description,
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
