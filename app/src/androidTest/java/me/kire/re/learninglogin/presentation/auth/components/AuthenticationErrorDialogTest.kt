package me.kire.re.learninglogin.presentation.auth.components

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import me.kire.re.learninglogin.R
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify

class AuthenticationErrorDialogTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun errorDisplayed() {
        val error = "Error message"
        composeTestRule.setContent {
            AuthenticationErrorDialog(
                error = error,
                dismissError = {}
            )
        }

        composeTestRule
            .onNodeWithText(error)
            .assertTextEquals(error)
    }

    @Test
    fun dismissTriggeredFromAction() {
        val dismissError: () -> Unit = mock()

        composeTestRule.setContent {
            AuthenticationErrorDialog(
                error = "Error message",
                dismissError = dismissError
            )
        }

        composeTestRule
            .onNodeWithText(
                InstrumentationRegistry.getInstrumentation()
                    .targetContext.getString(
                        R.string.error_action
                    )
            ).performClick()

        verify(dismissError).invoke()
    }
}