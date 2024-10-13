package me.kire.re.learninglogin.presentation.auth

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import me.kire.re.learninglogin.R
import me.kire.re.learninglogin.presentation.auth.Tags.TAG_AUTHENTICATION_BUTTON
import me.kire.re.learninglogin.presentation.auth.Tags.TAG_AUTHENTICATION_TOGGLE
import me.kire.re.learninglogin.presentation.auth.Tags.TAG_CONTENT
import me.kire.re.learninglogin.presentation.auth.Tags.TAG_ERROR_ALERT
import me.kire.re.learninglogin.presentation.auth.Tags.TAG_INPUT_EMAIL
import me.kire.re.learninglogin.presentation.auth.Tags.TAG_INPUT_PASSWORD
import me.kire.re.learninglogin.presentation.auth.Tags.TAG_PROGRESS
import org.junit.Rule
import org.junit.Test

class AuthenticationTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun signInTitleDisplayedByDefault() {
        composeTestRule.setContent {
            Authentication()
        }

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(
                    R.string.label_sign_in_to_account
                )
        ).assertIsDisplayed()
    }

    @Test
    fun needAccountDisplayedByDefault() {
        composeTestRule.setContent {
            Authentication()
        }

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(
                    R.string.action_need_account
                )
        ).isDisplayed()
    }

    @Test
    fun singUpTitleDisplayedAfterToggled() {
        composeTestRule.setContent { Authentication() }

        composeTestRule
            .onNodeWithText(
                InstrumentationRegistry.getInstrumentation()
                    .targetContext.getString(
                        R.string.action_need_account
                    )
            ).performClick()

        composeTestRule
            .onNodeWithText(
                InstrumentationRegistry.getInstrumentation()
                    .targetContext.getString(
                        R.string.label_sign_up_for_account
                    )
            ).assertIsDisplayed()
    }

    @Test
    fun signUpButtonDisplayedAfterToggle() {
        composeTestRule.setContent { Authentication() }

        composeTestRule
            .onNodeWithTag(
                TAG_AUTHENTICATION_TOGGLE
            ).performClick()

        composeTestRule
            .onNodeWithTag(
                TAG_AUTHENTICATION_BUTTON
            ).assertTextEquals(
                InstrumentationRegistry.getInstrumentation()
                    .targetContext.getString(
                        R.string.action_sign_up
                    )
            )
    }

    @Test
    fun alreadyHaveAccountDisplayedAfterToggle() {
        composeTestRule.setContent { Authentication() }

        composeTestRule
            .onNodeWithTag(
                TAG_AUTHENTICATION_TOGGLE
            ).apply {
                performClick()
                assertTextEquals(
                    InstrumentationRegistry.getInstrumentation()
                        .targetContext.getString(
                            R.string.action_already_have_account
                        )
                )
            }
    }

    @Test
    fun authenticationButtonDisplayedByDefault() {
        composeTestRule.setContent { Authentication() }

        composeTestRule
            .onNodeWithTag(
                TAG_AUTHENTICATION_BUTTON
            ).assertIsNotEnabled()
    }

    @Test
    fun authenticationButtonEnabledWithValidContent() {
        composeTestRule.setContent { Authentication() }

        composeTestRule
            .onNodeWithTag(
                TAG_INPUT_EMAIL
            ).performTextInput("random@mail.com")

        composeTestRule
            .onNodeWithTag(
                TAG_INPUT_PASSWORD
            ).performTextInput("password")

        composeTestRule
            .onNodeWithTag(
                TAG_AUTHENTICATION_BUTTON
            ).assertIsEnabled()
    }

    @Test
    fun errorAlertNotDisplayedByDefault() {
        composeTestRule.setContent { Authentication() }

        composeTestRule
            .onNodeWithTag(
                TAG_ERROR_ALERT
            ).assertDoesNotExist()
    }

    @Test
    fun errorAlertDisplayedAfterError() {
        composeTestRule.setContent {
            AuthenticationContent(
                authenticationState = AuthenticationState(error = "Some Error"),
                handleEvent = {}
            )
        }

        composeTestRule
            .onNodeWithTag(
                TAG_ERROR_ALERT
            ).assertIsDisplayed()
    }

    @Test
    fun progressNotDisplayedByDefault() {
        composeTestRule.setContent { Authentication() }

        composeTestRule
            .onNodeWithTag(
                TAG_PROGRESS
            ).assertDoesNotExist()
    }

    @Test
    fun progressDisplayedWhileLoading() {
        composeTestRule.setContent {
            AuthenticationContent(
                authenticationState = AuthenticationState(isLoading = true),
                handleEvent = {}
            )
        }

        composeTestRule
            .onNodeWithTag(
                TAG_PROGRESS
            ).assertIsDisplayed()
    }

    @Test
    fun progressNotDisplayedAfterLoading() {
        composeTestRule.setContent {
            AuthenticationContent(
                authenticationState = AuthenticationState(
                    email = "random@mail.com",
                    password = "password",
                ),
                handleEvent = {}
            )
        }

        composeTestRule.onNodeWithTag(TAG_AUTHENTICATION_BUTTON)
            .performClick()

        composeTestRule.onNodeWithTag(
            TAG_PROGRESS
        ).assertDoesNotExist()
    }

    @Test
    fun contentDisplayedAfterLoading() {
        composeTestRule.setContent { Authentication() }

        composeTestRule.onNodeWithTag(
            TAG_INPUT_EMAIL
        ).performTextInput("random@mail.com")

        composeTestRule.onNodeWithTag(
            TAG_INPUT_PASSWORD
        ).performTextInput("password")

        composeTestRule.onNodeWithTag(
            TAG_AUTHENTICATION_BUTTON
        ).performClick()

        composeTestRule.waitUntil(timeoutMillis = 2500L) {
            composeTestRule.onAllNodesWithTag(TAG_CONTENT).fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithTag(
            TAG_CONTENT
        ).assertExists()
    }
}