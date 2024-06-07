package org.example.project.feature.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.example.project.feature.auth.presentation.AuthAction
import org.example.project.feature.auth.presentation.AuthEvent
import org.example.project.feature.auth.presentation.AuthState
import org.example.project.feature.auth.presentation.AuthViewModel
import org.example.project.feature.navigation.authRoute
import org.example.project.feature.navigation.mainRoute
import org.example.project.utils.rememberClick
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = koinViewModel(),
    navController: NavController
) {
    val state by viewModel.states.collectAsStateWithLifecycle()
    val action by viewModel.actions.collectAsStateWithLifecycle(initialValue = null)
    val consumer = rememberClick<AuthEvent> { viewModel.obtainEvent(it) }

    LaunchedEffect(Unit) {
        viewModel.obtainEvent(AuthEvent.OnInit)
    }

    if (!state.isLoading) {
        Column {
            Text(
                text = "Авторизация",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(16.dp)
            )
            AuthContent(state, consumer)
        }
    }

    AuthAction(action, navController)
}

@Composable
private fun AuthAction(action: AuthAction?, navController: NavController) {
    LaunchedEffect(action) {
        when (action) {
            AuthAction.AuthSuccess -> navController.navigate(mainRoute) {
                popUpTo(authRoute) { inclusive = true }
            }

            null -> Unit
        }
    }
}

@Composable
private fun AuthContent(state: AuthState, eventConsumer: (AuthEvent) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .width(300.dp)
        ) {
            TextField(
                value = state.name,
                singleLine = true,
                onValueChange = { eventConsumer(AuthEvent.OnNameChanged(it)) },
                placeholder = { Text(text = "Введите имя") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.large),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = state.password,
                singleLine = true,
                onValueChange = { eventConsumer(AuthEvent.OnPasswordChanged(it)) },
                placeholder = { Text(text = "Введите пароль") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.large),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            )
            if (state.isError) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Ошибка авторизации",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 48.dp)
        ) {
            Button(
                onClick = { eventConsumer(AuthEvent.OnLoginClicked) },
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                )
            ) {
                Text(text = "Войти")
            }
            Spacer(modifier = Modifier.width(32.dp))
            Button(
                onClick = { eventConsumer(AuthEvent.OnRegisterClicked) },
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                )
            ) {
                Text(text = "Зарегистрироваться")
            }
        }
    }
}
