package dev.eknath.dhanika.repository

import dev.eknath.dhanika.room.dao.ExpenseDao
import dev.eknath.dhanika.room.dao.IncomeDao
import dev.eknath.dhanika.room.models.transactions.LocalExpense
import dev.eknath.dhanika.room.models.transactions.LocalIncome
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    val expenseSource: ExpenseDao
    val incomeSource: IncomeDao

    // Expense operations
    fun getAllExpenses(): Flow<List<LocalExpense>>
    suspend fun getExpenseById(expenseId: Long): LocalExpense?
    fun getExpensesByCategory(categoryId: Long): Flow<List<LocalExpense>>
    fun getExpensesByAccount(accountId: Long): Flow<List<LocalExpense>>
    fun getExpensesByDateRange(startDate: Long, endDate: Long): Flow<List<LocalExpense>>
    suspend fun saveExpense(expense: LocalExpense)
    suspend fun deleteExpense(expense: LocalExpense)

    // Income operations
    fun getAllIncomes(): Flow<List<LocalIncome>>
    suspend fun getIncomeById(incomeId: Long): LocalIncome?
    fun getIncomesByCategory(categoryId: Long): Flow<List<LocalIncome>>
    fun getIncomesByAccount(accountId: Long): Flow<List<LocalIncome>>
    fun getIncomesByDateRange(startDate: Long, endDate: Long): Flow<List<LocalIncome>>
    suspend fun saveIncome(income: LocalIncome)
    suspend fun deleteIncome(income: LocalIncome)
}
