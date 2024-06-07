package org.example.project.feature.recommendedWines.presentation

import org.example.project.feature.recommendedWines.domain.entities.WineModel

data class RecommendedWinesState(
    val isLoading: Boolean,
    val recommendedWines: List<WineModel>,
)

sealed class RecommendedWinesAction {
    data object RecommendedWinesFailure : RecommendedWinesAction()
}

sealed class RecommendedWinesEvent {
    data class OnInit(val wineType: String) : RecommendedWinesEvent()
}
