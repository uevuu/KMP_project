package org.example.project.feature.auth.presentation

data class AuthState(
    val name: String,
    val password: String,
    val isLoading: Boolean,
)

sealed class AuthAction {
    data object AuthSuccess : AuthAction()
    data object AuthFailure : AuthAction()
}

sealed class AuthEvent {
    data object OnLoginClicked : AuthEvent()
    data object OnRegisterClicked : AuthEvent()
}
