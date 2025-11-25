package dev.eknath.dhanika.ui.user

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.eknath.dhanika.room.dao.UserInfoDao
import dev.eknath.dhanika.room.models.LocalUserInfo
import kotlinx.coroutines.launch

class UserViewModel(val userDao: UserInfoDao) : ViewModel() {

    private val _userInfo = mutableStateOf<LocalUserInfo?>(null)
    val userInfo: State<LocalUserInfo?> = _userInfo

    init {
        getUserInfo()
    }

    fun updateUserName(name: String, onSuccess: (LocalUserInfo) -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            try {
                userDao.upsetUserInfo(
                    item = userInfo.value?.copy(name = name) ?: LocalUserInfo(name = name)
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
                _userInfo.value = userDao.getUserInfo()
                onSuccess(userInfo.value!!)
            } catch (e: Exception) {
                _userInfo.value = null
            }
        }
    }

}