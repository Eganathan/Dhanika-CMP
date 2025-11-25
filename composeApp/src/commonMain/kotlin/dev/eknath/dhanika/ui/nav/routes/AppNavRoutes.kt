package dev.eknath.dhanika.ui.nav.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import kotlinx.serialization.Serializable

@Serializable
internal sealed class AppNavRoute {
    @Composable
    abstract fun Content(navController: NavController)
}

internal val AllScreens = listOf<AppNavRoute>(
    HomeRoute,
    SettingsNavItem,
    UserDetailRoute,
)
