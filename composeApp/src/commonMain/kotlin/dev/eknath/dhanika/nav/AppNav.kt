package dev.eknath.dhanika.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.eknath.dhanika.nav.routes.AllScreens
import dev.eknath.dhanika.nav.routes.AppNavRoute
import dev.eknath.dhanika.nav.routes.HomeRoute

@Composable
fun AppNav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HomeRoute) {
        addRoutes(AllScreens, navController)
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