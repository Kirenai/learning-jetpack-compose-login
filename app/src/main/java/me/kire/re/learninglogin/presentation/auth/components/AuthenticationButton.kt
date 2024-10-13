package me.kire.re.learninglogin.presentation.auth.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import me.kire.re.learninglogin.R
import me.kire.re.learninglogin.presentation.auth.AuthenticationMode
import me.kire.re.learninglogin.presentation.auth.Tags.TAG_AUTHENTICATION_BUTTON

@Composable
fun AuthenticationButton(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    enableAuthentication: Boolean,
    onAuthenticate: () -> Unit
) {
    Button(
        modifier = modifier.testTag(TAG_AUTHENTICATION_BUTTON),
        onClick = onAuthenticate,
        enabled = enableAuthentication
    ) {
        Text(
            text = stringResource(
                id = if (authenticationMode == AuthenticationMode.SIGN_IN) {
                    R.string.action_sign_in
                } else {
                    R.string.action_sign_up
                }
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun AuthenticationButtonPreview() {
    AuthenticationButton(
        authenticationMode = AuthenticationMode.SIGN_IN,
        enableAuthentication = true,
        onAuthenticate = {})
}