package dev.eknath.dhanika.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.eknath.dhanika.repository.CategoryRepository
import dev.eknath.dhanika.room.models.LocalCategory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CategoryViewModel(private val categoryRepository: CategoryRepository) : ViewModel() {

    val categories: StateFlow<List<LocalCategory>> = categoryRepository.getAllCategories()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val isLoading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)

    fun saveCategory(category: LocalCategory, onSuccess: () -> Unit = {}, onError: (String) -> Unit = {}) {
        viewModelScope.launch {
            isLoading.value = true
            error.value = null
            try {
                categoryRepository.saveCategory(category)
                onSuccess()
            } catch (e: Exception) {
                error.value = e.message ?: "Failed to save category"
                onError(error.value!!)
            } finally {
                isLoading.value = false
            }
        }
    }

    fun deleteCategory(category: LocalCategory, onSuccess: () -> Unit = {}, onError: (String) -> Unit = {}) {
        viewModelScope.launch {
            isLoading.value = true
            error.value = null
            try {
                categoryRepository.deleteCategory(category)
                onSuccess()
            } catch (e: Exception) {
                error.value = e.message ?: "Failed to delete category"
                onError(error.value!!)
            } finally {
                isLoading.value = false
            }
        }
    }

    fun getCategoriesByType(accountType: Int): StateFlow<List<LocalCategory>> {
        return categoryRepository.getCategoriesByType(accountType)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    }
}
