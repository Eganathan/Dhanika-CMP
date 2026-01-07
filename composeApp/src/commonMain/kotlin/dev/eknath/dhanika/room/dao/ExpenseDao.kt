package dev.eknath.dhanika.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import dev.eknath.dhanika.room.models.transactions.LocalExpense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM EXPENSE ORDER BY date DESC")
    fun getAllExpenses(): Flow<List<LocalExpense>>

    @Query("SELECT * FROM EXPENSE WHERE id = :expenseId")
    suspend fun getExpenseById(expenseId: Long): LocalExpense?

    @Query("SELECT * FROM EXPENSE WHERE associatedCategoryId = :categoryId ORDER BY date DESC")
    fun getExpensesByCategory(categoryId: Long): Flow<List<LocalExpense>>

    @Query("SELECT * FROM EXPENSE WHERE associatedAccountId = :accountId ORDER BY date DESC")
    fun getExpensesByAccount(accountId: Long): Flow<List<LocalExpense>>

    @Query("SELECT * FROM EXPENSE WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    fun getExpensesByDateRange(startDate: Long, endDate: Long): Flow<List<LocalExpense>>

    @Upsert
    suspend fun upsertExpense(expense: LocalExpense)

    @Delete
    suspend fun deleteExpense(expense: LocalExpense)
}
