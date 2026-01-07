package dev.eknath.dhanika.repository

import dev.eknath.dhanika.room.dao.AccountDao
import dev.eknath.dhanika.room.models.accounts.LocalAccount
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    val localSource: AccountDao
    fun getAllAccounts(): Flow<List<LocalAccount>>
    suspend fun getAccountById(accountId: Long): LocalAccount?
    fun getAccountsByType(accountType: Int): Flow<List<LocalAccount>>
    suspend fun saveAccount(account: LocalAccount)
    suspend fun deleteAccount(account: LocalAccount)
    suspend fun updateBalance(accountId: Long, newBalance: Double)
}
