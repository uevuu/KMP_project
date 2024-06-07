package org.example.project.feature.auth.presentation

import kotlinx.coroutines.launch
import org.example.project.core.viewmodel.BaseViewModel
import org.example.project.di.PlatformSDK
import org.example.project.feature.auth.data.AuthRepository
import org.example.project.feature.utils.runSuspendCatching

class AuthViewModel : BaseViewModel<AuthState, AuthEvent, AuthAction>(
    initState = AuthState(
        name = "",
        password = "",
        isLoading = false
    )
) {

    private val authRepository: AuthRepository by PlatformSDK.lazyInstance()

    override fun obtainEvent(event: AuthEvent) {
        when (event) {
            AuthEvent.OnLoginClicked -> scope.launch {
                runSuspendCatching {
                    state = state.copy(isLoading = true)
                    authRepository.loginUser(state.name, state.password)
                }.fold(
                    onSuccess = { action = AuthAction.AuthSuccess },
                    onFailure = { action = AuthAction.AuthFailure }
                )
                state = state.copy(isLoading = false)
            }

            AuthEvent.OnRegisterClicked -> scope.launch {
                runSuspendCatching {
                    state = state.copy(isLoading = true)
                    authRepository.registerUser(state.name, state.password)
                }.fold(
                    onSuccess = { action = AuthAction.AuthSuccess },
                    onFailure = { action = AuthAction.AuthFailure }
                )
                state = state.copy(isLoading = false)
            }
        }
    }
}
