package dev.eknath.dhanika.ui.nav

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.eknath.dhanika.ui.nav.routes.AllScreens
import dev.eknath.dhanika.ui.nav.routes.AppNavRoute
import dev.eknath.dhanika.ui.nav.routes.UserDetailRoute

@Composable
fun AppNav() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { AppBottomNavigationBar(navController = navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = UserDetailRoute,
            modifier = androidx.compose.ui.Modifier.padding(paddingValues)
        ) { addRoutes(AllScreens, navController) }
    }
}

/**
 * This helper functions help me to simplify adding new screen process much simpler and easy in the future, especially when i have too may screens
 */
private fun NavGraphBuilder.addRoutes(routes: List<AppNavRoute>, navController: NavController) =
    routes.forEach { addRoute(route = it, navController = navController) }

private inline fun <reified T : AppNavRoute> NavGraphBuilder.addRoute(
    route: T,
    navController: NavController
) {
    composable(
        route = route::class,
        content = {
            route.Content(navController)
        }
    )
}