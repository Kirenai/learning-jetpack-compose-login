package me.kire.re.learninglogin.presentation.auth.components

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import me.kire.re.learninglogin.presentation.auth.Tags.TAG_INPUT_EMAIL
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class EmailInputTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun emailDisplayed() {
        val email = "random@random.random"
        composeTestRule.setContent {
            EmailInput(
                email = email,
                onEmailChange = {},
                onNextClicked = {}
            )
        }

        composeTestRule
            .onNodeWithTag(TAG_INPUT_EMAIL)
            .assert(hasText(email))
    }

    @Test
    fun emailChangedTriggered() {
        val email = "random@random.random"
        val onEmailChanged: (String) -> Unit = mock()

        composeTestRule.setContent {
            EmailInput(
                email = email,
                onEmailChange = onEmailChanged,
                onNextClicked = {}
            )
        }

        val appendedText = ".jetpack"
        composeTestRule
            .onNodeWithTag(TAG_INPUT_EMAIL)
            .performTextInput(appendedText)

        verify(onEmailChanged, times(1)).invoke(anyString())
    }
}