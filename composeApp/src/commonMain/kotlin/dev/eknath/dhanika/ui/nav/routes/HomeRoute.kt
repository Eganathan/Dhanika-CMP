package dev.eknath.dhanika.ui.nav.routes

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import kotlinx.serialization.Serializable

@Serializable
internal object HomeRoute : AppNavRoute() {

    override val label = "Home"

    @Composable
    override fun Content(navController: NavController) {
        Column {
            Text("Home")
            Button(onClick = { navController.navigate(SettingsNavItem) }) {
                Text("Go to Settings")
            }
        }
    }
}