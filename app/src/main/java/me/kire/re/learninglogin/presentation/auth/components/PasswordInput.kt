package me.kire.re.learninglogin.presentation.auth.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import me.kire.re.learninglogin.R
import me.kire.re.learninglogin.presentation.auth.Tags.TAG_INPUT_PASSWORD
import me.kire.re.learninglogin.presentation.auth.Tags.TAG_PASSWORD_HIDDEN

@Composable
fun PasswordInput(
    modifier: Modifier = Modifier,
    password: String,
    onPasswordChange: (String) -> Unit,
    onDoneClicked: () -> Unit
) {

    var isPasswordHidden by remember {
        mutableStateOf(true)
    }

    TextField(
        modifier = modifier
            .testTag(TAG_INPUT_PASSWORD),
        value = password,
        onValueChange = onPasswordChange,
        label = {
            Text(
                text = stringResource(
                    id = R.string.label_password
                )
            )
        },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null
            )
        },
        trailingIcon = {
            Icon(
                modifier = Modifier
                    .testTag(TAG_PASSWORD_HIDDEN + isPasswordHidden)
                    .clickable(
                        onClickLabel = if (isPasswordHidden) {
                            stringResource(
                                id = R.string.cd_show_password
                            )
                        } else stringResource(
                            id = R.string.cd_hide_password
                        )
                    ) {
                        isPasswordHidden = !isPasswordHidden
                    },
                imageVector = if (isPasswordHidden) {
                    Icons.Default.Visibility
                } else Icons.Default.VisibilityOff,
                contentDescription = null
            )
        },
        visualTransformation = if (isPasswordHidden) {
            PasswordVisualTransformation()
        } else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onDoneClicked()
            }
        )
    )
}

// Preview
@Composable
@Preview(showBackground = true)
private fun PasswordInputPreview() {
    PasswordInput(password = "********", onPasswordChange = {}, onDoneClicked = {})
}