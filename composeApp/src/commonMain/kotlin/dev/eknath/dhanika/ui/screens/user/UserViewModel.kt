package dev.eknath.dhanika.ui.screens.user

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.eknath.dhanika.repository.UserRepository
import dev.eknath.dhanika.room.models.LocalUserInfo
import kotlinx.coroutines.launch

class UserViewModel(val userRepository: UserRepository) : ViewModel() {

    private val _userInfo = mutableStateOf<LocalUserInfo?>(null)
    val userInfo: State<LocalUserInfo?> = _userInfo

    init {
        getUserInfo()
    }

    fun updateUserName(name: String, onSuccess: (LocalUserInfo) -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            try {
                userRepository.updateUserInfo(
                    user = userInfo.value?.copy(name = name) ?: LocalUserInfo(name = name)
                )
                getUserInfo(onSuccess)
            } catch (e: Exception) {
                onError()
            }
        }
    }

    fun getUserInfo(onSuccess: (LocalUserInfo) -> Unit = {}) {
        viewModelScope.launch {
            try {
                _userInfo.value = userRepository.getUserInfo()
                onSuccess(userInfo.value!!)
            } catch (e: Exception) {
                _userInfo.value = null
            }
        }
    }

}