package org.example.project.feature.recommendedWines.presentation

import kotlinx.coroutines.launch
import org.example.project.core.viewmodel.BaseViewModel
import org.example.project.di.PlatformSDK
import org.example.project.feature.recommendedWines.data.WinesRepository
import org.example.project.feature.utils.runSuspendCatching

class RecommendedWinesViewModel : BaseViewModel<RecommendedWinesState, RecommendedWinesEvent, RecommendedWinesAction>(
    initState = RecommendedWinesState(
        isLoading = false,
        recommendedWines = emptyList(),
    )
) {
    private val winesRepository: WinesRepository by PlatformSDK.lazyInstance()

    override fun obtainEvent(event: RecommendedWinesEvent) {
        when (event) {
            is RecommendedWinesEvent.OnInit -> scope.launch {
                state = state.copy(isLoading = true)
                val recommendedWines = runSuspendCatching { winesRepository.getRecommendedWines(event.wineType) }.fold(
                    onSuccess = { it },
                    onFailure = {
                        action = RecommendedWinesAction.RecommendedWinesFailure
                        return@launch
                    }
                )
                state = state.copy(
                    isLoading = false,
                    recommendedWines = recommendedWines,
                )
            }
        }
    }
}
