package dev.eknath.dhanika.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.eknath.dhanika.repository.TransactionRepository
import dev.eknath.dhanika.room.models.transactions.LocalExpense
import dev.eknath.dhanika.room.models.transactions.LocalIncome
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class TransactionViewModel(private val transactionRepository: TransactionRepository) : ViewModel() {

    val expenses: StateFlow<List<LocalExpense>> = transactionRepository.getAllExpenses()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val incomes: StateFlow<List<LocalIncome>> = transactionRepository.getAllIncomes()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val isLoading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)

    // Expense operations
    fun saveExpense(expense: LocalExpense, onSuccess: () -> Unit = {}, onError: (String) -> Unit = {}) {
        viewModelScope.launch {
            isLoading.value = true
            error.value = null
            try {
                transactionRepository.saveExpense(expense)
                onSuccess()
            } catch (e: Exception) {
                error.value = e.message ?: "Failed to save expense"
                onError(error.value!!)
            } finally {
                isLoading.value = false
            }
        }
    }

    fun deleteExpense(expense: LocalExpense, onSuccess: () -> Unit = {}, onError: (String) -> Unit = {}) {
        viewModelScope.launch {
            isLoading.value = true
            error.value = null
            try {
                transactionRepository.deleteExpense(expense)
                onSuccess()
            } catch (e: Exception) {
                error.value = e.message ?: "Failed to delete expense"
                onError(error.value!!)
            } finally {
                isLoading.value = false
            }
        }
    }

    // Income operations
    fun saveIncome(income: LocalIncome, onSuccess: () -> Unit = {}, onError: (String) -> Unit = {}) {
        viewModelScope.launch {
            isLoading.value = true
            error.value = null
            try {
                transactionRepository.saveIncome(income)
                onSuccess()
            } catch (e: Exception) {
                error.value = e.message ?: "Failed to save income"
                onError(error.value!!)
            } finally {
                isLoading.value = false
            }
        }
    }

    fun deleteIncome(income: LocalIncome, onSuccess: () -> Unit = {}, onError: (String) -> Unit = {}) {
        viewModelScope.launch {
            isLoading.value = true
            error.value = null
            try {
                transactionRepository.deleteIncome(income)
                onSuccess()
            } catch (e: Exception) {
                error.value = e.message ?: "Failed to delete income"
                onError(error.value!!)
            } finally {
                isLoading.value = false
            }
        }
    }

    fun getExpensesByDateRange(startDate: Long, endDate: Long): StateFlow<List<LocalExpense>> {
        return transactionRepository.getExpensesByDateRange(startDate, endDate)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    }

    fun getIncomesByDateRange(startDate: Long, endDate: Long): StateFlow<List<LocalIncome>> {
        return transactionRepository.getIncomesByDateRange(startDate, endDate)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    }
}
