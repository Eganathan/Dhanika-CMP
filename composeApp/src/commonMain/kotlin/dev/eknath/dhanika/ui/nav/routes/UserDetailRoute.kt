package dev.eknath.dhanika.ui.nav.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import dev.eknath.dhanika.ui.screens.user.UserScreen
import dev.eknath.dhanika.ui.screens.user.UserViewModel
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel


@Serializable
internal object UserDetailRoute : AppNavRoute() {

    override val label = "UserDetails"


    @Composable
    override fun Content(navController: NavController) {

        val userVM = koinViewModel<UserViewModel>()
        UserScreen(userVM)
    }
}