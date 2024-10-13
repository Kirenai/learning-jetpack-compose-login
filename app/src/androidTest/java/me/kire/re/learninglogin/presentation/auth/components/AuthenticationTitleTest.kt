package me.kire.re.learninglogin.presentation.auth.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import me.kire.re.learninglogin.R
import me.kire.re.learninglogin.presentation.auth.AuthenticationMode
import org.junit.Rule
import org.junit.Test

class AuthenticationTitleTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun signInTitleDisplayed() {
        composeTestRule.setContent {
            AuthenticationTitle(
                authenticationMode = AuthenticationMode.SIGN_IN
            )
        }

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(
                    R.string.label_sign_in_to_account
                )
        ).assertIsDisplayed()
    }

    @Test
    fun signUpTitleDisplayed() {
        composeTestRule.setContent {
            AuthenticationTitle(
                authenticationMode = AuthenticationMode.SIGN_UP
            )
        }

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(
                    R.string.label_sign_up_for_account
                )
        ).assertIsDisplayed()
    }
}