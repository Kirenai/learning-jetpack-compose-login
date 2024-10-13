package me.kire.re.learninglogin.presentation.auth.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import me.kire.re.learninglogin.R
import me.kire.re.learninglogin.presentation.auth.PasswordRequirement
import org.junit.Rule
import org.junit.Test

class PasswordRequirementsTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun passwordRequirementsDisplayedAsNotDisplayed() {
        val requirements = PasswordRequirement.entries.toList()
        val satisfiedRequirement = requirements[(0 until 3).random()]

        composeTestRule.setContent {
            PasswordRequirements(
                satisfiedRequirements = listOf(satisfiedRequirement)
            )
        }

        PasswordRequirement.entries.forEach {
            val requirement =
                InstrumentationRegistry.getInstrumentation()
                    .targetContext.getString(it.label)

            val result = if (it == satisfiedRequirement) {
                InstrumentationRegistry.getInstrumentation()
                    .targetContext.getString(
                        R.string.password_requirement_satisfied,
                        requirement
                    )
            } else {
                InstrumentationRegistry.getInstrumentation()
                    .targetContext.getString(
                        R.string.password_requirement_needed,
                        requirement
                    )
            }

            composeTestRule
                .onNodeWithText(result)
                .assertIsDisplayed()
        }
    }
}