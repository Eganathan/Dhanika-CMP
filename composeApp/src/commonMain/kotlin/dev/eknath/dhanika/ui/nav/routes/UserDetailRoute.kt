package dev.eknath.dhanika.ui.nav.routes

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dev.eknath.dhanika.platformspecific.getDhanikaDatabase
import dev.eknath.dhanika.repository.UserRepository
import dev.eknath.dhanika.repository.impl.UserRepositoryImpl
import dev.eknath.dhanika.ui.screens.user.UserScreen
import dev.eknath.dhanika.ui.screens.user.UserViewModel
import kotlinx.serialization.Serializable

//hack for quick fix
var platformAppContext: Any = ""

@Serializable
internal object UserDetailRoute : AppNavRoute() {
    @Composable
    override fun Content(navController: NavController) {
        val database = getDhanikaDatabase(context = platformAppContext)
        val userRepository = UserRepositoryImpl(database.userInfoDao())
        val userVM = viewModel<UserViewModel> { UserViewModel(userRepository) }
        UserScreen(userVM)
    }
}