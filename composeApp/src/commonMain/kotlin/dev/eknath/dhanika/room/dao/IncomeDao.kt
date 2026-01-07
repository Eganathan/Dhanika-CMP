package dev.eknath.dhanika.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import dev.eknath.dhanika.room.models.transactions.LocalIncome
import kotlinx.coroutines.flow.Flow

@Dao
interface IncomeDao {

    @Query("SELECT * FROM INCOME ORDER BY date DESC")
    fun getAllIncomes(): Flow<List<LocalIncome>>

    @Query("SELECT * FROM INCOME WHERE id = :incomeId")
    suspend fun getIncomeById(incomeId: Long): LocalIncome?

    @Query("SELECT * FROM INCOME WHERE associatedCategoryId = :categoryId ORDER BY date DESC")
    fun getIncomesByCategory(categoryId: Long): Flow<List<LocalIncome>>

    @Query("SELECT * FROM INCOME WHERE associatedAccountId = :accountId ORDER BY date DESC")
    fun getIncomesByAccount(accountId: Long): Flow<List<LocalIncome>>

    @Query("SELECT * FROM INCOME WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    fun getIncomesByDateRange(startDate: Long, endDate: Long): Flow<List<LocalIncome>>

    @Upsert
    suspend fun upsertIncome(income: LocalIncome)

    @Delete
    suspend fun deleteIncome(income: LocalIncome)
}
