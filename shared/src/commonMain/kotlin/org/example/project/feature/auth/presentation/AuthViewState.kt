package org.example.project.feature.auth.presentation

data class AuthState(
    val name: String,
    val password: String,
    val isLoading: Boolean,
    val isError: Boolean,
)

sealed class AuthAction {
    data object AuthSuccess : AuthAction()
}

sealed class AuthEvent {
    data object OnInit : AuthEvent()
    data class OnNameChanged(val newName: String) : AuthEvent()
    data class OnPasswordChanged(val newPassword: String) : AuthEvent()
    data object OnLoginClicked : AuthEvent()
    data object OnRegisterClicked : AuthEvent()
}
