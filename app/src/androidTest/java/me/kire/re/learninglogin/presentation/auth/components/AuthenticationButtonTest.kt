package me.kire.re.learninglogin.presentation.auth.components

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import me.kire.re.learninglogin.R
import me.kire.re.learninglogin.presentation.auth.AuthenticationMode
import me.kire.re.learninglogin.presentation.auth.Tags.TAG_AUTHENTICATION_BUTTON
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify

class AuthenticationButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun signInActionDisplayed() {
        composeTestRule.setContent {
            AuthenticationButton(
                authenticationMode = AuthenticationMode.SIGN_IN,
                enableAuthentication = true,
                onAuthenticate = { }
            )
        }

        composeTestRule.onNodeWithTag(
            TAG_AUTHENTICATION_BUTTON
        ).assertTextEquals(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(
                    R.string.action_sign_in
                )
        )
    }

    @Test
    fun signUpActionDisplayed() {
        composeTestRule.setContent {
            AuthenticationButton(
                authenticationMode = AuthenticationMode.SIGN_UP,
                enableAuthentication = true,
                onAuthenticate = { }
            )
        }

        composeTestRule.onNodeWithTag(
            TAG_AUTHENTICATION_BUTTON
        ).assertTextEquals(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(
                    R.string.action_sign_up
                )
        )
    }

    @Test
    fun authenticationTriggered() {
        val onAuthenticate: () -> Unit = mock()
        composeTestRule.setContent {
            AuthenticationButton(
                authenticationMode = AuthenticationMode.SIGN_UP,
                enableAuthentication = true,
                onAuthenticate = onAuthenticate
            )
        }

        composeTestRule
            .onNodeWithTag(TAG_AUTHENTICATION_BUTTON)
            .performClick()

        verify(onAuthenticate).invoke()
    }

    @Test
    fun authenticationButtonDisabled() {
        composeTestRule.setContent {
            AuthenticationButton(
                authenticationMode = AuthenticationMode.SIGN_UP,
                enableAuthentication = false,
                onAuthenticate = {}
            )
        }

        composeTestRule
            .onNodeWithTag(TAG_AUTHENTICATION_BUTTON)
            .assertIsNotEnabled()
    }

    @Test
    fun authenticationButtonEnabled() {
        composeTestRule.setContent {
            AuthenticationButton(
                authenticationMode = AuthenticationMode.SIGN_UP,
                enableAuthentication = true,
                onAuthenticate = {}
            )
        }

        composeTestRule
            .onNodeWithTag(TAG_AUTHENTICATION_BUTTON)
            .assertIsEnabled()
    }
}