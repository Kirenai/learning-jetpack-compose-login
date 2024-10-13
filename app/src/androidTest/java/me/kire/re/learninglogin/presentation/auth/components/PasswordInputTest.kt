package me.kire.re.learninglogin.presentation.auth.components

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import me.kire.re.learninglogin.presentation.auth.Tags.TAG_INPUT_PASSWORD
import me.kire.re.learninglogin.presentation.auth.Tags.TAG_PASSWORD_HIDDEN
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify

class PasswordInputTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun passwordDisplayed() {
        val password = "password"
        composeTestRule.setContent {
            PasswordInput(
                password = password,
                onPasswordChange = {},
                onDoneClicked = {}
            )
        }

        composeTestRule
            .onNodeWithTag(TAG_INPUT_PASSWORD)
            .assert(hasText("••••••••"))
    }

    @Test
    fun passwordChangedTriggered() {
        val password = "password"
        val onPasswordChanged: (String) -> Unit = mock()

        composeTestRule.setContent {
            PasswordInput(
                password = password,
                onPasswordChange = onPasswordChanged,
                onDoneClicked = {}
            )
        }

        val appendedText = "1234"
        composeTestRule
            .onNodeWithTag(TAG_INPUT_PASSWORD)
            .performTextInput(appendedText)

        verify(onPasswordChanged).invoke(anyString())
    }

    @Test
    fun passwordToggledReflectsState() {
        composeTestRule.setContent {
            PasswordInput(
                password = "password",
                onPasswordChange = {},
                onDoneClicked = {}
            )
        }

        composeTestRule
            .onNodeWithTag(
                TAG_PASSWORD_HIDDEN + "true"
            ).performClick()

        composeTestRule
            .onNodeWithTag(
                TAG_PASSWORD_HIDDEN + "false"
            ).assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(TAG_INPUT_PASSWORD)
            .assert(hasText("password"))
    }
}