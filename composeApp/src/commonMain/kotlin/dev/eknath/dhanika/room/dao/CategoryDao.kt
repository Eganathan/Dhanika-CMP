package dev.eknath.dhanika.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import dev.eknath.dhanika.room.models.LocalCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM CATEGORY")
    fun getAllCategories(): Flow<List<LocalCategory>>

    @Query("SELECT * FROM CATEGORY WHERE categoryId = :categoryId")
    suspend fun getCategoryById(categoryId: Long): LocalCategory?

    @Query("SELECT * FROM CATEGORY WHERE accountType = :accountType")
    fun getCategoriesByType(accountType: Int): Flow<List<LocalCategory>>

    @Upsert
    suspend fun upsertCategory(category: LocalCategory)

    @Delete
    suspend fun deleteCategory(category: LocalCategory)
}
