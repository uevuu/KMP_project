package org.example.project.feature.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import org.example.project.feature.common.domain.RecipeModel
import org.example.project.feature.main.presentation.MainAction
import org.example.project.feature.main.presentation.MainEvent
import org.example.project.feature.main.presentation.MainState
import org.example.project.feature.main.presentation.MainViewModel
import org.example.project.feature.navigation.createRecipeDetailsRoute
import org.example.project.feature.navigation.favourites
import org.example.project.feature.recipeDetails.presentation.RecipeDetailsEvent
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

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else {
        MainContent(state, consumer)
    }

    MainAction(action, navController)
}

@Composable
fun MainAction(action: MainAction?, navController: NavController) {
    LaunchedEffect(action) {
        when (action) {
            MainAction.MainFailure -> Unit
            is MainAction.OpenRecipe -> navController.navigate(createRecipeDetailsRoute(action.recipeId))
            MainAction.OpenFavourites -> navController.navigate(favourites)
            null -> Unit
        }
    }
}

@Composable
fun MainContent(state: MainState, eventConsumer: (MainEvent) -> Unit) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .align(Alignment.Center)
        ) {
            SearchField()
            WineTypesList(state, eventConsumer)
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = { eventConsumer(MainEvent.OnFavouriteRecipesClicked) },
        ) {
            Icon(Icons.Filled.Favorite, null)
        }
    }
}

@Composable
fun SearchField() {
    TextField(
        value = "",
        onValueChange = {},
        enabled = false,
        placeholder = { Text(text = "Search...") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                // Navigate to another screen
            },
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.Transparent,
            cursorColor = Color.Transparent,
            disabledContainerColor = Color.Gray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun RandomRecipesList(recipes: List<RecipeModel>, eventConsumer: (MainEvent) -> Unit) {
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(recipes, key = { it.id }) { recipe ->
            Card(
                modifier = Modifier
                    .width(200.dp)
                    .height(150.dp)
                    .padding(end = 16.dp)
                    .clickable {
                        eventConsumer(MainEvent.OnRecipeClicked(recipe.id))
                    },
                shape = RoundedCornerShape(8.dp),
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
                            .fillMaxWidth()
                            .height(100.dp)
                    )
                    Text(
                        text = recipe.title,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun WineTypesList(state: MainState, eventConsumer: (MainEvent) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            RandomRecipesList(state.randomRecipes, eventConsumer)
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(state.wineTypes, key = { it.value }) { wineType ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(
                    text = wineType.title,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    fontSize = 18.sp
                )
            }
        }
    }
}
