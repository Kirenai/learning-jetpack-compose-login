package me.kire.re.learninglogin.presentation.auth.components

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import me.kire.re.learninglogin.R
import me.kire.re.learninglogin.presentation.auth.AuthenticationMode
import me.kire.re.learninglogin.presentation.auth.Tags.TAG_AUTHENTICATION_TOGGLE
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class ToggleAuthenticationModeTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun needAccountActionDisplayed() {
        composeTestRule.setContent {
            ToggleAuthenticationMode(
                authenticationMode = AuthenticationMode.SIGN_IN,
                toggleAuthenticationMode = { }
            )
        }

        composeTestRule
            .onNodeWithTag(
                TAG_AUTHENTICATION_TOGGLE
            ).assertTextEquals(
                InstrumentationRegistry.getInstrumentation()
                    .targetContext.getString(
                        R.string.action_need_account
                    )
            )
    }

    @Test
    fun alreadyHaveAccountActionDisplayed() {
        composeTestRule.setContent {
            ToggleAuthenticationMode(
                authenticationMode = AuthenticationMode.SIGN_UP,
                toggleAuthenticationMode = { }
            )
        }

        composeTestRule
            .onNodeWithTag(
                TAG_AUTHENTICATION_TOGGLE
            ).assertTextEquals(
                InstrumentationRegistry.getInstrumentation()
                    .targetContext.getString(
                        R.string.action_already_have_account
                    )
            )
    }

    @Test
    fun toggleAuthenticationTriggered() {
        val toggleAuthenticationMode: () -> Unit = mock()

        composeTestRule.setContent {
            ToggleAuthenticationMode(
                authenticationMode = AuthenticationMode.SIGN_UP,
                toggleAuthenticationMode = toggleAuthenticationMode
            )
        }

        composeTestRule
            .onNodeWithTag(
                TAG_AUTHENTICATION_TOGGLE
            ).performClick()

        verify(toggleAuthenticationMode, times(1)).invoke()
    }
}