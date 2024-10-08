package me.kire.re.learninglogin.presentation.auth.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import me.kire.re.learninglogin.presentation.auth.PasswordRequirement

@Composable
fun PasswordRequirements(
    modifier: Modifier = Modifier,
    satisfiedRequirements: List<PasswordRequirement>
) {
    Column(
        modifier = modifier
    ) {
        PasswordRequirement.entries.forEach { requirement ->
            Requirement(
                message = stringResource(id = requirement.label),
                satisfied = satisfiedRequirements.contains(requirement)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PasswordRequirementsPreview() {
    val list: List<PasswordRequirement> =
        listOf(PasswordRequirement.NUMBER, PasswordRequirement.CAPITAL_LETTER)
    PasswordRequirements(
        satisfiedRequirements = list
    )
}