package io.redandroid.gameofthrones.screens.house.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.redandroid.data.model.Person
import io.redandroid.gameofthrones.R
import io.redandroid.gameofthrones.theme.GoTTheme

/**
 * This Composable lists all sworn members in Game Of Thrones.
 */
@Composable
fun SwornMembers(
    modifier: Modifier = Modifier,
    swornMembers: List<Person>
) {

    HeadlineCard(
        modifier = modifier,
        headline = stringResource(id = R.string.sworn_members),
        icon = painterResource(id = R.drawable.sworn_members)
    ) {

        Column {

            for (swornMember in swornMembers) {

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = swornMember.name,
                    style = GoTTheme.typography.medium.regular,
                    color = GoTTheme.colors.onSecondary
                )

                if (swornMember.titles.isNotEmpty()) {

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = swornMember.titles.joinToString(",\n"),
                        style = GoTTheme.typography.small.regular,
                        color = GoTTheme.colors.onSecondary
                    )

                }

            }
        }
    }
}


@Preview
@Composable
fun SwornMembersPreview() {
    SwornMembers(
        swornMembers = listOf(
            Person(
                name = "Delonne Allyrion",
                titles = listOf("Lady of Godsgrace")
            ),
            Person(
                name = "Delonne Allyrion",
                titles = listOf("Lady of Godsgrace", "Lady of Godsgrace")
            )
        )
    )
}