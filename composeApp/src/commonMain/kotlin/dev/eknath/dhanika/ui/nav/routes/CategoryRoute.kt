package dev.eknath.dhanika.ui.nav.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import dev.eknath.dhanika.ui.screens.category.CategoryScreen
import dev.eknath.dhanika.ui.viewmodel.CategoryViewModel
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
internal object CategoryRoute : AppNavRoute() {

    override val label = "Categories"

    @Composable
    override fun Content(navController: NavController) {
        val viewModel = koinViewModel<CategoryViewModel>()
        CategoryScreen(viewModel)
    }
}
