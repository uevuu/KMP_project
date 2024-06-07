package org.example.project.feature.recommendedWines

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import org.example.project.feature.recommendedWines.presentation.RecommendedWinesAction
import org.example.project.feature.recommendedWines.presentation.RecommendedWinesEvent
import org.example.project.feature.recommendedWines.presentation.RecommendedWinesState
import org.example.project.feature.recommendedWines.presentation.RecommendedWinesViewModel
import org.example.project.utils.rememberClick

@Composable
fun RecommendedWinesScreen(
    viewModel: RecommendedWinesViewModel = viewModel(),
    navController: NavController,
    wineType: String,
    wineTypeName: String
) {
    val state by viewModel.states.collectAsStateWithLifecycle()
    val action by viewModel.actions.collectAsStateWithLifecycle(initialValue = null)
    val consumer = rememberClick<RecommendedWinesEvent> { viewModel.obtainEvent(it) }

    LaunchedEffect(Unit) {
        viewModel.obtainEvent(RecommendedWinesEvent.OnInit(wineType))
    }

    Column {
        Text(
            text = "Сорт вина: $wineTypeName",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(16.dp)
        )
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            RecommendedWinesList(state, consumer)
        }
    }

    RecommendedWinesAction(action, navController)
}

@Composable
private fun RecommendedWinesAction(action: RecommendedWinesAction?, navController: NavController) {
    LaunchedEffect(action) {
        when (action) {
            RecommendedWinesAction.RecommendedWinesFailure -> Unit
            null -> Unit
        }
    }
}

@Composable
private fun RecommendedWinesList(state: RecommendedWinesState, eventConsumer: (RecommendedWinesEvent) -> Unit) {
    if (state.recommendedWines.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(modifier = Modifier.align(Alignment.Center), text = "Таких вин не найдено :(")
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 2),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            items(state.recommendedWines, key = { it.id }) { wine ->
                Card(
                    modifier = Modifier
                        .padding(4.dp)
                        .height(300.dp),
                    shape = MaterialTheme.shapes.medium,
                ) {
                    Column {
                        AsyncImage(
                            model = wine.imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier
                                .height(200.dp)
                                .width(150.dp)
                        )
                        Text(
                            text = wine.title,
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
