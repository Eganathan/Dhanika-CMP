package dev.eknath.dhanika.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.eknath.dhanika.repository.AccountRepository
import dev.eknath.dhanika.room.models.accounts.LocalAccount
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class AccountViewModel(private val accountRepository: AccountRepository) : ViewModel() {

    val accounts: StateFlow<List<LocalAccount>> = accountRepository.getAllAccounts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val isLoading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)

    fun saveAccount(account: LocalAccount, onSuccess: () -> Unit = {}, onError: (String) -> Unit = {}) {
        viewModelScope.launch {
            isLoading.value = true
            error.value = null
            try {
                accountRepository.saveAccount(account)
                onSuccess()
            } catch (e: Exception) {
                error.value = e.message ?: "Failed to save account"
                onError(error.value!!)
            } finally {
                isLoading.value = false
            }
        }
    }

    fun deleteAccount(account: LocalAccount, onSuccess: () -> Unit = {}, onError: (String) -> Unit = {}) {
        viewModelScope.launch {
            isLoading.value = true
            error.value = null
            try {
                accountRepository.deleteAccount(account)
                onSuccess()
            } catch (e: Exception) {
                error.value = e.message ?: "Failed to delete account"
                onError(error.value!!)
            } finally {
                isLoading.value = false
            }
        }
    }

    fun updateBalance(accountId: Long, newBalance: Double) {
        viewModelScope.launch {
            try {
                accountRepository.updateBalance(accountId, newBalance)
            } catch (e: Exception) {
                error.value = e.message ?: "Failed to update balance"
            }
        }
    }
}
