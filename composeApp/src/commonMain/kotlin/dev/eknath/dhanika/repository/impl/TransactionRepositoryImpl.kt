package dev.eknath.dhanika.repository.impl

import dev.eknath.dhanika.repository.TransactionRepository
import dev.eknath.dhanika.room.dao.ExpenseDao
import dev.eknath.dhanika.room.dao.IncomeDao
import dev.eknath.dhanika.room.models.transactions.LocalExpense
import dev.eknath.dhanika.room.models.transactions.LocalIncome
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single
class TransactionRepositoryImpl(
    override val expenseSource: ExpenseDao,
    override val incomeSource: IncomeDao
) : TransactionRepository {

    // Expense operations
    override fun getAllExpenses(): Flow<List<LocalExpense>> {
        return expenseSource.getAllExpenses()
    }

    override suspend fun getExpenseById(expenseId: Long): LocalExpense? {
        return expenseSource.getExpenseById(expenseId)
    }

    override fun getExpensesByCategory(categoryId: Long): Flow<List<LocalExpense>> {
        return expenseSource.getExpensesByCategory(categoryId)
    }

    override fun getExpensesByAccount(accountId: Long): Flow<List<LocalExpense>> {
        return expenseSource.getExpensesByAccount(accountId)
    }

    override fun getExpensesByDateRange(startDate: Long, endDate: Long): Flow<List<LocalExpense>> {
        return expenseSource.getExpensesByDateRange(startDate, endDate)
    }

    override suspend fun saveExpense(expense: LocalExpense) {
        expenseSource.upsertExpense(expense)
    }

    override suspend fun deleteExpense(expense: LocalExpense) {
        expenseSource.deleteExpense(expense)
    }

    // Income operations
    override fun getAllIncomes(): Flow<List<LocalIncome>> {
        return incomeSource.getAllIncomes()
    }

    override suspend fun getIncomeById(incomeId: Long): LocalIncome? {
        return incomeSource.getIncomeById(incomeId)
    }

    override fun getIncomesByCategory(categoryId: Long): Flow<List<LocalIncome>> {
        return incomeSource.getIncomesByCategory(categoryId)
    }

    override fun getIncomesByAccount(accountId: Long): Flow<List<LocalIncome>> {
        return incomeSource.getIncomesByAccount(accountId)
    }

    override fun getIncomesByDateRange(startDate: Long, endDate: Long): Flow<List<LocalIncome>> {
        return incomeSource.getIncomesByDateRange(startDate, endDate)
    }

    override suspend fun saveIncome(income: LocalIncome) {
        incomeSource.upsertIncome(income)
    }

    override suspend fun deleteIncome(income: LocalIncome) {
        incomeSource.deleteIncome(income)
    }
}
