package me.kire.re.learninglogin.presentation.auth.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.kire.re.learninglogin.presentation.auth.AuthenticationMode
import me.kire.re.learninglogin.presentation.auth.PasswordRequirement
import me.kire.re.learninglogin.presentation.auth.Tags.TAG_CONTENT

@Composable
fun AuthenticationForm(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    email: String?,
    password: String?,
    completedPasswordRequirements: List<PasswordRequirement>,
    enableAuthentication: Boolean,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onAuthenticate: () -> Unit,
    onToggleMode: () -> Unit
) {
    Column(
        modifier = modifier.testTag(TAG_CONTENT),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        AuthenticationTitle(
            authenticationMode = authenticationMode
        )

        Spacer(modifier = Modifier.height(40.dp))
        val passwordFocusRequester = FocusRequester()
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EmailInput(
                    modifier = Modifier.fillMaxWidth(),
                    email = email ?: "",
                    onEmailChange = onEmailChanged
                ) {
                    passwordFocusRequester.requestFocus()
                }
                Spacer(modifier = Modifier.height(16.dp))
                PasswordInput(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(passwordFocusRequester),
                    password = password ?: "",
                    onPasswordChange = onPasswordChanged,
                    onDoneClicked = onAuthenticate
                )

                Spacer(modifier = Modifier.height(12.dp))
                AnimatedVisibility(
                    visible = authenticationMode == AuthenticationMode.SIGN_UP
                ) {
                    PasswordRequirements(
                        modifier = Modifier.fillMaxWidth(),
                        satisfiedRequirements = completedPasswordRequirements
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
                AuthenticationButton(
                    authenticationMode = authenticationMode,
                    enableAuthentication = enableAuthentication,
                    onAuthenticate = onAuthenticate
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        ToggleAuthenticationMode(
            modifier = Modifier.fillMaxWidth(),
            authenticationMode = authenticationMode,
            toggleAuthenticationMode = onToggleMode
        )
    }
}

// Preview
@Composable
@Preview(showBackground = true)
private fun AuthenticationFormPreview() {
    AuthenticationForm(
        authenticationMode = AuthenticationMode.SIGN_UP,
        email = "email@email.email",
        password = "password",
        completedPasswordRequirements = listOf(),
        enableAuthentication = true,
        onEmailChanged = {},
        onPasswordChanged = {},
        onAuthenticate = {},
        onToggleMode = {}
    )
}