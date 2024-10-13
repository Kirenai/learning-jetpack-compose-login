package me.kire.re.learninglogin.presentation.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.viewmodel.compose.viewModel
import me.kire.re.learninglogin.presentation.auth.Tags.TAG_PROGRESS
import me.kire.re.learninglogin.presentation.auth.components.AuthenticationErrorDialog
import me.kire.re.learninglogin.presentation.auth.components.AuthenticationForm

@Composable
fun Authentication(
    modifier: Modifier = Modifier
) {
    val viewModel: AuthenticationViewModel = viewModel()

    AuthenticationContent(
        modifier = modifier.fillMaxWidth(),
        authenticationState = viewModel.uiState.collectAsState().value,
        handleEvent = viewModel::handleEvent
    )
}

@Composable
fun AuthenticationContent(
    modifier: Modifier = Modifier,
    authenticationState: AuthenticationState,
    handleEvent: (event: AuthenticationEvent) -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (authenticationState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.testTag(TAG_PROGRESS)
            )
        } else {
            AuthenticationForm(
                modifier = Modifier.fillMaxSize(),
                authenticationMode = authenticationState.authenticationMode,
                email = authenticationState.email,
                password = authenticationState.password,
                completedPasswordRequirements = authenticationState.passwordRequirements,
                enableAuthentication = authenticationState.isFormValid(),
                onEmailChanged = {
                    handleEvent(AuthenticationEvent.EmailChanged(it))
                },
                onPasswordChanged = {
                    handleEvent(AuthenticationEvent.PasswordChanged(it))
                },
                onAuthenticate = {
                    handleEvent(AuthenticationEvent.Authenticate)
                },
                onToggleMode = {
                    handleEvent(AuthenticationEvent.ToggleAuthenticationMode)
                }
            )

            authenticationState.error?.let {
                AuthenticationErrorDialog(
                    error = it,
                    dismissError = {
                        handleEvent(AuthenticationEvent.ErrorDismissed)
                    }
                )
            }
        }

    }
}