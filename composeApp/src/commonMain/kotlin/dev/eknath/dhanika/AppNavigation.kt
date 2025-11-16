package dev.eknath.dhanika

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Serializable
sealed class AppNavRoute {
    @Composable
    abstract fun Content(navController: NavController)
}

@Serializable
object HomeScreen : AppNavRoute() {
    @Composable
    override fun Content(navController: NavController) {
        Column {
            Text("Home")
            Button(onClick = { navController.navigate(SettingsScreen) }) {
                Text("Go to Settings")
            }
        }
    }
}

@Serializable
object SettingsScreen : AppNavRoute() {
    @Composable
    override fun Content(navController: NavController) {
        Column {
            Text("Setting Screen")
            Button(onClick = { navController.navigateUp() }) {
                Text("Navigate Up")
            }
        }
    }
}

private val AllScreens = listOf(HomeScreen, SettingsScreen)

@Composable
fun AppNav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HomeScreen) {
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