package dev.eknath.dhanika

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.eknath.dhanika.ui.CounterViewModel
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

@Serializable
object ViewModeTestingNavItem : AppNavRoute() {
    @Composable
    override fun Content(navController: NavController) {
        val viewModel = viewModel<CounterViewModel>()
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Counter: ${viewModel.count.value}")

            Button(onClick = { viewModel.count.value = viewModel.count.value.plus(1) }) {
                Text("Increment")
            }

            Button(onClick = { viewModel.count.value = viewModel.count.value.minus(1) }) {
                Text("Decrement")
            }
            Button(onClick = { viewModel.count.value = 0 }) {
                Text("Reset")
            }
        }

    }
}

private val AllScreens = listOf(HomeScreen, SettingsScreen, ViewModeTestingNavItem)

@Composable
fun AppNav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ViewModeTestingNavItem) {
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