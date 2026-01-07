package dev.eknath.dhanika.repository

import dev.eknath.dhanika.room.dao.CategoryDao
import dev.eknath.dhanika.room.models.LocalCategory
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    val localSource: CategoryDao
    fun getAllCategories(): Flow<List<LocalCategory>>
    suspend fun getCategoryById(categoryId: Long): LocalCategory?
    fun getCategoriesByType(accountType: Int): Flow<List<LocalCategory>>
    suspend fun saveCategory(category: LocalCategory)
    suspend fun deleteCategory(category: LocalCategory)
}
