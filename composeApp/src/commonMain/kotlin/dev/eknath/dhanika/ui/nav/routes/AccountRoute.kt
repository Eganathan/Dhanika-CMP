package dev.eknath.dhanika.ui.nav.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import dev.eknath.dhanika.ui.screens.account.AccountScreen
import dev.eknath.dhanika.ui.viewmodel.AccountViewModel
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
internal object AccountRoute : AppNavRoute() {

    override val label = "Accounts"

    @Composable
    override fun Content(navController: NavController) {
        val viewModel = koinViewModel<AccountViewModel>()
        AccountScreen(viewModel)
    }
}
