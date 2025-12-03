package dev.eknath.dhanika.ui.nav

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import dev.eknath.dhanika.ui.nav.routes.AppNavRoute
import dev.eknath.dhanika.ui.nav.routes.HomeRoute
import dev.eknath.dhanika.ui.nav.routes.SettingsNavItem
import dev.eknath.dhanika.ui.nav.routes.UserDetailRoute

@Composable
fun AppBottomNavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry.value

    Surface(
        modifier = modifier
            .padding(bottom = 20.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        shadowElevation = 8.dp,
        tonalElevation = 3.dp,
        shape = CircleShape
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // need to work on this later
            NavigationItem(
                isSelected = false,
                onClick = {
                    navController.navigate(HomeRoute)
                },
                label = HomeRoute.label,
            )

            NavigationItem(
                isSelected = false,
                onClick = {
                    navController.navigate(UserDetailRoute)
                },
                label = UserDetailRoute.label,
            )

            NavigationItem(
                isSelected = false,
                onClick = {
                    navController.navigate(SettingsNavItem)
                },
                label = SettingsNavItem.label,
            )
        }
    }
}

@Composable
private fun NavigationItem(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = label,
            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,

        )
    }
}
