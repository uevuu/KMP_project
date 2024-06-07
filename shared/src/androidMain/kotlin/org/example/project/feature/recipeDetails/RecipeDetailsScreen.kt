package org.example.project.feature.recipeDetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import org.example.project.feature.common.domain.RecipeModel
import org.example.project.feature.recipeDetails.presentation.RecipeDetailsEvent
import org.example.project.feature.recipeDetails.presentation.RecipeDetailsViewModel
import org.example.project.utils.formatHtml
import org.example.project.utils.rememberClick

@Composable
fun RecipeDetailsScreen(
    viewModel: RecipeDetailsViewModel = viewModel(),
    recipeId: Int,
) {
    val state by viewModel.states.collectAsStateWithLifecycle()
    val action by viewModel.actions.collectAsStateWithLifecycle(initialValue = null)
    val consumer = rememberClick<RecipeDetailsEvent> { viewModel.obtainEvent(it) }

    LaunchedEffect(Unit) {
        viewModel.obtainEvent(RecipeDetailsEvent.OnInitWithId(recipeId))
    }

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else {
        state.details?.let { RecipeDetailsContent(it, state.isFavourite, consumer) }
    }
}

@Composable
private fun RecipeDetailsContent(
    recipe: RecipeModel,
    isFavourite: Boolean,
    eventConsumer: (RecipeDetailsEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            AsyncImage(
                model = recipe.image,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .align(Alignment.Center)
            )
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                onClick = { eventConsumer(RecipeDetailsEvent.OnAddToFavouritesClicked) },
            ) {
                Icon(if (isFavourite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder, null)
            }
        }
        Text(
            text = recipe.title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = recipe.summary.formatHtml(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
