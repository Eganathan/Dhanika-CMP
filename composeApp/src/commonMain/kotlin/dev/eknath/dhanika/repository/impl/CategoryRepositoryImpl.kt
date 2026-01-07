package dev.eknath.dhanika.repository.impl

import dev.eknath.dhanika.repository.CategoryRepository
import dev.eknath.dhanika.room.dao.CategoryDao
import dev.eknath.dhanika.room.models.LocalCategory
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single
class CategoryRepositoryImpl(override val localSource: CategoryDao) : CategoryRepository {

    override fun getAllCategories(): Flow<List<LocalCategory>> {
        return localSource.getAllCategories()
    }

    override suspend fun getCategoryById(categoryId: Long): LocalCategory? {
        return localSource.getCategoryById(categoryId)
    }

    override fun getCategoriesByType(accountType: Int): Flow<List<LocalCategory>> {
        return localSource.getCategoriesByType(accountType)
    }

    override suspend fun saveCategory(category: LocalCategory) {
        localSource.upsertCategory(category)
    }

    override suspend fun deleteCategory(category: LocalCategory) {
        localSource.deleteCategory(category)
    }
}
