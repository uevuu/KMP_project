package org.example.project.feature.auth.presentation

import kotlinx.coroutines.launch
import org.example.project.core.viewmodel.BaseViewModel
import org.example.project.di.PlatformSDK
import org.example.project.feature.common.data.AuthRepository
import org.example.project.feature.utils.runSuspendCatching

class AuthViewModel : BaseViewModel<AuthState, AuthEvent, AuthAction>(
    initState = AuthState(
        name = "",
        password = "",
        isLoading = true,
        isError = false,
    )
) {

    private val authRepository: AuthRepository by PlatformSDK.lazyInstance()

    override fun obtainEvent(event: AuthEvent) {
        when (event) {
            AuthEvent.OnInit -> scope.launch {
                runSuspendCatching { authRepository.isUserAuthorized() }
                    .onSuccess {
                        if (it) {
                            action = AuthAction.AuthSuccess
                        } else {
                            state = state.copy(isLoading = false)
                        }
                    }.onFailure { state = state.copy(isLoading = false) }
            }

            AuthEvent.OnLoginClicked -> scope.launch {
                state = state.copy(isError = false)
                runSuspendCatching {
                    authRepository.loginUser(state.name, state.password)
                }.fold(
                    onSuccess = { action = AuthAction.AuthSuccess },
                    onFailure = { state = state.copy(isError = true) }
                )
                state = state.copy(isLoading = false)
            }

            AuthEvent.OnRegisterClicked -> scope.launch {
                state = state.copy(isError = false)
                runSuspendCatching {
                    authRepository.registerUser(state.name, state.password)
                }.fold(
                    onSuccess = { action = AuthAction.AuthSuccess },
                    onFailure = {
                        println(it.stackTraceToString())
                        state = state.copy(isError = true)
                    }
                )
                state = state.copy(isLoading = false)
            }

            is AuthEvent.OnNameChanged -> state = state.copy(name = event.newName)
            is AuthEvent.OnPasswordChanged -> state = state.copy(password = event.newPassword)
        }
    }
}
