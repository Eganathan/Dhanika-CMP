package dev.eknath.dhanika.ui.nav.routes

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import kotlinx.serialization.Serializable

@Serializable
internal object SettingsNavItem : AppNavRoute() {
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