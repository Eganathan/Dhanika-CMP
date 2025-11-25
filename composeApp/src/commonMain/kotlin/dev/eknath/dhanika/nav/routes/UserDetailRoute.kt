package dev.eknath.dhanika.nav.routes

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dev.eknath.dhanika.platformspecific.getDhanikaDatabase
import dev.eknath.dhanika.ui.user.UserScreen
import dev.eknath.dhanika.ui.user.UserViewModel
import kotlinx.serialization.Serializable

//hack for quick fix
var platformAppContext: Any = ""

@Serializable
internal object UserDetailRoute : AppNavRoute() {
    @Composable
    override fun Content(navController: NavController) {
        val database = getDhanikaDatabase(context = platformAppContext)
        val userVM = viewModel<UserViewModel> { UserViewModel(database.userInfoDao()) }
        UserScreen(userVM)
    }
}