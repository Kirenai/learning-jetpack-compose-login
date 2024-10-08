package me.kire.re.learninglogin.presentation.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.kire.re.learninglogin.R
import me.kire.re.learninglogin.presentation.auth.AuthenticationMode

@Composable
fun ToggleAuthenticationMode(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    toggleAuthenticationMode: () -> Unit
) {
    Surface(
        modifier = modifier
            .padding(16.dp),
        shadowElevation = 8.dp
    ) {
        TextButton(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(8.dp),
            onClick = toggleAuthenticationMode
        ) {
            Text(
                text = stringResource(
                    id = if (authenticationMode == AuthenticationMode.SIGN_IN) {
                        R.string.action_need_account
                    } else {
                        R.string.action_already_have_account
                    }
                )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ToggleAuthenticationModePreview() {
    ToggleAuthenticationMode(
        authenticationMode = AuthenticationMode.SIGN_IN,
        toggleAuthenticationMode = {}
    )
}
