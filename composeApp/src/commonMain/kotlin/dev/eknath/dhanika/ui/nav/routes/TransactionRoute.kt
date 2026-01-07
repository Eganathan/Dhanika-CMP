package dev.eknath.dhanika.ui.nav.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import dev.eknath.dhanika.ui.screens.transaction.TransactionScreen
import dev.eknath.dhanika.ui.viewmodel.TransactionViewModel
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
internal object TransactionRoute : AppNavRoute() {

    override val label = "Transactions"

    @Composable
    override fun Content(navController: NavController) {
        val viewModel = koinViewModel<TransactionViewModel>()
        TransactionScreen(viewModel)
    }
}
